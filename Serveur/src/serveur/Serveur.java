package serveur;

import java.io.*;
import java.net.*;

public class Serveur implements Runnable {
	private ServerSocket listen_socket;
	
	// Cree un serveur TCP - objet de la classe ServerSocket
	public Serveur(int port) throws IOException {
		listen_socket = new ServerSocket(port);
	}

	// Le serveur ecoute et accepte les connexions.
	// pour chaque connexion, il cree un ServiceInversion, 
	// qui va la traiter, et le lance
	public void run() {
		try {
			System.err.println("Lancement du serveur au port "+this.listen_socket.getLocalPort());
			while(true) {
				switch ((int)(this.listen_socket.getLocalPort())) {
					case 3000:
						//System.out.println("3000 case");
						new Thread(new ServiceReservation(listen_socket.accept())).start();
						
						break;
					case 4000:
						//System.out.println("4000 case");
						new Thread(new ServiceEmprunt(listen_socket.accept())).start();
						
						break;
					case 5000:
						//System.out.println("5000 case");
						new Thread(new ServiceRetour(listen_socket.accept())).start();
						
						break;
				}
					}
		}
		catch (IOException e) { 
			try {this.listen_socket.close();} catch (IOException e1) {}
			System.err.println("Arret du serveur au port "+this.listen_socket.getLocalPort());
		}
	}

	 // restituer les ressources --> finalize
	protected void finalize() throws Throwable {
		try {this.listen_socket.close();} catch (IOException e1) {}
	}
}
