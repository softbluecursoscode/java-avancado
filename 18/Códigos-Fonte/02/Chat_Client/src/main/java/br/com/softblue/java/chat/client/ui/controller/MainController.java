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
	 * Título da aplicação 
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
	 * Objeto para invocar as operações no servidor remotamente 
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
			// Obtém o nome do cliente no chat
			String name = mainModel.getName();

			// Verifica se houve a digitação do nome do servidor
			if (name != null && !name.trim().isEmpty()) {
				// Lê o nome do servidor do arquivo de configuração
				String server = ClientConfigFile.getServer();

				// Lê a porta RMI do arquivo de configuração
				int port = ClientConfigFile.getPort();

				// Cria o ServerInvoker, responsável pelas chamadas ao objeto
				// remoto
				serverInvoker = new ServerInvoker(server, port, name, this);

				// Conecta no servidor
				serverInvoker.connect();

				// Muda o estado do cliente para conectado
				mainModel.setConnected(true);

				// Muda o título da janela
				mainModel.setWindowTitle(String.format("%s [Conectado em %s:%s]", TITLE, ClientConfigFile.getServer(), ClientConfigFile.getPort()));
				
				// Coloca o foco no campo da mensagem
				txtMessage.requestFocus();

			} else {
				// Se não houve, devolve o foco para a caixa de digitação do nome
				txtNome.requestFocus();
			}

		} catch (Exception e) {
			FXUtils.showExceptionDialog(e);
		}
	}

	@FXML
	public void disconnect() {
		try {
			// Desconecta do servidor (se houver conexão)
			if (serverInvoker != null) {
				serverInvoker.disconnect();
			}
			
			// Limpa as informações da tela
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
			// Obtém a mensagem digitada pelo cliente
			String message = mainModel.getMessage();

			// Verifica se a mensagem é vazia
			if (message != null && !message.trim().isEmpty()) {
				// Envia a mensagem digitada
				serverInvoker.sendMessage(message);

				// Limpa a mensagem
				mainModel.setMessage("");

				// Retorna o foco para a caixa de digitação da mensagem
				txtMessage.requestFocus();

			} else {
				// Se a mensagem for vazia, retorna o foco para a caixa de
				// digitação da mensagem
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
		// Quando o Stage é definido, define o título
		stage.titleProperty().bind(mainModel.windowTitleProperty());
		mainModel.setWindowTitle(TITLE);
		
		// Garante que a desconexão do servidor será realizada quando a janela for fechada
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
		
		// As chamadas abaixo são necessárias para evitar que a aplicação continue rodando. Como o RMI Registry
		// usa daemon threads, ela não é fechada enquanto o RMI Registry continua ativo.
		Platform.exit();
		System.exit(0);
	}

	/**
	 * @see br.com.softblue.java.chat.client.business.ServerRequestHandler#onServerShutdown()
	 */
	@Override
	public void onServerShutdown() {
		// Este método não é chamado pela thread principal. O Platform.runLater() garante que a thread principal
		// vai executá-lo
		Platform.runLater(() -> {
			// Como o servidor foi desconectado, não é preciso que o cliente se desconecte do servidor
			serverInvoker = null;
			
			// Executa a desconexão do cliente
			disconnect();
	
			// Exibe um alerta falando que a conexão foi perdida
			FXUtils.showErrorDialog("A conexão com o servidor foi finalizada");
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
		// Cria um Stage para representar a janela de preferências
		Stage configStage = new Stage();
		
		// Define o título
		configStage.setTitle("Preferências");
		
		// A janela é modal, pois outras janelas ficam inacessíveis enquanto ela está aberta
		configStage.initModality(Modality.APPLICATION_MODAL);
		
		// Indica que a tela não pode ser dimensionada
		configStage.setResizable(false);
		
		// Carrega o layout da janela
		FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/Config.xml"));
		Pane root = loader.load();
		FXUtils.initLayout(loader, configStage);
		
		// Monta a cena e exibe a janela de preferências
		Scene scene = new Scene(root, 300, 120);
		configStage.setScene(scene);
		configStage.show();
	}
}
