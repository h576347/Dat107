package no.hvl.dat107.prosjekt;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ProsjektDeltagelseEAO {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
	
	//Legge til en ny prosjektdeltagelse
	public void nyProsjektD(ProsjektDeltagelse prosjektD) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.persist(prosjektD);
			tx.commit();
			
		}catch(Throwable e){
			if(tx.isActive()) {
				tx.rollback();
			}
			
		}finally {
			em.close();
		}
		
	}
	
	//Oppdatere antall timer brukt på et prosjekt
	public void oppdaterTimer(int aID, int pID) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Scanner tast = new Scanner(System.in);
		ProsjektDeltagelse pd = null;
		
		try {
			tx.begin();
			ProsjektdeltagelsePK pPK = new ProsjektdeltagelsePK(aID,pID);
			pd = em.find(ProsjektDeltagelse.class, pPK);
			
			System.out.println("Skriv inn antall timer: ");
			float timer = tast.nextInt();
			tast.nextLine();
			
			pd.setTimer(timer);
			em.merge(pd);
			tx.commit();
			
		}catch(Throwable e){
			e.printStackTrace();
			if(tx.isActive()) {
				tx.rollback();
			}
			
		}finally {
			em.close();
		}
		
	}
	
	//Finne toalt antall timer brukt på prosjekt
	public double totalAntallTimer(int pID) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		double timer = 0;
		
		Prosjekt p; 
		String queryString = "SELECT SUM p.timer FROM prosjektdeltagelse p WHERE p.pID =: pID";
		
		
		try {
			tx.begin();
			p= em.find(Prosjekt.class, pID);
			Query query =  em.createQuery(queryString);
			query.setParameter("pID", p);
			timer = (double) query.getSingleResult();
			tx.commit();
		}catch(Throwable e) {
			if(tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		return timer;
	}
}
