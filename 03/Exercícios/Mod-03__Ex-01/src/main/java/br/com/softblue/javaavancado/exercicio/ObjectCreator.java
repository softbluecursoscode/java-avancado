package br.com.softblue.javaavancado.exercicio;

import java.lang.reflect.Method;

public class ObjectCreator {

	/**
	 * Este método possui a lógica de instanciar e chamar os métodos automaticamente após a construção do objeto, 
	 * caso seja necessário
	 * @param clazz Classe que será utilizada na instanciação do objeto.
	 * @return Objeto criado e com os métodos já invocados (se for o caso). Se o objeto não puder ser criado, 
	 *         o retorno é null.
	 */
	public static <T> T create(Class<T> clazz) {
		
		try {
			// Cria uma instância da classe utilizando reflexão
			T obj = (T) clazz.getDeclaredConstructor().newInstance();
			
			// Obtém a lista de métodos da classe
			Method[] methods = clazz.getMethods();
			
			// Itera sobre cada método
			for (Method method : methods) {
				
				// Obtém a anotação @Init do método. Se ele não tiver a anotação, retorna null
				Init init = method.getAnnotation(Init.class);
				
				// Se houver a anotação e runOnInstantiation for true, invoca o método no objeto recém criado
				if (init != null && init.runOnInstantiation()) {
					method.invoke(obj, (Object[]) null);
				}
			}
			
			// Retorna o objeto criado
			return obj;
		
		} catch (Exception e) {
			// Se der qualquer problema, retorna null
			return null;
		}
	}
}
