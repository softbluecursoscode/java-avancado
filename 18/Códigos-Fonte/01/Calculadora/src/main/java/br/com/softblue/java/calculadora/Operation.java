package br.com.softblue.java.calculadora;

/**
 * Representa cada uma das opera��es da calculadora
 */
public enum Operation {
	SUM('+'),
	SUBTRACT('-'),
	MULTIPLY('*'),
	DIVIDE('/'),
	EQUALS('=');
	
	/**
	 * S�mbolo da opera��o
	 */
	char symbol;
	
	/**
	 * Construtor do enum
	 * @param symbol S�mbolo da opera��o
	 */
	Operation(char symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * Aplica a opera��o. O operador '=' n�o suporta esta opera��o
	 * @param n1 Operando 1
	 * @param n2 Operando 2
	 * @return Resultado da opera��o
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
		
		throw new UnsupportedOperationException("O operador '" + symbol + "' n�o suporta esta opera��o");
	}
	
	/**
	 * Obt�m um elemento do enum com base em um s�mbolo passado
	 * @param symbol S�mbolo da opera��o
	 * @return Elemento do enum ou null se o elemento n�o for encontrado
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
