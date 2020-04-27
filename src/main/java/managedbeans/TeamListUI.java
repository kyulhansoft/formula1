package managedbeans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jpapersistence.Logic;
import jpapersistence.Team;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.ModalPopup;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.pagebean.PageBean;

import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.eclnt.jsfserver.defaultscreens.Statusbar;

@CCGenClass (expressionBase="#{d.TeamListUI}")

public class TeamListUI extends PageBean implements Serializable {
    TeamDetailsUI m_teamDetailsUI = null;
    public TeamDetailsUI getTeamDetailsUI() { return m_teamDetailsUI; }

    FIXGRIDListBinding<GridItem> m_grid = new FIXGRIDListBinding<GridItem>();
    public FIXGRIDListBinding<GridItem> getGrid() { return m_grid; }

    public class GridItem extends FIXGRIDItem implements java.io.Serializable {
        private Team i_team;
        public Team getTeam() {
            return i_team;
        }
        public void setTeam(Team team) {
            i_team = team;
        }

        public void onRowSelect() {
        }

        public void onRowExecute() {
            showTeamDialogRight(this);
        }
    }
    
    private void showTeamDialogRight(GridItem item) {
        try {
            Team t = (Team)item.getTeam().clone();
            m_teamDetailsUI = new TeamDetailsUI();
            TeamDetailsUI.IListenerSaveCancelDel listener = new TeamDetailsUI.IListenerSaveCancelDel() {
                @Override
                public void reactOnSave(Team team) {
                    // if OK, then we change the edited row in the grid with same values
                    item.setTeam(t);
                    m_teamDetailsUI = null;
                    Logic logic = new Logic();
                    logic.saveTeam(team);
                }
                @Override
                public void reactOnCancel() {
                    m_teamDetailsUI = null;
                }
                @Override
                public void reactOnDelete() {
                    m_grid.getItems().remove(item);
                    m_teamDetailsUI = null;
                }
            };
            m_teamDetailsUI.prepare(t, listener);
            // m_teamDetailsUI.setTeam(item.getTeam());
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showNewTeamDialog() {
        final TeamDetailsUI teamDetailsUI = new TeamDetailsUI();
        TeamDetailsUI.IListenerSaveCancelDel listener = new TeamDetailsUI.IListenerSaveCancelDel() {
            @Override
            public void reactOnSave(Team team) {
                Logic logic = new Logic();
                logic.addTeam(team);
                closePopup(teamDetailsUI);
                GridItem newItem = new GridItem();
                newItem.setTeam(team);
                m_grid.getItems().add(newItem);
            }
            @Override
            public void reactOnCancel() {
                closePopup(teamDetailsUI);
            }
            @Override
            public void reactOnDelete() {}
        };
        teamDetailsUI.prepare(new Team(), listener);

        ModalPopup.IModalPopupListener closeListener = new ModalPopup.IModalPopupListener() {
            @Override
            public void reactOnPopupClosedByUser() {
                closePopup(teamDetailsUI);
            }
        };
        openModalPopup(teamDetailsUI, "Enter data for new team", 500, 500, closeListener);
    }

    // ------------------------------------------------------------------------
    // inner classes
    // ------------------------------------------------------------------------
    
    /* Listener to the user of the page bean. */
    public interface IListener
    {
    }
    
    // ------------------------------------------------------------------------
    // members
    // ------------------------------------------------------------------------
    
    private IListener m_listener;
    private void loadGridFromDatabase() {

    }
    
    // ------------------------------------------------------------------------
    // constructors & initialization
    // ------------------------------------------------------------------------

    public TeamListUI() {
        //System.out.println("TeamListUI() is invoked");
        Logic logic = new Logic();
        List<Team> teamList = logic.getTeamList();
        for (Team team : teamList) {
            //System.out.println(team.getName());
            GridItem gridItem = new GridItem();
            gridItem.setTeam(team);
            m_grid.getItems().add(gridItem);
        }
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("cctestPU");
        // EntityManager entityManager = emf.createEntityManager();
        // entityManager.getTransaction().begin();
        // List<Team> teamList = entityManager.createQuery("SELECT t FROM Team t").getResultList();
        // entityManager.getTransaction().commit();
        // entityManager.close();
        // emf.close();
        // for (Team team : teamList) {
        //     System.out.println(team.getName());
        //     GridItem gridItem = new GridItem();
        //     gridItem.setName(team.getName());
        //     gridItem.setEstablished(team.getEstablished());
        //     m_grid.getItems().add(gridItem);
        // }
        // teamList.forEach((team) -> {
        //     System.out.println(team.getName() + " | " + team.getEstablished().toString());
        //     GridItem gridItem = new GridItem();
        //     gridItem.setName(team.getName());
        //     gridItem.setEstablished(team.getEstablished());
        //     m_grid.getItems().add(gridItem);
        // });
    }

    public String getPageName() { return "/TeamList.jsp"; }
    public String getRootExpressionUsedInPage() { return "#{d.TeamListUI}"; }

    // ------------------------------------------------------------------------
    // public usage
    // ------------------------------------------------------------------------

    /* Initialization of the bean. Add any parameter that is required within your scenario. */
    public void prepare(IListener listener)
    {
        m_listener = listener;
    }

    public void onNewTeamAction(javax.faces.event.ActionEvent event) {
        showNewTeamDialog();
    }

    // public void onDeleteAction(javax.faces.event.ActionEvent event) {
    //     GridItem item = m_grid.getSelectedItem();
    //     Statusbar.outputAlert(item.getTeam().getName());
    // }

    // ------------------------------------------------------------------------
    // private usage
    // ------------------------------------------------------------------------
}
