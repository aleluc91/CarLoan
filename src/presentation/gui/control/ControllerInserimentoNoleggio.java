package presentation.gui.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entity.ContrattoParziale;
import presentation.gui.modelli_tabella.ModelloFascia;
import presentation.gui.modelli_tabella.ModelloMacchina;
import presentation.gui.modelli_tabella.ModelloModello;
import presentation.gui.modelli_tabella.ModelloTariffa;
import presentation.gui.modelli_tabella.carica_modelli.CaricaFascia;
import presentation.gui.modelli_tabella.carica_modelli.CaricaMacchina;
import presentation.gui.modelli_tabella.carica_modelli.CaricaModello;
import presentation.gui.modelli_tabella.carica_modelli.CaricaTariffa;
import utility.validazione.ValidazioneComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import business.applicationservice.transfer.Valori;

public class ControllerInserimentoNoleggio extends Controller implements
		Initializable {

	@FXML
	private Node root;

	@FXML
	private ComboBox<ModelloTariffa> cmbBase;

	@FXML
	private Label lblErroreBase;

	@FXML
	private ComboBox<String> cmbChilometraggio;

	@FXML
	private Label lblErroreChilometraggio;

	@FXML
	private ComboBox<ModelloFascia> cmbFascia;

	@FXML
	private Label lblErroreFascia;

	@FXML
	private ComboBox<ModelloModello> cmbModello;

	@FXML
	private Label lblErroreModello;

	@FXML
	private ComboBox<ModelloMacchina> cmbMacchina;

	@FXML
	private Label lblErroreMacchina;

	@FXML
	private Button btnAvanti;

	private ObservableList<ModelloTariffa> datiBase = FXCollections
			.observableArrayList();
	private ObservableList<String> datiChilometraggio = FXCollections
			.observableArrayList();
	private ObservableList<ModelloFascia> datiFascia = FXCollections
			.observableArrayList();
	private ObservableList<ModelloModello> datiModello = FXCollections
			.observableArrayList();
	private ObservableList<ModelloMacchina> datiMacchina = FXCollections
			.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		CaricaTariffa.getIstanza().caricaComboBox(cmbBase, datiBase);
		caricaChilometraggio();
		CaricaFascia.getIstanza().caricaComboBox(cmbFascia, datiFascia);
		inizializzaListener();
	}

	@FXML
	private void gestisciBottoneAvanti() {
		boolean controlloBase = new ValidazioneComboBox<ModelloTariffa>()
				.valida(cmbBase, lblErroreBase);
		boolean controlloChilometraggio = new ValidazioneComboBox<String>()
				.valida(cmbChilometraggio, lblErroreChilometraggio);
		boolean controlloFascia = new ValidazioneComboBox<ModelloFascia>()
				.valida(cmbFascia, lblErroreFascia);
		boolean controlloModello = new ValidazioneComboBox<ModelloModello>()
				.valida(cmbModello, lblErroreModello);
		boolean controlloMacchina = new ValidazioneComboBox<ModelloMacchina>()
				.valida(cmbMacchina, lblErroreMacchina);
		if (!controlloBase && !controlloChilometraggio && !controlloFascia
				&& !controlloModello && !controlloMacchina) {
			ContrattoParziale.getIstanza().setNoleggio(leggiValori());
			aggiungi("mostraInserimentoDettagli");
			chiudi(root);
		}
	}

	private void caricaChilometraggio() {
		datiChilometraggio.add("Limitato");
		datiChilometraggio.add("Illimitato");
		cmbChilometraggio.setItems(datiChilometraggio);
	}

	private List<Valori> leggiValori() {
		List<Valori> valori = new ArrayList<Valori>();
		valori.add(new Valori(ContrattoParziale.getIstanza()
				.getCodiceContratto()));
		valori.add(new Valori(cmbBase.getSelectionModel().getSelectedItem()
				.getId()));
		ContrattoParziale.getIstanza().setGiorni(
				cmbBase.getSelectionModel().getSelectedItem().getGiorni());
		ContrattoParziale.getIstanza().setTariffa(
				cmbBase.getSelectionModel().getSelectedItem().getTariffa());
		ContrattoParziale.getIstanza().setChiloemtri(
				cmbBase.getSelectionModel().getSelectedItem().getChilometri());
		valori.add(new Valori(cmbChilometraggio.getSelectionModel()
				.getSelectedItem()));
		valori.add(new Valori(cmbFascia.getSelectionModel().getSelectedItem()
				.getId()));
		ContrattoParziale.getIstanza().setCosto(
				cmbFascia.getSelectionModel().getSelectedItem().getCosto());
		valori.add(new Valori(cmbModello.getSelectionModel().getSelectedItem()
				.getModello()));
		valori.add(new Valori(cmbMacchina.getSelectionModel().getSelectedItem()
				.getTarga()));
		System.out.println(cmbMacchina.getSelectionModel().getSelectedItem()
				.getTarga());
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

						cmbModello.valueProperty().addListener(
								new ChangeListener<ModelloModello>() {

									@Override
									public void changed(
											ObservableValue<? extends ModelloModello> observable,
											ModelloModello oldValue,
											ModelloModello newValue) {
										// TODO Auto-generated method stub
										try {
											CaricaMacchina
													.getIstanza()
													.caricaComboBox(
															cmbMacchina,
															observable
																	.getValue()
																	.getModello(),
															datiMacchina);
										} catch (NullPointerException e) {

										}
									}

								});

					}

				});

	}

}
