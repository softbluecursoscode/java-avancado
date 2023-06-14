package br.com.softblue.javaavancado.exercicio;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que mapeia chaves a valores e permite recuperar um valor com base em uma chave.
 * 
 * @param <K> Tipo de dado das chaves
 * @param <V> Tipo de dado dos valores
 */
public class Map<K, V> {

	/**
	 * Lista de chaves 
	 */
	private List<K> keys = new ArrayList<K>();
	
	
	/**
	 * Lista de valores
	 */
	private List<V> values = new ArrayList<V>();
	
	/**
	 * Insere um par de chave e valor. Se a chave que está sendo inserida já existe, o código atualiza o valor 
	 * que corresponde à chave já existente (não insere a chave novamente).
	 * @param key Chave a ser inserida.
	 * @param value Valor associado à chave.
	 * @return True se a chave não existia anteriormente; false, caso contrário.
	 */
	public boolean put(K key, V value) {
		if (keys.contains(key)) { // A chave foi encontrada
			// Obtém o índice da chave
			int index = getKeyIndex(key);
			
			// Define o novo valor na lista de valores, no mesmo índice da chave encontrada
			values.set(index, value);
			
			// Retorna false porque a chave já existia
			return false;
		
		} else { // A chave não foi encontrada
			// Adiciona a chave na lista de chaves
			keys.add(key);
			
			// Adiciona o valor na lista de valores
			values.add(value);
			
			// Retorna true porque a chave não existia anteriormente
			return true;
		}
	}
	
	/**
	 * Obtém o valor associado a uma chave.
	 * @param key Chave para buscar.
	 * @return Valor associado à chave. Se a chave não for encontrada, retorna null.
	 */
	public V get(K key) {
		int index = getKeyIndex(key);
		
		// Se index for -1, significa que a chave não existe
		if (index < 0) {
			return null;
		}
		
		// Retorna o valor da lista de valores da posição equivalente à posição da chave
		return values.get(index);
	}
	
	/**
	 * Retorna o índice da lista para uma determinada chave
	 * @param key Chave para procurar
	 * @return Índice na lista que corresponde à chave. Se a chave não existir, retorna -1.
	 */
	private int getKeyIndex(K key) {
		// Itera sobre a lista de chaves para encontrar a chave pesquisada
		for (int i = 0; i < keys.size(); i++) {
			K k = keys.get(i);
			
			// Na hora em que a chave da lista for igual à chave passada como parâmetro, retorna o índice
			if (k.equals(key)) {
				return i;
			}
		}
		
		// Se chegou aqui é porque a chave não foi encontrada
		return -1;
	}
	
	/**
	 * Remove os itens armazenados por esta classe.
	 */
	public void clear() {
		// Limpa a lista de chaves
		keys.clear();
		
		// Limpa a lista de valores
		values.clear();
	}
}
