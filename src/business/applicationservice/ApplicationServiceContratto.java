package business.applicationservice;

import integration.dao.DAO;
import integration.dao.DAOFactory;

import java.time.LocalDate;
import java.util.List;

import entity.Cliente;
import entity.Contratto;
import business.applicationservice.transfer.Valori;

/**
 * Classe che si occupa di gestire i servizi per quanto riguarda l'entità
 * Contratto.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */
public class ApplicationServiceContratto implements
		ApplicationService<Contratto> {

	/**
	 * Nome del DAO da richiamare.
	 */
	private final String NOME_DAO_CONTRATTO = "Contratto";

	/**
	 * Istanza del DAO richiamato.
	 */
	private DAO<Contratto> daoContratto = DAOFactory.getDAO(NOME_DAO_CONTRATTO);

	/**
	 * Metodo che si occupa di richiamare il DAOContratto per l'inserimento di
	 * un nuovo elemento all'interno della tabella Contratto contenuta nel
	 * database.
	 * 
	 * @param valori
	 *            Lista di valori da inserire all'interno della tabella
	 *            Contratto.
	 * @return Restitisce True se l'operazione è andata a buon fine , False
	 *         altrimenti.
	 */
	@Override
	public boolean crea(List<Valori> valori) {
		return daoContratto.inserimento(leggiValori(valori));
	}

	/**
	 * Metodo che si occupa di richiamare il DAOContratto per la lettura di una
	 * riga contenuta nella tabella Contratto.
	 * 
	 * @return Restituisce una lista di oggetti ricevuta dalla chiamata al
	 *         DAOContratto.
	 */
	@Override
	public List<Contratto> leggi() {
		return daoContratto.leggi();
	}

	/**
	 * Metodo che si occup di richiamare il DAOContratto per la modifica di un
	 * elemento all'interno della tabella Contratto contenuta nel database.
	 * 
	 * @param Lista
	 *            di valori con cui verrà aggiornata la riga.
	 * @return Restituisce True se l'operazione è andata a buon fine , False
	 *         altrimenti.
	 */
	@Override
	public boolean modifica(List<Valori> valori) {
		return daoContratto.aggiorna(valori);
	}

	/**
	 * Metodo che si occupa di richiamare il DAOContratto per la ricerca di un
	 * elemento all'interno della tabella Contratto contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla ricerca all'interno della tabella
	 *            Contratto.
	 * @return Restituisce un oggetto ricevuto dalla chiamata al DAOContratto.
	 */
	@Override
	public Contratto cerca(List<Valori> valori) {
		String codice = valori.get(0).getString();
		List<Contratto> lista = daoContratto.leggi();
		if (lista != null) {
			Contratto conRitorno = null;
			for (Contratto contratto : daoContratto.leggi()) {
				if (contratto.getCodiceContratto().equalsIgnoreCase(codice)) {
					conRitorno = contratto;
				}
			}
			return conRitorno;
		} else {
			return null;
		}
	}

	/**
	 * Metodo che si occupa di richiamare il DAOContratto per l'eliminazione di
	 * un elemento all'interno della tabella Contratto contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla cancellazione della riga
	 *            all'interno della tabella Contratto.
	 * @return Restituisce True se l'operazione è andata a buon fine , Falso
	 *         altrimenti.
	 */
	@Override
	public boolean elimina(List<Valori> valori) {
		return false;
	}

	public boolean modificaValore(List<Valori> valori) {
		return daoContratto.aggiornaValore(valori);
	}

	/**
	 * Metodo che si occupa di istanziare un nuovo oggetto di tipo Contratto
	 * utilizzando la lista di valori ricevuta come parametro.
	 * 
	 * @param valori
	 *            Lista dei valori necessari per la creazione di un nuovo
	 *            oggetto di tipo Contratto.
	 * @return Restituisce l'oggetto di tipo Contratto istanziato.
	 */
	private Contratto leggiValori(List<Valori> valori) {
		String codice = valori.get(0).getString();
		Cliente cliente = new Cliente(valori.get(1).getString(), valori.get(2)
				.getString(), valori.get(3).getString());
		LocalDate dataInizio = valori.get(4).getData();
		LocalDate dataFine = valori.get(5).getData();
		double acconto = valori.get(6).getDouble();
		String agenzia = valori.get(7).getString();
		String agenziaRitorno = valori.get(8).getString();
		String attivo = "si";
		return new Contratto(codice, cliente, dataInizio, dataFine, acconto,
				agenzia, agenziaRitorno, attivo);
	}

}
