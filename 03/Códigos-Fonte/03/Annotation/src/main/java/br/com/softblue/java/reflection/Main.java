package br.com.softblue.java.reflection;

import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws Exception {
		MinhaClasse mc = new MinhaClasse();
		
		executar(mc);
	}
	
	@SuppressWarnings("unchecked")
	private static void executar(MinhaClasse mc) throws Exception {
		Class<MinhaClasse> c = (Class<MinhaClasse>) mc.getClass();
		
		Method[] methods = c.getDeclaredMethods();
		
		for (Method m : methods) {
			Executar annot = m.getDeclaredAnnotation(Executar.class);
			
			if (annot != null) {
				String arg = annot.value();
				m.invoke(mc, arg);
			}
		}
	}
}
