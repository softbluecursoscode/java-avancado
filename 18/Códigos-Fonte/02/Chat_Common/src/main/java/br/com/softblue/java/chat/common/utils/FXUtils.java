package br.com.softblue.java.chat.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * Métodos utilitários relacionados à interface gráfica da aplicação
 */
public class FXUtils {
	
	@SuppressWarnings("exports")
	public static void initLayout(FXMLLoader loader, Stage stage) throws IOException {		
		// Obtém o controller associado ao FXML
		Object controllerObj = loader.getController();
		
		// Se existe um controller e ele implementa StageAwareController, o método onStageDefined() é chamado
		// para informar o stage ao controller.
		if (controllerObj != null && controllerObj instanceof StageAwareController) {
			StageAwareController controller = (StageAwareController) controllerObj;
			controller.onStageDefined(stage);
		}
	}

	/**
	 * Exibe uma caixa de diálogo de exceção.
	 * @param t	Exceção cujos dados serão mostrados.
	 */
	public static void showExceptionDialog(Throwable t) {
		// Cria o dialog com os dados básicos
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Ocorreu uma Exceção");
		alert.setHeaderText("A exceção é do tipo " + t.getClass().getName());
		alert.setContentText(t.getMessage());

		// Cria a área estendida do dialog
		
		// Grava a stacktrace em um StringWriter
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);

		// Cria um TextArea com o conteúdo da stacktrace
		TextArea textArea = new TextArea(sw.toString());
		textArea.setEditable(false);
		textArea.setWrapText(true);
		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		
		// Cria um GridPane de uma célula para inserir o TextArea
		GridPane extendedPane = new GridPane();
		extendedPane.setMaxWidth(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);
		extendedPane.add(textArea, 0, 0);

		// Define a área espandida e exibe o dialog
		alert.getDialogPane().setExpandableContent(extendedPane);
		alert.showAndWait();
	}
	
	/**
	 * Exibe uma caixa de diálogo de alerta.
	 * @param message Mensagem de alerta a ser mostrada.
	 */
	public static void showErrorDialog(String message) {
		// Cria o dialog com os dados
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setHeaderText(message);
		alert.showAndWait();
	}
}
