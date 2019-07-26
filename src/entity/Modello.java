package entity;

/**
 * Classe in cui viene definita l'entità Modello.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */

public class Modello {

	private int id;
	private String modello;
	private String fascia;

	public Modello(int id, String modello, String fascia) {
		this.id = id;
		this.modello = modello;
		this.fascia = fascia;
	}

	public Modello(String modello, String fascia) {
		this.modello = modello;
		this.fascia = fascia;
	}

	public int getId() {
		return id;
	}

	public String getModello() {
		return modello;
	}

	public String getFascia() {
		return fascia;
	}

	@Override
	public String toString() {
		return getModello() + " - " + getFascia();
	}

}
