package presentation.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javafx.stage.Stage;
import business.applicationservice.factory.ASFactory;
import business.applicationservice.factory.ASMethodFactory;
import business.applicationservice.transfer.Valori;
import presentation.gui.StageFactory;

class ApplicationController {

	private final String REGEX_MOSTRA_SCHERMATA = "mostra[a-zA-Z]+";

	Object gestisciRichiesta(String nomeServizio, List<Valori> valori) {
		if (nomeServizio.matches(REGEX_MOSTRA_SCHERMATA)) {
			return mostraGUI(nomeServizio, valori);
		} else {
			return eseguiRichiesta(nomeServizio, valori);
		}
	}

	private Object eseguiRichiesta(String nomeServizio, List<Valori> valori) {
		Class<?> classe = ASFactory.getApplicationService(nomeServizio);
		Object object;
		try {
			object = classe.newInstance();
			if (valori != null) {
				return ASMethodFactory.getASMethod(classe, nomeServizio, true)
						.invoke(object, valori);
			} else {
				return ASMethodFactory.getASMethod(classe, nomeServizio, false)
						.invoke(object, (Object[]) null);

			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Stage mostraGUI(String nomeServizio, List<Valori> valori) {
		return StageFactory.getStage(nomeServizio, valori);
	}

}
