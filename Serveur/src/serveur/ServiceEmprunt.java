package serveur;
import java.net.Socket;

public class ServiceEmprunt implements Runnable {
	private final Socket client;

	public ServiceEmprunt(Socket socket) {
		this.client = socket;
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}

	

}
