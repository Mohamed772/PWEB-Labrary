package applications;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import documents.DVD;
import documents.IDocument;
import mediatheque.Mediatheque;
import serveur.*;

class ApplicationServeur {
	private final static int PORTRESERVATION = 3000;
	private final static int PORTEMPRUNT = 4000;
	private final static int PORTRETOUR = 5000;

	public static void main(String[] args) {
		try {
			initMediatheque();
			//new Thread(new Serveur(PORTRESERVATION)).start();
			new Thread(new Serveur(PORTEMPRUNT)).start();
			//new Thread(new Serveur(PORTRETOUR)).start();
		} catch (IOException e) {
				System.err.println(e);			
		}
	}
	
	public static void initMediatheque() {
		Mediatheque mediatheque = Mediatheque.getInstance();
		List<IDocument> list = new ArrayList<IDocument>();
		list.add(new DVD(0, "JCVD", false));
		list.add(new DVD(1, "Le man", false));
		list.add(new DVD(2, "Seul Two", true));
		mediatheque.setDocumentsDisponible(list);
	}
	
	
	
}

