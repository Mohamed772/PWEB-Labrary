package documents;

import utilisateurs.Abonne;
import exceptions.*;

public class DVD implements IDocument {
	int numero;
	String titre;
	boolean adulte;
	
	public DVD(int v_numero, String v_titre, boolean v_adulte) {
		this.numero = v_numero;
		this.titre = v_titre;
		this.adulte = v_adulte;
	}
	
	@Override
	public int numero() {
		return this.numero;
	}

	@Override
	public void reservationPour(Abonne ab) throws ReservationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void empruntPar(Abonne ab) throws EmpruntException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retour() {
		// TODO Auto-generated method stub
		
	}
	

}
