package no.hvl.dat107.prosjekt;

import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import no.hvl.dat107.ansatt.Ansatt;
import no.hvl.dat107.ansatt.AnsattEAO;

@Entity
@Table(name="prosjektDeltagelse", schema = "Oblig3")
@IdClass(ProsjektdeltagelsePK.class)
public class ProsjektDeltagelse {
	
	@Id 
	@ManyToOne
	@JoinColumn(name = "ansID")
	private Ansatt ansatt;
	
	@Id 
	@ManyToOne
	@JoinColumn(name = "pID")
	private Prosjekt prosjekt;
	
	private float timer;
	private String ansattRolle;
	
	public ProsjektDeltagelse(Ansatt ansatt, Prosjekt prosjekt, float timer, String ansattRolle) {
		this.ansatt = ansatt;
		this.prosjekt = prosjekt;
		this.timer = timer;
		this.ansattRolle = ansattRolle;
		
		ansatt.leggTilProsjektdeltagelse(this);
	    prosjekt.leggTilProsjektdeltagelse(this);
		
	}
	
	public ProsjektDeltagelse() {}
	
    
    public void skrivUt(String innrykk) {
        System.out.printf("%sDeltagelse: %s %s, %s, %d timer", innrykk, 
                ansatt.getFornavn(), ansatt.getEtternavn(), prosjekt.getpNavn(), timer);
    }
    

	public Ansatt getAnsatt() {
		return ansatt;
	}

	public void setAnsatt(Ansatt ansatt) {
		this.ansatt = ansatt;
	}

	public Prosjekt getProsjekt() {
		return prosjekt;
	}

	public void setProsjekt(Prosjekt prosjekt) {
		this.prosjekt = prosjekt;
	}

	public float getTimer() {
		return timer;
	}

	public void setTimer(float timer) {
		this.timer = timer;
	}

	public String getAnsattRolle() {
		return ansattRolle;
	}

	public void setAnsattRolle(String ansattRolle) {
		this.ansattRolle = ansattRolle;
	}
	
	//Registere prosjekt
	public static ProsjektDeltagelse registrerProsjekt() {
		Scanner tast = new Scanner(System.in);
		AnsattEAO ansattEAO = new AnsattEAO();
		Ansatt ansatt;
		Prosjekt p;
		ProsjektEAO pEAO = new ProsjektEAO();
		
		System.out.println("Skriv inn ansatt-id");
		int ans = tast.nextInt();
		ansatt = ansattEAO.finnAnsattMedId(ans);
		tast.nextLine();
		
		System.out.println("Skriv inn prosjekt-id");
		int pID = tast.nextInt();
		p = pEAO.finnProsjektMedId(pID);
		tast.nextLine();
		
		System.out.println("Skriv inn antall timer den ansatte har jobbet der");
		int tid = tast.nextInt();
		tast.nextLine();
		
		System.out.println("Skriv inn den ansatte sin rolle");
		String rolle = tast.nextLine();
		
		ProsjektDeltagelse pd = new ProsjektDeltagelse(ansatt,p,tid,rolle);
		return pd;
		
	}
	
	
	
	
	
	

}
