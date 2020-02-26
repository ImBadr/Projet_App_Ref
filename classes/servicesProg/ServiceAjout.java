package servicesProg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bri.ServiceRegistry;
public class ServiceAjout implements Runnable {
	
	private Socket client;
	private String url;
	
	public ServiceAjout(Socket socket, String url) {
		client = socket;
		this.url = url;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
			
			out.println("What is the name of your class that you want to load ?");
			
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
