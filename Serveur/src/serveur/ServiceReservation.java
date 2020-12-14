package serveur;
import java.net.Socket;

import mediatheque.Mediatheque;

public class ServiceReservation implements Runnable {
	private final Socket client;
	private final Mediatheque mediatheque = Mediatheque.getInstance();

	public ServiceReservation(Socket socket) {
		this.client = socket;
	}

	public void run() {
		// TODO Auto-generated method stub

	}

}
