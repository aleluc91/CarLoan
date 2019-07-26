package presentation.gui.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import utility.validazione.ValidazioneFactory;
import business.applicationservice.transfer.Valori;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllerInserimentoTariffa extends ControllerModificabile
		implements Initializable {

	private int id;
	private String tipo;
	private long chilometri;
	private int giorni;
	private double tariffa;

	@FXML
	private Node root;

	@FXML
	private TextField txtTipo;

	@FXML
	private Label lblErroreTipo;

	@FXML
	private TextField txtChilometri;

	@FXML
	private Label lblErroreChilometri;

	@FXML
	private TextField txtGiorni;

	@FXML
	private Label lblErroreGiorni;

	@FXML
	private TextField txtTariffa;

	@FXML
	private Label lblErroreTariffa;

	@FXML
	private Label lblPrincipale;

	public void setId(int id) {
		this.id = id;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setChilometri(long chilometri) {
		this.chilometri = chilometri;
	}

	public void setGiorni(int giorni) {
		this.giorni = giorni;
	}

	public void setTariffa(double tariffa) {
		this.tariffa = tariffa;
	}

	@Override
	public void isModifica() {
		modifica = true;
		lblPrincipale.setText("Modifica Tariffa");
		txtTipo.setText(tipo);
		txtChilometri.setText(String.valueOf(chilometri));
		txtGiorni.setText(String.valueOf(giorni));
		txtTariffa.setText(String.valueOf(tariffa));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setRoot(root);
		lblPrincipale.setText("   Nuova Tariffa");
		root.addEventHandler(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
						if (event.getCode().equals(KeyCode.ENTER))
							aggiungiTariffa();
					}

				});
	}

	@FXML
	private void gestisciBottoneConferma(ActionEvent event) {
		aggiungiTariffa();
	}

	private void aggiungiTariffa() {
		boolean controlloTipo = ValidazioneFactory.getValidazione("Tipo")
				.valida(txtTipo, lblErroreTipo);
		boolean controlloTariffa = ValidazioneFactory.getValidazione("Double")
				.valida(txtTariffa, lblErroreTariffa);
		if (!controlloTipo && !controlloTariffa) {
			if (modifica == true)
				aggiungi("modificaTariffa", leggiValori());
			else
				aggiungi("nuovaTariffa", leggiValori());
		}
	}

	@FXML
	private void gestisciBottoneAnnulla() {
		chiudi(root);
	}

	private List<Valori> leggiValori() {
		List<Valori> valori = new ArrayList<Valori>();
		valori.add(new Valori(txtTipo.getText()));
		valori.add(new Valori(new Long(txtChilometri.getText())));
		valori.add(new Valori(new Integer(txtGiorni.getText())));
		valori.add(new Valori(new Double(txtTariffa.getText())));
		if (modifica)
			valori.add(new Valori(id));
		return valori;
	}

}
