package br.com.softblue.java.calculadora;

import java.text.NumberFormat;
import java.util.Locale;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Classe com as regras de negócio da aplicação
 */
public class CalcModel {
	/**
	 * Constante para o número máximo de dígitos suportados pela calculadora
	 */
	private static final int MAX_LENGTH = 12;
	
	/**
	 * Constante para o número máximo de casas decimais suportadas pela calculadora
	 */
	private static final int MAX_FRACTION_DIGITS = 4;
	
	/**
	 * Objeto para formatação do número
	 */
	public static final NumberFormat NUMBER_FORMAT;

	static {
		// Usa o Locale US para usar "." no lugar de "," na formatação
		NUMBER_FORMAT = NumberFormat.getNumberInstance(new Locale("en", "US"));
		
		// O número mínimo de casas decimais é 0
		NUMBER_FORMAT.setMinimumFractionDigits(0);
		
		// Número máximo de casas decimais
		NUMBER_FORMAT.setMaximumFractionDigits(MAX_FRACTION_DIGITS);
		
		// Desativa o separador de milhar
		NUMBER_FORMAT.setGroupingUsed(false);
	}

	/**
	 * Valor atual mostrado no visor
	 */
	private DoubleProperty currentValue = new SimpleDoubleProperty();
	
	/**
	 * Indica se o valor do visor excedeu o máximo de dígitos que podem ser exibidos
	 */
	private BooleanProperty error = new SimpleBooleanProperty();

	/**
	 * Valor gravado para fazer parte de uma operação
	 */
	private double savedValue;
	
	/**
	 * Indica se a vírgula deve ser adicionada quando o próximo número for digitado
	 */
	private boolean appendComma;
	
	/**
	 * Armazena a operação corrente, que deve ser realizada quando o '=' for pressionado
	 */
	private Operation currentOperation;
	
	/**
	 * Indica se quando o próximo número for digitado o visor deve ser limpado antes
	 */
	private boolean clearOnNextNumber;
	
	/**
	 * Indica se uma operação foi aplicada
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
	 * Adiciona um número no visor
	 * @param number
	 */
	public void appendNumber(int number) {
		// Converte o que está sendo exibido em String
		String resultStr = getResultAsString();
		
		if (clearOnNextNumber) {
			// Se precisa limpar o visor, zera o resultado
			resultStr = "";
			if (operationApplied) {
				// Se uma operação foi aplicada, reinicia a calculadora
				clear();
			
			} else {
				// Se a operação não foi aplicada, limpa a flag de clearOnNextNumber
				clearOnNextNumber = false;
			}
		
		} else if (resultStr.length() == MAX_LENGTH) {
			// Se já chegou no limite de caracteres, não faz nada
			return;
		}

		if (appendComma) {
			// Se a vírgula precisa ser adicionada, coloca ela na frente do número
			resultStr += "." + number;
			
			// A vírgula não é mais necessária
			appendComma = false;

		} else {
			// Se não precisa adicionar a vírgula, concatena o novo número no final dos números que estão visíveis
			resultStr += number;
		}

		// Define o valor corrente convertendo a String em double
		setCurrentValue(Double.parseDouble(resultStr));
	}

	/**
	 * Adiciona a vírgula ao visor
	 */
	public void appendComma() {
		// Converte o que está sendo exibido em String
		String resultStr = getResultAsString();

		// Se ainda não contém a vírgula, habilita a vírgula no próximo número digitado
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
	 * Executa uma operação
	 * @param operation
	 */
	public void doOperation(Operation operation) {
		if (operation != Operation.EQUALS) {
			// Se não for a operação de '=', armazena a operação e o valor corrente
			currentOperation = operation;
			clearOnNextNumber = true;
			savedValue = getCurrentValue();
			operationApplied = false;

		} else if (!operationApplied && currentOperation != null) {
			// Se for a operação de '=' e existe uma operação a ser processada, faz o cálculo
			double value = currentOperation.apply(savedValue, getCurrentValue());
			operationApplied = true;
			setCurrentValue(value);
			clearOnNextNumber = true;
			
			// Converte o que está sendo exibido em String
			String resultStr = getResultAsString();
			if (resultStr.length() > MAX_LENGTH) {
				// Se o resultado tem tamanho maior do que o máximo permitido, trunca o valor e exibe
				// o indicativo de erro
				resultStr = resultStr.substring(0, MAX_LENGTH);
				setCurrentValue(Double.parseDouble(resultStr));
				setError(true);
			}
		}
		
		appendComma = false;
	}

	/**
	 * Converte o que está sendo exibido em String
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
