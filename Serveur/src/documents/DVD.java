package documents;

import utilisateurs.Abonne;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.Period;

import exceptions.*;

public class DVD implements IDocument {
	private int numero;
	private String titre;
	boolean adulte;
	private Abonne abonne;
	private LocalDateTime date;
	private boolean reserve;
	private static final int AGE_ADULTE = 16;
	
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
		if (adulte) {
			LocalDate currentDate = LocalDate.now();
			int age = Period.between(ab.getDateNaissance(), currentDate).getYears();
			if (age < AGE_ADULTE) {
				throw new ReservationException("Ce document necessite d'avoir au moins 16 ans pour etre reserve.");
			}
		}
		if (reserve) {
			if(abonne == ab) {
				throw new ReservationException("Ce document est deja emprunte par l'abonne connecte.");
			}
			else {
				throw new ReservationException("Ce document est deja reserve par une autre personne.");
			}
		} else {
			reserve = true;
			abonne = ab;
			date = LocalDateTime.now();
		}
	}

	public void empruntPar(Abonne ab) throws EmpruntException {
		if (adulte) {
			LocalDate currentDate = LocalDate.now();
			int age = Period.between(ab.getDateNaissance(), currentDate).getYears();
			if (age < AGE_ADULTE) {
				throw new EmpruntException("Ce document necessite d'avoir au moins 16 ans pour etre emprunte.");
			}
		}
		if (reserve) {
			if (abonne == ab) {
				date = LocalDateTime.now();;
				reserve = false;
			}else {
				throw new EmpruntException("Ce document est deja reserve par une autre personne");
			}
		}
		if (abonne == null) {
			this.date = LocalDateTime.now();
			this.abonne = ab;
			
		}
		else {
			if(abonne == ab) {
				throw new EmpruntException("Ce document est deja emprunte par l'abonne connecte.");
			}
			else {
				throw new EmpruntException("Ce document est deja emprunte par quelqu'un d'autre.");
			}
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
