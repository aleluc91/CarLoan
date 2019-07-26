package presentation.gui.modelli_tabella;

import entity.Agenzia;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe in cui viene definito un modello , che in questo caso rispecchia
 * l'entità Agenzia , utile nella definizione di tutti i cotrolli ( Tabelle ,
 * Menu di selezione , ecc) che hanno bisogno di visualizzare a schermo un dato
 * di tipo Agenzia.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */
public class ModelloAgenzia extends ModelloTabella {

	private StringProperty codice;
	private StringProperty nome;
	private StringProperty indirizzo;
	private StringProperty citta;
	private StringProperty cap;

	/**
	 * Metodo costruttore che riceve un parametro di tipo Agenzia , e in base ad
	 * i valori contenuti in esso , inizializza i campi dell'istanza creata.
	 * 
	 * @param agenzia
	 *            Riceve un parametro di tipo Agenzia , utilizzato per
	 *            inizializzare i campi dell'istanza creata.
	 */
	public ModelloAgenzia(Agenzia agenzia) {
		this.codice = new SimpleStringProperty(agenzia.getCodice());
		this.nome = new SimpleStringProperty(agenzia.getNome());
		this.indirizzo = new SimpleStringProperty(agenzia.getIndirizzo());
		this.citta = new SimpleStringProperty(agenzia.getCitta());
		this.cap = new SimpleStringProperty(agenzia.getCap());
	}

	/**
	 * Questo metodo è utile per le classi che si occupano del caricamento dei
	 * dati di tipo Agenzia all'interno di uno dei controlli visualizzati a
	 * schermo.
	 * 
	 * @return Restituisce il codice dell'agenzia.
	 */
	public String getValore() {
		return codice.get();
	}

	/**
	 * 
	 * @return Restituisce il codice dell'agenzia.
	 */
	public String getCodice() {
		return codice.get();
	}

	public StringProperty propertyCodice() {
		return codice;
	}

	/**
	 * 
	 * @return Restituisce il nome dell'agenzia.
	 */
	public String getNome() {
		return nome.get();
	}

	public StringProperty propertyNome() {
		return nome;
	}

	/**
	 * 
	 * @return Restituisce l'indirizzo dell'agenzia.
	 */
	public String getIndirizzo() {
		return indirizzo.get();
	}

	public StringProperty propertyIndirizzo() {
		return indirizzo;
	}

	/**
	 * 
	 * @return Restituisce la città in cui risiede l'agenzia.
	 */
	public String getCitta() {
		return citta.get();
	}

	public StringProperty propertyCitta() {
		return citta;
	}

	/**
	 * 
	 * @return Restituisce il CAP della città in cui riseide l'agenzia.
	 */
	public String getCap() {
		return cap.get();
	}

	public StringProperty propertyCap() {
		return cap;
	}

	@Override
	public String toString() {
		return "Codice : " + getCodice() + " | Nome : " + getNome()
				+ " | Città : " + getCitta();
	}

}
