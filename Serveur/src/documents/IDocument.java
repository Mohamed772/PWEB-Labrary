package documents;

import exceptions.EmpruntException;
import exceptions.ReservationException;
import utilisateurs.Abonne;

public interface IDocument {

	int numero();
	void reservationPour(Abonne ab) throws ReservationException;
	void empruntPar(Abonne ab) throws EmpruntException;
	void retour();

}
