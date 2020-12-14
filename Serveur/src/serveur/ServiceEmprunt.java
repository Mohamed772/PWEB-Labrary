package serveur;
import java.io.*;
import java.net.Socket;

import documents.IDocument;
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
			out.println(mediatheque.getDocumentsDisponible().get(0).toString());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test();
		
	}
	
	public void addEmprunt(Abonne client, IDocument document) {
		
	}
	public void test(){
		
	}
	
	protected void finalize() throws Throwable {
		client.close();
	}
}
