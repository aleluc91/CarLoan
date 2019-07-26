package presentation.gui;

import java.util.List;

import presentation.gui.control.ControllerInserimentoModello;
import presentation.gui.modelli_tabella.ModelloModello;
import business.applicationservice.transfer.Valori;

public class InserimentoModello extends
		SchermataModificabile<ControllerInserimentoModello> {

	private static final String PERCORSO_FXML = "fxml/InserimentoModello.fxml";
	private static final String TITOLO1 = "Inserimento Modello";
	private static final String TITOLO2 = "Modifica Modello";

	public InserimentoModello() {
		super(PERCORSO_FXML, TITOLO1);
	}

	public InserimentoModello(List<Valori> valori) {
		super(PERCORSO_FXML, TITOLO2);
		inizializza(controller, valori);
		controller.isModifica();
	}

	@Override
	protected void inizializza(ControllerInserimentoModello controller,
			List<Valori> valori) {
		// TODO Auto-generated method stub
		ModelloModello modello = (ModelloModello) valori.get(0).getModello();
		controller.setModello(modello);
	}

}
