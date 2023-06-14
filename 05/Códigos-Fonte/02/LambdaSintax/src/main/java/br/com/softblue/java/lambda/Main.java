package br.com.softblue.java.lambda;

public class Main {

	public static void main(String[] args) {
		
//		Calculator calc = new Calculator() {
//			
//			@Override
//			public void calculate(int x) {
//				x = x * x;
//				System.out.println(x);
//			}
//		};
		
		//Calculator calc = (e, f) -> e = e * e;
		//calc.calculate(7, 0);
		
		Calculator2 calc = p -> p * p;
		
		System.out.println(calc.calculate(9));
	}
}
