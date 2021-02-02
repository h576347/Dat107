package no.hvl.dat107.ansatt;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import no.hvl.dat107.avdeling.Avdeling;
import no.hvl.dat107.avdeling.AvdelingEAO;

import no.hvl.dat107.prosjekt.Prosjekt;
import no.hvl.dat107.prosjekt.ProsjektDeltagelse;

@Entity
@Table(schema = "Oblig3", name = "ansatt")
public class Ansatt {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int ansID;
	
	
	private String brukerNavn;
	
	private String fornavn;
	private String etternavn;
	private LocalDate datoAnsatt;
	private String stilling;
	private int mndsLonn;

	
	@OneToMany(mappedBy="ansatt", fetch = FetchType.EAGER)
    private List<ProsjektDeltagelse> deltagelser;
	
	@ManyToOne
	@JoinColumn(name = "avdeling", referencedColumnName= "avdID")
	private Avdeling avdeling;
	
	
	
	public Ansatt(String brukerNavn, String fornavn, String etternavn, LocalDate datoAnsatt, String stilling, int mndsLonn, Avdeling avdeling) {
		this.brukerNavn = brukerNavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.datoAnsatt = datoAnsatt;
		this.stilling = stilling;
		this.mndsLonn = mndsLonn;
		this.avdeling = avdeling;
	
		
		
	}
	public Ansatt() {
		this("","","",null,"",0,null);
	}
	
	
	@Override
	public String toString() {
		String utskrift = 
				"\n"+ "AnsattID: " + ansID + "\n" + 
				"Brukernavn: " + brukerNavn + "\n" +
				"Fornavn: " + fornavn + "\n" + 
				"Etternavn: " + etternavn + "\n" +
				"Ansettelsesdato: " + datoAnsatt + "\n" +
				"Stilling: " + stilling + "\n" + 
				"Månedslønn: " + mndsLonn + "\n" +
				"Avdeling: " + avdeling.getAvd_ID() + "\n"  ;
		return utskrift;
						
	
	
	}
	public void skrivUt(String innrykk) {
        System.out.printf("%sAnsatt nr %d: %s %s", innrykk, ansID, fornavn, etternavn);
    }
    
    public void skrivUtMedProsjekter() {
        System.out.println();
        skrivUt("");
        deltagelser.forEach(p -> p.skrivUt("\n   "));
    }
    
	public void leggTilProsjektdeltagelse(ProsjektDeltagelse prosjektdeltagelse) {
        deltagelser.add(prosjektdeltagelse);
    }

    public void fjernProsjektdeltagelse(ProsjektDeltagelse prosjektdeltagelse) {
        deltagelser.remove(prosjektdeltagelse);
    }
    
	public int getAnsID() {
		return ansID;
	}
	public void setAnsID(int ansID) {
		this.ansID = ansID;
	}
	public LocalDate getDatoAnsatt() {
		return datoAnsatt;
	}
	public void setDatoAnsatt(LocalDate datoAnsatt) {
		this.datoAnsatt = datoAnsatt;
	}
	public int getMndsLonn() {
		return mndsLonn;
	}
	public void setMndsLonn(int mndsLonn) {
		this.mndsLonn = mndsLonn;
	}
	
	public void setAnsattID(int ansID) {
		this.ansID = ansID;
	}
	public int getAnsattID () {
		return ansID;
	}
	public String getBrukerNavn() {
		return brukerNavn;
	}
	public void setBrukerNavn(String brukerNavn) {
		this.brukerNavn = brukerNavn;
	}
	public String getFornavn() {
		return fornavn;
	}
	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}
	public String getEtternavn() {
		return etternavn;
	}
	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}
	public LocalDate getAnsattDato() {
		return datoAnsatt;
	}
	public void setAnsattDato(LocalDate ansattDato) {
		this.datoAnsatt = ansattDato;
	}
	public String getStilling() {
		return stilling;
	}
	public void setStilling(String stilling) {
		this.stilling = stilling;
	}
	public int getMndLonn() {
		return mndsLonn;
	}
	public void setMndLonn(int mndLonn) {
		this.mndsLonn = mndLonn;
	}
	public Avdeling getAvdeling() {
		return avdeling;
	}
	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}
	
	/*public Ansatt lesAnsatt() {
		Scanner tast = new Scanner(System.in);
		
		AvdelingEAO avdelingEAO = new AvdelingEAO();
		
		System.out.println("Skriv inn brukernavn(maks 3 bokstaver) må vere unikt");
		String bNavn = tast.nextLine();
		
		System.out.println("Skriv inn fornavn");
		String fNavn = tast.nextLine();
		
		System.out.println("Skriv inn etternavn");
		String eNavn = tast.nextLine();
		
		System.out.println("Skriv inn ansettelses dato");
		LocalDate dato = LocalDate.parse(tast.nextLine());
		
		System.out.println("Skriv inn stilling");
		String stilling = tast.nextLine();
		
		System.out.println("Skriv inn månedslønn");
		int mndLoenn = tast.nextInt();
		tast.nextLine();
		
		System.out.println("Skriv  inn avdelings_id");
		int avd_id = tast.nextInt();
		Avdeling avd = avdelingEAO.finnAvdelingMedId(avd_id);
		tast.nextLine();
		
		Ansatt ansatt = new Ansatt(bNavn, fNavn, eNavn, dato, stilling, mndLoenn, avd);
		
		return ansatt;
	}*/
	

}
