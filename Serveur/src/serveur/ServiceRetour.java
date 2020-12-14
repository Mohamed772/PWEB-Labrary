package serveur;
import java.net.Socket;

public class ServiceRetour implements Runnable {
	private final Socket client;

	public ServiceRetour(Socket socket) {
		this.client = socket;
	}

	public void run() {
		// TODO Auto-generated method stub

	}

}
