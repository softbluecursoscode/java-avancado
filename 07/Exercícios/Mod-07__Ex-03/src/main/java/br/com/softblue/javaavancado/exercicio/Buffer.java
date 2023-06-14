package br.com.softblue.javaavancado.exercicio;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Esta classe representa o buffer, que é compartilhado entre o produtor e o consumidor
 */
public class Buffer {

	/**
	 * Tamanho do buffer
	 */
	private static final int TAMANHO = 5;
	
	/**
	 * Buffer propriamente dito (array de inteiros)
	 */
	private int[] b = new int[TAMANHO];
	
	/**
	 * Controla a posição onde o próximo elemento deve ser inserido no buffer 
	 */
	private int posInsere;
	
	/**
	 * Controla a posição onde o próximo elemento deve ser removido do buffer
	 */
	private int posRemove;
	
	/**
	 * Controla a quantidade de itens no buffer não consumidos
	 */
	private int qtdeItens;
	
	/**
	 * Lock para controlar o acesso aos métodos de inserção e remoção
	 */
	private ReentrantLock lock = new ReentrantLock();
	
	/**
	 * Condição utilizada para sincronizar o acesso ao buffer
	 */
	private Condition cond = lock.newCondition();
	
	/**
	 * Insere um item no buffer.
	 * Este método é sincronizado porque apenas uma thread por vez pode chamar os métodos inserir() ou
	 * remover(), já que estes métodos são consideradas regiões críticas.
	 * @param item Item a ser inserido
	 */
	public void inserir(int item) {
		//Tenta obter o lock
		lock.lock();
		
		try {
			/*
			 * Se o buffer já está cheio, o produtor deve dormir, aguardando que existam espaços no buffer
			 * para ele produzir
			 */
			while(qtdeItens == TAMANHO) {
				try {
					cond.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			/*
			 * Se o buffer estiver vazio, o notifyAll() acorda o consumidor, que provavelmente estava dormindo
			 * pois não havia itens para ele consumir
			 */
			if(qtdeItens == 0) {
				cond.signalAll();
			}
			
			/*
			 * Insere o item no buffer. O buffer é implementado de forma circular. Isto é, depois do item ser 
			 * produzido na última posição ele volta a ser produzido na posição 0.
			 */
			b[posInsere] = item;
			posInsere = (posInsere + 1) % TAMANHO;
			qtdeItens++;
			
			//imprime o conteúdo do buffer no console
			imprimir();
		
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * Remove um item do buffer.
	 * Este método é sincronizado porque apenas uma thread por vez pode chamar os métodos remover() ou
	 * inserir(), já que estes métodos são consideradas regiões críticas.
	 * @return Item removido
	 */
	public int remover() {
		//Tenta obter o lock
		lock.lock();
		
		try {
			//se o buffer estiver vazio, o consumidor deve dormir, aguardando até que algum item seja produzido
			while(qtdeItens == 0) {
				try {
					cond.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			/*
			 * Se o buffer estiver cheio, o notifyAll() acorda o produtor, que provavelmente estava dormindo
			 * pois o buffer já estava com o número máximo de itens
			 */
			if(qtdeItens == TAMANHO) {
				cond.signalAll();
			}
			
			/*
			 * Remove o item do buffer. O buffer é implementado de forma circular. Isto é, depois do item ser 
			 * consumido na última posição ele volta a ser consumido na posição 0.
			 */
			int item = b[posRemove];
			posRemove = (posRemove + 1) % TAMANHO;
			qtdeItens--;
			
			//imprime o conteúdo do buffer no console
			imprimir();
			return item;
		
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * Imprime o conteúdo do buffer
	 */
	private void imprimir() {
		int i = posRemove;
		int qtdeImpressos = 0;
		
		boolean vazio = true;
		while(qtdeImpressos < qtdeItens) {
			vazio = false;
			System.out.print(b[i] + " ");
			i = (i + 1) % TAMANHO;
			qtdeImpressos++;
		}
		
		if(vazio) {
			System.out.print("-");
		}
		
		System.out.println();
	}
}
