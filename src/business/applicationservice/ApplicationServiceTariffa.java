package business.applicationservice;

import integration.dao.DAO;
import integration.dao.DAOFactory;

import java.util.List;

import entity.Tariffa;
import business.applicationservice.transfer.Valori;

/**
 * Classe che si occupa di gestire i servizi per quanto riguarda l'entità
 * Tariffa.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */
public class ApplicationServiceTariffa implements ApplicationService<Tariffa> {

	/**
	 * Nome del DAO da richimare.
	 */
	private final String NOME_DAO = "Tariffa";

	/**
	 * Istanza del DAO richiamato.
	 */
	private DAO<Tariffa> daoTariffa = DAOFactory.getDAO(NOME_DAO);

	/**
	 * Metodo che si occupa di richiamare il DAOTariffa per l'inserimento di un
	 * nuovo elemento all'interno della tabella Tariffa contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori da inserire all'interno della tabella Tariffa.
	 * @return Restitisce True se l'operazione è andata a buon fine , False
	 *         altrimenti.
	 */
	@Override
	public boolean crea(List<Valori> valori) {
		return daoTariffa.inserimento(leggiValori(valori));
	}

	/**
	 * Metodo che si occupa di richiamare il DAOTariffa per la lettura di una
	 * riga contenuta nella tabella Tariffa.
	 * 
	 * @return Restituisce una lista di oggetti ricevuta dalla chiamata al
	 *         DAOTariffa.
	 */
	@Override
	public List<Tariffa> leggi() {
		return daoTariffa.leggi();
	}

	/**
	 * Metodo che si occup di richiamare il DAOTariffa per la modifica di un
	 * elemento all'interno della tabella Tariffa contenuta nel database.
	 * 
	 * @param Lista
	 *            di valori con cui verrà aggiornata la riga.
	 * @return Restituisce True se l'operazione è andata a buon fine , False
	 *         altrimenti.
	 */
	@Override
	public boolean modifica(List<Valori> valori) {
		return daoTariffa.aggiorna(valori);
	}

	/**
	 * Metodo che si occupa di richiamare il DAOTariffa per la ricerca di un
	 * elemento all'interno della tabella Tariffa contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla ricerca all'interno della tabella
	 *            Tariffa.
	 * @return Restituisce un oggetto ricevuto dalla chiamata al DAOTariffa.
	 */
	@Override
	public Tariffa cerca(List<Valori> valori) {
		int id = valori.get(0).getInt();
		List<Tariffa> lista = daoTariffa.leggi();
		if (lista != null) {
			Tariffa tarRitorno = null;
			for (Tariffa tariffa : daoTariffa.leggi()) {
				if (tariffa.getId() == id) {
					tarRitorno = tariffa;
				}
			}
			return tarRitorno;
		} else {
			return null;
		}
	}

	/**
	 * Metodo che si occupa di richiamare il DAOTariffa per l'eliminazione di un
	 * elemento all'interno della tabella Tariffa contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla cancellazione della riga
	 *            all'interno della tabella Tariffa.
	 * @return Restituisce True se l'operazione è andata a buon fine , Falso
	 *         altrimenti.
	 */
	@Override
	public boolean elimina(List<Valori> valori) {
		return daoTariffa.elimina(valori.get(0).getString());
	}

	/**
	 * Metodo che si occupa di istanziare un nuovo oggetto di tipo Tariffa
	 * utilizzando la lista di valori ricevuta come parametro.
	 * 
	 * @param valori
	 *            Lista dei valori necessari per la creazione di un nuovo
	 *            oggetto di tipo Tariffa.
	 * @return Restituisce l'oggetto di tipo Tariffa istanziato.
	 */
	private Tariffa leggiValori(List<Valori> valori) {
		int id = 0;
		String tipo = valori.get(0).getString();
		long chilometri = valori.get(1).getLong();
		int giorni = valori.get(2).getInt();
		double tariffa = valori.get(3).getDouble();
		return new Tariffa(id, tipo, chilometri, giorni, tariffa);
	}

}
