package business.applicationservice;

import integration.dao.DAO;
import integration.dao.DAOFactory;

import java.util.List;

import entity.Macchina;
import business.applicationservice.transfer.Valori;

/**
 * Classe che si occupa di gestire i servizi per quanto riguarda l'entità
 * Macchina.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */
public class ApplicationServiceMacchina implements ApplicationService<Macchina> {

	/**
	 * Nome del DAO da richiamare.
	 */
	private final String NOME_DAO = "Macchina";

	/**
	 * Istanza del DAO richiamato.
	 */
	private DAO<Macchina> daoMacchina = DAOFactory.getDAO(NOME_DAO);

	/**
	 * Metodo che si occupa di richiamare il DAOMacchina per l'inserimento di un
	 * nuovo elemento all'interno della tabella Macchina contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori da inserire all'interno della tabella
	 *            Macchina.
	 * @return Restitisce True se l'operazione è andata a buon fine , False
	 *         altrimenti.
	 */
	@Override
	public boolean crea(List<Valori> valori) {
		return daoMacchina.inserimento(leggiValori(valori));
	}

	/**
	 * Metodo che si occupa di richiamare il DAOMacchina per la lettura di una
	 * riga contenuta nella tabella Macchina.
	 * 
	 * @return Restituisce una lista di oggetti ricevuta dalla chiamata al
	 *         DAOMacchina.
	 */
	@Override
	public List<Macchina> leggi() {
		return daoMacchina.leggi();
	}

	/**
	 * Metodo che si occup di richiamare il DAOMacchina per la modifica di un
	 * elemento all'interno della tabella Macchina contenuta nel database.
	 * 
	 * @param Lista
	 *            di valori con cui verrà aggiornata la riga.
	 * @return Restituisce True se l'operazione è andata a buon fine , False
	 *         altrimenti.
	 */
	@Override
	public boolean modifica(List<Valori> valori) {
		return daoMacchina.aggiorna(valori);
	}

	/**
	 * Metodo che si occupa di richiamare il DAOMacchina per la ricerca di un
	 * elemento all'interno della tabella Macchina contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla ricerca all'interno della tabella
	 *            Macchina.
	 * @return Restituisce un oggetto ricevuto dalla chiamata al DAOMacchina.
	 */
	@Override
	public Macchina cerca(List<Valori> valori) {
		String targa = valori.get(0).getString();
		List<Macchina> lista = daoMacchina.leggi();
		if (lista != null) {
			Macchina macRitorno = null;
			for (Macchina macchina : daoMacchina.leggi()) {
				if (macchina.getTarga().equalsIgnoreCase(targa)) {
					macRitorno = macchina;
				}
			}
			return macRitorno;
		} else {
			return null;
		}
	}

	/**
	 * Metodo che si occupa di richiamare il DAOMacchina per l'eliminazione di
	 * un elemento all'interno della tabella Macchina contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla cancellazione della riga
	 *            all'interno della tabella Macchina.
	 * @return Restituisce True se l'operazione è andata a buon fine , Falso
	 *         altrimenti.
	 */
	@Override
	public boolean elimina(List<Valori> valori) {
		return daoMacchina.elimina(valori.get(0).getString());
	}

	/**
	 * Metodo che si occupa di istanziare un nuovo oggetto di tipo Macchina
	 * utilizzando la lista di valori ricevuta come parametro.
	 * 
	 * @param valori
	 *            Lista dei valori necessari per la creazione di un nuovo
	 *            oggetto di tipo Macchina.
	 * @return Restituisce l'oggetto di tipo Macchina istanziato.
	 */
	private Macchina leggiValori(List<Valori> valori) {
		String targa = valori.get(0).getString();
		String fascia = valori.get(1).getString();
		String modello = valori.get(2).getString();
		String agenzia = valori.get(3).getString();
		String noleggio = valori.get(4).getString();
		String manutenzione = valori.get(5).getString();
		long chilometraggio = valori.get(6).getLong();
		return new Macchina(targa, fascia, modello, agenzia, noleggio,
				manutenzione, chilometraggio);
	}

	public boolean modificaValore(List<Valori> valori) {
		return daoMacchina.aggiornaValore(valori);
	}

}
