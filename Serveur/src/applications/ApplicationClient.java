package applications;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ApplicationClient {
		private final static int PORT = 4000;
		private final static String HOST = "localhost"; 
	
	public static void main(String[] args) throws IOException {
		Socket socket = null;		
			// Cree une socket pour communiquer avec le service se trouvant sur la
			// machine host au port PORT
			socket = new Socket(HOST, PORT);
			// Cree les streams pour lire et ecrire du texte dans cette socket
			BufferedReader sin = new BufferedReader (new InputStreamReader(socket.getInputStream ( )));
			PrintWriter sout = new PrintWriter (socket.getOutputStream ( ), true);
			// Cree le stream pour lire du texte a partir du clavier 
			// (on pourrait aussi utiliser Scanner)
			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));			
			// Informe l'utilisateur de la connection
			System.out.println("Connecté au serveur " + socket.getInetAddress() + ":"+ socket.getPort());
			
			String line;
			System.out.println("Bienvenue dans le service de réservation de documents de la mediatheque");
			
			System.out.print(sin.readLine());
			line = clavier.readLine();
			
			socket.close();
		// Refermer dans tous les cas la socket
		try { if (socket != null) socket.close(); } 
		catch (IOException e2) { ; }		
	}
}

