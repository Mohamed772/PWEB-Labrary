package mediatheque;

import java.time.LocalDate;

import documents.IDocument;
import utilisateurs.Abonne;

public class Emprunt {
	
	private IDocument document;
	private LocalDate date;
	private Abonne abonne;
	
	
	
	public Emprunt(IDocument document, LocalDate date, Abonne abonne) {
		super();
		this.document = document;
		this.date = date;
		this.abonne = abonne;
	}
	public Abonne getAbonne() {
		return abonne;
	}
	public void setAbonne(Abonne abonne) {
		this.abonne = abonne;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public IDocument getDocument() {
		return document;
	}
	public void setDocument(IDocument document) {
		this.document = document;
	}
}
