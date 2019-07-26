package business.applicationservice;

import business.applicationservice.transfer.Valori;

import java.util.List;

/**
 * Interfaccia contente la dichiarazione di tutti i metodi che compongono
 * l'application service dell'applicazione.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 * @param <T>
 */
public interface ApplicationService<T> {

	/**
	 * Metodo che si occupa di richiamare il DAO per l'inserimento di un nuovo
	 * elemento all'interno della tabella selezionata contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori da inserire all'interno della tabella.
	 * @return Restitisce True se l'operazione è andata a buon fine , False
	 *         altrimenti.
	 */
	public boolean crea(List<Valori> valori);

	/**
	 * Metodo che si occupa di richiamare il DAO per la lettura di una riga
	 * contenuta nella tabella selezionata.
	 * 
	 * @return Restituisce una lista di oggetti ricevuta dalla chiamata al DAO.
	 */
	public List<T> leggi();

	/**
	 * Metodo che si occup di richiamare il DAO per la modifica di un elemento
	 * all'interno della tabella selezionata contenuta nel database.
	 * 
	 * @param Lista
	 *            di valori con cui verrà aggiornata la riga.
	 * @returnRestitisce Restituisce True se l'operazione è andata a buon fine ,
	 *                   False altrimenti.
	 */
	public boolean modifica(List<Valori> valori);

	/**
	 * Metodo che si occupa di richiamare il DAO per la ricerca di un elemento
	 * all'interno della tabella selezionata contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla ricerca all'interno della tabella.
	 * @return Restituisce un oggetto ricevuto dalla chiamata al DAO.
	 */
	public T cerca(List<Valori> valori);

	/**
	 * Metodo che si occupa di richiamare il DAO per l'eliminazione di un
	 * elemento all'interno della tabella contenuta nel database.
	 * 
	 * @param valori
	 *            Lista di valori utili alla cancellazione della riga
	 *            all'interno della tabella.
	 * @return Restituisce True se l'operazione è andata a buon fine , Falso
	 *         altrimenti.
	 */
	public boolean elimina(List<Valori> valori);

}
