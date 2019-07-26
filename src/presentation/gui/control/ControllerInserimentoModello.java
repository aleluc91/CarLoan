package presentation.gui.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import business.applicationservice.transfer.Valori;
import presentation.gui.Schermata;
import presentation.gui.modelli_tabella.ModelloFascia;
import presentation.gui.modelli_tabella.ModelloModello;
import presentation.gui.modelli_tabella.carica_modelli.CaricaFascia;
import utility.controlli_esistenza.EsistenzaModello;
import utility.validazione.ValidazioneFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllerInserimentoModello extends ControllerModificabile
		implements Initializable {

	private ModelloModello modello;

	private String inFascia;

	@FXML
	private Node root;

	@FXML
	private ImageView imgPrincipale;

	@FXML
	private Label lblTitolo;

	@FXML
	private TextField txtModello;

	@FXML
	private Label lblErroreModello;

	@FXML
	private ComboBox<ModelloFascia> cmbFascia;

	@FXML
	private Label lblErroreFascia;

	@FXML
	private Button btnConferma;

	@FXML
	private Button btnAnnulla;

	private ObservableList<ModelloFascia> datiFascia = FXCollections
			.observableArrayList();

	private EsistenzaModello inModello = new EsistenzaModello();

	public void setModello(ModelloModello modello) {
		this.modello = modello;
	}

	@Override
	public void isModifica() {
		// TODO Auto-generated method stub
		modifica = true;
		lblTitolo.setText("Modifica Modello");
		txtModello.setText(modello.getModello());
		CaricaFascia.getIstanza().cercaComboBox(cmbFascia, modello.getFascia(),
				datiFascia);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setRoot(root);
		imgPrincipale.setImage(new Image(Schermata.class.getResourceAsStream(
				"fxml/immagini/carloan.png")));
		lblTitolo.setText("Nuovo Modello");
		CaricaFascia.getIstanza().caricaComboBox(cmbFascia, datiFascia);
		root.addEventHandler(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
						if (event.getCode().equals(KeyCode.ENTER))
							aggiungiModello();
					}

				});
	}

	@FXML
	private void gestisciBottoneConferma(ActionEvent event) {
		aggiungiModello();
	}

	private void aggiungiModello() {
		boolean controlloModello = ValidazioneFactory.getValidazione("Modello")
				.valida(txtModello, lblErroreModello);
		if (!controlloModello) {
			int indice = cmbFascia.getSelectionModel().getSelectedIndex();
			if (indice > -1) {
				inFascia = cmbFascia.getSelectionModel().getSelectedItem()
						.getId();
				if (modifica == true) {
					if (txtModello.getText().equalsIgnoreCase(
							modello.getModello())
							&& inFascia.equalsIgnoreCase(modello.getFascia()))
						aggiungi("modificaModello", leggiValori());
					else {
						if (!inModello.esistenza(txtModello.getText(),
								inFascia, lblErroreModello))
							aggiungi("modificaModello", leggiValori());
					}
				} else {
					if (!inModello.esistenza(txtModello.getText(), inFascia,
							lblErroreModello))
						aggiungi("nuovoModello", leggiValori());
				}
			}
		}
	}

	@FXML
	private void gestisciBottoneAnnulla() {
		chiudi(root);
	}

	private List<Valori> leggiValori() {
		List<Valori> valori = new ArrayList<Valori>();
		valori.add(new Valori(txtModello.getText()));
		valori.add(new Valori(inFascia));
		if (modifica) {
			valori.add(new Valori(modello.getModello()));
			valori.add(new Valori(modello.getFascia()));
		}
		return valori;
	}

}
