package utility.controlli_esistenza;

import entity.Contratto;
import javafx.scene.control.Label;

public class EsistenzaCodice extends InDatabase<Contratto> {

	private static final String RICHIESTA = "leggiContratto";
	private static final String METODO = "getCodiceContratto";

	public EsistenzaCodice() {
		super(RICHIESTA, METODO);
	}

	@Override
	public boolean esistenza(String valore, Label labelErrore) {
		// TODO Auto-generated method stub
		return verifica(valore, labelErrore);
	}

}
