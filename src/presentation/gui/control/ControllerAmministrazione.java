package presentation.gui.control;

import java.net.URL;
import java.util.ResourceBundle;

import presentation.gui.Schermata;
import presentation.gui.modelli_tabella.ModelloAgenzia;
import presentation.gui.modelli_tabella.ModelloFascia;
import presentation.gui.modelli_tabella.ModelloMacchina;
import presentation.gui.modelli_tabella.ModelloModello;
import presentation.gui.modelli_tabella.ModelloTariffa;
import presentation.gui.modelli_tabella.carica_modelli.CaricaAgenzia;
import presentation.gui.modelli_tabella.carica_modelli.CaricaFascia;
import presentation.gui.modelli_tabella.carica_modelli.CaricaMacchina;
import presentation.gui.modelli_tabella.carica_modelli.CaricaModello;
import presentation.gui.modelli_tabella.carica_modelli.CaricaTariffa;
import utility.Messaggi;
import utility.controlli_esistenza.EsistenzaContratto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerAmministrazione extends Controller implements
		Initializable {

	@FXML
	private Node root;

	@FXML
	private ImageView imgPrincipale;

	@FXML
	public TableView<ModelloAgenzia> tabellaAgenzia;

	@FXML
	private TableColumn<ModelloAgenzia, String> colonnaCodice;

	@FXML
	private TableColumn<ModelloAgenzia, String> colonnaNome;

	@FXML
	private TableColumn<ModelloAgenzia, String> colonnaIndirizzo;

	@FXML
	private TableColumn<ModelloAgenzia, String> colonnaCitta;

	@FXML
	private TableColumn<ModelloAgenzia, String> colonnaCap;

	public ObservableList<ModelloAgenzia> datiAgenzia = FXCollections
			.observableArrayList();

	@FXML
	public TableView<ModelloFascia> tabellaFascia;

	@FXML
	public TableColumn<ModelloFascia, String> colonnaIdentificativo;

	@FXML
	public TableColumn<ModelloFascia, String> colonnaDescrizione;

	@FXML
	public TableColumn<ModelloFascia, Double> colonnaCosto;

	public ObservableList<ModelloFascia> datiFascia = FXCollections
			.observableArrayList();

	@FXML
	public TableView<ModelloModello> tabellaModello;

	@FXML
	private TableColumn<ModelloModello, String> colonnaModello;

	@FXML
	private TableColumn<ModelloModello, String> colonnaFascia;

	public ObservableList<ModelloModello> datiModello = FXCollections
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

	@FXML
	private TableView<ModelloTariffa> tabellaTariffa;

	@FXML
	private TableColumn<ModelloTariffa, String> colonnaTipo;

	@FXML
	private TableColumn<ModelloTariffa, Long> colonnaChilometri;

	@FXML
	private TableColumn<ModelloTariffa, Double> colonnaTariffa;

	private ObservableList<ModelloTariffa> datiTariffa = FXCollections
			.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setRoot(root);
		imgPrincipale.setImage(new Image(Schermata.class.getResourceAsStream(
				"fxml/immagini/carloan.png")));
		colonnaCodice.setCellValueFactory(datiCella -> datiCella.getValue()
				.propertyCodice());
		colonnaNome.setCellValueFactory(datiCella -> datiCella.getValue()
				.propertyNome());
		colonnaIndirizzo.setCellValueFactory(datiCella -> datiCella.getValue()
				.propertyIndirizzo());
		colonnaCitta.setCellValueFactory(datiCella -> datiCella.getValue()
				.propertyCitta());
		colonnaCap.setCellValueFactory(datiCella -> datiCella.getValue()
				.propertyCap());
		colonnaIdentificativo.setCellValueFactory(datiCella -> datiCella
				.getValue().propertyId());
		colonnaDescrizione.setCellValueFactory(datiCella -> datiCella
				.getValue().propertyRequisiti());
		colonnaCosto.setCellValueFactory(datiCella -> datiCella.getValue()
				.propertyCosto().asObject());
		colonnaModello.setCellValueFactory(datiCella -> datiCella.getValue()
				.propertyModello());
		colonnaFascia.setCellValueFactory(datiCella -> datiCella.getValue()
				.propertyFascia());
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
		colonnaTipo.setCellValueFactory(datiCella -> datiCella.getValue()
				.getTipoProperty());
		colonnaChilometri.setCellValueFactory(datiCella -> datiCella.getValue()
				.getChilometriProperty().asObject());
		colonnaTariffa.setCellValueFactory(datiCella -> datiCella.getValue()
				.getTariffaProperty().asObject());
		aggiorna();

	}

	@FXML
	private void gestisciAggiungiAgenzia(ActionEvent event) {
		aggiungi("mostraInserimentoAgenzia");
		aggiorna();
	}

	@FXML
	private void gestisciModificaAgenzia(ActionEvent event) {
		if (!datiAgenzia.isEmpty()) {
			int indice = tabellaAgenzia.getSelectionModel().getSelectedIndex();
			if (indice >= 0) {
				if (!EsistenzaContratto.verificaAgenzia(tabellaAgenzia
						.getSelectionModel().getSelectedItem().getCodice())) {
					modifica(tabellaAgenzia, datiAgenzia,
							"mostraInserimentoAgenzia");
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
	private void gestisciEliminaAgenzia() {
		if (!datiAgenzia.isEmpty()) {
			int indice = tabellaAgenzia.getSelectionModel().getSelectedIndex();
			if (indice >= 0) {
				if (!EsistenzaContratto.verificaAgenzia(tabellaAgenzia
						.getSelectionModel().getSelectedItem().getCodice())) {
					String richiesta = "eliminaAgenzia";
					elimina(tabellaAgenzia, datiAgenzia, richiesta);
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
	public void gestisciAggiungiFascia(ActionEvent event) {
		aggiungi("mostraInserimentoFascia");
		aggiorna();
	}

	@FXML
	public void gestisciModificaFascia(ActionEvent event) {
		if (!datiFascia.isEmpty()) {
			int indice = tabellaFascia.getSelectionModel().getSelectedIndex();
			if (indice >= 0) {
				if (!EsistenzaContratto.verificaFascia(tabellaFascia
						.getSelectionModel().getSelectedItem().getId())) {
					modifica(tabellaFascia, datiFascia,
							"mostraInserimentoFascia");
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
	public void gestisciEliminaFascia() {
		if (!datiFascia.isEmpty()) {
			int indice = tabellaFascia.getSelectionModel().getSelectedIndex();
			if (indice >= 0) {
				if (!EsistenzaContratto.verificaFascia(tabellaFascia
						.getSelectionModel().getSelectedItem().getId())) {
					String richiesta = "eliminaFascia";
					elimina(tabellaFascia, datiFascia, richiesta);
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
	public void gestisciAggiungiModello(ActionEvent event) {
		if (!datiFascia.isEmpty()) {
			aggiungi("mostraInserimentoModello");
			aggiorna();
		} else {
			Messaggi.msgErroreSelezione();
		}
	}

	@FXML
	private void gestisciModificaModello(ActionEvent event) {
		if (!datiModello.isEmpty()) {
			int indice = tabellaModello.getSelectionModel().getSelectedIndex();
			if (indice >= 0) {
				if (!EsistenzaContratto.verificaModello(tabellaModello
						.getSelectionModel().getSelectedItem().getModello())) {
					modifica(tabellaModello, datiModello,
							"mostraInserimentoModello");
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
	public void gestisciEliminaModello() {
		if (!datiModello.isEmpty()) {
			int indice = tabellaModello.getSelectionModel().getSelectedIndex();
			if (indice >= 0) {
				if (!EsistenzaContratto.verificaModello(tabellaModello
						.getSelectionModel().getSelectedItem().getModello())) {
					String richiesta = "eliminaModello";
					elimina(tabellaModello, datiModello, richiesta);
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
	private void gestisciAggiungiMacchina() {
		aggiungi("mostraInserimentoMacchina");
		aggiorna();
	}

	@FXML
	private void gestisciModificaMacchina() {
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
	private void gestisciEliminaMacchina() {
		if (!datiMacchina.isEmpty()) {
			int indice = tabellaMacchina.getSelectionModel().getSelectedIndex();
			if (indice >= 0) {
				if (!EsistenzaContratto.verificaMacchina(tabellaMacchina
						.getSelectionModel().getSelectedItem().getTarga())) {
					String richiesta = "eliminaMacchina";
					elimina(tabellaMacchina, datiMacchina, richiesta);
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
	private void gestisciAggiungiTariffa() {
		aggiungi("mostraInserimentoTariffa");
		aggiorna();
	}

	@FXML
	private void gestisciModificaTariffa() {
		if (!datiTariffa.isEmpty()) {
			int indice = tabellaTariffa.getSelectionModel().getSelectedIndex();
			if (indice >= 0) {

				if (!EsistenzaContratto.verificaTariffa(tabellaTariffa
						.getSelectionModel().getSelectedItem().getId())) {
					modifica(tabellaTariffa, datiTariffa,
							"mostraInserimentoTariffa");
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
	private void gestisciEliminaTariffa() {
		if (!datiTariffa.isEmpty()) {
			int indice = tabellaTariffa.getSelectionModel().getSelectedIndex();
			if (indice >= 0) {
				System.out.println(tabellaTariffa.getSelectionModel()
						.getSelectedItem().getId());
				if (!EsistenzaContratto.verificaTariffa(tabellaTariffa
						.getSelectionModel().getSelectedItem().getId())) {
					elimina(tabellaTariffa, datiTariffa, "eliminaTariffa");
					aggiorna();
				} else {
					Messaggi.msgEsistenza();
				}
			} else {
				Messaggi.msgErroreSelezione();
			}
		}

	}

	private void aggiornaAgenzia() {
		CaricaAgenzia.getIstanza().caricaTabella(tabellaAgenzia, datiAgenzia);
	}

	private void aggiornaFascia() {
		CaricaFascia.getIstanza().caricaTabella(tabellaFascia, datiFascia);
	}

	private void aggiornaModello() {
		CaricaModello.getIstanza().caricaTabella(tabellaModello, datiModello);
	}

	private void aggiornaMacchina() {
		CaricaMacchina.getIstanza()
				.caricaTabella(tabellaMacchina, datiMacchina);
	}

	private void aggiornaTariffa() {
		CaricaTariffa.getIstanza().caricaTabella(tabellaTariffa, datiTariffa);
	}

	private void aggiorna() {
		aggiornaAgenzia();
		aggiornaFascia();
		aggiornaModello();
		aggiornaMacchina();
		aggiornaTariffa();
	}

}
