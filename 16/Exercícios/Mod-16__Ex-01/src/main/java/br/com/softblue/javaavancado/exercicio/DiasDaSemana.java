package br.com.softblue.javaavancado.exercicio;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DiasDaSemana extends Application {
	
	// Cria��o dos objetos Locale
	private static final Locale LOCALE_US = new Locale("en", "US");
	private static final Locale LOCALE_BR = new Locale("pt", "BR");
	
	// Locale atual usado pelo ResourceBundle. Inicia com pt-BR
	private Locale currentLocale = LOCALE_BR;

	// Lista de bot�es
	private List<Button> buttons = FXCollections.observableArrayList();
	
	// Refer�ncias aos bot�es da aplica��o
	@FXML private Button btn0;
	@FXML private Button btn1;
	@FXML private Button btn2;
	@FXML private Button btn3;
	@FXML private Button btn4;
	@FXML private Button btn5;
	@FXML private Button btn6;
	@FXML private Button btnChange;
	
	public static void main(String[] args) {
		launch(args);	
	}
	
	/**
	 * Chamado quando o controller � inicializado
	 */
	public void initialize() {
		// Adiciona os bot�es na lista
		buttons.addAll(Arrays.asList(btn0, btn1, btn2, btn3, btn4, btn5, btn6));
		
		// Define os textos dos bot�es
		defineText();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = FXMLLoader.load(getClass().getResource("Layout.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		primaryStage.show();
	}
	
	/**
	 * Este m�todo � chamado quando o bot�o de mudan�a de l�ngua � acionado
	 */
	@FXML
	public void onClick(ActionEvent event) {
		// Alterna o Locale corrente
		if (currentLocale == LOCALE_BR) {
			currentLocale = LOCALE_US;
		} else {
			currentLocale = LOCALE_BR;
		}
		
		// Redefine os textos na nova l�ngua
		defineText();
	}
	
	private void defineText() {
		// Obt�m um ResourceBundle com base no Locale atual
		ResourceBundle bundle = ResourceBundle.getBundle("br.com.softblue.javaavancado.exercicio.DiasDaSemana", currentLocale);
		
		// Define os textos para os dias da semana
		for (Button btn : buttons) {
			String dow = bundle.getString(btn.getId());
			btn.setText(dow);
		}
		
		// Define o texto para o bot�o de mudan�a de l�ngua
		btnChange.setText(bundle.getString("btn_mudar"));
	}
}
