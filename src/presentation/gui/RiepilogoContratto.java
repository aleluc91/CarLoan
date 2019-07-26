package presentation.gui;

import java.util.List;

import presentation.gui.control.ControllerRiepilogoContratto;
import presentation.gui.modelli_tabella.ModelloContratto;
import business.applicationservice.transfer.Valori;

public class RiepilogoContratto extends
		SchermataModificabile<ControllerRiepilogoContratto> {

	private static final String PERCORSO_FXML = "fxml/RiepilogoContratto.fxml";
	private static final String TITOLO = "Riepilogo Contratto";

	public RiepilogoContratto() {
		super(PERCORSO_FXML, TITOLO);
	}

	public RiepilogoContratto(List<Valori> valori) {
		super(PERCORSO_FXML, TITOLO);
		inizializza(controller, valori);
		controller.isModifica();
	}

	@Override
	protected void inizializza(ControllerRiepilogoContratto controller,
			List<Valori> valori) {
		ModelloContratto dati = (ModelloContratto) valori.get(0).getModello();
		if (valori.size() <= 1) {
			controller.setModello(dati);
		} else {
			controller.setModello(dati);
			controller.setChiusura(true);
		}

	}

}
