package br.com.softblue.java.calculadora;

import java.text.NumberFormat;
import java.util.Locale;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Classe com as regras de neg�cio da aplica��o
 */
public class CalcModel {
	/**
	 * Constante para o n�mero m�ximo de d�gitos suportados pela calculadora
	 */
	private static final int MAX_LENGTH = 12;
	
	/**
	 * Constante para o n�mero m�ximo de casas decimais suportadas pela calculadora
	 */
	private static final int MAX_FRACTION_DIGITS = 4;
	
	/**
	 * Objeto para formata��o do n�mero
	 */
	public static final NumberFormat NUMBER_FORMAT;

	static {
		// Usa o Locale US para usar "." no lugar de "," na formata��o
		NUMBER_FORMAT = NumberFormat.getNumberInstance(new Locale("en", "US"));
		
		// O n�mero m�nimo de casas decimais � 0
		NUMBER_FORMAT.setMinimumFractionDigits(0);
		
		// N�mero m�ximo de casas decimais
		NUMBER_FORMAT.setMaximumFractionDigits(MAX_FRACTION_DIGITS);
		
		// Desativa o separador de milhar
		NUMBER_FORMAT.setGroupingUsed(false);
	}

	/**
	 * Valor atual mostrado no visor
	 */
	private DoubleProperty currentValue = new SimpleDoubleProperty();
	
	/**
	 * Indica se o valor do visor excedeu o m�ximo de d�gitos que podem ser exibidos
	 */
	private BooleanProperty error = new SimpleBooleanProperty();

	/**
	 * Valor gravado para fazer parte de uma opera��o
	 */
	private double savedValue;
	
	/**
	 * Indica se a v�rgula deve ser adicionada quando o pr�ximo n�mero for digitado
	 */
	private boolean appendComma;
	
	/**
	 * Armazena a opera��o corrente, que deve ser realizada quando o '=' for pressionado
	 */
	private Operation currentOperation;
	
	/**
	 * Indica se quando o pr�ximo n�mero for digitado o visor deve ser limpado antes
	 */
	private boolean clearOnNextNumber;
	
	/**
	 * Indica se uma opera��o foi aplicada
	 */
	private boolean operationApplied;

	
	public DoubleProperty currentValueProperty() {
		return this.currentValue;
	}

	public double getCurrentValue() {
		return this.currentValueProperty().get();
	}

	public void setCurrentValue(double result) {
		this.currentValueProperty().set(result);
	}

	/**
	 * Adiciona um n�mero no visor
	 * @param number
	 */
	public void appendNumber(int number) {
		// Converte o que est� sendo exibido em String
		String resultStr = getResultAsString();
		
		if (clearOnNextNumber) {
			// Se precisa limpar o visor, zera o resultado
			resultStr = "";
			if (operationApplied) {
				// Se uma opera��o foi aplicada, reinicia a calculadora
				clear();
			
			} else {
				// Se a opera��o n�o foi aplicada, limpa a flag de clearOnNextNumber
				clearOnNextNumber = false;
			}
		
		} else if (resultStr.length() == MAX_LENGTH) {
			// Se j� chegou no limite de caracteres, n�o faz nada
			return;
		}

		if (appendComma) {
			// Se a v�rgula precisa ser adicionada, coloca ela na frente do n�mero
			resultStr += "." + number;
			
			// A v�rgula n�o � mais necess�ria
			appendComma = false;

		} else {
			// Se n�o precisa adicionar a v�rgula, concatena o novo n�mero no final dos n�meros que est�o vis�veis
			resultStr += number;
		}

		// Define o valor corrente convertendo a String em double
		setCurrentValue(Double.parseDouble(resultStr));
	}

	/**
	 * Adiciona a v�rgula ao visor
	 */
	public void appendComma() {
		// Converte o que est� sendo exibido em String
		String resultStr = getResultAsString();

		// Se ainda n�o cont�m a v�rgula, habilita a v�rgula no pr�ximo n�mero digitado
		if (!resultStr.contains(".")) {
			appendComma = true;
		}
	}

	/**
	 * Limpa o objeto
	 */
	public void clear() {
		setCurrentValue(0);
		savedValue = 0;
		currentOperation = null;
		appendComma = false;
		clearOnNextNumber = false;
		operationApplied = false;
		setError(false);
	}

	/**
	 * Executa uma opera��o
	 * @param operation
	 */
	public void doOperation(Operation operation) {
		if (operation != Operation.EQUALS) {
			// Se n�o for a opera��o de '=', armazena a opera��o e o valor corrente
			currentOperation = operation;
			clearOnNextNumber = true;
			savedValue = getCurrentValue();
			operationApplied = false;

		} else if (!operationApplied && currentOperation != null) {
			// Se for a opera��o de '=' e existe uma opera��o a ser processada, faz o c�lculo
			double value = currentOperation.apply(savedValue, getCurrentValue());
			operationApplied = true;
			setCurrentValue(value);
			clearOnNextNumber = true;
			
			// Converte o que est� sendo exibido em String
			String resultStr = getResultAsString();
			if (resultStr.length() > MAX_LENGTH) {
				// Se o resultado tem tamanho maior do que o m�ximo permitido, trunca o valor e exibe
				// o indicativo de erro
				resultStr = resultStr.substring(0, MAX_LENGTH);
				setCurrentValue(Double.parseDouble(resultStr));
				setError(true);
			}
		}
		
		appendComma = false;
	}

	/**
	 * Converte o que est� sendo exibido em String
	 * @return
	 */
	private String getResultAsString() {
		return NUMBER_FORMAT.format(getCurrentValue());
	}

	public BooleanProperty errorProperty() {
		return this.error;
	}

	public boolean isError() {
		return this.errorProperty().get();
	}

	public void setError(final boolean error) {
		this.errorProperty().set(error);
	}

}
