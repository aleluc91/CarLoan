package presentation.gui.control;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import presentation.gui.SchermataModificabile;
import entity.ContrattoParziale;
import utility.controlli_esistenza.EsistenzaCodice;
import utility.validazione.ValidazioneFactory;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllerInserimentoNuovoContratto extends Controller implements
		Initializable {

	@FXML
	private Node root;

	@FXML
	private ImageView imgPrincipale;

	@FXML
	private Label lblCodiceContratto;

	@FXML
	private TextField txtNome;

	@FXML
	public Label lblErroreNome;

	@FXML
	private TextField txtCognome;

	@FXML
	public Label lblErroreCognome;

	@FXML
	private TextField txtTelefono;

	@FXML
	public Label lblErroreTelefono;

	@FXML
	private Button btnAvanti;

	@FXML
	private Button btnAnnulla;

	private EsistenzaCodice inContratto = new EsistenzaCodice();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imgPrincipale.setImage(new Image(SchermataModificabile.class.getResourceAsStream(
				"fxml/immagini/icona7.png")));
		boolean creato = false;
		String codice;
		do {
			codice = generaCodice();
			creato = !inContratto.esistenza(codice, null);
		} while (!creato);
		ContrattoParziale.getIstanza().setCodiceContratto(codice);
		lblCodiceContratto.setText(codice);
		root.addEventHandler(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
						if (event.getCode().equals(KeyCode.ENTER))
							nuovoContratto();
					}

				});
	}

	private String generaCodice() {
		Random generatore = new Random();
		String codice = "";
		for (int i = 1; i <= 5; ++i) {
			codice = codice + String.valueOf(generatore.nextInt(9));
		}
		return codice;

	}

	@FXML
	private void gestisciBottoneAvanti() {
		nuovoContratto();
	}

	private void nuovoContratto() {
		boolean controlloNome = ValidazioneFactory.getValidazione("Nome")
				.valida(txtNome, lblErroreNome);
		boolean controlloCognome = ValidazioneFactory.getValidazione("Cognome")
				.valida(txtCognome, lblErroreCognome);
		boolean controlloTelefono = ValidazioneFactory.getValidazione(
				"Telefono").valida(txtTelefono, lblErroreTelefono);
		if (!controlloNome && !controlloCognome && !controlloTelefono) {
			ContrattoParziale.getIstanza().setCliente(txtNome.getText(),
					txtCognome.getText(), txtTelefono.getText());
			aggiungi("mostraInserimentoNoleggio");
			chiudi(root);
		}
	}

	@FXML
	private void gestisciBottoneAnnulla() {
		chiudi(root);
	}

}
