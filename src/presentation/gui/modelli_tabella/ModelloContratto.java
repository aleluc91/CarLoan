package presentation.gui.modelli_tabella;

import entity.Contratto;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe in cui viene definito un modello , che in questo caso rispecchia
 * l'entità Contratto , utile nella definizione di tutti i cotrolli ( Tabelle ,
 * Menu di selezione , ecc) che hanno bisogno di visualizzare a schermo un dato
 * di tipo Contratto.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */
public class ModelloContratto extends ModelloTabella {

	private StringProperty codice;
	private StringProperty nome;
	private StringProperty cognome;
	private StringProperty telefono;
	private StringProperty dataInizio;
	private StringProperty dataFine;
	private DoubleProperty acconto;
	private StringProperty agenzia;
	private StringProperty agenziaRitorno;
	private StringProperty attivo;

	/**
	 * Metodo costruttore che riceve un parametro di tipo Contratto , e in base
	 * ad i valori contenuti in esso , inizializza i campi dell'istanza creata.
	 * 
	 * @param agenzia
	 *            Riceve un parametro di tipo Contrtto , utilizzato per
	 *            inizializzare i campi dell'istanza creata.
	 */
	public ModelloContratto(Contratto contratto) {
		super();
		this.codice = new SimpleStringProperty(contratto.getCodiceContratto());
		this.nome = new SimpleStringProperty(contratto.getCliente().getNome());
		this.cognome = new SimpleStringProperty(contratto.getCliente()
				.getCognome());
		this.telefono = new SimpleStringProperty(contratto.getCliente()
				.getNumeroTelefono());
		this.dataInizio = new SimpleStringProperty(contratto.getDataInizio()
				.toString());
		this.dataFine = new SimpleStringProperty(contratto.getDataFine()
				.toString());
		this.acconto = new SimpleDoubleProperty(contratto.getAcconto());
		this.agenzia = new SimpleStringProperty(contratto.getAgenzia());
		this.agenziaRitorno = new SimpleStringProperty(
				contratto.getAgenziaRitorno());
		this.attivo = new SimpleStringProperty(contratto.getAttivo());
	}

	/**
	 * Questo metodo è utile per le classi che si occupano del caricamento dei
	 * dati di tipo Contratto all'interno di uno dei controlli visualizzati a
	 * schermo.
	 * 
	 * @return Restituisce il codice del Contratto.
	 */
	public String getValore() {
		return getCodice();
	}

	public StringProperty getCodiceProperty() {
		return codice;
	}

	public StringProperty getNomeProperty() {
		return nome;
	}

	public StringProperty getCognomeProperty() {
		return cognome;
	}

	public StringProperty getTelefonoProperty() {
		return telefono;
	}

	public StringProperty getDataInizioProperty() {
		return dataInizio;
	}

	public StringProperty getDataFineProperty() {
		return dataFine;
	}

	public DoubleProperty getAccontoProperty() {
		return acconto;
	}

	public StringProperty getAgenziaProperty() {
		return agenzia;
	}

	public StringProperty getAgenziaRitornoProperty() {
		return agenziaRitorno;
	}

	public StringProperty getAgenziaAttivoProperty() {
		return attivo;
	}

	/**
	 * 
	 * @return Restituisce il codice del contratto.
	 */
	public String getCodice() {
		return codice.get();
	}

	/**
	 * 
	 * @return Restituisce il nome del cliente che ha stipulato il contratto.
	 */
	public String getNome() {
		return nome.get();
	}

	/**
	 * 
	 * @return Restituisce il cognome del cliente che ha stipulato il contratto.
	 */
	public String getCognome() {
		return cognome.get();
	}

	/**
	 * 
	 * @return Restituisce il numero di telefono del cliente che ha stipulto il
	 *         contrtto.
	 */
	public String getTelefono() {
		return telefono.get();
	}

	/**
	 * 
	 * @return Restituisce la data di inizio del contratto.
	 */
	public String getDataInizio() {
		return dataInizio.get();
	}

	/**
	 * 
	 * @return Restituisce la data in cui termina il contratto.
	 */
	public String getDataFine() {
		return dataFine.get();
	}

	/**
	 * 
	 * @return Restituisce l'acconto versato dal cliente in fase di stipulazione
	 *         del contratto.
	 */
	public Double getAcconto() {
		return acconto.get();
	}

	/**
	 * 
	 * @return Restituisce il codice dell'agenzia in cui è stato stipulato il
	 *         contratto.
	 */
	public String getAgenzia() {
		return agenzia.get();
	}

	/**
	 * 
	 * @return Restituisce l'agenzia scelta dal cliente per effettuare il reso
	 *         della macchina.
	 */
	public String getAgenziaRitorno() {
		return agenziaRitorno.get();
	}

	/**
	 * 
	 * @return Restituisce lo stato attuale del contratto (Attivo o Terminato).
	 */
	public String getAttivo() {
		return attivo.get();
	}
}
