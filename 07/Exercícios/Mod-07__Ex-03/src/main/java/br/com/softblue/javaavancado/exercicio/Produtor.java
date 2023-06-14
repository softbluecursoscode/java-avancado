package br.com.softblue.javaavancado.exercicio;


/**
 * Thread que representa o produtor.
 * Tudo o que ele faz é produzir (inserir) itens no buffer. O produtor deve dormir quando o buffer estiver
 * cheio.
 */
public class Produtor implements Runnable {

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
	 * @param qtdeItens Quantidade de itens a serem produzidos
	 * @param buffer Buffer para colocar os itens
	 */
	public Produtor(int qtdeItens, Buffer buffer) {
		this.qtdeItens = qtdeItens;
		this.buffer = buffer;
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		/*
		 * O produtor fica em um loop produzindo até o máximo de itens que ele deve produzir.
		 * Depois de produzir um item, ele dorme durante um tempo randômico (entre 0ms e 500ms).
		 * Toda a lógica do sincronismo entre o produtor e o consumidor fica no buffer, já que ele é o 
		 * objeto compartilhado entre as threads
		 */
		for (int i = 1; i <= qtdeItens; i++) {

			int item = (int) (Math.random() * qtdeItens);
			buffer.inserir(item);

			int tempo = (int) (Math.random() * 500);
			try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
