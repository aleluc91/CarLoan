package entity;

/**
 * Classe in cui viene definita l'entità fascia.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */

public class Fascia {

	private String id;
	private String requisiti;
	private double costo;

	public Fascia(String tipo, String descrizione, double costo) {
		this.id = tipo;
		this.requisiti = descrizione;
		this.costo = costo;
	}

	public String getId() {
		return id;
	}

	public String getRequisiti() {
		return requisiti;
	}

	public double getCosto() {
		return costo;
	}

	@Override
	public String toString() {
		return " - " + id + " - " + requisiti + " - " + costo + " Euro";
	}

}
