package serveur;
import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;

import documents.IDocument;
import mediatheque.Emprunt;
import mediatheque.Mediatheque;
import mediatheque.Reservation;
import utilisateurs.Abonne;

public class ServiceEmprunt implements Runnable {
	private final Socket client;
	private final Mediatheque mediatheque = Mediatheque.getInstance();

	public ServiceEmprunt(Socket socket) {
		this.client = socket;
	}

	public void run() {
		
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
			out.println(mediatheque.getDocumentsDisponible().get(0).toString());
			
			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test();
		
	}
	
	public void emprunter(int v_numero, Abonne abo) {
		List<IDocument> documentsDisponible = mediatheque.getDocumentsDisponible();
		List<Emprunt> documentsEmpruntes = mediatheque.getDocumentsEmpruntes();
		
		for (IDocument doc : documentsDisponible){
			if (doc.numero() == v_numero) {
				documentsEmpruntes.add(new Emprunt(doc, LocalDate.now(), abo));
				documentsDisponible.remove(doc);
			}
		} 
		mediatheque.setDocumentsEmpruntes(documentsEmpruntes);
		 mediatheque.setDocumentsDisponible(documentsDisponible);
	}
	public void test(){
		
	}
	
	protected void finalize() throws Throwable {
		client.close();
	}
}
