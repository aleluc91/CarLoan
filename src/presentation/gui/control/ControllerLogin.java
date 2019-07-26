package presentation.gui.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entity.Autenticazione;
import entity.Sessione;
import business.applicationservice.transfer.Valori;
import presentation.controller.FrontController;
import utility.Messaggi;
import utility.validazione.ValidazioneFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ControllerLogin extends Controller implements Initializable {

	@FXML
	private Node root;

	@FXML
	private TextField txtUsername;

	@FXML
	Label lblErroreUsername;

	@FXML
	private PasswordField txtPassword;

	@FXML
	Label lblErrorePassword;

	@FXML
	private Button btnLogin;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		root.addEventHandler(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
						if (event.getCode().equals(KeyCode.ENTER))
							login();
					}

				});
	}

	private void login() {
		boolean controlloUsername = ValidazioneFactory.getValidazione(
				"Username").valida(txtUsername, lblErroreUsername);
		boolean controlloPassword = ValidazioneFactory.getValidazione(
				"Password").valida(txtPassword, lblErrorePassword);
		if (!controlloUsername && !controlloPassword) {
			List<Valori> values = new ArrayList<Valori>();
			values.add(new Valori(txtUsername.getText().trim()));
			values.add(new Valori(txtPassword.getText().trim()));
			FrontController fc = new FrontController();
			Autenticazione autenticazione = (Autenticazione) fc
					.processaRichiesta("cercaAutenticazione", values);
			if (autenticazione != null) {
				Sessione.setAutenticazione(autenticazione);
				if (Sessione.getAutenticazione().getPrivilegi()
						.equalsIgnoreCase("admin")) {
					fc = new FrontController();
					Stage administrationStage = (Stage) fc.processaRichiesta(
							"mostraAmministrazione", null);
					administrationStage.show();
					chiudi(root);
				} else if (Sessione.getAutenticazione().getPrivilegi()
						.equalsIgnoreCase("agenzia")) {
					fc = new FrontController();
					Stage mainAgenziaStage = (Stage) fc.processaRichiesta(
							"mostraMainAgenzia", null);
					mainAgenziaStage.show();
					chiudi(root);
				}
			} else {
				String type = "errore";
				String title = "Errore!";
				String header = "Login non riuscito!";
				String content = "Username o password errati! Riprovare.";
				Messaggi.showMessage(type, title, header, content);
			}
		}
	}

	@FXML
	private void gestisciLogin(ActionEvent event) {
		login();
	}

}
