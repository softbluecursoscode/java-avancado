package br.com.softblue.java.chat.server.ui;

import br.com.softblue.java.chat.common.utils.FXUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Application do JavaFX
 */
public class ChatServer extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Carrega o layout da tela
		FXMLLoader loader = new FXMLLoader(ChatServer.class.getResource("/ServerWindow.fxml"));
		Pane root = loader.load();
		
		FXUtils.initLayout(loader, primaryStage);
		
		// Define os parâmetros da tela e exibe
		primaryStage.setTitle("Servidor de Chat");
		primaryStage.setResizable(false);
		Scene scene = new Scene(root, 300, 100);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Inicia a aplicação JavaFX
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
