package utility.controlli_esistenza;

import entity.Macchina;
import javafx.scene.control.Label;

public class EsistenzaTarga extends InDatabase<Macchina> {

	private static final String RICHIESTA = "leggiMacchina";
	private static final String METODO = "getTarga";

	public EsistenzaTarga() {
		super(RICHIESTA, METODO);
	}

	@Override
	public boolean esistenza(String valore, Label labelErrore) {
		// TODO Auto-generated method stub
		return verifica(valore, labelErrore);
	}

}
