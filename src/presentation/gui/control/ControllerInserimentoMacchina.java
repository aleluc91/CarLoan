package presentation.gui.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entity.Sessione;
import presentation.controller.FrontController;
import presentation.gui.Schermata;
import presentation.gui.SchermataModificabile;
import presentation.gui.modelli_tabella.ModelloAgenzia;
import presentation.gui.modelli_tabella.ModelloFascia;
import presentation.gui.modelli_tabella.ModelloModello;
import presentation.gui.modelli_tabella.carica_modelli.CaricaAgenzia;
import presentation.gui.modelli_tabella.carica_modelli.CaricaFascia;
import presentation.gui.modelli_tabella.carica_modelli.CaricaModello;
import utility.controlli_esistenza.EsistenzaTarga;
import utility.validazione.ValidazioneComboBox;
import utility.validazione.ValidazioneFactory;
import business.applicationservice.transfer.Valori;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class ControllerInserimentoMacchina extends ControllerModificabile
		implements Initializable {

	private String targa;
	private String fascia;
	private String modello;
	private String agenzia;
	private String noleggiata;
	private String manutenzione;
	private long chilometraggio;

	@FXML
	private Node root;

	@FXML
	private ImageView imgPrincipale;

	@FXML
	private Label lblTitolo;

	@FXML
	private TextField txtTarga;

	@FXML
	private Label lblErroreTarga;

	@FXML
	private ComboBox<ModelloFascia> cmbFascia;

	@FXML
	private Label lblErroreFascia;

	@FXML
	private ComboBox<ModelloModello> cmbModello;

	@FXML
	private Label lblErroreModello;

	@FXML
	private ComboBox<ModelloAgenzia> cmbAgenzia;

	@FXML
	private Label lblErroreAgenzia;

	@FXML
	private ComboBox<String> cmbNoleggiata;

	@FXML
	private Label lblErroreNoleggiata;

	@FXML
	private ComboBox<String> cmbManutenzione;

	@FXML
	private Label lblErroreManutenzione;

	@FXML
	private TextField txtChilometraggio;

	@FXML
	private Label lblErroreChilometraggio;

	@FXML
	private Button btnConferma;

	@FXML
	private Button btnAnnulla;

	private ObservableList<ModelloFascia> datiFascia = FXCollections
			.observableArrayList();

	private ObservableList<ModelloModello> datiModello = FXCollections
			.observableArrayList();

	private ObservableList<ModelloAgenzia> datiAgenzia = FXCollections
			.observableArrayList();

	private ObservableList<String> datiNoleggiata = FXCollections
			.observableArrayList();

	private ObservableList<String> datiManutenzione = FXCollections
			.observableArrayList();

	private EsistenzaTarga inMacchina = new EsistenzaTarga();

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public void setFascia(String fascia) {
		this.fascia = fascia;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public void setAgenzia(String agenzia) {
		this.agenzia = agenzia;
	}

	public void setNoleggiata(String noleggiata) {
		this.noleggiata = noleggiata;
	}

	public void setManutenzione(String manutenzione) {
		this.manutenzione = manutenzione;
	}

	public void setChilometraggio(Long chilometraggio) {
		this.chilometraggio = chilometraggio;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setRoot(root);
		imgPrincipale.setImage(new Image(SchermataModificabile.class.getResourceAsStream(
				"fxml/immagini/carloan.png")));
		lblTitolo.setText("Nuova Macchina");
		root.addEventHandler(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
						if (event.getCode().equals(KeyCode.ENTER))
							aggiungiMacchina();
					}

				});
		CaricaFascia.getIstanza().caricaComboBox(cmbFascia, datiFascia);
		CaricaAgenzia.getIstanza().caricaComboBox(cmbAgenzia, datiAgenzia);
		CaricaModello.getIstanza().caricaComboBox(cmbModello, fascia,
				datiModello);
		caricaNoleggiata();
		caricaManutenzione();
		if (Sessione.getAutenticazione().getPrivilegi()
				.equalsIgnoreCase("admin")) {
			cmbNoleggiata.getSelectionModel().select(0);
			cmbManutenzione.getSelectionModel().select(0);
			txtChilometraggio.setText(String.valueOf(chilometraggio));
			cmbNoleggiata.setDisable(true);
			cmbManutenzione.setDisable(true);
			txtChilometraggio.setDisable(true);
		} else if (Sessione.getAutenticazione().getPrivilegi()
				.equalsIgnoreCase("agenzia")) {
			cmbNoleggiata.getSelectionModel().select(0);
			cmbManutenzione.getSelectionModel().select(0);
			txtChilometraggio.setText(String.valueOf(chilometraggio));
			txtTarga.setDisable(true);
			cmbAgenzia.setDisable(true);
			cmbFascia.setDisable(true);
			cmbModello.setDisable(true);
			cmbNoleggiata.setDisable(true);
			cmbManutenzione.setDisable(false);
			txtChilometraggio.setDisable(true);
		}
		inizializzaListener();
	}

	@Override
	public void isModifica() {
		modifica = true;
		lblTitolo.setText("Modifica Macchina");
		txtTarga.setText(targa);
		CaricaFascia.getIstanza().cercaComboBox(cmbFascia, fascia, datiFascia);
		CaricaModello.getIstanza().caricaComboBox(cmbModello, fascia,
				datiModello);
		CaricaModello.getIstanza().cercaComboBox(cmbModello, fascia,
				datiModello);
		CaricaAgenzia.getIstanza().cercaComboBox(cmbAgenzia, agenzia,
				datiAgenzia);
		cmbNoleggiata.getSelectionModel().select(noleggiata);
		cmbManutenzione.getSelectionModel().select(manutenzione);
		txtChilometraggio.setText(String.valueOf(chilometraggio));
	}

	private void caricaNoleggiata() {
		datiNoleggiata.add("Disponibile");
		datiNoleggiata.add("Indisponibile");
		cmbNoleggiata.setItems(datiNoleggiata);
	}

	private void caricaManutenzione() {
		datiManutenzione.add("Disponibile");
		datiManutenzione.add("Ordinaria");
		datiManutenzione.add("Straordinaria");
		cmbManutenzione.setItems(datiManutenzione);
	}

	@FXML
	private void gestisciBottoneConferma() {
		aggiungiMacchina();
	}

	private void aggiungiMacchina() {
		if (Sessione.getAutenticazione().getPrivilegi()
				.equalsIgnoreCase("admin")) {
			boolean controlloTarga = ValidazioneFactory.getValidazione("Targa")
					.valida(txtTarga, lblErroreTarga);
			boolean controlloFascia = new ValidazioneComboBox<ModelloFascia>()
					.valida(cmbFascia, lblErroreFascia);
			boolean controlloModello = new ValidazioneComboBox<ModelloModello>()
					.valida(cmbModello, lblErroreModello);
			boolean controlloAgenzia = new ValidazioneComboBox<ModelloAgenzia>()
					.valida(cmbAgenzia, lblErroreAgenzia);
			boolean controlloChilometraggio = ValidazioneFactory
					.getValidazione("Double").valida(txtChilometraggio,
							lblErroreChilometraggio);
			if (!controlloTarga && !controlloFascia && !controlloModello
					&& !controlloAgenzia && !controlloChilometraggio) {
				if (modifica == true) {
					if (txtTarga.getText().equalsIgnoreCase(targa))
						aggiungi("modificaMacchina", leggiValori());
					else {
						if (!inMacchina.esistenza(txtTarga.getText(),
								lblErroreTarga))
							aggiungi("modificaMacchina", leggiValori());
					}
				} else {
					if (!inMacchina.esistenza(txtTarga.getText(),
							lblErroreTarga))
						aggiungi("nuovaMacchina", leggiValori());
				}
			}
		} else if (Sessione.getAutenticazione().getPrivilegi()
				.equalsIgnoreCase("agenzia")) {
			FrontController fc = new FrontController();
			List<Valori> valori = new ArrayList<Valori>();
			valori.add(new Valori("manutenzione"));
			valori.add(new Valori(cmbManutenzione.getSelectionModel()
					.getSelectedItem()));
			valori.add(new Valori(txtTarga.getText()));
			fc.processaRichiesta("modificaValoreMacchina", valori);
			chiudi(root);
		}
	}

	@FXML
	private void gestisciBottoneAnnulla() {
		chiudi(root);
	}

	private List<Valori> leggiValori() {
		List<Valori> valori = new ArrayList<Valori>();
		valori.add(new Valori(txtTarga.getText()));
		valori.add(new Valori(cmbFascia.getSelectionModel().getSelectedItem()
				.getId()));
		valori.add(new Valori(cmbModello.getSelectionModel().getSelectedItem()
				.getModello()));
		valori.add(new Valori(cmbAgenzia.getSelectionModel().getSelectedItem()
				.getCodice()));
		valori.add(new Valori(cmbNoleggiata.getSelectionModel()
				.getSelectedItem()));
		valori.add(new Valori(cmbManutenzione.getSelectionModel()
				.getSelectedItem()));
		valori.add(new Valori(new Long(txtChilometraggio.getText().trim())));
		if (modifica)
			valori.add(new Valori(targa));
		return valori;
	}

	private void inizializzaListener() {
		cmbFascia.valueProperty().addListener(
				new ChangeListener<ModelloFascia>() {

					@Override
					public void changed(
							ObservableValue<? extends ModelloFascia> observable,
							ModelloFascia oldValue, ModelloFascia newValue) {
						// TODO Auto-generated method stub
						CaricaModello.getIstanza().caricaComboBox(cmbModello,
								observable.getValue().getId(), datiModello);
					}

				});
	}

}
