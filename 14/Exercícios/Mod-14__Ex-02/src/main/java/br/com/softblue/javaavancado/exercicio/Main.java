package br.com.softblue.javaavancado.exercicio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Application do JavaFX
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Carrega o layout e monta a cena
		Pane root = FXMLLoader.load(getClass().getResource("/Layout.fxml"));
		Scene scene = new Scene(root, 220, 220);

		// Define características da janela e exibe
		primaryStage.setTitle("Buscador de Métodos");
		primaryStage.setMinWidth(300);
		primaryStage.setMinHeight(200);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
