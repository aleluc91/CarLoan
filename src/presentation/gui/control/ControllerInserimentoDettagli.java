package presentation.gui.control;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import presentation.controller.FrontController;
import presentation.gui.SchermataModificabile;
import presentation.gui.modelli_tabella.ModelloAgenzia;
import presentation.gui.modelli_tabella.carica_modelli.CaricaAgenzia;
import utility.validazione.ValidazioneFactory;
import business.applicationservice.transfer.Valori;
import entity.ContrattoParziale;
import entity.Sessione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerInserimentoDettagli extends Controller implements
		Initializable {

	@FXML
	private Node root;

	@FXML
	private ImageView imgPrincipale;

	@FXML
	private DatePicker dataInizio;

	@FXML
	private Label lblErroreDataInizio;

	@FXML
	private DatePicker dataFine;

	@FXML
	private Label lblErroreDataFine;

	@FXML
	private Label lblCosto;

	@FXML
	private TextField txtAcconto;

	@FXML
	private Label lblErroreAcconto;

	@FXML
	private ComboBox<ModelloAgenzia> cmbAgenzia;

	@FXML
	private Label lblErroreAgenzia;

	private ObservableList<ModelloAgenzia> datiAgenzia = FXCollections
			.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*imgPrincipale.setImage(new
		Image(SchermataModificabile.class.getResourceAsStream("fxml/immagini/icona1.png")));*/
		aggiorna();
	}

	@FXML
	private void gestisciConferma() {
		boolean controlloDataInizio = ValidazioneFactory.getValidazione("Data")
				.valida(dataInizio, lblErroreDataInizio);
		boolean controlloDataFine = ValidazioneFactory.getValidazione("Data")
				.valida(dataFine, lblErroreDataFine);
		boolean controlloAcconto = ValidazioneFactory.getValidazione("Double")
				.valida(txtAcconto, lblErroreAcconto);
		if (!controlloDataInizio && !controlloDataFine && !controlloAcconto) {
			long differenza;
			differenza = ChronoUnit.DAYS.between(LocalDate.now(),
					dataInizio.getValue());
			if (differenza >= 0) {
				differenza = ChronoUnit.DAYS.between(dataInizio.getValue(),
						dataFine.getValue());
				if (differenza >= 0) {
					FrontController fc = new FrontController();
					fc.processaRichiesta("nuovoContratto",
							leggiValoriContratto());
					fc.processaRichiesta("nuovoNoleggio", leggiValoriNoleggio());
					fc.processaRichiesta("modificaValoreMacchina",
							leggiValoriMacchina());
					chiudi(root);
				} else {
					lblErroreDataFine
							.setText("La data non può precedere la data iniziale");
				}
			} else {
				lblErroreDataInizio
						.setText("La data non può precedere la data corrente");
			}
		}
	}

	@FXML
	private void gestisciCosto() {
		if (dataInizio.getValue() != null && dataFine.getValue() != null) {
			int giorni = ContrattoParziale.getIstanza().getGiorni();
			long differenza = ChronoUnit.DAYS.between(dataInizio.getValue(),
					dataFine.getValue());
			if (differenza >= 0) {
				if (ContrattoParziale.getIstanza().getNoleggio()
						.getChilometraggio().equalsIgnoreCase("Limitato")) {
					double tariffa = ContrattoParziale.getIstanza().getCosto();
					double chilometri = ContrattoParziale.getIstanza()
							.getChilometri();
					double costo = ((tariffa * chilometri) / giorni)
							* differenza;
					lblCosto.setText(String.valueOf(costo));
				} else {
					double tariffa = ContrattoParziale.getIstanza()
							.getTariffa();
					double costo = (tariffa / giorni) * differenza;
					lblCosto.setText(String.valueOf(costo));
				}
			}
		} else {
			lblCosto.setText("");
		}
	}

	@FXML
	private void gestisciBottoneAnnulla() {
		chiudi(root);
	}

	private List<Valori> leggiValoriContratto() {
		List<Valori> valori = new ArrayList<Valori>();
		valori.add(new Valori(ContrattoParziale.getIstanza()
				.getCodiceContratto()));
		valori.add(new Valori(ContrattoParziale.getIstanza().getCliente()
				.getNome()));
		valori.add(new Valori(ContrattoParziale.getIstanza().getCliente()
				.getCognome()));
		valori.add(new Valori(ContrattoParziale.getIstanza().getCliente()
				.getNumeroTelefono()));
		valori.add(new Valori(dataInizio.getValue()));
		valori.add(new Valori(dataFine.getValue()));
		valori.add(new Valori(new Double(txtAcconto.getText())));
		valori.add(new Valori(Sessione.getAutenticazione().getAgenzia()));
		System.out.println(cmbAgenzia.getSelectionModel().getSelectedItem()
				.getCodice());
		valori.add(new Valori(cmbAgenzia.getSelectionModel().getSelectedItem()
				.getCodice()));
		return valori;
	}

	private List<Valori> leggiValoriNoleggio() {
		List<Valori> valori = new ArrayList<Valori>();
		valori.add(new Valori(ContrattoParziale.getIstanza()
				.getCodiceContratto()));
		valori.add(new Valori(ContrattoParziale.getIstanza().getNoleggio()
				.getBase()));
		valori.add(new Valori(ContrattoParziale.getIstanza().getNoleggio()
				.getChilometraggio()));
		valori.add(new Valori(ContrattoParziale.getIstanza().getNoleggio()
				.getFascia()));
		valori.add(new Valori(ContrattoParziale.getIstanza().getNoleggio()
				.getModello()));
		valori.add(new Valori(ContrattoParziale.getIstanza().getNoleggio()
				.getMacchina()));
		return valori;
	}

	private List<Valori> leggiValoriMacchina() {
		List<Valori> valori = new ArrayList<Valori>();
		valori.add(new Valori("noleggiata"));
		valori.add(new Valori("Indisponibile"));
		valori.add(new Valori(ContrattoParziale.getIstanza().getNoleggio()
				.getMacchina()));
		return valori;
	}

	private void aggiorna() {
		CaricaAgenzia.getIstanza().cercaComboBox(cmbAgenzia,
				Sessione.getAutenticazione().getAgenzia(), datiAgenzia);
	}
	
	

}
