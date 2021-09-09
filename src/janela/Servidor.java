package janela;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Servidor extends Application{

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage palco) throws Exception {
		// Path to the FXML File
		URL arquivoFXML = getClass().getResource("./Servidor.fxml");

        // Create the Pane and all Details
        Pane root = (Pane) FXMLLoader.load(arquivoFXML);

        // Create the Scene
        Scene scene = new Scene(root);
        // Set the Scene to the Stage
        palco.setScene(scene);
        // Set the Title to the Stage
        palco.setTitle("Servidor MQTT");
        // Display the Stage
        palco.show();
	}
}
