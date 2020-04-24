package managedbeans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import jpapersistence.Team;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.pagebean.PageBean;
import org.eclnt.ccee.util.StringUtil;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

@CCGenClass (expressionBase="#{d.TestpageUI}")

public class TestpageUI extends PageBean implements Serializable {

    public void onButtonAction(javax.faces.event.ActionEvent event) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cctestPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            Team t = new Team();
            t.setName("Ferrari");
            //t.setEstabilished(new GregorianCalendar(1950, Calendar.JULY, 18));
            t.setEstablished(LocalDate.of(1950, Month.JULY, 18));
            em.persist(t);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        try {
            Team t = new Team();
            t.setName("McLaren");
            //t.setDateEst(new GregorianCalendar(1960, Calendar.SEPTEMBER, 20));
            t.setEstablished(LocalDate.of(1960, Month.SEPTEMBER, 20));
            em.persist(t);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        try {
            Team t = new Team();
            t.setName("Williams");
            //t.setDateEst(new GregorianCalendar(1970, Calendar.MARCH, 10));
            t.setEstablished(LocalDate.of(1970, Month.MARCH, 10));
            em.persist(t);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        try {
            Team t = new Team();
            t.setName("Alfa Romeo");
            //t.setDateEst(new GregorianCalendar(1945, Calendar.FEBRUARY, 5));
            t.setEstablished(LocalDate.of(1945, Month.FEBRUARY, 5));
            em.persist(t);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        em.getTransaction().commit();
        em.close();
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
    
    // ------------------------------------------------------------------------
    // constructors & initialization
    // ------------------------------------------------------------------------

    public TestpageUI()
    {
        System.out.println("TestpageUI constructor");
        System.out.println(StringUtil.isEmpty(null));
    }

    public String getPageName() { return "/testpage.jsp"; }
    public String getRootExpressionUsedInPage() { return "#{d.TestpageUI}"; }

    // ------------------------------------------------------------------------
    // public usage
    // ------------------------------------------------------------------------

    /* Initialization of the bean. Add any parameter that is required within your scenario. */
    public void prepare(IListener listener)
    {
        m_listener = listener;
    }

    // ------------------------------------------------------------------------
    // private usage
    // ------------------------------------------------------------------------
}
