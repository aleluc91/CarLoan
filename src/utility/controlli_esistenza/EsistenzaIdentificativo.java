package utility.controlli_esistenza;

import entity.Fascia;
import javafx.scene.control.Label;

public class EsistenzaIdentificativo extends InDatabase<Fascia> {

	private static final String RICHIESTA = "leggiFascia";
	private static final String METODO = "getId";

	public EsistenzaIdentificativo() {
		super(RICHIESTA, METODO);
	}

	@Override
	public boolean esistenza(String valore, Label labelErrore) {
		// TODO Auto-generated method stub
		return verifica(valore, labelErrore);
	}

}
