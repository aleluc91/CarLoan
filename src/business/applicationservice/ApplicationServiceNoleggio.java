package business.applicationservice;

import integration.dao.DAO;
import integration.dao.DAOFactory;

import java.util.List;

import entity.Noleggio;
import business.applicationservice.transfer.Valori;

/**
 * Classe che si occupa di gestire i servizi per quanto riguarda l'entità
 * Noleggio.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */
public class ApplicationServiceNoleggio implements ApplicationService<Noleggio> {

	/**
	 * Nome del DAO da richiamare.
	 */
	private final String NOME_DAO = "Noleggio";

	/**
	 * Istanza del DAO richiamato.
	 */
	private DAO<Noleggio> daoNoleggio = DAOFactory.getDAO(NOME_DAO);

	/**
	 * Metodo che si occupa di richiamare il DAONoleggio per l'inserimento di un
	 * nuovo elemento all'interno della tabella Noleggio contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori da inserire all'interno della tabella
	 *            Noleggio.
	 * @return Restitisce True se l'operazione è andata a buon fine , False
	 *         altrimenti.
	 */
	@Override
	public boolean crea(List<Valori> valori) {
		return daoNoleggio.inserimento(leggiValori(valori));
	}

	/**
	 * Metodo che si occupa di richiamare il DAONoleggio per la lettura di una
	 * riga contenuta nella tabella Noleggio.
	 * 
	 * @return Restituisce una lista di oggetti ricevuta dalla chiamata al
	 *         DAONoleggio.
	 */
	@Override
	public List<Noleggio> leggi() {
		return daoNoleggio.leggi();
	}

	/**
	 * Metodo che si occup di richiamare il DAONoleggio per la modifica di un
	 * elemento all'interno della tabella Noleggio contenuta nel database.
	 * 
	 * @param Lista
	 *            di valori con cui verrà aggiornata la riga.
	 * @return Restituisce True se l'operazione è andata a buon fine , False
	 *         altrimenti.
	 */
	@Override
	public boolean modifica(List<Valori> valori) {
		return false;
	}

	/**
	 * Metodo che si occupa di richiamare il DAONoleggio per la ricerca di un
	 * elemento all'interno della tabella Noleggio contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla ricerca all'interno della tabella
	 *            Noleggio.
	 * @return Restituisce un oggetto ricevuto dalla chiamata al DAONoleggio.
	 */
	@Override
	public Noleggio cerca(List<Valori> valori) {
		String codice = valori.get(0).getString();
		List<Noleggio> lista = daoNoleggio.leggi();
		if (lista != null) {
			Noleggio nolRitorno = null;
			for (Noleggio noleggio : daoNoleggio.leggi()) {
				if (noleggio.getContratto().equalsIgnoreCase(codice)) {
					nolRitorno = noleggio;
				}
			}
			return nolRitorno;
		} else {
			return null;
		}
	}

	/**
	 * Metodo che si occupa di richiamare il DAONoleggio per l'eliminazione di
	 * un elemento all'interno della tabella Noleggio contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla cancellazione della riga
	 *            all'interno della tabella Noleggio.
	 * @return Restituisce True se l'operazione è andata a buon fine , Falso
	 *         altrimenti.
	 */
	@Override
	public boolean elimina(List<Valori> valori) {
		return false;
	}

	/**
	 * Metodo che si occupa di istanziare un nuovo oggetto di tipo Noleggio
	 * utilizzando la lista di valori ricevuta come parametro.
	 * 
	 * @param valori
	 *            Lista dei valori necessari per la creazione di un nuovo
	 *            oggetto di tipo Noleggio.
	 * @return Restituisce l'oggetto di tipo Noleggio istanziato.
	 */
	private Noleggio leggiValori(List<Valori> valori) {
		String codice = valori.get(0).getString();
		int tariffa = valori.get(1).getInt();
		String chilometraggio = valori.get(2).getString();
		String fascia = valori.get(3).getString();
		String modello = valori.get(4).getString();
		String macchina = valori.get(5).getString();
		return new Noleggio(codice, tariffa, chilometraggio, fascia, modello,
				macchina);
	}

}
