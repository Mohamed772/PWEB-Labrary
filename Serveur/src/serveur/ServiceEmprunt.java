package serveur;

import java.io.*;
import java.net.Socket;
import java.util.List;

import documents.IDocument;
import exceptions.EmpruntException;
import mediatheque.Mediatheque;
import utilisateurs.Abonne;

public class ServiceEmprunt implements Runnable {
	private final Socket client;
	private final Mediatheque mediatheque = Mediatheque.getInstance();
	private Abonne ab;
	private boolean isBanned = false;

	public ServiceEmprunt(Socket socket) {
		this.client = socket;
		ab = null;
	}

	public void run() {

		try {

			boolean empruntAgain = true;
			boolean boolCheckAbo = false;

			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			out.println("Bienvenue dans le service d'emprunt de documents de la mediatheque");

			out.println("Saisir le numero d'abonne :\n");

			String line;

			while (!boolCheckAbo) {
				line = in.readLine();

				while (!entryInt(line)) {
					out.println("Votre saisie ne correspond pas a un nombre entier. Veuillez reessayer :");
					line = in.readLine();
				}

				boolCheckAbo = checkAbo(Integer.parseInt(line), out);

			}

			if (!isBanned) {

				while (empruntAgain) {

					out.println("Actuellement connecte sur le profil abonne de " + ab.toString()
							+ "\n\nVoici la liste de tous les DVD appartenant a la mediatheque :\n");

					showDocuments(out);

					out.println("\nVeuillez saisir le numero du DVD que vous voulez emprunter :\n");

					line = in.readLine();

					while (!entryInt(line)) {
						out.println("Votre saisie ne correspond pas a un nombre entier. Veuillez reessayer :");
						line = in.readLine();
					}

					emprunter(Integer.parseInt(line), out);

					out.println("Souhaitez-vous emprunter un autre document ?");

					while (true) {
						line = in.readLine();

						if (line.equalsIgnoreCase("Non")) {
							empruntAgain = false;
							out.println(
									"Vous allez être redirigé vers le menu principal.\n\n.................................\n");
							break;
						} else if (line.equalsIgnoreCase("Oui")) {
							break;
						} else {
							out.println("Veuillez uniquement répondre par [Oui]/[Non] :");
						}
					}
				}
			}

			in.close();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void emprunter(int v_numeroDoc, PrintWriter out) {
		boolean isFound = false;

		List<IDocument> documents = mediatheque.getDocuments();

		for (IDocument doc : documents) {
			if (doc.numero() == v_numeroDoc) {

				try {
					doc.empruntPar(ab);
					isFound = true;
				} catch (EmpruntException e) {
					out.println(e.getMessage());
				}
			}
		}
		if (!isFound) {
			out.println("Le numero saisi ne correspond a aucun document recense dans notre mediatheque.\n");
		}

	}

	public boolean checkAbo(int v_numeroAbo, PrintWriter out) {

		List<Abonne> abonnes = mediatheque.getAbonnes();
		for (Abonne abo : abonnes) {
			if (abo.getNumero() == v_numeroAbo) {
				if (abo.getDateban() == null) {
					ab = abo;

				} else {
					ab = abo;
					out.println("Vous etes bannis jusqu'au " + ab.getDateban().plusMonths(ab.getBannissement()).getDayOfWeek()
							+ " " + ab.getDateban().plusMonths(ab.getBannissement()).getDayOfMonth() + " "
							+ ab.getDateban().plusMonths(ab.getBannissement()).getMonth() + " "
							+ ab.getDateban().plusMonths(ab.getBannissement()).getYear()
							+ ". Vous ne pouvez donc pas emprunter de document. \nRetour au menu principal.\n");
					isBanned = true;
				}
				return true;
			}
		}
		out.println("Votre saisie ne correspond a aucun abonne inscrit dans notre mediatheque. Veuillez reesayer :\n");
		return false;

	}

	// TODO : effacer le bannissement dans la classe DVD

	public void showDocuments(PrintWriter out) {
		List<IDocument> documents = mediatheque.getDocuments();
		for (IDocument doc : documents) {
			out.println(doc.toString());
		}
	}

	public boolean entryInt(String line) {
		try {
			Integer.parseInt(line);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}

	}

	protected void finalize() throws Throwable {
		client.close();
	}
}
