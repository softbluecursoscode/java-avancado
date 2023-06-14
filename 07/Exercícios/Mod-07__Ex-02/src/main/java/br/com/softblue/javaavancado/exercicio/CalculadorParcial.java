package br.com.softblue.javaavancado.exercicio;
import java.util.concurrent.Callable;

/**
 * Esta classe faz o cálculo de cada parcial da série de Gregory. O cálculo de cada parcial é feito
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
		 * Este é o código executado quando a thread começa a sua execução. Este método basicamente faz o
		 * cálculo da parcial da thread usando a fórmula da série de Gregory
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
