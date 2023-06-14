package br.com.softblue.javaavancado.exercicio;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Esta classe representa o buffer, que � compartilhado entre o produtor e o consumidor
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
	 * Controla a posi��o onde o pr�ximo elemento deve ser inserido no buffer 
	 */
	private int posInsere;
	
	/**
	 * Controla a posi��o onde o pr�ximo elemento deve ser removido do buffer
	 */
	private int posRemove;
	
	/**
	 * Controla a quantidade de itens no buffer n�o consumidos
	 */
	private int qtdeItens;
	
	/**
	 * Lock para controlar o acesso aos m�todos de inser��o e remo��o
	 */
	private ReentrantLock lock = new ReentrantLock();
	
	/**
	 * Condi��o utilizada para sincronizar o acesso ao buffer
	 */
	private Condition cond = lock.newCondition();
	
	/**
	 * Insere um item no buffer.
	 * Este m�todo � sincronizado porque apenas uma thread por vez pode chamar os m�todos inserir() ou
	 * remover(), j� que estes m�todos s�o consideradas regi�es cr�ticas.
	 * @param item Item a ser inserido
	 */
	public void inserir(int item) {
		//Tenta obter o lock
		lock.lock();
		
		try {
			/*
			 * Se o buffer j� est� cheio, o produtor deve dormir, aguardando que existam espa�os no buffer
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
			 * pois n�o havia itens para ele consumir
			 */
			if(qtdeItens == 0) {
				cond.signalAll();
			}
			
			/*
			 * Insere o item no buffer. O buffer � implementado de forma circular. Isto �, depois do item ser 
			 * produzido na �ltima posi��o ele volta a ser produzido na posi��o 0.
			 */
			b[posInsere] = item;
			posInsere = (posInsere + 1) % TAMANHO;
			qtdeItens++;
			
			//imprime o conte�do do buffer no console
			imprimir();
		
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * Remove um item do buffer.
	 * Este m�todo � sincronizado porque apenas uma thread por vez pode chamar os m�todos remover() ou
	 * inserir(), j� que estes m�todos s�o consideradas regi�es cr�ticas.
	 * @return Item removido
	 */
	public int remover() {
		//Tenta obter o lock
		lock.lock();
		
		try {
			//se o buffer estiver vazio, o consumidor deve dormir, aguardando at� que algum item seja produzido
			while(qtdeItens == 0) {
				try {
					cond.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			/*
			 * Se o buffer estiver cheio, o notifyAll() acorda o produtor, que provavelmente estava dormindo
			 * pois o buffer j� estava com o n�mero m�ximo de itens
			 */
			if(qtdeItens == TAMANHO) {
				cond.signalAll();
			}
			
			/*
			 * Remove o item do buffer. O buffer � implementado de forma circular. Isto �, depois do item ser 
			 * consumido na �ltima posi��o ele volta a ser consumido na posi��o 0.
			 */
			int item = b[posRemove];
			posRemove = (posRemove + 1) % TAMANHO;
			qtdeItens--;
			
			//imprime o conte�do do buffer no console
			imprimir();
			return item;
		
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * Imprime o conte�do do buffer
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
