package mediatheque;

import java.util.ArrayList;
import java.util.List;
import documents.IDocument;

public  class Mediatheque {
	private static List<IDocument> documents;
	private static Mediatheque instance = new Mediatheque();
	
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
	
	
	
}
