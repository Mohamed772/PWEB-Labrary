package serveur;

import java.io.IOException;

class ApplicationServeur {
	private final static int PORTRESERVATION = 3000;
	private final static int PORTEMPRUNT = 4000;
	private final static int PORTRETOUR = 5000;

	public static void main(String[] args) {
		try {
			new Thread(new Serveur(PORTRESERVATION)).start();
			new Thread(new Serveur(PORTEMPRUNT)).start();
			new Thread(new Serveur(PORTRETOUR)).start();
		} catch (IOException e) {
				System.err.println(e);			
		}
	}
}

