package br.com.softblue.java.chat.client.ui.controller;

import java.io.IOException;

import br.com.softblue.java.chat.client.business.ServerRequestHandler;
import br.com.softblue.java.chat.client.business.ServerInvoker;
import br.com.softblue.java.chat.client.config.ClientConfigFile;
import br.com.softblue.java.chat.client.ui.model.MainUIModel;
import br.com.softblue.java.chat.common.ChatException;
import br.com.softblue.java.chat.common.utils.FXUtils;
import br.com.softblue.java.chat.common.utils.StageAwareController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller da tela principal
 */
public class MainController implements ServerRequestHandler, StageAwareController {
	/**
	 * T�tulo da aplica��o 
	 */
	public static final String TITLE = "Aplicativo Chat";
	
	@FXML
	private TextField txtNome;
	@FXML
	private TextArea txtMessages;
	@FXML
	private TextField txtMessage;
	@FXML
	private Button btnConnect;
	@FXML
	private Button btnDisconnect;
	@FXML
	private Button btnSend;

	/**
	 * Objeto para invocar as opera��es no servidor remotamente 
	 */
	private ServerInvoker serverInvoker;

	/**
	 * Modelo de dados
	 */
	private MainUIModel mainModel = new MainUIModel();

	@FXML
	private void initialize() {
		// Define os bindings
		btnConnect.disableProperty().bind(mainModel.connectedProperty());
		btnDisconnect.disableProperty().bind(mainModel.connectedProperty().not());
		txtMessage.disableProperty().bind(mainModel.connectedProperty().not());
		txtNome.disableProperty().bind(mainModel.connectedProperty());
		btnSend.disableProperty().bind(mainModel.connectedProperty().not());
		
		mainModel.nameProperty().bindBidirectional(txtNome.textProperty());
		mainModel.messageProperty().bindBidirectional(txtMessage.textProperty());
		mainModel.messagesProperty().bindBidirectional(txtMessages.textProperty());
	}

	@FXML
	public void connect() {
		try {
			// Obt�m o nome do cliente no chat
			String name = mainModel.getName();

			// Verifica se houve a digita��o do nome do servidor
			if (name != null && !name.trim().isEmpty()) {
				// L� o nome do servidor do arquivo de configura��o
				String server = ClientConfigFile.getServer();

				// L� a porta RMI do arquivo de configura��o
				int port = ClientConfigFile.getPort();

				// Cria o ServerInvoker, respons�vel pelas chamadas ao objeto
				// remoto
				serverInvoker = new ServerInvoker(server, port, name, this);

				// Conecta no servidor
				serverInvoker.connect();

				// Muda o estado do cliente para conectado
				mainModel.setConnected(true);

				// Muda o t�tulo da janela
				mainModel.setWindowTitle(String.format("%s [Conectado em %s:%s]", TITLE, ClientConfigFile.getServer(), ClientConfigFile.getPort()));
				
				// Coloca o foco no campo da mensagem
				txtMessage.requestFocus();

			} else {
				// Se n�o houve, devolve o foco para a caixa de digita��o do nome
				txtNome.requestFocus();
			}

		} catch (Exception e) {
			FXUtils.showExceptionDialog(e);
		}
	}

	@FXML
	public void disconnect() {
		try {
			// Desconecta do servidor (se houver conex�o)
			if (serverInvoker != null) {
				serverInvoker.disconnect();
			}
			
			// Limpa as informa��es da tela
			mainModel.setConnected(false);
			mainModel.setName("");
			mainModel.setMessages("");
			mainModel.setMessage("");
			mainModel.setWindowTitle(TITLE);
			txtNome.requestFocus();

		} catch (ChatException e) {
			FXUtils.showExceptionDialog(e);
		}
	}

	@FXML
	public void sendMessage() {
		try {
			// Obt�m a mensagem digitada pelo cliente
			String message = mainModel.getMessage();

			// Verifica se a mensagem � vazia
			if (message != null && !message.trim().isEmpty()) {
				// Envia a mensagem digitada
				serverInvoker.sendMessage(message);

				// Limpa a mensagem
				mainModel.setMessage("");

				// Retorna o foco para a caixa de digita��o da mensagem
				txtMessage.requestFocus();

			} else {
				// Se a mensagem for vazia, retorna o foco para a caixa de
				// digita��o da mensagem
				txtMessage.requestFocus();
			}

		} catch (ChatException e) {
			FXUtils.showExceptionDialog(e);
		}
	}

	/**
	 * @see br.com.softblue.java.chat.common.utils.StageAwareController#onStageDefined(javafx.stage.Stage)
	 */
	public void onStageDefined(Stage stage)  {
		// Quando o Stage � definido, define o t�tulo
		stage.titleProperty().bind(mainModel.windowTitleProperty());
		mainModel.setWindowTitle(TITLE);
		
		// Garante que a desconex�o do servidor ser� realizada quando a janela for fechada
		stage.setOnCloseRequest(event -> exit());
	}
	
	@FXML
	public void exit() {
		if (serverInvoker != null) {
			try {
				// Desconecta do servidor
				serverInvoker.disconnect();
			} catch (Exception e) {
			}
		}
		
		// As chamadas abaixo s�o necess�rias para evitar que a aplica��o continue rodando. Como o RMI Registry
		// usa daemon threads, ela n�o � fechada enquanto o RMI Registry continua ativo.
		Platform.exit();
		System.exit(0);
	}

	/**
	 * @see br.com.softblue.java.chat.client.business.ServerRequestHandler#onServerShutdown()
	 */
	@Override
	public void onServerShutdown() {
		// Este m�todo n�o � chamado pela thread principal. O Platform.runLater() garante que a thread principal
		// vai execut�-lo
		Platform.runLater(() -> {
			// Como o servidor foi desconectado, n�o � preciso que o cliente se desconecte do servidor
			serverInvoker = null;
			
			// Executa a desconex�o do cliente
			disconnect();
	
			// Exibe um alerta falando que a conex�o foi perdida
			FXUtils.showErrorDialog("A conex�o com o servidor foi finalizada");
		});
	}

	/**
	 * @see br.com.softblue.java.chat.client.business.ServerRequestHandler#onMessageReceived(java.lang.String)
	 */
	@Override
	public void onMessageReceived(String message) {
		// Adiciona a mensagem ao TextArea de mensagens
		mainModel.appendMessage(message);
		
		txtMessages.positionCaret(txtMessages.getText().length());
	}

	@FXML
	public void openPreferencesWindow() throws IOException {
		// Cria um Stage para representar a janela de prefer�ncias
		Stage configStage = new Stage();
		
		// Define o t�tulo
		configStage.setTitle("Prefer�ncias");
		
		// A janela � modal, pois outras janelas ficam inacess�veis enquanto ela est� aberta
		configStage.initModality(Modality.APPLICATION_MODAL);
		
		// Indica que a tela n�o pode ser dimensionada
		configStage.setResizable(false);
		
		// Carrega o layout da janela
		FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/Config.xml"));
		Pane root = loader.load();
		FXUtils.initLayout(loader, configStage);
		
		// Monta a cena e exibe a janela de prefer�ncias
		Scene scene = new Scene(root, 300, 120);
		configStage.setScene(scene);
		configStage.show();
	}
}
