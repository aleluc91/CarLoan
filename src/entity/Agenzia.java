package entity;

/**
 * Classe che si occupa di definire l'entità Agenzia.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */

public class Agenzia {

	private String codice;
	private String nome;
	private String indirizzo;
	private String citta;
	private String cap;

	/**
	 * Metodo costruttore in cui vengono assegnati all'istanza dell'oggetto di
	 * tipo agenzia i valori ricevuti come parametri.
	 * 
	 * @param numero
	 *            Numero identificativo dell'agenzia.
	 * @param nome
	 *            Nome dell'agenzia
	 * @param indirizzo
	 *            Indirizzo dell'agenzia
	 * @param citta
	 *            Città in cui risiede l'agenzia
	 * @param cap
	 *            CAP della città in cui risiede l'agenzia
	 */
	public Agenzia(String numero, String nome, String indirizzo, String citta,
			String cap) {
		this.codice = numero;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.cap = cap;
		this.citta = citta;
	}

	public Agenzia(String numero, String nome, String indirizzo, String citta,
			String cap, String codice_modifica) {
		this.codice = numero;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.cap = cap;
		this.citta = citta;
	}

	/**
	 * 
	 * @return Restituisce il codice dell'agenzia.
	 */
	public String getValore() {
		return codice;
	}

	/**
	 * 
	 * @return Restituisce il codice dell'agenzia.
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * 
	 * @return Restituisce il nome dell'agenzia.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @return Restituisce l'indirizzo dell'agenzia.
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * 
	 * @return Restituisce la città in cui risiede l'agenzia.
	 */
	public String getCitta() {
		return citta;
	}

	/**
	 * 
	 * @return Restituisce il CAP della città in cui risiede l'agenzia.
	 */
	public String getCap() {
		return cap;
	}

	@Override
	public String toString() {
		return codice + " - " + nome + " - " + indirizzo + " - " + citta
				+ " - " + cap;
	}

}
