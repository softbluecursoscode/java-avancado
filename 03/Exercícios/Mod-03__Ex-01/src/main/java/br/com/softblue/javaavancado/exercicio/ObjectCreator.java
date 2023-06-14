package br.com.softblue.javaavancado.exercicio;

import java.lang.reflect.Method;

public class ObjectCreator {

	/**
	 * Este m�todo possui a l�gica de instanciar e chamar os m�todos automaticamente ap�s a constru��o do objeto, 
	 * caso seja necess�rio
	 * @param clazz Classe que ser� utilizada na instancia��o do objeto.
	 * @return Objeto criado e com os m�todos j� invocados (se for o caso). Se o objeto n�o puder ser criado, 
	 *         o retorno � null.
	 */
	public static <T> T create(Class<T> clazz) {
		
		try {
			// Cria uma inst�ncia da classe utilizando reflex�o
			T obj = (T) clazz.getDeclaredConstructor().newInstance();
			
			// Obt�m a lista de m�todos da classe
			Method[] methods = clazz.getMethods();
			
			// Itera sobre cada m�todo
			for (Method method : methods) {
				
				// Obt�m a anota��o @Init do m�todo. Se ele n�o tiver a anota��o, retorna null
				Init init = method.getAnnotation(Init.class);
				
				// Se houver a anota��o e runOnInstantiation for true, invoca o m�todo no objeto rec�m criado
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
