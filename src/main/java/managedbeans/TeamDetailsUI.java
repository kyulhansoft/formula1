package managedbeans;

import java.io.Serializable;

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
    public interface IListener {
        public void reactOnOK(Team team);
        public void reactOnCancel();
    }
    
    // ------------------------------------------------------------------------
    // members
    // ------------------------------------------------------------------------
    
    private IListener m_listener;
    private IListener m_deleteListener;
    String m_btnDelEnabled = "false";
    
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
    public void prepare(Team team, IListener listener, IListener deleteListener) {
        this.team = team;
        m_listener = listener;
        if (team != null) {
            setBtnDelEnabled("true");
        }
        m_deleteListener = deleteListener;
    }

    public void onCancelAction(javax.faces.event.ActionEvent event) {
        if (m_listener != null) {
            m_listener.reactOnCancel();
        }
    }

    public void onSaveAction(javax.faces.event.ActionEvent event) {
        if (m_listener != null) {
            m_listener.reactOnOK(this.team);
        }
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
                    m_deleteListener.reactOnOK(t);
                }
                @Override
                public void reactOnNo() {
                    Statusbar.outputAlert("NO");
                }
            });
    }

    // ------------------------------------------------------------------------
    // private usage
    // ------------------------------------------------------------------------
}
