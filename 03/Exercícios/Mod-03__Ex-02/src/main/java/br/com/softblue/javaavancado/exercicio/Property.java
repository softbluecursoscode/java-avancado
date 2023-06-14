package br.com.softblue.javaavancado.exercicio;

import java.lang.reflect.Method;

/**
 * Classe que possui m�todos para trabalhar com os atributos do objeto
 */
public class Property {

	/**
	 * @see Property#set(Object, String, Object, Class)
	 */
	public static <T> void set(Object obj, String property, T value) throws Exception {
		set(obj, property, value, null);
	}
	
	/**
	 * Define o valor de uma propriedade em um objeto
	 * @param obj Objeto a ser alterado
	 * @param property Nome da propriedade
	 * @param value Valor para a propriedade
	 * @param paramType Tipo de dado da propriedade sendo alterada. Se for null, o tipo utilizado ser� 
	 *                  o mesmo tipo de dado do par�metro value fornecido
	 * @throws Exception
	 */
	public static <T> void set(Object obj, String property, T value, Class<?> paramType) throws Exception {
		Class<?> clazz = obj.getClass();
		
		// Converte a primeira letra da propriedade para mai�scula
		char c = Character.toUpperCase(property.charAt(0));
		
		// Monta o nome do m�todo setter a ser procurado
		String setMethod = "set" + c + property.substring(1);
		
		if (paramType == null) {
			// Se paramType n�o foi definido, considere que ele � do mesmo tipo do valor da propriedade
			paramType = value.getClass();
		}
		
		// Obt�m uma refer�ncia ao m�todo
		Method method = clazz.getMethod(setMethod, paramType);
		
		// Faz a invoca��o
		method.invoke(obj, value);
	}
	
	/**
	 * Obt�m o valor de uma propriedade de um objeto
	 * @param obj Objeto a ser consultado
	 * @param property Nome da propriedade
	 * @param returnType Tipo de dado a ser retornado pelo m�todo
	 * @return Valor da propriedade
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(Object obj, String property, Class<T> returnType) throws Exception {
		Class<?> clazz = obj.getClass();
		
		// Converte a primeira letra da propriedade para mai�scula
		char c = Character.toUpperCase(property.charAt(0));
		
		// Monta o nome do m�todo getter a ser procurado
		String getMethod = "get" + c + property.substring(1);
		
		// Obt�m uma refer�ncia ao m�todo
		Method method = clazz.getMethod(getMethod);
		
		// Faz a invoca��o e armazena o retorno (o m�todo getter retorna um valor)
		Object ret = method.invoke(obj);
		
		// Faz um casting para o tipo desejado e retorna o valor da propriedade
		return (T) ret;
	}
}
