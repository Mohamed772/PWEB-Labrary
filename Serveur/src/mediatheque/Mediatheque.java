package mediatheque;

import java.util.ArrayList;
import java.util.List;
import documents.IDocument;
import utilisateurs.Abonne;

public  class Mediatheque {
	private static List<IDocument> documents;
	private static Mediatheque instance = new Mediatheque();
	private static List<Abonne> abonnes;
	
	private Mediatheque() {
		setDocuments(new ArrayList<IDocument>());
	}
	public static Mediatheque getInstance() {
		return instance;
	}
	public static void setInstance(Mediatheque instance) {
		Mediatheque.instance = instance;
	}
	public List<IDocument> getDocuments() {
		return documents;
	}
	public void setDocuments(List<IDocument> documents) {
		Mediatheque.documents = documents;
	}
	public void setAbonnes(List<Abonne> abonnes) {
		Mediatheque.abonnes = abonnes;
	}
	public List<Abonne> getAbonnes() {
		return abonnes;
	}
	
	
	
}
