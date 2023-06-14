package br.com.softblue.java.chat.client.ui.controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import br.com.softblue.java.chat.client.config.ClientConfigFile;
import br.com.softblue.java.chat.client.ui.model.ConfigUIModel;
import br.com.softblue.java.chat.common.utils.FXUtils;
import br.com.softblue.java.chat.common.utils.StageAwareController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

/**
 * Controller da tela de preferências
 */
public class ConfigController implements StageAwareController {

	@FXML
	private TextField txtServer;
	
	@FXML
	private TextField txtPort;
	
	private Stage stage;
	
	// Modelo de dados
	private ConfigUIModel configModel = new ConfigUIModel();
	
	@FXML
	private void initialize() {
		// Cria um NumberFormat para formatar o número da porta
		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
		nf.setGroupingUsed(false);
		
		// Cria os bindings
		txtServer.textProperty().bindBidirectional(configModel.serverProperty());
		txtPort.textProperty().bindBidirectional(configModel.portProperty(), new NumberStringConverter(nf));
		
		// Mostra na tela o servidor e porta configurados
		configModel.setServer(ClientConfigFile.getServer());
		configModel.setPort(ClientConfigFile.getPort());
	}

	@Override
	public void onStageDefined(Stage stage) {
		this.stage = stage;
	}
	
	@FXML
	public void confirm() {
		// Obtém o servidor informado
		String server = configModel.getServer();
		
		// Valida se o servidor foi preenchido
		if (server == null || server.trim().isEmpty()) {
			FXUtils.showErrorDialog("Forneça um nome de servidor.");
			return;
		}
		
		// Obtém a porta informada
		int port = configModel.getPort();
		
		// Valida se a porta foi preenchida
		if (port == 0) {
			FXUtils.showErrorDialog("Forneça uma porta.");
			return;
		}
		
		// Define os novos valores de porta e servidor e salva estas informações no arquivo de configuração
		ClientConfigFile.setServer(server);
		ClientConfigFile.setPort(port);
		try {
			ClientConfigFile.save();
		} catch (IOException e) {
			FXUtils.showExceptionDialog(e);
		}
		
		// Fecha a janela
		stage.close();
	}
	
	@FXML
	public void cancel() {
		// Fecha a janela sem gravar as alterações
		stage.close();
	}
}
