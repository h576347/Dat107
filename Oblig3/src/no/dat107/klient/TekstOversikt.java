package no.dat107.klient;

public class TekstOversikt {
	
	//Meny oversikt
	public  String oversikt() {
		String utskrift= "-----------------------------------------------------------" + "\n"
				+ "1 - for å legge til en ny ansatt" +"\n"
				+ "2 - for å oppdatere ansatt" + "\n"
				+ "3 - for å finne ansatt ved hjelp av id" + "\n"
				+ "4 - finn ansatt ved hjelp av brukernavn" + "\n"
				+ "5 - skriv ut liste over alle ansatte" + "\n"
				+ "6 - finn avdeling ved søk etter avdelings_id" + "\n"
				+ "7 - skriv ut ansatte på en avdeling ved bruk av avdelings_id" + "\n"
				+ "8 - skrive ut oversikten på nytt"+ "\n"
				+ "9 - legge inn ny avdeling" + "\n"
				+ "10 - endre avdeling ansatt jobber på" + "\n"
				+ "11 - legge til ett ny prosjekt" + "\n"
				+ "12 - registrer prosjektdeltakelse" + "\n"
				+ "13 - Endre antall timer en ansatt har brukt på ett prosjekt" + "\n"
				+ "14 - skrive ut total antall timer jobbet på prosjekt, inkl. liste av deltagere" +"\n"
				+ "Trykk 0, for å avslutte" + "\n"
				+"---------------------------------------------------------------";
		return utskrift;
	}

}
