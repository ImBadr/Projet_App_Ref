package bri;


import java.io.*;
import java.net.*;
import java.util.Map;

import utilisateur.Utilisateur;


class ServiceBRiAmateur implements Runnable {
	
	private Socket client;
	private Map<Utilisateur,String> users;
	
	ServiceBRiAmateur(Socket socket, Map<Utilisateur,String> users) {
		client = socket;
		this.users = users;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
			
			
			out.println(ServiceRegistry.toStringue()+"##Tapez le num�ro de service d�sir� :");
			int choix = Integer.parseInt(in.readLine());
			
			// instancier le service num�ro "choix" en lui passant la socket "client"
			// invoquer run() pour cette instance ou la lancer dans un thread � part 
			
		}
		catch (IOException e) {
			//Fin du service
		}

		try {client.close();} catch (IOException e2) {}
	}
	
	protected void finalize() throws Throwable {
		 client.close(); 
	}

	// lancement du service
	public void start() {
		(new Thread(this)).start();		
	}

}
