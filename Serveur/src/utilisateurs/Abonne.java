package utilisateurs;

import java.time.LocalDate;
import java.util.Timer;


public class Abonne {
	int numero;
	private String nom;
	private String prenom;
	private LocalDate dateNaissance;
	private LocalDate dateBan;
	
	private Timer chronoDeban;
	
	private final static int BANNISSEMENT = 1; // En mois
	
	public Abonne(int v_numero, String v_nom, String v_prenom, String v_dateNaissance) {
		this.numero = v_numero;
		this.nom = v_nom;
		this.prenom = v_prenom;
		this.dateNaissance = LocalDate.parse(v_dateNaissance);
	}
	
	public int getNumero() {
		return numero;
	}
	
	public String getNom() {
		return nom;
	}
	
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	
	public synchronized LocalDate getDateban() {
		return dateBan;
	}
	
	public synchronized void setDateban(LocalDate dt) {
		this.dateBan = dt;
		chronoDeban = new Timer();
		chronoDeban.schedule(new TimerDeban(this), BANNISSEMENT * 30 * 24 * 60 * 60 * 1000);

	}
	
	// TODO : Montrer ça à Raph
	public synchronized void setDeban() {
		this.dateBan = null ;
		chronoDeban.cancel();
	}
	
	
	public String toString() {
		return prenom + " " + nom;
	}
	
	public int getBannissement() {
		return BANNISSEMENT;
	}
	
}
