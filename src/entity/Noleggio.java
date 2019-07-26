package entity;

/**
 * Classe in cui viene definita l'entità Noleggio.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */

public class Noleggio {

	private String contratto;
	private int base;
	private String chilometraggio;
	private String fascia;
	private String modello;
	private String macchina;

	public Noleggio(String contratto, int base, String chilometraggio,
			String fascia, String modello, String macchina) {
		super();
		this.contratto = contratto;
		this.base = base;
		this.chilometraggio = chilometraggio;
		this.fascia = fascia;
		this.modello = modello;
		this.macchina = macchina;
	}

	public String getContratto() {
		return contratto;
	}

	public int getBase() {
		return base;
	}

	public String getChilometraggio() {
		return chilometraggio;
	}

	public String getFascia() {
		return fascia;
	}

	public String getModello() {
		return modello;
	}

	public String getMacchina() {
		return macchina;
	}

}
