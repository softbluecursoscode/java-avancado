package br.com.softblue.java.inner;

public class Main {

	public static void main(String[] args) {
		Bebida b1 = new Cha();
		
		Bebida b2 = new Bebida() {
			@Override
			public void preparar() {
				System.out.println("Preparando bebida não identificada");
			}
		};
		
		b1.preparar();
		b2.preparar();
	}
}
