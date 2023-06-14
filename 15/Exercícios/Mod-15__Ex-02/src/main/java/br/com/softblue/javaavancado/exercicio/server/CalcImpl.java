package br.com.softblue.javaavancado.exercicio.server;

import br.com.softblue.javaavancado.exercicio.common.Calc;

/**
 * Classe que implementa as operações definidas na interface {@link Calc}
 */
public class CalcImpl implements Calc {

	/**
	 * @see br.com.softblue.javaavancado.exercicio.common.Calc#somar(double, double)
	 */
	@Override
	public double somar(double n1, double n2) {
		return n1 + n2;
	}

	/**
	 * @see br.com.softblue.javaavancado.exercicio.common.Calc#subtrair(double, double)
	 */
	@Override
	public double subtrair(double n1, double n2) {
		return n1 - n2;
	}

	/**
	 * @see br.com.softblue.javaavancado.exercicio.common.Calc#multiplicar(double, double)
	 */
	@Override
	public double multiplicar(double n1, double n2) {
		return n1 * n2;
	}

	/**
	 * @see br.com.softblue.javaavancado.exercicio.common.Calc#dividir(double, double)
	 */
	@Override
	public double dividir(double n1, double n2) {
		return n1 / n2;
	}
}
