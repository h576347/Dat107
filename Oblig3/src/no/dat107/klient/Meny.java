package no.dat107.klient;

import java.util.Scanner;

import no.hvl.dat107.ansatt.Ansatt;
import no.hvl.dat107.ansatt.AnsattEAO;
import no.hvl.dat107.avdeling.Avdeling;
import no.hvl.dat107.avdeling.AvdelingEAO;
import no.hvl.dat107.prosjekt.Prosjekt;
import no.hvl.dat107.prosjekt.ProsjektDeltagelse;
import no.hvl.dat107.prosjekt.ProsjektDeltagelseEAO;
import no.hvl.dat107.prosjekt.ProsjektEAO;

public class Meny {

	private LeggTilAnsattOgAvdeling leggTil = new LeggTilAnsattOgAvdeling();
	private TekstOversikt to = new TekstOversikt();
	
	public void start() {
		Scanner tast = new Scanner(System.in);
		AnsattEAO ansattEAO = new AnsattEAO();
		AvdelingEAO avdelingEAO = new AvdelingEAO();
		Prosjekt prosjekt = new Prosjekt();
		ProsjektEAO prosjektEAO = new ProsjektEAO();
		ProsjektDeltagelseEAO prosjektDEAO = new ProsjektDeltagelseEAO();
		
		
		Ansatt ansatt;
		Ansatt a = null;
		int aID = 0;
		int pID = 0 ;

		
		System.out.println(to.oversikt());
		//svaret satt = -1, for å være sikker på at brukeren skriver inn gyldig tall
		int svar = -1;
	
		
		int id = 0;
		String bNavn = "";
		
		while(svar != 0) {
			
			svar = tast.nextInt();
			tast.nextLine();
			
			if(svar == 3 || svar == 6 || svar == 7){
				System.out.println("Skriv inn id-nummer");
				id = tast.nextInt();
				tast.nextLine();
			}
			else if(svar == 4 || svar == 2){			
				System.out.println("Skriv inn brukernavn");
				bNavn = tast.nextLine();
				
			} else if(svar == 9) {
				
				System.out.println("Skriv inn Id på sjef");
				id = tast.nextInt();
				tast.nextLine();
				a = ansattEAO.finnAnsattMedId(id);
				
			}else if(svar == 13) {
				System.out.println("Skriv inn ID til ansatt");
				aID = tast.nextInt();
				tast.nextLine();
				
				System.out.println("Skriv inn ID til Prosjekt");
				pID = tast.nextInt();
				tast.nextLine();
			
			}else if(svar == 14) {
				System.out.println("Skriv inn ID til prosjekt");
				pID = tast.nextInt();
				
			}else if(svar == 10) {
				System.out.println("Skriv inn ID til den ansatte");
				id = tast.nextInt();
				tast.nextLine();
				a = ansattEAO.finnAnsattMedId(id);
				
			}
			
			switch(svar) {
		
			case 1:
				ansattEAO.leggTilAnsatt(leggTil.nyAnsatt());
				System.out.println("Ansatt lagt til");
				break;
				
			case 2:
				ansattEAO.oppdaterAnsatt(ansattEAO.finnAnsattMedBrukernavn(bNavn));
				System.out.println("Ansatt er oppdatert");
				break;
				
			case 3:
				System.out.println(ansattEAO.finnAnsattMedId(id));
				break;
				
			case 4:
				System.out.println(ansattEAO.finnAnsattMedBrukernavn(bNavn));
				break;
				
			case 5:
				System.out.println(ansattEAO.listeOverAlleAnsatte());
				break;
			
			case 6:
				System.out.println("Avdeling_id: " + id + " er " + avdelingEAO.finnAvdelingMedId(id).getNavn());
				break;
				
			case 7:
				System.out.println(avdelingEAO.finnAvdelingMedId(id).getAnsatt());
				System.out.println("Sjefen for avdelingen er: " + avdelingEAO.finnAvdelingMedId(id).getSjef_ID());
				break;
			
			case 8:
				System.out.println(to.oversikt());
				break;
				
			case 9:
				Avdeling avd = leggTil.nyAvdeling(a);
				avdelingEAO.leggTilNyAvdeling(avd,a);
				System.out.println("Avdelingen " + avd + " er lagt til");
				break;
				
			case 10:
				//Oppdatere prosjektdeltagelse
				ansattEAO.oppdaterAvdeling(a);
				break;
				
			case 11: 
				//legge til nytt prosjekt
				prosjektEAO.nyttProsjekt(prosjekt.nyttProsjekt());
				System.out.println("Prosjekt lagt til");
				break;
				
			case 12:
				prosjektDEAO.nyProsjektD(ProsjektDeltagelse.registrerProsjekt());
				System.out.println("Prosjekt registrert");
				break;
				
			case 13:
				prosjektDEAO.oppdaterTimer(aID,pID);
				break;
				
			case 14:
				
				prosjektEAO.finnProsjektMedId(id).skrivUtMedAnsatte();
				
				System.out.println("Total antall timer: " + prosjektDEAO.totalAntallTimer(pID));
				
				break;
			
			case 0:
				break;	
				
			}
		}
		
		

	}
	

}
