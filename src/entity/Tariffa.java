package entity;

/**
 * Classe che si occupa di definire l'entità Tariffa.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */

public class Tariffa {

	private int id;
	private String tipo;
	private long chilometri;
	private int giorni;
	private double tariffa;

	public Tariffa(int id, String tipo, long chilometri, int giorni,
			double tariffa) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.chilometri = chilometri;
		this.giorni = giorni;
		this.tariffa = tariffa;
	}

	public int getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	public long getChilometri() {
		return chilometri;
	}

	public int getGiorni() {
		return giorni;
	}

	public double getTariffa() {
		return tariffa;
	}

}
