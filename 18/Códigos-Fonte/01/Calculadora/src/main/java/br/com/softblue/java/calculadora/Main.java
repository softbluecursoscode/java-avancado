package br.com.softblue.java.calculadora;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * JavaFX Application
 */
public class Main extends Application {

	/**
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Carrega o layout
		Pane root = FXMLLoader.load(getClass().getResource("/Layout.fxml"));
		
		// Cria a cena
		Scene scene = new Scene(root, 220, 220);
		
		// Adiciona o arquivo CSS à cena
		scene.getStylesheets().add("styles.css");
		
		// Define parâmetros da janela e inicia
		primaryStage.setTitle("Calculadora");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * Método main()
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
