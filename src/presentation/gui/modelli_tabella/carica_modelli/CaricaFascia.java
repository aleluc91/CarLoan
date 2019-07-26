package presentation.gui.modelli_tabella.carica_modelli;

import entity.Fascia;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import presentation.gui.modelli_tabella.ModelloFascia;

public class CaricaFascia extends Carica<ModelloFascia, Fascia> {

	private static CaricaFascia istanza = null;

	private static final String RICHIESTA = "leggiFascia";

	private CaricaFascia() {
		super(ModelloFascia.class, Fascia.class, RICHIESTA);
	}

	public static CaricaFascia getIstanza() {
		if (istanza == null)
			return new CaricaFascia();
		else
			return istanza;
	}

	@Override
	public void caricaTabella(TableView<ModelloFascia> tabella,
			ObservableList<ModelloFascia> dati) {
		carica(tabella, dati);
	}

	@Override
	public void caricaComboBox(ComboBox<ModelloFascia> combobox,
			ObservableList<ModelloFascia> dati) {
		carica(combobox, dati);
	}

	@Override
	public void cercaComboBox(ComboBox<ModelloFascia> combobox, String ricerca,
			ObservableList<ModelloFascia> dati) {
		int i = 0;
		for (ModelloFascia modello : dati) {
			if (modello.getId().equalsIgnoreCase(ricerca)) {
				combobox.getSelectionModel().select(i);
			}
			i++;
		}
	}

}
