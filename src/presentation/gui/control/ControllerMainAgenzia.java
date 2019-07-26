package presentation.gui.control;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import entity.Sessione;
import presentation.gui.Schermata;
import presentation.gui.modelli_tabella.ModelloContratto;
import presentation.gui.modelli_tabella.ModelloMacchina;
import presentation.gui.modelli_tabella.carica_modelli.CaricaContratto;
import presentation.gui.modelli_tabella.carica_modelli.CaricaMacchina;
import utility.Messaggi;
import utility.controlli_esistenza.EsistenzaContratto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControllerMainAgenzia extends Controller implements Initializable {

	@FXML
	private Node root;

	@FXML
	private ImageView imgPrincipale;

	@FXML
	private Label lblAgenzia;

	@FXML
	private Button btnNuovoContratto;

	@FXML
	private TableView<ModelloContratto> tabellaContratto;

	@FXML
	private TableColumn<ModelloContratto, String> colonnaCodice;

	@FXML
	private TableColumn<ModelloContratto, String> colonnaNome;

	@FXML
	private TableColumn<ModelloContratto, String> colonnaCognome;

	@FXML
	private TableColumn<ModelloContratto, String> colonnaTelefono;

	@FXML
	private TableColumn<ModelloContratto, String> colonnaDataInizio;

	@FXML
	private TableColumn<ModelloContratto, String> colonnaDataFine;

	@FXML
	private TableColumn<ModelloContratto, Double> colonnaAcconto;

	private ObservableList<ModelloContratto> datiContratto = FXCollections
			.observableArrayList();

	@FXML
	private TableView<ModelloMacchina> tabellaMacchina;

	@FXML
	private TableColumn<ModelloMacchina, String> colonnaTarga;

	@FXML
	private TableColumn<ModelloMacchina, String> colonnaModelloMacchina;

	@FXML
	private TableColumn<ModelloMacchina, String> colonnaAgenzia;

	@FXML
	private TableColumn<ModelloMacchina, String> colonnaNoleggiata;

	@FXML
	private TableColumn<ModelloMacchina, String> colonnaManutenzione;

	@FXML
	private TableColumn<ModelloMacchina, Long> colonnaChilometraggio;

	private ObservableList<ModelloMacchina> datiMacchina = FXCollections
			.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblAgenzia.setText("Sei loggato come agenzia "
				+ Sessione.getAutenticazione().getAgenzia() + "!");
		imgPrincipale.setImage(new Image(Schermata.class.getResourceAsStream(
				"fxml/immagini/carloan.png")));
		colonnaCodice.setCellValueFactory(datiCella -> datiCella.getValue()
				.getCodiceProperty());
		colonnaNome.setCellValueFactory(datiCella -> datiCella.getValue()
				.getNomeProperty());
		colonnaCognome.setCellValueFactory(datiCella -> datiCella.getValue()
				.getCognomeProperty());
		colonnaTelefono.setCellValueFactory(datiCella -> datiCella.getValue()
				.getTelefonoProperty());
		colonnaDataInizio.setCellValueFactory(datiCella -> datiCella.getValue()
				.getDataInizioProperty());
		colonnaDataFine.setCellValueFactory(datiCella -> datiCella.getValue()
				.getDataFineProperty());
		colonnaAcconto.setCellValueFactory(datiCella -> datiCella.getValue()
				.getAccontoProperty().asObject());
		colonnaTarga.setCellValueFactory(datiCella -> datiCella.getValue()
				.propertyTarga());
		colonnaModelloMacchina.setCellValueFactory(datiCella -> datiCella
				.getValue().propertyModello());
		colonnaAgenzia.setCellValueFactory(datiCella -> datiCella.getValue()
				.propertyAgenzia());
		colonnaNoleggiata.setCellValueFactory(datiCella -> datiCella.getValue()
				.propertyNoleggio());
		colonnaManutenzione.setCellValueFactory(datiCella -> datiCella
				.getValue().propertyManutenzione());
		colonnaChilometraggio.setCellValueFactory(datiCella -> datiCella
				.getValue().propertyChilometraggio().asObject());
		aggiorna();
	}

	@FXML
	private void gestisciNuovoContratto() {

		aggiungi("mostraInserimentoContratto");
		aggiorna();
	}

	@FXML
	private void gestisciRiepilogo() {
		modifica(tabellaContratto, datiContratto, "mostraRiepilogoContratto");
		aggiorna();
	}

	@FXML
	private void gestisciModifica() {
		if (!datiMacchina.isEmpty()) {
			int indice = tabellaMacchina.getSelectionModel().getSelectedIndex();
			if (indice >= 0) {
				if (!EsistenzaContratto.verificaMacchina(tabellaMacchina
						.getSelectionModel().getSelectedItem().getTarga())) {
					modifica(tabellaMacchina, datiMacchina,
							"mostraInserimentoMacchina");
					aggiorna();
				} else {
					Messaggi.msgEsistenza();
				}
			} else {
				Messaggi.msgErroreSelezione();
			}
		}
	}

	@FXML
	private void gestisciChiudiContratto() {
		if (tabellaContratto.getSelectionModel().getSelectedIndex() >= 0) {
			LocalDate dataOdierna = LocalDate.parse(LocalDate.now().toString());
			LocalDate dataInizio = LocalDate.parse(tabellaContratto
					.getSelectionModel().getSelectedItem().getDataInizio());
			long differenza = ChronoUnit.DAYS.between(dataInizio, dataOdierna);
			if (differenza >= 0) {
				modifica(tabellaContratto, datiContratto,
						"mostraRiepilogoContratto", "");
				aggiorna();
			} else {
				Messaggi.msgErroreContratto();
			}
		}
	}

	private void aggiorna() {
		CaricaContratto.getIstanza().caricaTabella(tabellaContratto,
				datiContratto);
		CaricaMacchina.getIstanza().caricaTabella(tabellaMacchina,
				Sessione.getAutenticazione().getAgenzia(), datiMacchina);
	}
}
