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

    public String getNameBgPaint() { return m_nameBgPaint; }
    public void setNameBgPaint(String value) { this.m_nameBgPaint = value; }

    // String m_name;
    // public String getName() { return m_name; }
    // public void setName(String value) { this.m_name = value; }
    Team team;
    public Team getTeam() { return team; }
    //public void setTeam(Team team) { this.team = team; }

    // ------------------------------------------------------------------------
    // inner classes
    // ------------------------------------------------------------------------
    
    /* Listener to the user of the page bean. */
    public interface IListenerSaveCancelDel {
        public void reactOnSave(Team team);
        public void reactOnCancel();
        public void reactOnDelete();
    }
    
    // ------------------------------------------------------------------------
    // members
    // ------------------------------------------------------------------------
    
    //private IListener m_listener;
    private IListenerSaveCancelDel m_listenerSCD;
    //private IListener m_deleteListener;
    private String m_btnDelEnabled = "false";
    private String m_nameBgPaint;
    private Map<String, String> m_bgPaints = new HashMap<String, String>();
    
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
        m_bgPaints.clear();
        if (inputNameHasError()) {
            m_bgPaints.put("nameBgPaint", "error()");
            Statusbar.outputAlert("Wrong");
            return;
        }
        if (inputEstablishedHasError()) {
            m_bgPaints.put("establishedBgPaint", "error()");
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

    public Map<String, String> getBgPaints() { return m_bgPaints; }

    // ------------------------------------------------------------------------
    // private usage
    // ------------------------------------------------------------------------
}
