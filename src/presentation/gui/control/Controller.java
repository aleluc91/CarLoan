package presentation.gui.control;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import business.applicationservice.transfer.Valori;
import presentation.controller.FrontController;
import presentation.gui.modelli_tabella.ModelloTabella;
import utility.Messaggi;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class Controller {

	private Node root;

	protected void setRoot(Node root) {
		this.root = root;
	}

	protected void aggiungi(String richiesta) {
		FrontController fc = new FrontController();
		Stage stage = (Stage) fc.processaRichiesta(richiesta, null);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}

	protected <T> void modifica(TableView<T> tabella, ObservableList<T> dati,
			String richiesta) {
		if (!dati.isEmpty()) {
			int indice = tabella.getSelectionModel().getSelectedIndex();
			if (indice >= 0) {
				T t = tabella.getSelectionModel().getSelectedItem();
				FrontController fc = new FrontController();
				List<Valori> valori = new ArrayList<Valori>();
				valori.add(new Valori((ModelloTabella) t));
				Stage stage = (Stage) fc.processaRichiesta(richiesta, valori);
				stage.showAndWait();
			} else {
				Messaggi.msgErroreSelezione();
			}
		}
	}

	protected <T> void modifica(TableView<T> tabella, ObservableList<T> dati,
			String richiesta, String valore) {
		if (!dati.isEmpty()) {
			int indice = tabella.getSelectionModel().getSelectedIndex();
			if (indice >= 0) {
				T t = tabella.getSelectionModel().getSelectedItem();
				FrontController fc = new FrontController();
				List<Valori> valori = new ArrayList<Valori>();
				valori.add(new Valori((ModelloTabella) t));
				valori.add(new Valori(valore));
				Stage stage = (Stage) fc.processaRichiesta(richiesta, valori);
				stage.showAndWait();
			} else {
				Messaggi.msgErroreSelezione();
			}
		}
	}

	protected <TModello> void elimina(TableView<TModello> tabella,
			ObservableList<TModello> dati, String richiesta) {
		if (!dati.isEmpty()) {
			int indice = tabella.getSelectionModel().getSelectedIndex();
			if (indice > -1) {
				if (Messaggi.controlloElimina() == 1) {
					TModello t = tabella.getSelectionModel().getSelectedItem();
					FrontController fc = new FrontController();
					List<Valori> valori = new ArrayList<Valori>();
					valori.add(new Valori(getValore(t)));
					if ((boolean) fc.processaRichiesta(richiesta, valori)) {
						Messaggi.msgConferma();
					}
				}
			} else {
				Messaggi.msgErroreSelezione();
			}
		}
	}

	private <TModello> String getValore(TModello t) {
		Method metodo;
		try {
			metodo = t.getClass()
					.getDeclaredMethod("getValore", new Class[] {});
			String valore = (String) metodo.invoke(t, new Object[] {});
			return valore;
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	protected Stage getStage(Node root) {
		return (Stage) root.getScene().getWindow();
	}

	protected void chiudi(Node root) {
		getStage(root).close();
	}

	protected void messaggioConferma() {
		String type = "informazione";
		String title = "Conferma operazione";
		String content = "Operazione effettuata con successo!";
		Messaggi.showMessage(type, title, "", content);
	}

	protected void messaggioErrore() {
		String type = "errore";
		String title = "Errore!";
		String content = "Si è verificato un errore!";
		Messaggi.showMessage(type, title, "", content);
	}

}
