package no.hvl.dat107.avdeling;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import no.hvl.dat107.ansatt.Ansatt;

@Entity
@Table(name ="avdeling", schema = "Oblig3")
public class Avdeling {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int avdID;
	
	private String avdNavn;
	
	@OneToOne
	@JoinColumn(name ="sjefID", referencedColumnName="ansId")
	private Ansatt sjefID;
	
	
	@OneToMany(mappedBy = "avdeling", fetch = FetchType.EAGER)
	private List<Ansatt> ansatt;
	
	
	public Avdeling(String avdNavn, Ansatt sjefID) {
		this.avdNavn = avdNavn;
		this.sjefID = sjefID;
		
	}
	public Avdeling() {
		this("", null);
	}
	public int getAvd_ID() {
		return avdID;
	}
	public void setAvd_ID(int avd_ID) {
		this.avdID = avdID;
	}
	public String getNavn() {
		return avdNavn;
	}
	public void setNavn(String avdNavn) {
		this.avdNavn = avdNavn;
	}
	public Ansatt getSjef_ID() {
		return sjefID;
	}
	@Override
	public String toString() {
		return "\n" + "Avdeling [avdeling_ID=" + avdID + "\n "+", avdNavn=" + avdNavn  + "\n" +", sjef_ID=" + sjefID + ", ansatt="
				+ ansatt + "]" + "\n";
	}
	public void setSjef_ID(Ansatt sjefID) {
		this.sjefID = sjefID;
	}
	public List<Ansatt> getAnsatt() {
		return ansatt;
	}
	public void setAnsatt(List<Ansatt> ansatt) {
		this.ansatt = ansatt;
	}

}
