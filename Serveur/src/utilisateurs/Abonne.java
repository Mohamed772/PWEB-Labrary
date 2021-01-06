package utilisateurs;

import java.time.LocalDate;

public class Abonne {
	int numero;
	String nom;
	String prenom;
	LocalDate dateNaissance;
	
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
	
	public String toString() {
		return prenom + " " + nom;
	}
	
}
