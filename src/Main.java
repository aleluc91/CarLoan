import javafx.application.Application;
import javafx.stage.Stage;
import presentation.controller.FrontController;

public class Main extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FrontController fc = new FrontController();
		Stage loginStage = (Stage) fc.processaRichiesta("mostraLogin", null);
		loginStage.show();
	}

}
