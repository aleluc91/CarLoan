package utility.controlli_esistenza;

import entity.Agenzia;
import javafx.scene.control.Label;

public class EsistenzaCA extends InDatabase<Agenzia> {

	private static final String RICHIESTA = "leggiAgenzia";
	private static final String METODO = "getCodice";

	public EsistenzaCA() {
		super(RICHIESTA, METODO);
	}

	@Override
	public boolean esistenza(String valore, Label labelErrore) {
		// TODO Auto-generated method stub
		return verifica(valore, labelErrore);
	}

}
