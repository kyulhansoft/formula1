package managedbeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import jpapersistence.Logic;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.OKPopup;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.pagebean.PageBean;

import javax.faces.event.ActionEvent;
import jpapersistence.Team;

@CCGenClass (expressionBase="#{d.TeamDetailsUI}")

public class TeamDetailsUI extends PageBean implements Serializable {
    Team team;
    public Team getTeam() { return team; }

    // ------------------------------------------------------------------------
    // inner classes
    // ------------------------------------------------------------------------
    
    /* Listener to the user of the page bean. */
    public interface IListenerSaveCancelDel {
        public void reactOnSave(Team team);
        public void reactOnCancel();
        public void reactOnDelete();
    }

    public class ControlInfo {
        boolean i_hasError = false;
        String i_tooltip;
        public String getBgPaint() {
            if (i_hasError) return "error()";
            return null;
        }
        public String getBorder() {
            if (i_hasError) return "left:2;color:#c55a11";
            return null;
        }
        public String getTooltip() {
            if (i_hasError) return i_tooltip;
            return null;
        }
        public void indicateError(String tooltip) {
            i_hasError = true;
            i_tooltip = tooltip;
        }
    }

    public class CisMap extends HashMap<String, ControlInfo> {
        @Override
        public ControlInfo get(Object key) {
            ControlInfo result = super.get(key);
            if (result == null) {
                result = new ControlInfo();
                put((String)key, result);
            }
            return result;
        }
    }
    
    // ------------------------------------------------------------------------
    // members
    // ------------------------------------------------------------------------
    
    //private IListener m_listener;
    private IListenerSaveCancelDel m_listenerSCD;
    //private IListener m_deleteListener;
    private String m_btnDelEnabled = "false";
    private String m_nameBgPaint;
    private CisMap m_cis = new CisMap();
    
    // ------------------------------------------------------------------------
    // constructors & initialization
    // ------------------------------------------------------------------------

    public TeamDetailsUI() {
    }

    public String getPageName() { return "/TeamDetails.jsp"; }
    public String getRootExpressionUsedInPage() { return "#{d.TeamDetailsUI}"; }

    // ------------------------------------------------------------------------
    // public usage
    // ------------------------------------------------------------------------

    public CisMap getCis() { return m_cis; }

    /* Initialization of the bean. Add any parameter that is required within your scenario. */
    public void prepare(Team team, IListenerSaveCancelDel listener) {
        this.team = team;
        m_listenerSCD = listener;
        if (team.getId() != null) {
            setBtnDelEnabled("true");
        }
    }

    public void onCancelAction(javax.faces.event.ActionEvent event) {
        if (m_listenerSCD != null) {
            m_listenerSCD.reactOnCancel();
        }
    }

    public void onSaveAction(javax.faces.event.ActionEvent event) {
        for (ControlInfo ci : m_cis.values()) {
            ci.i_hasError = false;
        }
        if (inputNameHasError()) {
            m_cis.get("name").indicateError("Please input Name");
            Statusbar.outputAlert("Wrong");
            return;
        }
        if (inputEstablishedHasError()) {
            m_cis.get("established").indicateError("Please input date of established");
            Statusbar.outputAlert("Wrong");
            return;
        }
        if (m_listenerSCD != null) {
            m_listenerSCD.reactOnSave(this.team);
        }
    }

    private boolean inputNameHasError() {
        if (this.team.getName() == null || this.team.getName().equals("")) {
            return true;
        }
        return false;
    }

    private boolean inputEstablishedHasError() {
        if (this.team.getEstablished() == null || this.team.getEstablished().toString().equals("")) {
            return true;
        }
        return false;
    }

    public String getBtnDelEnabled() { return m_btnDelEnabled; }
    public void setBtnDelEnabled(String value) { m_btnDelEnabled = value; }

    public void onDeleteAction(javax.faces.event.ActionEvent event) {
        Team t = this.team;
        YESNOPopup.createInstance("Be careful!", "Please confirm deleting by pressing YES.",
            new YESNOPopup.IYesNoListener() {
                @Override
                public void reactOnYes() {
                    Logic logic = new Logic();
                    logic.deleteTeam(t);
                    m_listenerSCD.reactOnDelete();
                }
                @Override
                public void reactOnNo() {}
            });
    }



    // ------------------------------------------------------------------------
    // private usage
    // ------------------------------------------------------------------------
}
