package entity;

/**
 * Classe in cui viene definita l'entita Autenticazione
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */

public class Autenticazione {

	private String username;
	private String password;
	private String agenzia;
	private String privilegi;

	public Autenticazione(String username, String password, String agenzia,
			String privilegi) {
		this.username = username;
		this.password = password;
		this.agenzia = agenzia;
		this.privilegi = privilegi;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public String getAgenzia() {
		return this.agenzia;
	}

	public String getPrivilegi() {
		return this.privilegi;
	}

}
