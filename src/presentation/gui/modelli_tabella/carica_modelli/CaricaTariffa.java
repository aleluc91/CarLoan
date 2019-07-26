package presentation.gui.modelli_tabella.carica_modelli;

import entity.Tariffa;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import presentation.gui.modelli_tabella.ModelloTariffa;

public class CaricaTariffa extends Carica<ModelloTariffa, Tariffa> {

	private static CaricaTariffa istanza = null;

	private static final String RICHIESTA = "leggiTariffa";

	private CaricaTariffa() {
		super(ModelloTariffa.class, Tariffa.class, RICHIESTA);
	}

	public static CaricaTariffa getIstanza() {
		if (istanza == null) {
			return new CaricaTariffa();
		} else {
			return istanza;
		}
	}

	@Override
	public void caricaTabella(TableView<ModelloTariffa> tabella,
			ObservableList<ModelloTariffa> dati) {
		carica(tabella, dati);
	}

	@Override
	public void caricaComboBox(ComboBox<ModelloTariffa> combobox,
			ObservableList<ModelloTariffa> dati) {
		carica(combobox, dati);
	}

	@Override
	public void cercaComboBox(ComboBox<ModelloTariffa> combobox,
			String ricerca, ObservableList<ModelloTariffa> dati) {
		int i = 0;
		for (ModelloTariffa modello : dati) {
			if (modello.getId() == Integer.valueOf(ricerca)) {
				combobox.getSelectionModel().select(i);
			}
			i++;
		}
	}

}
