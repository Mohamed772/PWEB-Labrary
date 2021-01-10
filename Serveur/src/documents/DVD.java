package documents;

import utilisateurs.Abonne;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.Period;
import java.util.Timer;

import exceptions.*;

public class DVD implements IDocument {
	private int numero;
	private String titre;
	boolean adulte;
	private Abonne abonne;
	private LocalDateTime date;
	private boolean reserve;
	private static final int AGE_ADULTE = 16;
	private static final int H_RESERVATION = 2; // En heures
	private static final int DELAI_RESA = 2; // En semaines

	private Timer chronoReservation;

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
		synchronized (this) {
			if (reserve) {
				if (abonne == ab) {
					throw new ReservationException("Ce document est deja emprunte par l'abonne connecte.");
				} else {
					throw new ReservationException("Ce document est deja reserve par une autre personne jusqu'� "
							+ date.plusHours(H_RESERVATION).getHour() + "h" + date.plusHours(H_RESERVATION).getMinute()
							+ ".");
				}
			} else {
				reserve = true;
				abonne = ab;
				date = LocalDateTime.now();
				chronoReservation = new Timer();
				chronoReservation.schedule(new TimerReservation(this), H_RESERVATION * 60 * 60 * 1000);

			}
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
		synchronized (this) {
			if (reserve) {
				if (abonne == ab) {
					date = LocalDateTime.now();
					reserve = false;
					chronoReservation.cancel();
					chronoReservation = new Timer();
					chronoReservation.schedule(new TimerRetourRetard(ab), 20 * 1000);// DELAI_RESA * 7 * 24 * 60 * 60 * 1000);

				} else {
					throw new EmpruntException("Ce document est deja reserve par une autre personne");
				}
			}
			if (abonne == null) {
				this.date = LocalDateTime.now();
				this.abonne = ab;
				chronoReservation = new Timer();
				chronoReservation.schedule(new TimerRetourRetard(ab), 30);// DELAI_RESA * 7 * 24 * 60 * 60 * 1000);

			} else {
				if (abonne == ab) {
					throw new EmpruntException("Ce document est deja emprunte par l'abonne connecte.");
				} else {
					throw new EmpruntException("Ce document est deja emprunte par quelqu'un d'autre.");
				}
			}
		}
	}

	public void retour() {
		synchronized (this) {
			reserve = false;
			date = null;
			abonne = null;
			// Pour ne pas cr�er des incoh�rences si on retourne un document r�serv�
			chronoReservation.cancel();
		}
	}

	public String toString() {
		return numero + " " + titre + " " + (this.adulte ? "pour adulte" : "Pour tous");

	}

}
