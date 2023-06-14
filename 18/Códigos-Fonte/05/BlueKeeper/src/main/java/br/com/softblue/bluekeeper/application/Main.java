package br.com.softblue.bluekeeper.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Classe de Application do JavaFX
 */
public class Main extends Application {
	
	/**
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Carrega o arquivo de layout
		Pane root = FXMLLoader.load(getClass().getResource("Layout.fxml"));
		
		// Cria a cena
		Scene scene = new Scene(root, 850, 400);
		
		// Define largura e altura m�nima para a janela
		primaryStage.setMinWidth(850);
		primaryStage.setMinHeight(400);
		
		// Define o t�tulo da janela
		primaryStage.setTitle("BlueKeeper");
		
		// Associa a cena � janela
		primaryStage.setScene(scene);
		
		// Exibe a janela
		primaryStage.show();
	}

	/**
	 * M�todo main(): onde tudo come�a
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		launch(args);
	}
}
