package no.hvl.dat107.prosjekt;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProsjektEAO {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
	
	public void nyttProsjekt(Prosjekt prosjekt) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.persist(prosjekt);
			tx.commit();
			
			
		}catch(Throwable e){
			if(tx.isActive()) {
				tx.rollback();
			}
			
		}finally {
			em.close();
		}
		
	}
	
	public Prosjekt finnProsjektMedId(int id) {
		EntityManager em = emf.createEntityManager();

		Prosjekt prosjekt;

		try {
			prosjekt = em.find(Prosjekt.class, id);
		} finally {
			em.close();

		}
		return prosjekt;
	}

}
