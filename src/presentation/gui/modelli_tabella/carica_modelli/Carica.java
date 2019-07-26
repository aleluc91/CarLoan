package presentation.gui.modelli_tabella.carica_modelli;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import presentation.controller.FrontController;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

/**
 * Classe astratta , e generica , in cui vengono definite le operazioni di
 * caricamento dei controlli di una schermata , e la ricerca di un dato
 * all'interno di controlli già caricati.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 * @param <TModello>
 *            Modello utilizzato dal controllo.
 * @param <TEntita>
 *            Entità utilizzata dal modello.
 */
abstract class Carica<TModello, TEntita> {

	private Class<TModello> classe_T;
	private Class<TEntita> classe_S;
	protected String richiesta;

	/**
	 * 
	 * @param classe_T
	 * @param classe_S
	 * @param richiesta
	 */
	protected Carica(Class<TModello> classe_T, Class<TEntita> classe_S,
			String richiesta) {
		this.classe_T = classe_T;
		this.classe_S = classe_S;
		this.richiesta = richiesta;
	}

	/**
	 * 
	 * @param tabella
	 * @param dati
	 */
	@SuppressWarnings("unchecked")
	protected void carica(TableView<TModello> tabella,
			ObservableList<TModello> dati) {
		FrontController fc = new FrontController();
		List<TEntita> lista = (ArrayList<TEntita>) fc.processaRichiesta(
				richiesta, null);
		if (lista != null && lista.size() > 0) {
			dati.clear();
			for (TEntita s : lista) {
				dati.add(getIstanza(s));
			}
			tabella.setItems(dati);
			tabella.setDisable(false);
		} else {
			tabella.setItems(null);
			tabella.setDisable(true);
		}
	}

	@SuppressWarnings("unchecked")
	protected void carica(ComboBox<TModello> combobox,
			ObservableList<TModello> dati) {
		FrontController fc = new FrontController();
		List<TEntita> lista = (ArrayList<TEntita>) fc.processaRichiesta(
				richiesta, null);
		if (lista != null && lista.size() > 0) {
			dati.clear();
			for (TEntita s : lista) {
				dati.add(getIstanza(s));
			}
			combobox.setItems(dati);
		} else {
			combobox.setItems(null);
			combobox.setDisable(true);
		}
	}

	private TModello getIstanza(TEntita s) {
		try {
			return classe_T.getConstructor(classe_S).newInstance(s);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	abstract public void caricaTabella(TableView<TModello> tabella,
			ObservableList<TModello> dati);

	abstract public void caricaComboBox(ComboBox<TModello> combobox,
			ObservableList<TModello> dati);

	abstract public void cercaComboBox(ComboBox<TModello> combobox,
			String ricerca, ObservableList<TModello> dati);

}
