package presentation.gui;

import java.util.List;

import presentation.gui.control.ControllerInserimentoFascia;
import presentation.gui.modelli_tabella.ModelloFascia;
import business.applicationservice.transfer.Valori;

public class InserimentoFascia extends
		SchermataModificabile<ControllerInserimentoFascia> {

	private static final String PERCORSO_FXML = "fxml/InserimentoFascia.fxml";
	private static final String TITOLO1 = "Inserimento Fascia";
	private static final String TITOLO2 = "Modifica fascia";

	public InserimentoFascia() {
		super(PERCORSO_FXML, TITOLO1);
	}

	public InserimentoFascia(List<Valori> valori) {
		super(PERCORSO_FXML, TITOLO2);
		inizializza(controller, valori);
		controller.isModifica();
	}

	@Override
	protected void inizializza(ControllerInserimentoFascia controller,
			List<Valori> valori) {
		// TODO Auto-generated method stub
		ModelloFascia modello = (ModelloFascia) valori.get(0).getModello();
		controller.setModello(modello);
	}

}
