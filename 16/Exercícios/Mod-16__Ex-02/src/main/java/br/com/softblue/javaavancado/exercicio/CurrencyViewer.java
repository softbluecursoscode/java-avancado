package br.com.softblue.javaavancado.exercicio;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CurrencyViewer extends Application {

	// Campo de digitação do valor
	@FXML
	private TextField txtValor;

	// Combo box com os locales
	@FXML
	private ChoiceBox<String> choLocale;

	// Campo de exibição do resultado após a formatação
	@FXML
	private Text txtResultado;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Chamado quando o controller é inicializado
	 */
	public void initialize() {
		// Monta uma lista de locales: apenas os locales com língua e país e ordenada em ordem alfabética
		List<String> locales = Stream.of(Locale.getAvailableLocales())
			.filter(l -> !l.getCountry().isEmpty())
			.map(l -> l.getLanguage() + "-" + l.getCountry())
			.sorted()
			.collect(Collectors.toList());

		// Coloca os locales no ChoiceBox
		choLocale.setItems(FXCollections.observableArrayList(locales));
		
		// Adiciona um listener para ser chamado quando um novo locale é escolhido no ChoiceBox
		choLocale.valueProperty().addListener((event, oldValue, newValue) -> formatCurrency());
		
		// Seleciona a primeira opção do ChoiceBox
		choLocale.getSelectionModel().select(0);
		
		// Adiciona um listener para ser chamado quando um novo valor é digitado no campo
		txtValor.textProperty().addListener((event, oldValue, newValue) -> formatCurrency());
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = FXMLLoader.load(getClass().getResource("Layout.fxml"));

		Scene scene = new Scene(root, 370, 120);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Visualizador de Valores Monetários");

		primaryStage.show();
	}

	/**
	 * Faz a formatação do valor com base no valor e locale fornecidos.
	 */
	private void formatCurrency() {
		// Obtém o valor digitado pelo usuário
		String value = txtValor.getText();

		// Converte a string para um objeto Locale
		Locale locale = Locale.forLanguageTag(choLocale.getSelectionModel().getSelectedItem());

		// Cria um objeto para formatar moedas com base no locale
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);

		try {
			// Formata o valor digitado pelo usuário
			String formattedValue = nf.format(Double.parseDouble(value));

			// Coloca o valor formatado na caixa de texto de resultado
			txtResultado.setText(formattedValue);

		} catch (NumberFormatException e) {
			// Se a conversão do valor digitado pelo usuário em um double não
			// for possível,
			// deixa a caixa de texto do resultado em branco
			txtResultado.setText("");
		}
	}
}
