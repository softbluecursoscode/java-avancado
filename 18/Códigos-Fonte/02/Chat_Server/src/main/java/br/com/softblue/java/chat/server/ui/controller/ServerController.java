package br.com.softblue.java.chat.server.ui.controller;

import br.com.softblue.java.chat.common.ChatException;
import br.com.softblue.java.chat.common.utils.FXUtils;
import br.com.softblue.java.chat.common.utils.StageAwareController;
import br.com.softblue.java.chat.server.business.ServerHandler;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller associado à tela da aplicação JavaFX
 */
public class ServerController implements StageAwareController {

	@FXML
	private Button btnStart;
	@FXML
	private Button btnStop;
	@FXML
	private Label lblStatus;
	
	// Referencia o objeto que executa a lógica de negócio da aplicação
	private ServerHandler serverHandler;
	
	// Property que indica se o servidor está executando
	private BooleanProperty serverStarted = new SimpleBooleanProperty();

	@FXML
	private void initialize() {
		// Define os bindings
		btnStart.disableProperty().bind(serverStarted);
		btnStop.disableProperty().bind(serverStarted.not());
		
		try {
			// Instancia o ServerHandler
			serverHandler = new ServerHandler();
		} catch (Exception e) {
			FXUtils.showExceptionDialog(e);
		}
	}

	@FXML
	public void onStart() {
		try {
			// Inicia o servidor
			int port = serverHandler.startServer();
			lblStatus.setText("Servidor iniciado na porta " + port);
			serverStarted.set(true);
		
		} catch (ChatException e) {
			FXUtils.showExceptionDialog(e);
		}
	}

	@FXML
	public void onStop() {
		try {
			// Para o servidor
			serverHandler.stopServer();
			lblStatus.setText("Servidor parado");
			serverStarted.set(false);
		
		} catch (ChatException e) {
			FXUtils.showExceptionDialog(e);
		}
	}
	
	/**
	 * @see br.com.softblue.java.chat.common.utils.StageAwareController#onStageDefined(javafx.stage.Stage)
	 */
	public void onStageDefined(Stage stage) {
		stage.setOnCloseRequest(event -> {
			// Quando a janela for fechada, primeiro para o servidor
			if (serverHandler != null) {
				try {
					serverHandler.stopServer();
				} catch (Exception e) {
				}
			}
			
			// As chamadas abaixo são necessárias para evitar que a aplicação continue rodando. Como o RMI Registry
			// usa daemon threads, ela não é fechada enquanto o RMI Registry continua ativo.
			Platform.exit();
			System.exit(0);
		});
	}
}
