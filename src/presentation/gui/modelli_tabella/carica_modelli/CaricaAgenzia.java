package presentation.gui.modelli_tabella.carica_modelli;

import java.util.Iterator;

import entity.Agenzia;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import presentation.gui.modelli_tabella.ModelloAgenzia;

public class CaricaAgenzia extends Carica<ModelloAgenzia, Agenzia> {

	private static CaricaAgenzia istanza = null;

	private static final String RICHIESTA = "leggiAgenzia";

	private CaricaAgenzia() {
		super(ModelloAgenzia.class, Agenzia.class, RICHIESTA);
	}

	public static CaricaAgenzia getIstanza() {
		if (istanza == null)
			return new CaricaAgenzia();
		else
			return istanza;

	}

	@Override
	public void caricaTabella(TableView<ModelloAgenzia> tabella,
			ObservableList<ModelloAgenzia> dati) {
		// TODO Auto-generated method stub
		carica(tabella, dati);
	}

	@Override
	public void caricaComboBox(ComboBox<ModelloAgenzia> combobox,
			ObservableList<ModelloAgenzia> dati) {
		carica(combobox, dati);
	}

	public void caricaComboBox(ComboBox<ModelloAgenzia> combobox,
			String valore, ObservableList<ModelloAgenzia> dati) {
		carica(combobox, dati);
		for (Iterator<ModelloAgenzia> it = dati.iterator(); it.hasNext();) {
			ModelloAgenzia modello = it.next();
			if (!modello.getCodice().equalsIgnoreCase(valore)) {
				it.remove();
			}
		}
	}

	@Override
	public void cercaComboBox(ComboBox<ModelloAgenzia> combobox,
			String ricerca, ObservableList<ModelloAgenzia> dati) {
		carica(combobox, dati);
		int i = 0;
		for (ModelloAgenzia modello : dati) {
			if (modello.getCodice().equalsIgnoreCase(ricerca)) {
				combobox.getSelectionModel().select(i);
			}
			i++;
		}
	}

}
