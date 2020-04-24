package jpapersistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Logic {
	
	private EntityManagerFactory m_emf;
	private EntityManager m_em;
	
	public Logic() {
		try {
			m_emf = Persistence.createEntityManagerFactory("cctestPU");
	        m_em = m_emf.createEntityManager();
		} catch (Exception e) {
			System.out.println("Error when initializing Entity Manager");
			e.printStackTrace();
		}
	}
	
	private void beginTransaction() {
		m_em.getTransaction().begin();
	}
	
	private void commit() {
		m_em.getTransaction().commit();
		m_em.close();
		m_emf.close();
	}
	
    public List<Team> getTeamList() {
        beginTransaction();
        List<Team> teamList = m_em.createQuery("SELECT t FROM Team t").getResultList();
        commit();
        return teamList;
        // for (Team team : teamList) {
        //     System.out.println(team.getName());
        //     GridItem gridItem = new GridItem();
        //     gridItem.setName(team.getName());
        //     gridItem.setEstablished(team.getEstablished());
        //     m_grid.getItems().add(gridItem);
        // }
        // teamList.forEach((team) -> {
        //     System.out.println(team.getName() + " | " + team.getEstablished().toString());
        //     TeamListUI.GridItem gridItem = new TeamListUI.GridItem();
        //     gridItem.setName(team.getName());
        //     gridItem.setEstablished(team.getEstablished());
        //     m_grid.getItems().add(gridItem);
        // });
    }

    public Team getTeam(UUID id) {
		return m_em.find(Team.class, id);
	}

    public void addTeam(Team team) {
		beginTransaction();
		m_em.persist(team);
		commit();
	}
    
    public void saveTeam(Team team) {
		// LocalDateTime now = LocalDateTime.now();
		// System.out.println(now + " Logic->saveTeam()");
		// System.out.println(team.getName());
		// System.out.println(team.getEstablished());
		// System.out.println(team.getId());
    	
    	//Team t = new Team();
    	//t.setName("Aaaa");
    	//t.setEstablished(LocalDate.of(1901, Month.APRIL, 3));
    	
    	beginTransaction();
        
        // Team t = m_em.find(Team.class, team.getId());
        // t.setName(team.getName());
        // t.setEstablished(team.getEstablished());
    	// m_em.persist(t);

		m_em.merge(team);
    	
    	commit();
    }

    public void deleteTeam(Team team) {
		beginTransaction();
		Team t = m_em.find(Team.class, team.getId());
		m_em.remove(t);
		commit();
	}
}
