package bri;


import java.io.*;
import java.net.*;
import java.util.Map;

import utilisateur.Utilisateur;


public class ServeurBRi implements Runnable {
	private ServerSocket listen_socket;
	private Map<Utilisateur, String> users;
	
	// Cree un serveur TCP - objet de la classe ServerSocket
	public ServeurBRi(int port, Map<Utilisateur, String> users) {
		try {
			listen_socket = new ServerSocket(port);
			this.users = users;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// Le serveur ecoute et accepte les connections.
	// pour chaque connection, il cree un ServiceInversion, 
	// qui va la traiter.
	public void run() {
		try {
			while(true) {
				switch (listen_socket.getLocalPort()) {
				case 3000:
					new ServiceBRiProg(listen_socket.accept(), users).start();
					break;
				case 6000:
					new ServiceBRiAmateur(listen_socket.accept(), users).start();
					break;
				}
				
				
			}
		}
		catch (IOException e) { 
			try {this.listen_socket.close();} catch (IOException e1) {}
			System.err.println("Pb sur le port d'écoute :"+e);
		}
	}

	 // restituer les ressources --> finalize
	protected void finalize() throws Throwable {
		try {this.listen_socket.close();} catch (IOException e1) {}
	}

	// lancement du serveur
	public void lancer() {
		(new Thread(this)).start();		
	}
}
