package bri;

import java.util.List;
import java.util.Vector;

import verification.VerificationServiceBRI;

public class ServiceRegistry {
	// cette classe est un registre de services
	// partag�e en concurrence par les clients et les "ajouteurs" de services,
	// un Vector pour cette gestion est pratique

	static {
		servicesClasses = new Vector();
	}
	private static List<Class<?>> servicesClasses;

// ajoute une classe de service apr�s contr�le de la norme BLTi
	public static void addService(Class<?> classeName) throws Exception {
		// v�rifier la conformit� par introspection
		// si non conforme --> exception avec message clair
		// si conforme, ajout au vector
		VerificationServiceBRI.verificationBRI(classeName);
		servicesClasses.add(classeName);
	}
	
// renvoie la classe de service (numService -1)	
	public static Class<?> getServiceClass(int numService) {
		return servicesClasses.get(numService - 1);
	}
	
// liste les activit�s pr�sentes
	public static String toStringue() {
		String result = "Activit�s pr�sentes :##";
		int cpt = 1;
		for(Class<?> c : servicesClasses) {
			result += "#" + cpt++ + " " + c.getName();
		}
		return result;
	}

}
