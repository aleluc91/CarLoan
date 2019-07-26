package presentation.gui.modelli_tabella;

import entity.Macchina;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe in cui viene definito un modello , che in questo caso rispecchia
 * l'entità Macchina , utile nella definizione di tutti i cotrolli ( Tabelle ,
 * Menu di selezione , ecc) che hanno bisogno di visualizzare a schermo un dato
 * di tipo Macchina.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */
public class ModelloMacchina extends ModelloTabella {

	private StringProperty targa;
	private StringProperty fascia;
	private StringProperty modello;
	private StringProperty agenzia;
	private StringProperty noleggio;
	private StringProperty manutenzione;
	private LongProperty chilometraggio;

	/**
	 * Metodo costruttore che riceve un parametro di tipo Macchina , e in base
	 * ad i valori contenuti in esso , inizializza i campi dell'istanza creata.
	 * 
	 * @param agenzia
	 *            Riceve un parametro di tipo Macchina , utilizzato per
	 *            inizializzare i campi dell'istanza creata.
	 */
	public ModelloMacchina(Macchina macchina) {
		this.targa = new SimpleStringProperty(macchina.getTarga());
		this.fascia = new SimpleStringProperty(macchina.getFascia());
		this.modello = new SimpleStringProperty(macchina.getModello());
		this.agenzia = new SimpleStringProperty(macchina.getAgenzia());
		this.noleggio = new SimpleStringProperty(macchina.getNoleggio());
		this.manutenzione = new SimpleStringProperty(macchina.getManutenzione());
		this.chilometraggio = new SimpleLongProperty(
				macchina.getChilometraggio());
	}

	/**
	 * Questo metodo è utile per le classi che si occupano del caricamento dei
	 * dati di tipo Contratto all'interno di uno dei controlli visualizzati a
	 * schermo.
	 * 
	 * @return Restituisce la targa della macchina.
	 */
	public String getValore() {
		return targa.get();
	}

	public StringProperty propertyTarga() {
		return targa;
	}

	public StringProperty propertyFascia() {
		return fascia;
	}

	public StringProperty propertyModello() {
		return modello;
	}

	public StringProperty propertyAgenzia() {
		return agenzia;
	}

	public StringProperty propertyNoleggio() {
		return noleggio;
	}

	public StringProperty propertyManutenzione() {
		return manutenzione;
	}

	public LongProperty propertyChilometraggio() {
		return chilometraggio;
	}

	/**
	 * 
	 * @return Restituisce la targa dell macchina.
	 */
	public String getTarga() {
		return targa.get();
	}

	/**
	 * 
	 * @return Restituisce l'identificativo della fascia a cui appartiene la
	 *         macchina.
	 */
	public String getFascia() {
		return fascia.get();
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
	 * @return Restituisce il codice dell'agenzia in cui si trovo attualmente la
	 *         macchina.
	 */
	public String getAgenzia() {
		return agenzia.get();
	}

	/**
	 * 
	 * @return Restituisce un valore di tipo Stringa (Si o No) che indica se la
	 *         macchina al momento è noleggiata , oppure no.
	 */
	public String getNoleggio() {
		return noleggio.get();
	}

	/**
	 * 
	 * @return Restituisce un valore di tipo Stringa (Oridnaria , Straordina1ria
	 *         o Nessuna) che indica se la macchina in questo momento è in
	 *         manutenzione , oppure no.
	 */
	public String getManutenzione() {
		return manutenzione.get();
	}

	/**
	 * 
	 * @return Restituisce il chilometraggio attuale della macchina.
	 */
	public long getChilometraggio() {
		return chilometraggio.get();
	}

	@Override
	public String toString() {
		return "targa : " + getTarga() + " | fascia : " + getFascia()
				+ " | modello : " + getModello();
	}

}
