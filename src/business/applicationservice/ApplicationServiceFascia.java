package business.applicationservice;

import integration.dao.DAO;
import integration.dao.DAOFactory;

import java.util.List;

import entity.Fascia;
import business.applicationservice.transfer.Valori;

/**
 * Classe che si occupa di gestire i servizi per quanto riguarda l'entità
 * Fascia.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */
public class ApplicationServiceFascia implements ApplicationService<Fascia> {

	/**
	 * Nome del DAO da richiamare.
	 */
	private final String NOME_DAO = "Fascia";

	/**
	 * Istanza del DAO richiamato.
	 */
	private DAO<Fascia> daoFascia = DAOFactory.getDAO(NOME_DAO);

	/**
	 * Metodo che si occupa di richiamare il DAOFascia per l'inserimento di un
	 * nuovo elemento all'interno della tabella Fascia contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori da inserire all'interno della tabella Fascia.
	 * @return Restitisce True se l'operazione è andata a buon fine , False
	 *         altrimenti.
	 */
	@Override
	public boolean crea(List<Valori> valori) {
		return daoFascia.inserimento(leggiValori(valori));
	}

	/**
	 * Metodo che si occupa di richiamare il DAOFascia per la lettura di una
	 * riga contenuta nella tabella Fascia.
	 * 
	 * @return Restituisce una lista di oggetti ricevuta dalla chiamata al
	 *         DAOFascia.
	 */
	@Override
	public List<Fascia> leggi() {
		return daoFascia.leggi();
	}

	/**
	 * Metodo che si occup di richiamare il DAOFascia per la modifica di un
	 * elemento all'interno della tabella Fascia contenuta nel database.
	 * 
	 * @param Lista
	 *            di valori con cui verrà aggiornata la riga.
	 * @return Restituisce True se l'operazione è andata a buon fine , False
	 *         altrimenti.
	 */
	@Override
	public boolean modifica(List<Valori> valori) {
		return daoFascia.aggiorna(valori);
	}

	/**
	 * Metodo che si occupa di richiamare il DAOFascia per la ricerca di un
	 * elemento all'interno della tabella Fascia contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla ricerca all'interno della tabella
	 *            Fascia.
	 * @return Restituisce un oggetto ricevuto dalla chiamata al DAOFascia.
	 */
	@Override
	public Fascia cerca(List<Valori> valori) {
		// TODO Auto-generated method stub
		String id = valori.get(0).getString();
		List<Fascia> lista = daoFascia.leggi();
		if (lista != null) {
			Fascia fasciaRitorno = null;
			for (Fascia fascia : daoFascia.leggi()) {
				if (fascia.getId().equalsIgnoreCase(id)) {
					fasciaRitorno = fascia;
				}
			}
			return fasciaRitorno;
		} else {
			return null;
		}
	}

	/**
	 * Metodo che si occupa di richiamare il DAOFascia per l'eliminazione di un
	 * elemento all'interno della tabella Fascia contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla cancellazione della riga
	 *            all'interno della tabella Fascia.
	 * @return Restituisce True se l'operazione è andata a buon fine , Falso
	 *         altrimenti.
	 */
	@Override
	public boolean elimina(List<Valori> valori) {
		return daoFascia.elimina(valori.get(0).getString());
	}

	/**
	 * Metodo che si occupa di istanziare un nuovo oggetto di tipo Fascia
	 * utilizzando la lista di valori ricevuta come parametro.
	 * 
	 * @param valori
	 *            Lista dei valori necessari per la creazione di un nuovo
	 *            oggetto di tipo Fascia.
	 * @return Restituisce l'oggetto di tipo Fascia istanziato.
	 */
	private Fascia leggiValori(List<Valori> valori) {
		String identificativo = valori.get(0).getString();
		String descrizione = valori.get(1).getString();
		double costo = valori.get(2).getDouble();
		return new Fascia(identificativo, descrizione, costo);
	}

}
