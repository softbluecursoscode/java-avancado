package br.com.softblue.javaavancado.exercicio;


/**
 * Thread que representa o consumidor.
 * Tudo o que ele faz é consumir (remover) itens do buffer. O consumidor deve dormir enquanto não houver
 * itens no buffer para ele consumir.
 */
public class Consumidor implements Runnable {

	/**
	 * Quantidade de itens que devem ser consumidos
	 */
	private int qtdeItens;
	
	/**
	 * Referência para o buffer
	 */
	private Buffer buffer;

	/**
	 * Construtor
	 * @param qtdeItens Quantidade de itens a serem consumidos
	 * @param buffer Buffer com os itens
	 */
	public Consumidor(int qtdeItens, Buffer buffer) {
		this.qtdeItens = qtdeItens;
		this.buffer = buffer;
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		/*
		 * O consumidor fica em um loop consumindo até o máximo de itens que ele deve consumir.
		 * Depois de consumir um item, ele dorme durante um tempo randômico (entre 0ms e 500ms).
		 * Toda a lógica do sincronismo entre o produtor e o consumidor fica no buffer, já que ele é o 
		 * objeto compartilhado entre as threads
		 */
		for (int i = 1; i <= qtdeItens; i++) {
			buffer.remover();

			int tempo = (int) (Math.random() * 500);
			try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
