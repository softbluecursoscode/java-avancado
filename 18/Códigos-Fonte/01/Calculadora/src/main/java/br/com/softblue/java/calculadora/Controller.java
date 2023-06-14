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
	 * Objeto que possui as regras de neg�cio
	 */
	private CalcModel calcModel = new CalcModel();

	/**
	 * Chamado pelo JavaFX quando o controller � instanciado
	 */
	@FXML
	private void initialize() {
		lblResult.textProperty().bindBidirectional(calcModel.currentValueProperty(), new NumberStringConverter(CalcModel.NUMBER_FORMAT));
		lblError.visibleProperty().bind(calcModel.errorProperty());
	}

	/**
	 * O usu�rio digitou um n�mero de 0 a 9
	 * @param event
	 */
	@FXML
	public void onNumber(ActionEvent event) {
		// Obt�m o texto do bot�o pressionado para saber que n�mero foi digitado
		Button button = (Button) event.getSource();
		int number = Integer.parseInt(button.getText());
		calcModel.appendNumber(number);
	}

	/**
	 * O usu�rio solicitou a limpeza dos c�lculos
	 */
	@FXML
	public void onClear() {
		calcModel.clear();
	}

	/**
	 * O usu�rio digitou a v�rgula
	 */
	@FXML
	public void onComma() {
		calcModel.appendComma();
	}

	/**
	 * O usu�rio digitou uma opera��o
	 * @param event
	 */
	@FXML
	public void onOperation(ActionEvent event) {
		// Obt�m o texto do bot�o pressionado para saber que opera��o foi escolhida
		Button button = (Button) event.getSource();
		Operation operation = Operation.fromSymbol(button.getText().charAt(0));
		calcModel.doOperation(operation);
	}
}
