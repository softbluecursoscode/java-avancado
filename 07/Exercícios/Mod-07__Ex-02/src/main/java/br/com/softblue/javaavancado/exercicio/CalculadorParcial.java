package br.com.softblue.javaavancado.exercicio;
import java.util.concurrent.Callable;

/**
 * Esta classe faz o c�lculo de cada parcial da s�rie de Gregory. O c�lculo de cada parcial � feito
 * em uma thread separada.
 */
public class CalculadorParcial implements Callable<Double> {
	/**
	 * ID da thread
	 */
	private int id;

	/**
	 * Construtor
	 * @param id ID da thread
	 */
	public CalculadorParcial(int id) {
		this.id = id;
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public Double call() {
		/*
		 * Este � o c�digo executado quando a thread come�a a sua execu��o. Este m�todo basicamente faz o
		 * c�lculo da parcial da thread usando a f�rmula da s�rie de Gregory
		 */
		double soma = 0;

		for (int i = id; i < Calculador.NUM_ITERACOES; i += Calculador.NUM_THREADS) {
			int s;
			if (i % 2 == 0) {
				s = 1;
			} else {
				s = -1;
			}

			soma += (double) s / (2 * i + 1);
		}
		
		/*
		 * Depois da parcial calculada, a thread retorna o valor que ela calculou
		 */
		return soma;
	}
}
