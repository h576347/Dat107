package no.hvl.dat107.ansatt;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import no.hvl.dat107.avdeling.AvdelingEAO;
import no.hvl.dat107.prosjekt.ProsjektDeltagelse;

public class AnsattEAO {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");

	
public AnsattEAO() {}


	//Legge til ny ansatt i databasen
	public void leggTilAnsatt(Ansatt nyAnsatt) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(nyAnsatt);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	
	}
	
	//Oppdatere en allerede eksisterrende ansatt
	public void oppdaterAnsatt(Ansatt ansatt) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Scanner tast = new Scanner(System.in);
		String input;
		int tall;

		try {
			tx.begin();
			System.out.println("Ønsker du å oppdatere ny stilling? j/n");
			if(tast.nextLine().equals("j")) {
				System.out.println("Skriv inn ny stilling: ");
				input = tast.nextLine();
				ansatt.setStilling(input);
			}
			System.out.println("Ønsker du å oppdatere månedslønn?  j/n");
			if(tast.nextLine().equals("j")) {
				System.out.println("Skriv inn ny månedslønn: ");
				tall = tast.nextInt();
				tast.nextLine();
				ansatt.setMndLonn(tall);
			}
			

			System.out.println(ansatt);
			
			em.merge(ansatt);
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}
	
	
	//Finne ansatt ved hjelp av id
	public Ansatt finnAnsattMedId(int id) {
		EntityManager em = emf.createEntityManager();

		Ansatt ansatt;

		try {
			ansatt = em.find(Ansatt.class, id);
		} finally {
			em.close();
		}
		return ansatt;
	}

	//Finne ansatt ved hjelp av brukernavn
	public Ansatt finnAnsattMedBrukernavn(String brukerNavn) {
		Ansatt ansatt = null;
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		

		try {
			tx.begin();
			TypedQuery<Ansatt> query = em.createQuery("SELECT t FROM Ansatt t WHERE t.brukerNavn = :brukerNavn", Ansatt.class);
			query.setParameter("brukerNavn", brukerNavn);
			ansatt = query.getSingleResult();
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}

		} finally {
			em.close();
		}
		return ansatt;

	}

	//Få en liste over alle annsatte
	public List<Ansatt> listeOverAlleAnsatte() {
		EntityManager em = emf.createEntityManager();

		List<Ansatt> ansatt;
		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT t FROM Ansatt t", Ansatt.class);
			ansatt = query.getResultList();
		} finally {
			em.close();
		}
		return ansatt;
	}
	
	//Oppdatere avdelinger
	public void oppdaterAvdeling(Ansatt ansatt) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Scanner tast = new Scanner(System.in);
		AvdelingEAO avdelingEAO = new AvdelingEAO();
		
		try {
			tx.begin();
			
			if(ansatt.getAvdeling().getSjef_ID().equals(ansatt)) {
				System.out.println("Den ansatte er sjef, og kan ikke bytte avdeling");
			}else {
			
				System.out.println("Skriv inn ny id-avdeling: ");
				int svar = tast.nextInt();
				tast.nextLine();
			
				ansatt.setAvdeling(avdelingEAO.finnAvdelingMedId(svar));
				em.merge(ansatt);
				System.out.println(ansatt);
			}
			tx.commit();
			
		} catch(Throwable e) {
			if(tx.isActive()) {
				tx.rollback();
			}
			
		}finally {
			em.close();
		}

		
	}
	
	//Finne prosjektdeltagelser
	public ProsjektDeltagelse finnProsjektdeltagelse(int ansattId, int prosjektId) {
        
        String queryString = "SELECT pd FROM Prosjektdeltagelse pd " 
                + "WHERE pd.ansatt.id = :ansattId AND pd.prosjekt.id = :prosjektId";

        EntityManager em = emf.createEntityManager();

        ProsjektDeltagelse pd = null;
        try {
            TypedQuery<ProsjektDeltagelse> query 
                    = em.createQuery(queryString, ProsjektDeltagelse.class);
            query.setParameter("ansattId", ansattId);
            query.setParameter("prosjektId", prosjektId);
            pd = query.getSingleResult();
            
        } catch (NoResultException e) {
            // e.printStackTrace();
        } finally {
            em.close();
        }
        return pd;
    } 

}
