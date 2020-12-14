package mediatheque;

import java.time.LocalDate;

import documents.IDocument;
import utilisateurs.Abonne;

public class Reservation extends Emprunt {
	public Reservation(IDocument document, LocalDate date, Abonne abonne) {
		super(document, date, abonne);
	}
}
