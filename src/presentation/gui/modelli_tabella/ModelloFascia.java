package presentation.gui.modelli_tabella;

import entity.Fascia;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe in cui viene definito un modello , che in questo caso rispecchia
 * l'entità Fascia , utile nella definizione di tutti i cotrolli ( Tabelle ,
 * Menu di selezione , ecc) che hanno bisogno di visualizzare a schermo un dato
 * di tipo Fascia.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */
public class ModelloFascia extends ModelloTabella {

	private StringProperty id;
	private StringProperty requisiti;
	private DoubleProperty costo;

	/**
	 * Metodo costruttore che riceve un parametro di tipo Fascia , e in base ad
	 * i valori contenuti in esso , inizializza i campi dell'istanza creata.
	 * 
	 * @param agenzia
	 *            Riceve un parametro di tipo Fascia , utilizzato per
	 *            inizializzare i campi dell'istanza creata.
	 */
	public ModelloFascia(Fascia fascia) {
		this.id = new SimpleStringProperty(fascia.getId());
		this.requisiti = new SimpleStringProperty(fascia.getRequisiti());
		this.costo = new SimpleDoubleProperty(fascia.getCosto());
	}

	/**
	 * Questo metodo è utile per le classi che si occupano del caricamento dei
	 * dati di tipo Contratto all'interno di uno dei controlli visualizzati a
	 * schermo.
	 * 
	 * @return Restituisce l'identifictivo della fascia.
	 */
	public String getValore() {
		return id.get();
	}

	public StringProperty propertyId() {
		return id;
	}

	public StringProperty propertyRequisiti() {
		return requisiti;
	}

	public DoubleProperty propertyCosto() {
		return costo;
	}

	/**
	 * 
	 * @return Restituisce l'identifictivo della fascia.
	 */
	public String getId() {
		return id.get();
	}

	/**
	 * 
	 * @return Restituisce i requisiti necessari per la fascia.
	 */
	public String getRequisiti() {
		return requisiti.get();
	}

	/**
	 * 
	 * @return Restituisce il costo della fascia per chilometro.
	 */
	public double getCosto() {
		return costo.get();
	}

	@Override
	public String toString() {
		return "Fascia = " + getId() + " | Requisiti = " + getRequisiti()
				+ " | Costo x Km = " + getCosto() + " Euro";
	}

}
