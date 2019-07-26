package presentation.gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Schermata extends Stage {

	protected Parent root;
	protected Scene scene;

	protected Schermata(String percorsoFXML, String titolo) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(percorsoFXML));
			root = loader.load();
			scene = new Scene(root);
			getIcons().add(
					new Image(getClass().getResourceAsStream(
							"fxml/immagini/carloan.png")));
			setTitle(titolo);
			setResizable(false);
			String path = getClass().getResource("fxml/css/stile.css")
					.toExternalForm();
			scene.getStylesheets().add(path);
			setScene(scene);
			loader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
