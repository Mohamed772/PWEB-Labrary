package serveur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import documents.IDocument;
import mediatheque.Mediatheque;
import utilisateurs.Abonne;

public class ServiceRetour implements Runnable {
	private final Socket client;
	private final Mediatheque mediatheque = Mediatheque.getInstance();

	public ServiceRetour(Socket socket) {
		this.client = socket;
	}

	public void run() {
	
		try {
			
			boolean retourAgain = true;
			
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
			out.println("Bienvenue dans le service de retour de documents de la mediatheque");
			
			while(retourAgain) {
			
				out.println("Quel film souhaitez-vous retourner ?");
				
				String line = in.readLine();
				
				while(!entryInt(line)) {
					out.println("Votre saisie ne correspond pas a un nombre entier. Veuillez reessayer :");
					line = in.readLine();
				}
				
				retourner(Integer.parseInt(line), out);
				
				out.println("Souhaitez-vous retourner un autre document ?");
				
				// Boucle au cas ou la saisie soit differente de oui ou non
				
				while(true) {
					line = in.readLine();
					
					if(line.equalsIgnoreCase("Non")) {
						retourAgain = false;
						out.println("Vous allez être redirigé vers le menu principal.\n\n.................................\n");
						break;
					}
					else if(line.equalsIgnoreCase("Oui")) {
						break;
					}
					else {
						out.println("Veuillez uniquement répondre par [Oui]/[Non] :");
					}
				}
				
			}
			
						
			
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// TODO: Faire en sorte que si le numero ne correspond a aucun bon doc on puisse retenter notre chance (en mettant la méthode retourner en boolean)
	public void retourner(int v_numero, PrintWriter out) {
		boolean isFound = false;
		
		List<IDocument> documents = mediatheque.getDocuments();
		
		for (IDocument doc : documents){
			if (doc.numero() == v_numero) {
				doc.retour();
				isFound = true;
				out.println("Merci ! Le document a bien ete retourne.\n");
				break;
			}
		}
		if(!isFound) {
			out.println("Le numero saisi ne correspond a aucun document recense dans notre mediatheque.\n");
		}

	}
	
	public boolean entryInt(String line) {
		try {
			Integer.parseInt(line);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
		
	}
	
	protected void finalize() throws Throwable {
		client.close();
	}

}
