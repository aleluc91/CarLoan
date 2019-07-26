package presentation.gui;

import presentation.controller.FrontController;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utility.Messaggi;

class Amministrazione extends Schermata {

	private static final String PERCORSO_FXML = "fxml/Amministrazione.fxml";
	private static final String TITOLO = "Amministrazione Sistema";

	public Amministrazione() {
		super(PERCORSO_FXML, TITOLO);
		
		super.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				int risultato = Messaggi.esci();
				if (risultato == 1) {
					FrontController fc = new FrontController();
					Stage stage = (Stage) fc.processaRichiesta("mostraLogin",
							null);
					stage.show();
				} else if (risultato == 2) {
					close();
				} else {
					we.consume();
				}
			}
		});
	}

}
