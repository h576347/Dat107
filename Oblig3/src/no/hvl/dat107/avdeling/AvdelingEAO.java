package no.hvl.dat107.avdeling;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import no.hvl.dat107.ansatt.Ansatt;
import no.hvl.dat107.ansatt.AnsattEAO;

public class AvdelingEAO {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");

	//Finne avdeling ved hjelp av avdelingens id
	public Avdeling finnAvdelingMedId(int id) {
		EntityManager em = emf.createEntityManager();

		Avdeling avdeling;

		try {
			avdeling = em.find(Avdeling.class, id);
		} finally {
			em.close();

		}
		return avdeling;
	}

	//Legge til en nyy avdeling
	public void leggTilNyAvdeling(Avdeling avdeling, Ansatt sjef) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
	
	

		try {
			tx.begin();
			em.persist(avdeling);
			sjef.setAvdeling(avdeling);
			em.merge(sjef);
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

}
