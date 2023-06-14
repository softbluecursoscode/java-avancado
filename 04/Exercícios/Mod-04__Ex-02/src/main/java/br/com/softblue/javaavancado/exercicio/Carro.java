package br.com.softblue.javaavancado.exercicio;

public class Carro {

	// Inner class
	private class Motor {
		@SuppressWarnings("unused") // Esta linha evita que apare�a um warning no atributo, por n�o estar sendo usado
		private boolean ligado;
		
		public void ligar() {
			ligado = true;
		}
	}
	
	// Motor do carro
	private Motor motor = new Motor();
	
	// Liga o motor, delegando a chamada ao objeto da inner class
	public void ligarMotor() {
		motor.ligar();
	}
}
