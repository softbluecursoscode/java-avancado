package br.com.softblue.javaavancado.exercicio.common;

/**
 * Interface que cont�m as opera��es que poder�o ser executadas remotamente por um cliente
 */
public interface Calc {

	/**
	 * Soma dois valores
	 * @param n1 Valor 1
	 * @param n2 Valor 2
	 * @return Resultado da opera��o
	 * @throws CalcException
	 */
	public double somar(double n1, double n2) throws CalcException;
	
	/**
	 * Subtrai dois valores
	 * @param n1 Valor 1
	 * @param n2 Valor 2
	 * @return Resultado da opera��o
	 * @throws CalcException
	 */
	public double subtrair(double n1, double n2) throws CalcException;
	
	/**
	 * Multiplica dois valores
	 * @param n1 Valor 1
	 * @param n2 Valor 2
	 * @return Resultado da opera��o
	 * @throws CalcException
	 */
	public double multiplicar(double n1, double n2) throws CalcException;
	
	/**
	 * Divide dois valores
	 * @param n1 Valor 1
	 * @param n2 Valor 2
	 * @return Resultado da opera��o
	 * @throws CalcException
	 */
	public double dividir(double n1, double n2) throws CalcException;
}
