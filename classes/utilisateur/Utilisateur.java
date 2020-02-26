package utilisateur;

public class Utilisateur {
	private String login;
	private String password;
	
	
	public Utilisateur(String l, String p) {
		this.login = l;
		this.password = p;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public boolean controlIdentity(String login, String password) {
		return (this.login.equals(login)) && (this.password.equals(password));
	}
	

}
