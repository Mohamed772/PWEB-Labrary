package applications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

class ApplicationClient {
	private static int PORT;
	private final static String HOST = "localhost";

	private final static Scanner clavier = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		while (true) {

			boolean isOver = false;
			boolean exitProg = false;

			System.out.println("             ____________________________________________________\n"
					+ "            /                                                    \\\n"
					+ "           |    _____________________________________________     |\n"
					+ "           |   |                                             |    |\n"
					+ "           |   |                                             |    |\n"
					+ "           |   |             B I E N V E N U E               |    |\n"
					+ "           |   |                                             |    |\n"
					+ "           |   |                                             |    |\n"
					+ "           |   |         sur l'application de votre          |    |\n"
					+ "           |   |                                             |    |\n"
					+ "           |   |                Mediatheque                  |    |\n"
					+ "           |   |                                             |    |\n"
					+ "           |   |_____________________________________________|    |\n"
					+ "           |                                                      |\n"
					+ "            \\____________________________________________________/\n"
					+ "                   \\_______________________________________/\n"
					+ "                _______________________________________________\n"
					+ "             _-'    .-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.  --- `-_\n"
					+ "          _-'.-.-. .---.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.--.  .-.-.`-_\n"
					+ "       _-'.-.-.-. .---.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-`__`. .-.-.-.`-_\n"
					+ "     _-'.-.-.-.-. .-----.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-----. .-.-.-.-.`-_\n"
					+ " _-'.-.-.-.-.-. .---.-. .-----------------------------. .-.---. .---.-.-.-.`-_\n"
					+ ":-----------------------------------------------------------------------------:\n"
					+ "`---._.-----------------------------------------------------------------._.---'\n\n"
					+ "     _______________________________________________________________________\n"
					+ "    |                                                                       |\n"
					+ "    |   Veuillez saisir le service desire [Réservation]/[Emprunt]/[Retour]  |\n"
					+ "    |_______________________________________________________________________|\n\n"
					+ "                  Saisir [Quitter] pour fermer l'application");

			while (!isOver) {

				String line = clavier.nextLine().toUpperCase().replace("É", "E");

				if (line.equalsIgnoreCase("QUITTER")) {
					System.out.println("L'application va s'arreter. Merci d'avoir utilise nos services.");
					exitProg = true;
					break;
				} else {
					switch (line) {
					case "RESERVATION":
						System.out.println("ok résa");
						PORT = 3000;
						startServReserv();
						isOver = true;
						break;
					case "EMPRUNT":
						System.out.println("ok emprunt");
						PORT = 4000;
						startServEmprunt();
						isOver = true;
						break;
					case "RETOUR":
						System.out.println("ok retour");
						PORT = 5000;
						startServRetour();
						isOver = true;
						break;
					default:
						System.out.println("Vous n'avez pas saisi une commande valide");
						break;
					}
				}
			}
			if(exitProg)
				break;
		}
		clavier.close();
	}

	public static void startServRetour() throws UnknownHostException, IOException {
		Socket socket = null;

		socket = new Socket(HOST, PORT);

		// Création des streams pour lire et ecrire du texte dans la socket
		BufferedReader sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter sout = new PrintWriter(socket.getOutputStream(), true);

		System.out.println("Connection au serveur " + socket.getInetAddress() + " : " + socket.getPort());

		while (true) {
			String testString = sin.readLine();
			System.out.println(testString);
			if (testString.startsWith("Votre") || testString.startsWith("Veuillez")
					|| testString.startsWith("Souhaitez") || testString.startsWith("Quel")) {
				String line = clavier.nextLine();
				sout.println(line);
			} else if (testString.startsWith("Vous")) {
				while (sin.ready()) {
					System.out.println(sin.readLine());
				}
				break;
			}
		}

		sin.close();
		sout.close();
		socket.close();
		// Refermer dans tous les cas la socket
		try {
			if (socket != null)
				socket.close();
		} catch (IOException e2) {
			;
		}
	}

	public static void startServEmprunt() throws UnknownHostException, IOException {
		Socket socket = null;

		socket = new Socket(HOST, PORT);

		// Création des streams pour lire et ecrire du texte dans la socket
		BufferedReader sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter sout = new PrintWriter(socket.getOutputStream(), true);

		System.out.println("Connection au serveur " + socket.getInetAddress() + " : " + socket.getPort());

		while (true) {
			String testString = sin.readLine();
			System.out.println(testString);
			if (testString.startsWith("Votre") || testString.startsWith("Veuillez")
					|| testString.startsWith("Souhaitez") || testString.startsWith("Saisir")) {
				String line = clavier.nextLine();
				sout.println(line);
			} else if (testString.startsWith("Vous")) {
				while (sin.ready()) {
					System.out.println(sin.readLine());
				}
				break;
			}
		}

		sin.close();
		sout.close();
		socket.close();
		// Refermer dans tous les cas la socket
		try {
			if (socket != null)
				socket.close();
		} catch (IOException e2) {
			;
		}
	}

	public static void startServReserv() throws UnknownHostException, IOException {
		Socket socket = null;

		socket = new Socket(HOST, PORT);

		// Création des streams pour lire et ecrire du texte dans la socket
		BufferedReader sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter sout = new PrintWriter(socket.getOutputStream(), true);

		System.out.println("Connection au serveur " + socket.getInetAddress() + " : " + socket.getPort());

		while (true) {
			String testString = sin.readLine();
			System.out.println(testString);
			if (testString.startsWith("Votre") || testString.startsWith("Veuillez")
					|| testString.startsWith("Souhaitez") || testString.startsWith("Saisir")) {
				String line = clavier.nextLine();
				sout.println(line);
			} else if (testString.startsWith("Vous")) {
				while (sin.ready()) {
					System.out.println(sin.readLine());
				}
				break;
			}
		}

		sin.close();
		sout.close();
		socket.close();
		// Refermer dans tous les cas la socket
		try {
			if (socket != null)
				socket.close();
		} catch (IOException e2) {
			;
		}
	}

}
