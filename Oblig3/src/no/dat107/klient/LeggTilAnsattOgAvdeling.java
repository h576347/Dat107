package no.dat107.klient;

import java.time.LocalDate;
import java.util.Scanner;

import no.hvl.dat107.ansatt.Ansatt;
import no.hvl.dat107.avdeling.Avdeling;
import no.hvl.dat107.avdeling.AvdelingEAO;
import no.hvl.dat107.prosjekt.ProsjektEAO;

public class LeggTilAnsattOgAvdeling {
	
	//Oppretter ny ansatt
		public  Ansatt nyAnsatt() {
			Scanner tast = new Scanner(System.in);
		
			AvdelingEAO avdelingEAO = new AvdelingEAO();
			ProsjektEAO prosjektEAO = null;
			
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
		}
		
		//Oppretter ny avdeling
		public Avdeling nyAvdeling(Ansatt sjef) {
			
			Scanner tast = new Scanner(System.in);
			
			System.out.println("Skriv inn navnet på avdelingen: ");
			String avd = tast.nextLine();
			
			Avdeling avdeling = new Avdeling(avd,sjef);
			
			return avdeling;
			
			
		}

}
