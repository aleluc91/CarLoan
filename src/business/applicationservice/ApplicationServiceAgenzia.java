package business.applicationservice;

import integration.dao.DAO;
import integration.dao.DAOFactory;

import java.util.ArrayList;
import java.util.List;

import entity.Agenzia;
import business.applicationservice.transfer.Valori;

/**
 * Classe che si occupa di gestire i servizi per quanto riguarda l'entità
 * Agenzia.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */
public class ApplicationServiceAgenzia implements ApplicationService<Agenzia> {

	/**
	 * Nome del DAO da richiamare.
	 */
	private static final String NOME_DAO = "Agenzia";

	/**
	 * Istanza del DAO chiamato.
	 */
	private DAO<Agenzia> daoAgenzia = DAOFactory.getDAO(NOME_DAO);

	/**
	 * Metodo che si occupa di richiamare il DAOAgenzia per l'inserimento di un
	 * nuovo elemento all'interno della tabella Agenzia contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori da inserire all'interno della tabella Agenzia.
	 * @return Restitisce True se l'operazione è andata a buon fine , False
	 *         altrimenti.
	 */
	@Override
	public boolean crea(List<Valori> valori) {
		List<Valori> valoriAutenticazione = new ArrayList<Valori>();
		valoriAutenticazione.add(valori.get(0));
		valoriAutenticazione.add(valori.get(1));
		List<Valori> valoriAgenzia = new ArrayList<Valori>();
		valoriAgenzia.add(valori.get(2));
		valoriAgenzia.add(valori.get(3));
		valoriAgenzia.add(valori.get(4));
		valoriAgenzia.add(valori.get(5));
		valoriAgenzia.add(valori.get(6));
		if (daoAgenzia.inserimento(leggiValori(valoriAgenzia))) {
			valoriAutenticazione.add(new Valori(valoriAgenzia.get(0)
					.getString()));
			valoriAutenticazione.add(new Valori("agenzia"));
			return new ApplicationServiceAutenticazione()
					.crea(valoriAutenticazione);
		} else {
			return false;
		}
	}

	/**
	 * Metodo che si occupa di richiamare il DAOAgenzia per la lettura di una
	 * riga contenuta nella tabella Agenzia.
	 * 
	 * @return Restituisce una lista di oggetti ricevuta dalla chiamata al
	 *         DAOAgenzia.
	 */
	@Override
	public List<Agenzia> leggi() {
		return daoAgenzia.leggi();
	}

	/**
	 * Metodo che si occup di richiamare il DAOAgenzia per la modifica di un
	 * elemento all'interno della tabella Agenzia contenuta nel database.
	 * 
	 * @param Lista
	 *            di valori con cui verrà aggiornata la riga.
	 * @return Restituisce True se l'operazione è andata a buon fine , False
	 *         altrimenti.
	 */
	@Override
	public boolean modifica(List<Valori> valori) {
		List<Valori> valoriAutenticazione = new ArrayList<Valori>();
		valoriAutenticazione.add(valori.get(0));
		valoriAutenticazione.add(valori.get(1));
		List<Valori> valoriAgenzia = new ArrayList<Valori>();
		valoriAgenzia.add(valori.get(2));
		valoriAgenzia.add(valori.get(3));
		valoriAgenzia.add(valori.get(4));
		valoriAgenzia.add(valori.get(5));
		valoriAgenzia.add(valori.get(6));
		valoriAgenzia.add(valori.get(7));
		if (daoAgenzia.aggiorna(valoriAgenzia)) {
			valoriAutenticazione.add(new Valori(valoriAgenzia.get(0)
					.getString()));
			valoriAutenticazione.add(valori.get(2));
			return new ApplicationServiceAutenticazione()
					.modifica(valoriAutenticazione);
		} else {
			return false;
		}
	}

	/**
	 * Metodo che si occupa di richiamare il DAOAgenzia per la ricerca di un
	 * elemento all'interno della tabella Agenzia contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla ricerca all'interno della tabella
	 *            Agenzia.
	 * @return Restituisce un oggetto ricevuto dalla chiamata al DAOAgenzia.
	 */
	@Override
	public Agenzia cerca(List<Valori> valori) {
		return null;
	}

	/**
	 * Metodo che si occupa di richiamare il DAOAgenzia per l'eliminazione di un
	 * elemento all'interno della tabella Agenzia contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla cancellazione della riga
	 *            all'interno della tabella Agenzia.
	 * @return Restituisce True se l'operazione è andata a buon fine , Falso
	 *         altrimenti.
	 */
	@Override
	public boolean elimina(List<Valori> valori) {
		return daoAgenzia.elimina(valori.get(0).getString());
	}

	/**
	 * Metodo che si occupa di istanziare un nuovo oggetto di tipo Agenzia
	 * utilizzando la lista di valori ricevuta come parametro.
	 * 
	 * @param valori
	 *            Lista dei valori necessari per la creazione di un nuovo
	 *            oggetto di tipo Agenzia.
	 * @return Restituisce l'oggetto di tipo Agenzia istanziato.
	 */
	private Agenzia leggiValori(List<Valori> valori) {
		String codice = valori.get(0).getString();
		String nome = valori.get(1).getString();
		String indirizzo = valori.get(2).getString();
		String citta = valori.get(3).getString();
		String CAP = valori.get(4).getString();
		return new Agenzia(codice, nome, indirizzo, citta, CAP);
	}

}
