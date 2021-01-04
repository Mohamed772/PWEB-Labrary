package documents;

import utilisateurs.Abonne;

import java.time.LocalDate;

import exceptions.*;

public class DVD implements IDocument {
	private int numero;
	private String titre;
	boolean adulte;
	private Abonne abonne;
	@SuppressWarnings("unused")
	private LocalDate date;
	private boolean reserve;
	
	public DVD(int v_numero, String v_titre, boolean v_adulte) {
		this.numero = v_numero;
		this.titre = v_titre;
		this.adulte = v_adulte;
		this.date = null;
		this.abonne = null;
	}
	
	public int numero() {
		return this.numero;
	}

	public void reservationPour(Abonne ab) throws ReservationException {
		if (reserve) {
			throw new ReservationException(" Ce document est deja reserve par une autre personne");
		}else {
			reserve = true;
			abonne = ab;
			date = LocalDate.now();
		}
	}

	public void empruntPar(Abonne ab) throws EmpruntException {
		if (reserve) {
			if (abonne == ab) {
				date = LocalDate.now();;
				reserve = false;
			}else {
				throw new EmpruntException(" Ce document est deja reserve par une autre personne");
			}
		}
		if (abonne == null) {
			this.date = LocalDate.now();
			this.abonne = ab;
			
		}else {
			throw new EmpruntException(" Ce document est deja emprunte ");
		}
	}

	public void retour() {
		reserve = false;
		date = null;
		abonne = null;
	}
	
	public String toString() {
		return numero + " " + titre + " " + (this.adulte?"pour adulte":"Pour tous");
		
	}

}
