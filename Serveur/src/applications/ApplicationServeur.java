package applications;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import documents.DVD;
import documents.IDocument;
import mediatheque.Mediatheque;
import serveur.*;
import utilisateurs.Abonne;


class ApplicationServeur {
	private final static int PORTRESERVATION = 3000;
	private final static int PORTEMPRUNT = 4000;
	private final static int PORTRETOUR = 5000;

	public static void main(String[] args) {
		try {
			initMediatheque();
			new Thread(new Serveur(PORTRESERVATION)).start();
			new Thread(new Serveur(PORTEMPRUNT)).start();
			new Thread(new Serveur(PORTRETOUR)).start();
			
		} catch (IOException e) {
				System.err.println(e);			
		}
	}
	
	public static void initMediatheque() {
		Mediatheque mediatheque = Mediatheque.getInstance();
		
		// Ajout de DOCUMENTS
		List<IDocument> listDoc = new ArrayList<IDocument>();
		listDoc.add(new DVD(1, "JCVD", false));
		listDoc.add(new DVD(2, "Le man", false));
		listDoc.add(new DVD(3, "Seul Two", true));
		mediatheque.setDocuments(listDoc);
		
		// Ajout d'ABONNE
		List<Abonne> listAbo = new ArrayList<Abonne>();
		listAbo.add(new Abonne(1, "Moron", "Lola-Marie", "2000-01-27"));
		listAbo.add(new Abonne(2, "Ben Belkacem", "Mohamed", "2001-10-09"));
		listAbo.add(new Abonne(3, "Kieffer", "Emmie", "2001-11-03"));
		listAbo.add(new Abonne(4, "Moron", "Carla", "2005-10-14"));
		mediatheque.setAbonnes(listAbo);
		
		
	}
}

