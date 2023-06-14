package br.com.softblue.javaavancado.exercicio;


/**
 * Thread que representa o consumidor.
 * Tudo o que ele faz � consumir (remover) itens do buffer. O consumidor deve dormir enquanto n�o houver
 * itens no buffer para ele consumir.
 */
public class Consumidor implements Runnable {

	/**
	 * Quantidade de itens que devem ser consumidos
	 */
	private int qtdeItens;
	
	/**
	 * Refer�ncia para o buffer
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
		 * O consumidor fica em um loop consumindo at� o m�ximo de itens que ele deve consumir.
		 * Depois de consumir um item, ele dorme durante um tempo rand�mico (entre 0ms e 500ms).
		 * Toda a l�gica do sincronismo entre o produtor e o consumidor fica no buffer, j� que ele � o 
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
