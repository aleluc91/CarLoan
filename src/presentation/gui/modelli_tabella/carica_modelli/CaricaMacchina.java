package presentation.gui.modelli_tabella.carica_modelli;

import java.util.Iterator;

import entity.Macchina;
import entity.Sessione;
import presentation.gui.modelli_tabella.ModelloMacchina;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

public class CaricaMacchina extends Carica<ModelloMacchina, Macchina> {

	private static CaricaMacchina istanza = null;

	private static final String RICHIESTA = "leggiMacchina";

	private CaricaMacchina() {
		super(ModelloMacchina.class, Macchina.class, RICHIESTA);
	}

	public static CaricaMacchina getIstanza() {
		if (istanza == null)
			return new CaricaMacchina();
		else
			return istanza;
	}

	@Override
	public void caricaTabella(TableView<ModelloMacchina> tabella,
			ObservableList<ModelloMacchina> dati) {
		carica(tabella, dati);
	}

	public void caricaTabella(TableView<ModelloMacchina> tabella,
			String valore, ObservableList<ModelloMacchina> dati) {
		carica(tabella, dati);
		for (Iterator<ModelloMacchina> it = dati.iterator(); it.hasNext();) {
			ModelloMacchina modello = it.next();
			if (!modello.getAgenzia().equalsIgnoreCase(valore)) {
				it.remove();
			}
		}
	}

	@Override
	public void caricaComboBox(ComboBox<ModelloMacchina> combobox,
			ObservableList<ModelloMacchina> dati) {
		carica(combobox, dati);

	}

	public void caricaComboBox(ComboBox<ModelloMacchina> combobox,
			String valore, ObservableList<ModelloMacchina> dati) {
		carica(combobox, dati);
		for (Iterator<ModelloMacchina> it = dati.iterator(); it.hasNext();) {
			ModelloMacchina modello = it.next();
			if (!modello.getModello().equalsIgnoreCase(valore)
					|| !modello.getNoleggio().equalsIgnoreCase("Disponibile")
					|| !modello.getManutenzione().equalsIgnoreCase(
							"Disponibile")
					|| !modello.getAgenzia().equalsIgnoreCase(
							Sessione.getAutenticazione().getAgenzia())) {
				it.remove();
			}
		}
	}

	@Override
	public void cercaComboBox(ComboBox<ModelloMacchina> combobox,
			String ricerca, ObservableList<ModelloMacchina> dati) {

	}

}
