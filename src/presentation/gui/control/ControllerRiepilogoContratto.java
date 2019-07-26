package presentation.gui.control;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import business.applicationservice.transfer.Valori;
import entity.Fascia;
import entity.Macchina;
import entity.Noleggio;
import entity.Tariffa;
import presentation.controller.FrontController;
import presentation.gui.modelli_tabella.ModelloContratto;
import utility.validazione.ValidazioneFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerRiepilogoContratto extends ControllerModificabile
		implements Initializable {

	private boolean chiusura;
	private ModelloContratto modello;
	private Noleggio noleggio;
	private Tariffa tariffa;
	private Fascia fascia;
	private Macchina macchina;

	@FXML
	private Node root;

	@FXML
	private Label lblCodice;

	@FXML
	private Label lblNome;

	@FXML
	private Label lblCognome;

	@FXML
	private Label lblTelefono;

	@FXML
	private Label lblDataInizio;

	@FXML
	private Label lblDataFine;

	@FXML
	private Label lblAcconto;

	@FXML
	private Label lblAgenzia;

	@FXML
	private Label lblBase;

	@FXML
	private Label lblChilometraggio;

	@FXML
	private Label lblFascia;

	@FXML
	private Label lblModello;

	@FXML
	private Label lblTarga;

	@FXML
	private Label lblAgenziaRitorno;

	@FXML
	private Label lblCostoParziale;

	@FXML
	private TextField txtChilometri;

	@FXML
	private Label lblErroreChilometri;

	@FXML
	private Label lblCostoTotale;

	@FXML
	private Button btnChiudi;
	
	@FXML
	private Button btnElimina;

	public void setModello(ModelloContratto modello) {
		this.modello = modello;
	}

	public void setChiusura(boolean valore) {
		this.chiusura = valore;
	}

	@Override
	public void isModifica() {
		lblCodice.setText(modello.getCodice());
		lblNome.setText(modello.getNome());
		lblCognome.setText(modello.getCognome());
		lblTelefono.setText(modello.getTelefono());
		lblDataInizio.setText(modello.getDataInizio());
		lblDataFine.setText(modello.getDataFine());
		lblAcconto.setText(String.valueOf(modello.getAcconto()));
		lblAgenzia.setText(modello.getAgenzia());
		lblAgenziaRitorno.setText(modello.getAgenziaRitorno());
		FrontController fc = new FrontController();
		List<Valori> valori = new ArrayList<Valori>();
		valori.add(new Valori(modello.getCodice()));
		noleggio = (Noleggio) fc.processaRichiesta("cercaNoleggio", valori);
		if (noleggio != null) {
			lblBase.setText(String.valueOf(noleggio.getBase()));
			lblChilometraggio.setText(noleggio.getChilometraggio());
			lblFascia.setText(noleggio.getFascia());
			lblModello.setText(noleggio.getModello());
			lblTarga.setText(noleggio.getMacchina());
			valori = new ArrayList<Valori>();
			valori.add(new Valori(noleggio.getMacchina()));
			macchina = (Macchina) fc.processaRichiesta("cercaMacchina", valori);
			valori = new ArrayList<Valori>();
			valori.add(new Valori(noleggio.getBase()));
			tariffa = (Tariffa) fc.processaRichiesta("cercaTariffa", valori);
			valori = new ArrayList<Valori>();
			valori.add(new Valori(noleggio.getFascia()));
			fascia = (Fascia) fc.processaRichiesta("cercaFascia", valori);
			calcolaCosto();
		}
		if (!chiusura) {
			txtChilometri.setDisable(true);
			btnChiudi.setDisable(true);
			btnElimina.setDisable(false);
			
		} else {
			txtChilometri.setDisable(false);
			btnChiudi.setDisable(false);
			btnElimina.setDisable(true);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	private void calcolaCosto() {
		long differenza = ChronoUnit.DAYS.between(
				LocalDate.parse(modello.getDataInizio()),
				LocalDate.parse(LocalDate.now().toString()));
		int giorni = tariffa.getGiorni();
		if (differenza >= 0) {
			if (noleggio.getChilometraggio().equalsIgnoreCase("Limitato")) {
				double prezzo = fascia.getCosto();
				double chilometri = tariffa.getChilometri();
				double costo;
				if (differenza == 0)
					costo = (prezzo * chilometri) / giorni;
				else
					costo = ((prezzo * chilometri) / giorni) * differenza;
				lblCostoParziale.setText(String.valueOf(costo));
				lblCostoTotale.setText(String.valueOf(costo
						- modello.getAcconto()));
			} else {
				double prezzo = tariffa.getTariffa();
				double costo;
				if (differenza == 0)
					costo = (prezzo / giorni);
				else
					costo = (prezzo / giorni) * differenza;
				lblCostoParziale.setText(String.valueOf(costo));
				lblCostoTotale.setText(String.valueOf(costo
						- modello.getAcconto()));
			}
		}
	}

	@FXML
	private void gestisciChiudi() {
		boolean controlloChilometri = ValidazioneFactory.getValidazione(
				"Double").valida(txtChilometri, lblErroreChilometri);
		if (!controlloChilometri) {
			FrontController fc = new FrontController();
			List<Valori> valori = new ArrayList<Valori>();
			valori.add(new Valori("attivo"));
			valori.add(new Valori("no"));
			valori.add(new Valori(modello.getCodice()));
			fc.processaRichiesta("modificaValoreContratto", valori);
			valori = new ArrayList<Valori>();
			valori.add(new Valori("noleggiata"));
			valori.add(new Valori("Disponibile"));
			valori.add(new Valori(macchina.getTarga()));
			fc.processaRichiesta("modificaValoreMacchina", valori);
			valori = new ArrayList<Valori>();
			valori.add(new Valori("chilometraggio"));
			valori.add(new Valori(new Long(macchina.getChilometraggio()
					+ new Long(txtChilometri.getText()))));
			valori.add(new Valori(macchina.getTarga()));
			fc.processaRichiesta("modificaValoreMacchina", valori);
			chiudi(root);
		}

	}
	
	@FXML
    private void eliminaContratto(){
    	FrontController fc = new FrontController();
		List<Valori> valori = new ArrayList<Valori>();
		valori.add(new Valori("attivo"));
		valori.add(new Valori("no"));
		valori.add(new Valori(modello.getCodice()));
		fc.processaRichiesta("modificaValoreContratto", valori);
		valori = new ArrayList<Valori>();
		valori.add(new Valori("noleggiata"));
		valori.add(new Valori("Disponibile"));
		valori.add(new Valori(macchina.getTarga()));
		fc.processaRichiesta("modificaValoreMacchina", valori);
		chiudi(root);
	}

}
