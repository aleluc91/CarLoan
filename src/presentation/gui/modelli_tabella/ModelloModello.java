package presentation.gui.modelli_tabella;

import entity.Modello;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe in cui viene definito un modello , che in questo caso rispecchia
 * l'entità Modello (modello della macchina) , utile nella definizione di tutti
 * i cotrolli ( Tabelle , Menu di selezione , ecc) che hanno bisogno di
 * visualizzare a schermo un dato di tipo Modello.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */
public class ModelloModello extends ModelloTabella {

	private StringProperty modello;
	private StringProperty fascia;

	/**
	 * Metodo costruttore che riceve un parametro di tipo Modello , e in base ad
	 * i valori contenuti in esso , inizializza i campi dell'istanza creata.
	 * 
	 * @param agenzia
	 *            Riceve un parametro di tipo Modello , utilizzato per
	 *            inizializzare i campi dell'istanza creata.
	 */
	public ModelloModello(Modello modello) {
		this.modello = new SimpleStringProperty(modello.getModello());
		this.fascia = new SimpleStringProperty(modello.getFascia());
	}

	/**
	 * Questo metodo è utile per le classi che si occupano del caricamento dei
	 * dati di tipo Contratto all'interno di uno dei controlli visualizzati a
	 * schermo.
	 * 
	 * @return Restituisce la targa della macchina.
	 */
	public String getValore() {
		return modello.get();
	}

	public StringProperty propertyModello() {
		return modello;
	}

	public StringProperty propertyFascia() {
		return fascia;
	}

	/**
	 * 
	 * @return Restituisce il modello della macchina.
	 */
	public String getModello() {
		return modello.get();
	}

	/**
	 * 
	 * @return Restituisce la fascia a cui appartiene il modello.
	 */
	public String getFascia() {
		return fascia.get();
	}

	@Override
	public String toString() {
		return "Modello : " + getModello();
	}

}
