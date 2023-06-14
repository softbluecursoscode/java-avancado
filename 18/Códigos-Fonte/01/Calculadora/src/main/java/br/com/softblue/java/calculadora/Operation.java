package br.com.softblue.java.calculadora;

/**
 * Representa cada uma das operações da calculadora
 */
public enum Operation {
	SUM('+'),
	SUBTRACT('-'),
	MULTIPLY('*'),
	DIVIDE('/'),
	EQUALS('=');
	
	/**
	 * Símbolo da operação
	 */
	char symbol;
	
	/**
	 * Construtor do enum
	 * @param symbol Símbolo da operação
	 */
	Operation(char symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * Aplica a operação. O operador '=' não suporta esta operação
	 * @param n1 Operando 1
	 * @param n2 Operando 2
	 * @return Resultado da operação
	 */
	public double apply(double n1, double n2) {
		if (this == Operation.SUM) {
			return n1 + n2;
		
		} else if (this == Operation.SUBTRACT) {
			return n1 - n2;
		
		} else if (this == Operation.MULTIPLY) {
			return n1 * n2;
		
		} else if (this == Operation.DIVIDE) {
			return n1 / n2;
		}
		
		throw new UnsupportedOperationException("O operador '" + symbol + "' não suporta esta operação");
	}
	
	/**
	 * Obtém um elemento do enum com base em um símbolo passado
	 * @param symbol Símbolo da operação
	 * @return Elemento do enum ou null se o elemento não for encontrado
	 */
	public static Operation fromSymbol(char symbol) {
		for (Operation o : Operation.values()) {
			if (o.symbol == symbol) {
				return o;
			}
		}
		
		return null;
	}
}
