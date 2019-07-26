package utility.controlli_esistenza;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import presentation.controller.FrontController;
import business.applicationservice.transfer.Valori;

abstract class InDatabase<T> {

	protected String richiesta;
	protected String nomeMetodo;

	protected InDatabase(String richiesta, String nomeMetodo) {
		this.richiesta = richiesta;
		this.nomeMetodo = nomeMetodo;
	}

	@SuppressWarnings("unchecked")
	protected boolean verifica(String stringaRicerca, Label labelErrore) {
		FrontController fc = new FrontController();
		boolean errore = false;
		List<Valori> valori = new ArrayList<Valori>();
		valori.add(new Valori(stringaRicerca));
		List<T> lista = (ArrayList<T>) fc.processaRichiesta(richiesta, null);
		if (lista != null) {
			for (T t : lista) {
				Method metodo;
				try {
					metodo = t.getClass().getDeclaredMethod(nomeMetodo,
							new Class[] {});
					String getGenerica = (String) metodo.invoke(t,
							new Object[] {});
					if (getGenerica.equalsIgnoreCase(stringaRicerca)) {
						errore = true;
					}
				} catch (NoSuchMethodException | SecurityException e) {
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
			}
		}
		if (errore == true) {
			if (labelErrore != null) {
				labelErrore
						.setText("Valore già presente all'interno del database!");
				return true;
			} else
				return true;
		} else {
			if (labelErrore != null) {
				labelErrore.setText("");
				return false;
			} else
				return false;
		}
	}

	abstract public boolean esistenza(String campoDiTesto, Label labelErrore);

}
