package serveur;
import java.net.Socket;

public class ServiceReservation implements Runnable {
	private final Socket client;

	public ServiceReservation(Socket socket) {
		this.client = socket;
	}

	public void run() {
		// TODO Auto-generated method stub

	}

}
