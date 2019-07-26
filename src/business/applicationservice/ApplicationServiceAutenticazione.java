package business.applicationservice;

import integration.dao.DAO;
import integration.dao.DAOFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import entity.Autenticazione;
import utility.LeggiConfig;
import utility.MD5;
import business.applicationservice.transfer.Valori;

/**
 * Classe che si occupa di gestire i servizi per quanto riguarda l'entità
 * Autenticazione.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */
public class ApplicationServiceAutenticazione implements
		ApplicationService<Autenticazione> {

	/**
	 * Nome del DAO da richiamare.
	 */
	private final String NOME_DAO = "Autenticazione";

	/**
	 * Istanza del DAO richiamato.
	 */
	private DAO<Autenticazione> daoAutenticazione = DAOFactory.getDAO(NOME_DAO);

	/**
	 * Metodo che si occupa di richiamare il DAOAutenticazione per l'inserimento
	 * di un nuovo elemento all'interno della tabella Autenticazione contenuta
	 * nel database.
	 * 
	 * @param valori
	 *            Lista di valori da inserire all'interno della tabella
	 *            Autenticazione.
	 * @return Restitisce True se l'operazione è andata a buon fine , False
	 *         altrimenti.
	 */
	@Override
	public boolean crea(List<Valori> valori) {
		return daoAutenticazione.inserimento(leggiValori(valori));
	}

	/**
	 * Metodo che si occupa di richiamare il DAOAutenticazione per la lettura di
	 * una riga contenuta nella tabella Autenticazione.
	 * 
	 * @return Restituisce una lista di oggetti ricevuta dalla chiamata al
	 *         DAOAutenticazione.
	 */
	@Override
	public List<Autenticazione> leggi() {
		return daoAutenticazione.leggi();
	}

	/**
	 * Metodo che si occup di richiamare il DAOAutenticazione per la modifica di
	 * un elemento all'interno della tabella Autenticazione contenuta nel
	 * database.
	 * 
	 * @param Lista
	 *            di valori con cui verrà aggiornata la riga.
	 * @return Restituisce True se l'operazione è andata a buon fine , False
	 *         altrimenti.
	 */
	@Override
	public boolean modifica(List<Valori> valori) {
		return daoAutenticazione.aggiorna(valori);
	}

	/**
	 * Metodo che si occupa di richiamare il DAOAutenticazione per la ricerca di
	 * un elemento all'interno della tabella Autenticazione contenuta nel
	 * database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla ricerca all'interno della tabella
	 *            Autenticazione.
	 * @return Restituisce un oggetto ricevuto dalla chiamata al
	 *         DAOAutenticazione.
	 */
	@Override
	public Autenticazione cerca(List<Valori> valori) {
		String username = valori.get(0).getString();
		String password = MD5.getMD5(valori.get(1).getString());
		try {
			String adminUsername = LeggiConfig.getValoreProp(DAO.class
					.getResource("risorse/config.properties").getPath(),
					"admin_username");
			String adminPassword = LeggiConfig.getValoreProp(DAO.class
					.getResource("risorse/config.properties").getPath(),
					"admin_password");
			if (username == adminUsername && password == adminPassword) {
				return new Autenticazione(adminUsername, adminPassword, null,
						"admin");
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		List<Autenticazione> lista = daoAutenticazione.leggi();
		if (lista != null) {
			Autenticazione autRitorno = null;
			for (Autenticazione autenticazione : daoAutenticazione.leggi()) {
				if (autenticazione.getUsername().equalsIgnoreCase(username)
						&& autenticazione.getPassword().equalsIgnoreCase(
								password)) {
					autRitorno = autenticazione;
				}
			}
			return autRitorno;
		} else {
			return null;
		}
	}

	/**
	 * Metodo che si occupa di richiamare il DAOAutenticazione per
	 * l'eliminazione di un elemento all'interno della tabella Autenticazione
	 * contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla cancellazione della riga
	 *            all'interno della tabella Autenticazione.
	 * @return Restituisce True se l'operazione è andata a buon fine , Falso
	 *         altrimenti.
	 */
	@Override
	public boolean elimina(List<Valori> valori) {
		return false;
	}

	/**
	 * Metodo che si occupa di istanziare un nuovo oggetto di tipo
	 * Autenticazione utilizzando la lista di valori ricevuta come parametro.
	 * 
	 * @param valori
	 *            Lista dei valori necessari per la creazione di un nuovo
	 *            oggetto di tipo Autenticazione.
	 * @return Restituisce l'oggetto di tipo Autenticazione istanziato.
	 */
	private Autenticazione leggiValori(List<Valori> valori) {
		String username = valori.get(0).getString();
		String password = valori.get(1).getString();
		String agenzia = valori.get(2).getString();
		String privilegi = valori.get(3).getString();
		return new Autenticazione(username, password, agenzia, privilegi);
	}

}
