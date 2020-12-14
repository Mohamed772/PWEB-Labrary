package utilisateurs;

public class Abonne {
	int numero;
	String nom;
	String dateNaissance;
	
	public Abonne(int v_numero, String v_nom, String v_dateNaissance) {
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
	
	public String getDateNaissance() {
		return dateNaissance;
	}
	
}
