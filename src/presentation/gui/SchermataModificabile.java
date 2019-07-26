package presentation.gui;

import java.io.IOException;
import java.util.List;

import business.applicationservice.transfer.Valori;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public abstract class SchermataModificabile<T> extends Stage {

	protected T controller;

	public SchermataModificabile(String percorsoFXML, String titolo) {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(
					percorsoFXML));
			root = loader.load();
			getIcons().add(
					new Image(getClass().getResourceAsStream(
							"fxml/immagini/carloan.png")));
			Scene scene = new Scene(root);
			setResizable(false);
			setTitle(titolo);
			setScene(scene);
			String path = getClass().getResource("fxml/css/stile.css")
					.toExternalForm();
			scene.getStylesheets().add(path);
			controller = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	abstract protected void inizializza(T controller, List<Valori> valori);

}
