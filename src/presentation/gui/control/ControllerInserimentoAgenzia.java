package presentation.gui.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import presentation.gui.SchermataModificabile;

import presentation.gui.modelli_tabella.ModelloAgenzia;
import utility.MD5;
import utility.controlli_esistenza.EsistenzaCA;
import utility.controlli_esistenza.EsistenzaUsername;
import utility.validazione.ValidazioneFactory;
import business.applicationservice.transfer.Valori;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllerInserimentoAgenzia extends ControllerModificabile
		implements Initializable {

	private ModelloAgenzia modelloAgenzia;
	public String username;
	public String password;

	@FXML
	private Node root;

	@FXML
	private ImageView imgPrincipale;

	@FXML
	private Label lblTitolo;

	@FXML
	private TextField txtUsername;

	@FXML
	private Label lblErroreUsername;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Label lblErrorePassword;

	@FXML
	private TextField txtCodiceAgenzia;

	@FXML
	private Label lblErroreCodice;

	@FXML
	private TextField txtNomeAgenzia;

	@FXML
	private Label lblErroreNome;

	@FXML
	private TextField txtIndirizzo;

	@FXML
	private Label lblErroreIndirizzo;

	@FXML
	private TextField txtCitta;

	@FXML
	private Label lblErroreCitta;

	@FXML
	private TextField txtCAP;

	@FXML
	private Label lblErroreCAP;

	@FXML
	private Button btnConferma;

	@FXML
	private Button btnIndietro;

	private EsistenzaCA inAgenzia = new EsistenzaCA();
	private EsistenzaUsername inAutenticazione = new EsistenzaUsername();

	public void setModello(ModelloAgenzia modello) {
		this.modelloAgenzia = modello;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setRoot(root);
		imgPrincipale.setImage(new Image(SchermataModificabile.class.getResourceAsStream(
				"fxml/immagini/carloan.png")));
		lblTitolo.setText("Nuova Agenzia");
		root.addEventHandler(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
						if (event.getCode().equals(KeyCode.ENTER))
							aggiungiAgenzia();
					}

				});
	}

	public void isModifica() {
		modifica = true;
		lblTitolo.setText("Modifica Agenzia");
		txtUsername.setText(username);
		txtCodiceAgenzia.setText(modelloAgenzia.getCodice());
		txtNomeAgenzia.setText(modelloAgenzia.getNome());
		txtIndirizzo.setText(modelloAgenzia.getIndirizzo());
		txtCitta.setText(modelloAgenzia.getCitta());
		txtCAP.setText(modelloAgenzia.getCap());
	}

	@FXML
	private void gestisciBottoneConferma(ActionEvent event) {
		aggiungiAgenzia();
	}

	private void aggiungiAgenzia() {
		boolean controlloUsername = ValidazioneFactory.getValidazione(
				"Username").valida(txtUsername, lblErroreUsername);
		boolean controlloPassword = ValidazioneFactory.getValidazione(
				"Password").valida(txtPassword, lblErrorePassword);
		boolean controlloCodice = ValidazioneFactory.getValidazione("CA")
				.valida(txtCodiceAgenzia, lblErroreCodice);
		boolean controlloNome = ValidazioneFactory.getValidazione("NA").valida(
				txtNomeAgenzia, lblErroreNome);
		boolean controlloVia = ValidazioneFactory.getValidazione("Indirizzo")
				.valida(txtIndirizzo, lblErroreIndirizzo);
		boolean controlloCitta = ValidazioneFactory.getValidazione("Citta")
				.valida(txtCitta, lblErroreCitta);
		boolean controlloCAP = ValidazioneFactory.getValidazione("CAP").valida(
				txtCAP, lblErroreCAP);
		if (!controlloUsername && !controlloPassword && !controlloCodice
				&& !controlloNome && !controlloVia && !controlloCitta
				&& !controlloCAP)
			if (modifica) {
				if (txtCodiceAgenzia.getText().trim()
						.equalsIgnoreCase(modelloAgenzia.getCodice()))
					aggiungi("modificaAgenzia", leggiValori());
				else {
					if (!inAgenzia.esistenza(txtCodiceAgenzia.getText(),
							lblErroreCodice))
						aggiungi("modificaAgenzia", leggiValori());
				}
			} else {
				if (!inAgenzia.esistenza(txtCodiceAgenzia.getText(),
						lblErroreCodice)
						&& !inAutenticazione.esistenza(txtUsername.getText(),
								lblErroreUsername))
					aggiungi("nuovaAgenzia", leggiValori());
			}
	}

	@FXML
	private void gestisciBottoneIndietro() {
		chiudi(root);
	}

	private List<Valori> leggiValori() {
		List<Valori> valori = new ArrayList<Valori>();
		valori.add(new Valori(txtUsername.getText()));
		valori.add(new Valori(MD5.getMD5(txtPassword.getText())));
		valori.add(new Valori(txtCodiceAgenzia.getText()));
		valori.add(new Valori(txtNomeAgenzia.getText()));
		valori.add(new Valori(txtIndirizzo.getText()));
		valori.add(new Valori(txtCitta.getText()));
		valori.add(new Valori(txtCAP.getText()));
		if (modifica)
			valori.add(new Valori(modelloAgenzia.getCodice()));
		return valori;
	}

}
