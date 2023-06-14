package br.com.softblue.javaavancado.exercicio;

import java.lang.reflect.Method;

/**
 * Classe que possui métodos para trabalhar com os atributos do objeto
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
	 * @param paramType Tipo de dado da propriedade sendo alterada. Se for null, o tipo utilizado será 
	 *                  o mesmo tipo de dado do parâmetro value fornecido
	 * @throws Exception
	 */
	public static <T> void set(Object obj, String property, T value, Class<?> paramType) throws Exception {
		Class<?> clazz = obj.getClass();
		
		// Converte a primeira letra da propriedade para maiúscula
		char c = Character.toUpperCase(property.charAt(0));
		
		// Monta o nome do método setter a ser procurado
		String setMethod = "set" + c + property.substring(1);
		
		if (paramType == null) {
			// Se paramType não foi definido, considere que ele é do mesmo tipo do valor da propriedade
			paramType = value.getClass();
		}
		
		// Obtém uma referência ao método
		Method method = clazz.getMethod(setMethod, paramType);
		
		// Faz a invocação
		method.invoke(obj, value);
	}
	
	/**
	 * Obtém o valor de uma propriedade de um objeto
	 * @param obj Objeto a ser consultado
	 * @param property Nome da propriedade
	 * @param returnType Tipo de dado a ser retornado pelo método
	 * @return Valor da propriedade
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(Object obj, String property, Class<T> returnType) throws Exception {
		Class<?> clazz = obj.getClass();
		
		// Converte a primeira letra da propriedade para maiúscula
		char c = Character.toUpperCase(property.charAt(0));
		
		// Monta o nome do método getter a ser procurado
		String getMethod = "get" + c + property.substring(1);
		
		// Obtém uma referência ao método
		Method method = clazz.getMethod(getMethod);
		
		// Faz a invocação e armazena o retorno (o método getter retorna um valor)
		Object ret = method.invoke(obj);
		
		// Faz um casting para o tipo desejado e retorna o valor da propriedade
		return (T) ret;
	}
}
