package appli;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import bri.ServeurBRi;
import bri.ServiceRegistry;
import utilisateur.Utilisateur;

public class BRiLaunch {
	private final static int PORT_AMA = 6000;
	private final static int PORT_PROG = 3000;
	
	public static void main(String[] args) throws MalformedURLException {
		@SuppressWarnings("resource")
		Scanner clavier = new Scanner(System.in);
		
		// URLClassLoader sur ftp
		URLClassLoader urlcl =  new URLClassLoader(new URL[] 
	    		  {new URL("ftp://loclahost:2121/classes")});
		
		System.out.println("Bienvenue dans votre gestionnaire dynamique d'activité BRi");
		System.out.println("Pour ajouter une activité, celle-ci doit être présente sur votre serveur ftp");
		System.out.println("A tout instant, en tapant le nom de la classe, vous pouvez l'intégrer");
		System.out.println("Les clients se connectent au serveur 3000 pour lancer une activité");
		
		Map<Utilisateur, String> programmeurs = new HashMap<>();
		programmeurs.put(new Utilisateur("badr","tadjer"), "ftp://localhost:2121/Badr");
		programmeurs.put(new Utilisateur("albe","cusin"), "ftp://localhost:2121/Albe");
		
		Map<Utilisateur, String> amateurs = new HashMap<>();
		amateurs.put(new Utilisateur("brette","jfb"), "1");
		amateurs.put(new Utilisateur("rossit","jr"), "2");
		
		new Thread(new ServeurBRi(PORT_AMA, amateurs)).start();
		new Thread(new ServeurBRi(PORT_PROG, programmeurs)).start();
		
		while (true){
				try {
					String classeName = clavier.next();
					// charger la classe et la déclarer au ServiceRegistry
					Class<?> classe = urlcl.loadClass(classeName);
					ServiceRegistry.addService(classe);
				} catch (Exception e) {
					System.out.println(e);
				}
			}		
	}
}
