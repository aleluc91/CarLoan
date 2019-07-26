package presentation.gui.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import presentation.gui.modelli_tabella.ModelloFascia;
import utility.controlli_esistenza.EsistenzaIdentificativo;
import utility.validazione.ValidazioneFactory;
import business.applicationservice.transfer.Valori;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllerInserimentoFascia extends ControllerModificabile
		implements Initializable {

	private ModelloFascia modelloFascia;

	@FXML
	private Node root;

	@FXML
	private Label lblTitolo;

	@FXML
	private TextField txtIdentificativo;

	@FXML
	private Label lblErroreIdentificativo;

	@FXML
	private TextArea txtRequisiti;

	@FXML
	private Label lblErroreRequisiti;

	@FXML
	private TextField txtCosto;

	@FXML
	private Label lblErroreCosto;

	@FXML
	private Button btnConferma;

	@FXML
	private Button btnAnnulla;

	private EsistenzaIdentificativo inFascia = new EsistenzaIdentificativo();

	public void setModello(ModelloFascia modello) {
		this.modelloFascia = modello;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setRoot(root);
		lblTitolo.setText(" Nuova Fascia");
		root.addEventHandler(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
						if (event.getCode().equals(KeyCode.ENTER))
							aggiungiFascia();
					}

				});
	}

	@Override
	public void isModifica() {
		// TODO Auto-generated method stub
		modifica = true;
		lblTitolo.setText("Modifica Fascia");
		txtIdentificativo.setText(modelloFascia.getId());
		txtRequisiti.setText(modelloFascia.getRequisiti());
		txtCosto.setText(String.valueOf(modelloFascia.getCosto()));
	}

	@FXML
	private void gestisciBottoneConferma(ActionEvent event) {
		aggiungiFascia();
	}

	private void aggiungiFascia() {
		boolean controlloIdentificativo = ValidazioneFactory.getValidazione(
				"Identificativo").valida(txtIdentificativo,
				lblErroreIdentificativo);
		boolean controlloRequisiti = ValidazioneFactory.getValidazione(
				"Descrizione").valida(txtRequisiti, lblErroreRequisiti);
		boolean controlloCosto = ValidazioneFactory.getValidazione("Double")
				.valida(txtCosto, lblErroreCosto);
		if (!controlloIdentificativo && !controlloRequisiti && !controlloCosto)
			if (modifica == true) {
				if (txtIdentificativo.getText().trim()
						.equalsIgnoreCase(modelloFascia.getId()))
					aggiungi("modificaFascia", leggiValori());
				else {
					if (!inFascia.esistenza(txtIdentificativo.getText().trim(),
							lblErroreIdentificativo))
						aggiungi("modificaFascia", leggiValori());
				}
			} else {
				if (!inFascia.esistenza(txtIdentificativo.getText().trim(),
						lblErroreIdentificativo))
					aggiungi("nuovaFascia", leggiValori());
			}
	}

	@FXML
	private void gestisciBottoneAnnulla() {
		chiudi(root);
	}

	private List<Valori> leggiValori() {
		List<Valori> valori = new ArrayList<Valori>();
		valori.add(new Valori(txtIdentificativo.getText().trim()));
		valori.add(new Valori(txtRequisiti.getText()));
		valori.add(new Valori(new Double(txtCosto.getText().trim())));
		if (modifica)
			valori.add(new Valori(modelloFascia.getId()));
		return valori;
	}

}
