package presentation.gui.modelli_tabella;

import entity.Tariffa;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe in cui viene definito un modello , che in questo caso rispecchia
 * l'entità Tariffa , utile nella definizione di tutti i cotrolli ( Tabelle ,
 * Menu di selezione , ecc) che hanno bisogno di visualizzare a schermo un dato
 * di tipo Tariffa.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */
public class ModelloTariffa extends ModelloTabella {

	private IntegerProperty id;
	private StringProperty tipo;
	private LongProperty chilometri;
	private IntegerProperty giorni;
	private DoubleProperty tariffa;

	/**
	 * Metodo costruttore che riceve un parametro di tipo Tariffa , e in base ad
	 * i valori contenuti in esso , inizializza i campi dell'istanza creata.
	 * 
	 * @param agenzia
	 *            Riceve un parametro di tipo Tariffa , utilizzato per
	 *            inizializzare i campi dell'istanza creata.
	 */
	public ModelloTariffa(Tariffa tariffa) {
		super();
		this.id = new SimpleIntegerProperty(tariffa.getId());
		this.tipo = new SimpleStringProperty(tariffa.getTipo());
		this.chilometri = new SimpleLongProperty(tariffa.getChilometri());
		this.giorni = new SimpleIntegerProperty(tariffa.getGiorni());
		this.tariffa = new SimpleDoubleProperty(tariffa.getTariffa());
	}

	/**
	 * Questo metodo è utile per le classi che si occupano del caricamento dei
	 * dati di tipo Contratto all'interno di uno dei controlli visualizzati a
	 * schermo.
	 * 
	 * @return Restituisce la targa della macchina.
	 */
	public String getValore() {
		return String.valueOf(id.get());
	}

	public IntegerProperty getIdProperty() {
		return id;
	}

	public StringProperty getTipoProperty() {
		return tipo;
	}

	public LongProperty getChilometriProperty() {
		return chilometri;
	}

	public IntegerProperty getGiorniProperty() {
		return giorni;
	}

	public DoubleProperty getTariffaProperty() {
		return tariffa;
	}

	/**
	 * 
	 * @return Restituisce l'identificativo della tariffa.
	 */
	public int getId() {
		return id.get();
	}

	/**
	 * 
	 * @return Restituisce il tipo di tariffa (Giornaliera , Settimanale , ecc).
	 */
	public String getTipo() {
		return tipo.get();
	}

	/**
	 * 
	 * @return Restituisce i chilometri (Giornalieri , Settimanali , ecc)
	 *         consentiti dalla tariffa.
	 */
	public long getChilometri() {
		return chilometri.get();
	}

	/**
	 * 
	 * @return Restituisce i giorni di durata della tariffa.
	 */
	public int getGiorni() {
		return giorni.get();
	}

	/**
	 * Restituisce il costo della tariffa , solamente nel caso sia stata scelta
	 * una tariffa a chilometraggio illimitato.
	 * 
	 * @return Restituisce il costo della tariffa.
	 */
	public double getTariffa() {
		return tariffa.get();
	}

	@Override
	public String toString() {
		return "tipo : " + getTipo() + " | chilometri : " + getChilometri()
				+ " | giorni : " + getGiorni() + " | tariffa : " + getTariffa();
	}

}
