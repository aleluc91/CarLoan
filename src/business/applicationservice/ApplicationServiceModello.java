package business.applicationservice;

import integration.dao.DAO;
import integration.dao.DAOFactory;

import java.util.List;

import entity.Modello;
import business.applicationservice.transfer.Valori;

/**
 * Classe che si occupa di gestire i servizi per quanto riguarda l'entità
 * Modello.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */
public class ApplicationServiceModello implements ApplicationService<Modello> {

	/**
	 * Nome del DAO da richiamare.
	 */
	private final String NOME_DAO = "Modello";

	/**
	 * Istanza del DAO richiamato.
	 */
	private DAO<Modello> daoModello = DAOFactory.getDAO(NOME_DAO);

	/**
	 * Metodo che si occupa di richiamare il DAOModello per l'inserimento di un
	 * nuovo elemento all'interno della tabella Modello contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori da inserire all'interno della tabella Modello.
	 * @return Restitisce True se l'operazione è andata a buon fine , False
	 *         altrimenti.
	 */
	@Override
	public boolean crea(List<Valori> valori) {
		return daoModello.inserimento(leggiValori(valori));
	}

	/**
	 * Metodo che si occupa di richiamare il DAOModello per la lettura di una
	 * riga contenuta nella tabella Modello.
	 * 
	 * @return Restituisce una lista di oggetti ricevuta dalla chiamata al
	 *         DAOModello.
	 */
	@Override
	public List<Modello> leggi() {
		return daoModello.leggi();
	}

	/**
	 * Metodo che si occup di richiamare il DAOModello per la modifica di un
	 * elemento all'interno della tabella Modello contenuta nel database.
	 * 
	 * @param Lista
	 *            di valori con cui verrà aggiornata la riga.
	 * @return Restituisce True se l'operazione è andata a buon fine , False
	 *         altrimenti.
	 */
	@Override
	public boolean modifica(List<Valori> valori) {
		return daoModello.aggiorna(valori);
	}

	/**
	 * Metodo che si occupa di richiamare il DAOModello per la ricerca di un
	 * elemento all'interno della tabella Modello contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla ricerca all'interno della tabella
	 *            Contratto.
	 * @return Restituisce un oggetto ricevuto dalla chiamata al DAOModello.
	 */
	@Override
	public Modello cerca(List<Valori> valori) {
		String fascia = valori.get(0).getString();
		List<Modello> lista = daoModello.leggi();
		if (lista != null) {
			Modello modRitorno = null;
			for (Modello modello : daoModello.leggi()) {
				if (modello.getFascia().equalsIgnoreCase(fascia)) {
					modRitorno = modello;
				}
			}
			return modRitorno;
		} else {
			return null;
		}
	}

	/**
	 * Metodo che si occupa di richiamare il DAOModello per l'eliminazione di un
	 * elemento all'interno della tabella Modello contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla cancellazione della riga
	 *            all'interno della tabella Modello.
	 * @return Restituisce True se l'operazione è andata a buon fine , Falso
	 *         altrimenti.
	 */
	@Override
	public boolean elimina(List<Valori> valori) {
		return daoModello.elimina(valori.get(0).getString());
	}

	/**
	 * Metodo che si occupa di istanziare un nuovo oggetto di tipo Modello
	 * utilizzando la lista di valori ricevuta come parametro.
	 * 
	 * @param valori
	 *            Lista dei valori necessari per la creazione di un nuovo
	 *            oggetto di tipo Modello.
	 * @return Restituisce l'oggetto di tipo Modello istanziato.
	 */
	private Modello leggiValori(List<Valori> valori) {
		String modello = valori.get(0).getString();
		String fascia = valori.get(1).getString();
		return new Modello(modello, fascia);
	}

}
