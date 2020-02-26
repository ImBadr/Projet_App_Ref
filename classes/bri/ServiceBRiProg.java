package bri;

import java.io.*;
import java.net.*;
import java.util.Map;

import servicesProg.ServiceAjout;
import utilisateur.Utilisateur;

class ServiceBRiProg implements Runnable {

	private Socket client;
	private Map<Utilisateur, String> users;

	private static final int ADD = 1;
	private static final int UPDATE = 2;
	private static final int CHANGE_FTP = 3;
	private static final int STOP_AND_GO = 4;
	private static final int UNINSTALL = 5;

	ServiceBRiProg(Socket socket, Map<Utilisateur, String> users) {
		client = socket;
		this.users = users;
	}

	private String verifyIdentity(String login, String password) {
		String url = "";
		for (Map.Entry<Utilisateur, String> entry : users.entrySet()) {
			if (url.isEmpty())
				if(entry.getKey().controlIdentity(login, password))
					url = entry.getValue();
		}
		return url;
	}
	

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);

			out.println("Enter your Login : ");
			String login = in.readLine();
			out.println("Enter your Password : ");
			String password = in.readLine();
			String url = verifyIdentity(login, password);
			if (!url.isEmpty()) {
				String message = "Welcome on the Programming Server !#n";
				message += "#1 Add a new Service #n";
				message += "#2 Update an existing Service #n";
				message += "#3 Change FTP adress #n";
				out.println(message);
				boolean badFormat = false;
				while (badFormat)
					try {
						switch (Integer.parseInt(in.readLine())) {
						case ADD:
							new Thread(new ServiceAjout(client, url)).start();
							break;
						case UPDATE:

							break;
						case CHANGE_FTP:

							break;
						case STOP_AND_GO:

							break;
						case UNINSTALL:

							break;
						default:
							badFormat = false;
							out.println("You are wrong it is written, are you dumb, do you know how to read?#n Please try again");
							
							break;
						}

					} catch (Exception e) {
						badFormat = false;
						out.println("We are asking for a INTEGER, please respect the rules !#n");
					}
			} else
				out.println("You are nobody, get out of the server !");

			out.println(ServiceRegistry.toStringue() + "##Tapez le numéro de service désiré :");
			int choix = Integer.parseInt(in.readLine());

			// instancier le service numéro "choix" en lui passant la socket "client"
			// invoquer run() pour cette instance ou la lancer dans un thread à part

		} catch (IOException e) {
			// Fin du service
		}

		try {
			client.close();
		} catch (IOException e2) {
		}
	}

	protected void finalize() throws Throwable {
		client.close();
	}

	// lancement du service
	public void start() {
		(new Thread(this)).start();
	}

}
