package presentation.gui.modelli_tabella.carica_modelli;

import java.util.Iterator;

import entity.Modello;
import presentation.gui.modelli_tabella.ModelloModello;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

public class CaricaModello extends Carica<ModelloModello, Modello> {

	private static CaricaModello istanza = null;

	private static final String RICHIESTA = "leggiModello";

	private CaricaModello() {
		super(ModelloModello.class, Modello.class, RICHIESTA);
	}

	public static CaricaModello getIstanza() {
		if (istanza == null) {
			return new CaricaModello();
		} else {
			return istanza;
		}
	}

	@Override
	public void caricaTabella(TableView<ModelloModello> tabella,
			ObservableList<ModelloModello> dati) {
		carica(tabella, dati);

	}

	@Override
	public void caricaComboBox(ComboBox<ModelloModello> combobox,
			ObservableList<ModelloModello> dati) {
		carica(combobox, dati);
	}

	public void caricaComboBox(ComboBox<ModelloModello> combobox,
			String fascia, ObservableList<ModelloModello> dati) {
		carica(combobox, dati);
		for (Iterator<ModelloModello> it = dati.iterator(); it.hasNext();) {
			ModelloModello modello = it.next();
			if (!modello.getFascia().equalsIgnoreCase(fascia)) {
				it.remove();
			}
		}
	}

	public void cercaComboBox(ComboBox<ModelloModello> combobox, String fascia,
			ObservableList<ModelloModello> dati) {
		if (combobox != null && dati != null) {
			int i = 0;
			for (ModelloModello modello : dati) {
				if (modello.getFascia().equalsIgnoreCase(fascia)) {
					combobox.getSelectionModel().select(i);
				}
				i++;
			}
		}
	}

}
