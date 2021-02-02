package no.hvl.dat107.prosjekt;

import java.util.List;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import no.hvl.dat107.ansatt.Ansatt;

import no.hvl.dat107.prosjekt.ProsjektDeltagelse;

//Oppretter en prosjekt klasse

@Entity
@Table(name = "prosjekt", schema = "Oblig3")
public class Prosjekt {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pID;
	
	private String pNavn;
	private String info;
	
	
	 @OneToMany(mappedBy="prosjekt", fetch= FetchType.EAGER)
	 private List<ProsjektDeltagelse> deltagelser;
	
	
	public Prosjekt(String pNavn, String info) {
		this.pNavn = pNavn;
		this.info = info;
	
	}
	
	public void skrivUt(String innrykk) {
	    System.out.printf("%sProsjekt nr %d: %s ", innrykk, pID, pNavn);
	 }
	 
	public void skrivUtMedAnsatte() {
	     System.out.println();
	     skrivUt("");
	     deltagelser.forEach(a -> a.skrivUt("\n   "));
	}

	public void leggTilProsjektdeltagelse(ProsjektDeltagelse prosjektdeltagelse) {
	    deltagelser.add(prosjektdeltagelse);
	}

	public void fjernProsjektdeltagelse(ProsjektDeltagelse prosjektdeltagelse) {
		deltagelser.remove(prosjektdeltagelse);
	}
	 
	public Prosjekt() {
		this("","");
	}

	public int getPID() {
		return pID;
	}

	public String getpNavn() {
		return pNavn;
	}

	public void setpNavn(String pNavn) {
		this.pNavn = pNavn;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<ProsjektDeltagelse> getDeltagelser() {
		return deltagelser;
	}

	//Opprette nytt prosjekt
	public static Prosjekt nyttProsjekt() {
		Scanner tast = new Scanner(System.in);
		String tittel, info;
		
		System.out.println("Skriv inn navn på prosjektet: ");
		tittel = tast.nextLine();
		
		System.out.println("Skriv inn en beskrivelse om " + tittel);
		info = tast.nextLine();
		
		Prosjekt p = new Prosjekt(tittel, info);
		System.out.println();
		
		return p;
			
		
	
		
	}

}
