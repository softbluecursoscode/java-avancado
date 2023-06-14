package br.com.softblue.java.calculadora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;

/**
 * Controller do JavaFX
 */
public class Controller {

	@FXML
	private Label lblResult;

	@FXML
	private Label lblError;

	/**
	 * Objeto que possui as regras de negócio
	 */
	private CalcModel calcModel = new CalcModel();

	/**
	 * Chamado pelo JavaFX quando o controller é instanciado
	 */
	@FXML
	private void initialize() {
		lblResult.textProperty().bindBidirectional(calcModel.currentValueProperty(), new NumberStringConverter(CalcModel.NUMBER_FORMAT));
		lblError.visibleProperty().bind(calcModel.errorProperty());
	}

	/**
	 * O usuário digitou um número de 0 a 9
	 * @param event
	 */
	@FXML
	public void onNumber(ActionEvent event) {
		// Obtém o texto do botão pressionado para saber que número foi digitado
		Button button = (Button) event.getSource();
		int number = Integer.parseInt(button.getText());
		calcModel.appendNumber(number);
	}

	/**
	 * O usuário solicitou a limpeza dos cálculos
	 */
	@FXML
	public void onClear() {
		calcModel.clear();
	}

	/**
	 * O usuário digitou a vírgula
	 */
	@FXML
	public void onComma() {
		calcModel.appendComma();
	}

	/**
	 * O usuário digitou uma operação
	 * @param event
	 */
	@FXML
	public void onOperation(ActionEvent event) {
		// Obtém o texto do botão pressionado para saber que operação foi escolhida
		Button button = (Button) event.getSource();
		Operation operation = Operation.fromSymbol(button.getText().charAt(0));
		calcModel.doOperation(operation);
	}
}
