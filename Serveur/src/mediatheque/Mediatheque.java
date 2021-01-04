package mediatheque;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import documents.IDocument;
import utilisateurs.Abonne;

public  class Mediatheque {
	private static List<IDocument> documentsDisponible;
	private static List<Reservation> documentsReserves;
	private static List<Emprunt> documentsEmpruntes;
	private static Mediatheque instance = new Mediatheque();
	private Mediatheque() {
		documentsDisponible = new ArrayList<IDocument>();
		documentsReserves = new ArrayList<Reservation>();
		documentsEmpruntes = new ArrayList<Emprunt>();
	}
	public static Mediatheque getInstance() {
		return instance;
	}
	public static void setInstance(Mediatheque instance) {
		Mediatheque.instance = instance;
	}
	public  List<IDocument> getDocumentsDisponible() {
		return documentsDisponible;
	}
	public  void setDocumentsDisponible(List<IDocument> documentsDisponible) {
		Mediatheque.documentsDisponible = documentsDisponible;
	}
	public  List<Reservation> getDocumentsReserves() {
		return documentsReserves;
	}
	public  void setDocumentsReserves(List<Reservation> documentsReserves) {
		Mediatheque.documentsReserves = documentsReserves;
	}
	public  List<Emprunt> getDocumentsEmpruntes() {
		return documentsEmpruntes;
	}
	public  void setDocumentsEmpruntes(List<Emprunt> documentsEmpruntes) {
		Mediatheque.documentsEmpruntes = documentsEmpruntes;
	}
	
	public void emprunter(int v_numero, Abonne abo) {
		for (IDocument doc : documentsDisponible){
			if (doc.numero() == v_numero) {
				documentsEmpruntes.add(new Emprunt(doc, LocalDate.now(), abo));
				documentsDisponible.remove(doc);
			}
		}
	}
}
