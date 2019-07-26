package utility.controlli_esistenza;

import entity.Autenticazione;
import javafx.scene.control.Label;

public class EsistenzaUsername extends InDatabase<Autenticazione> {

	private static final String RICHIESTA = "leggiAutenticazione";
	private static final String METODO = "getUsername";

	public EsistenzaUsername() {
		super(RICHIESTA, METODO);
	}

	@Override
	public boolean esistenza(String valore, Label labelErrore) {
		// TODO Auto-generated method stub
		return verifica(valore, labelErrore);
	}

}
