package entity;

/**
 * Classe in cui viene definta l'entità Sessione. In questa classe verranno
 * memorizzati i valori necessari a riconoscere chi è connesso al momento al
 * sistema , in modo da poter limitare le funzioni del sistema , in base ai
 * privilegi dell'utente attuale.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */

public class Sessione {

	private static Autenticazione autenticazione;

	public static void setAutenticazione(Autenticazione autenticazione) {
		Sessione.autenticazione = autenticazione;
	}

	public static Autenticazione getAutenticazione() {
		return Sessione.autenticazione;
	}

}
