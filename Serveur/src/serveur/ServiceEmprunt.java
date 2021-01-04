package serveur;
import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;

import documents.IDocument;
import exceptions.EmpruntException;
import mediatheque.Mediatheque;
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
			out.println("Bienvenue dans le service de r�servation de documents de la mediatheque");
			
			out.println("Quel film voulez vous ? \n");
			
			showDocuments(out);
			//String line = in.readLine();
			//System.out.println(line);
						
			
			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test();
		
	}
	
	public void emprunter(int v_numero, Abonne abo) throws EmpruntException {
		List<IDocument> documents = mediatheque.getDocuments();
		
		for (IDocument doc : documents){
			if (doc.numero() == v_numero) {
				
				doc.empruntPar(abo);
			}
		} 
		 mediatheque.setDocuments(documents);
	}
	public void test(){
		
	}
	public void showDocuments(PrintWriter out) {
		List<IDocument> documents = mediatheque.getDocuments();
		for (IDocument doc : documents) {
			out.println(doc.toString());
		}
	}
	
	protected void finalize() throws Throwable {
		client.close();
	}
}
