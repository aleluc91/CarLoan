package entity;

/**
 * Classe in cui viene definita l'entità Macchina.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */

public class Macchina {

	private String targa;
	private String fascia;
	private String modello;
	private String agenzia;
	private String noleggio;
	private String manutenzione;
	private long chilometraggio;

	public Macchina(String targa, String fascia, String modello,
			String agenzia, String noleggio, String manutenzione,
			long chilometraggio) {
		this.targa = targa;
		this.fascia = fascia;
		this.modello = modello;
		this.agenzia = agenzia;
		this.noleggio = noleggio;
		this.manutenzione = manutenzione;
		this.chilometraggio = chilometraggio;
	}

	public String getTarga() {
		return targa;
	}

	public String getFascia() {
		return fascia;
	}

	public String getModello() {
		return modello;
	}

	public String getAgenzia() {
		return agenzia;
	}

	public String getNoleggio() {
		return noleggio;
	}

	public String getManutenzione() {
		return manutenzione;
	}

	public long getChilometraggio() {
		return chilometraggio;
	}

}
