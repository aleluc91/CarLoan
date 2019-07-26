package presentation.gui;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import business.applicationservice.transfer.Valori;
import javafx.stage.Stage;

public class StageFactory {

	private static final String MOSTRA = "mostra";
	private static final int LUNGHEZZA = MOSTRA.length();
	private static final String PERCORSO = "presentation.gui.";

	public static Stage getStage(String serviceName, List<Valori> valori) {
		return getIstanzaStage(getClasse(serviceName), valori);
	}

	private static Class<?> getClasse(String nomeServizio) {
		String nomeClasse = getPercorso(estraiNomeStage(nomeServizio));
		Class<?> classe = null;
		try {
			classe = Class.forName(nomeClasse);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classe;
	}

	private static String estraiNomeStage(String nomeServizio) {
		return nomeServizio.substring(LUNGHEZZA, nomeServizio.length());
	}

	private static String getPercorso(String nomePercorso) {
		return PERCORSO + nomePercorso;
	}

	private static Stage getIstanzaStage(Class<?> classe, List<Valori> valori) {
		Stage applicationIstance = null;
		try {
			if (valori != null) {
				Class<?> params[] = { List.class };
				Constructor<?> constructor = classe.getConstructor(params);
				applicationIstance = (Stage) constructor.newInstance(valori);
			} else {
				Class<?> params[] = {};
				Constructor<?> constructor = classe.getConstructor(params);
				applicationIstance = (Stage) constructor
						.newInstance((Object[]) null);
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return applicationIstance;
	}

}
