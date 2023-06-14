package br.com.softblue.java.chat.client.ui;

import br.com.softblue.java.chat.common.utils.FXUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Application do JavaFX
 */
public class ChatClient extends Application {
	
	/**
	 * Método main.
	 * @param args
	 */
	public static void main(String[] args) {
		// Abre a interface gráfica do cliente
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Define os parâmetros da janela
		primaryStage.setMinWidth(380);
		primaryStage.setMinHeight(250);
		
		// Carrega o layout
		FXMLLoader loader = new FXMLLoader(ChatClient.class.getResource("/MainLayout.fxml"));
		Pane root = loader.load();
		FXUtils.initLayout(loader, primaryStage);

		// Monta a cena e exibe a janela
		Scene scene = new Scene(root, 500, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
