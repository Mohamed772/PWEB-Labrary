package utilisateurs;

import java.time.LocalDate;

public class Abonne {
	int numero;
	String nom;
	LocalDate dateNaissance;
	
	public Abonne(int v_numero, String v_nom, LocalDate v_dateNaissance) {
		this.numero = v_numero;
		this.nom = v_nom;
		this.dateNaissance = v_dateNaissance;
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
	
}
