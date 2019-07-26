package presentation.gui.modelli_tabella.carica_modelli;

import java.util.Iterator;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import presentation.gui.modelli_tabella.ModelloContratto;
import entity.Contratto;
import entity.Sessione;

public class CaricaContratto extends Carica<ModelloContratto, Contratto> {

	private static CaricaContratto istanza = null;

	private static final String RICHIESTA = "leggiContratto";

	private CaricaContratto() {
		super(ModelloContratto.class, Contratto.class, RICHIESTA);
	}

	public static CaricaContratto getIstanza() {
		if (istanza == null)
			return new CaricaContratto();
		else
			return istanza;

	}

	@Override
	public void caricaTabella(TableView<ModelloContratto> tabella,
			ObservableList<ModelloContratto> dati) {
		carica(tabella, dati);
		for (Iterator<ModelloContratto> it = dati.iterator(); it.hasNext();) {
			ModelloContratto modello = it.next();
			System.out.println(modello.getAttivo());
			if (!modello.getAgenzia().equalsIgnoreCase(
					Sessione.getAutenticazione().getAgenzia())
					&& !modello.getAgenziaRitorno().equalsIgnoreCase(
							Sessione.getAutenticazione().getAgenzia())
					|| !modello.getAttivo().equalsIgnoreCase("si")) {
				it.remove();
			}
		}
	}

	public void caricaTabella(TableView<ModelloContratto> tabella,
			String valore, ObservableList<ModelloContratto> dati) {
		carica(tabella, dati);
		for (Iterator<ModelloContratto> it = dati.iterator(); it.hasNext();) {
			ModelloContratto modello = it.next();
			if (!modello.getAgenzia().equalsIgnoreCase(
					Sessione.getAutenticazione().getAgenzia())
					&& !modello.getAgenziaRitorno().equalsIgnoreCase(
							Sessione.getAutenticazione().getAgenzia())
					&& !modello.getAttivo().equalsIgnoreCase(valore)) {
				it.remove();
			}
		}
	}

	@Override
	public void caricaComboBox(ComboBox<ModelloContratto> combobox,
			ObservableList<ModelloContratto> dati) {
		carica(combobox, dati);

	}

	@Override
	public void cercaComboBox(ComboBox<ModelloContratto> combobox,
			String ricerca, ObservableList<ModelloContratto> dati) {
		// TODO Auto-generated method stub

	}

}
