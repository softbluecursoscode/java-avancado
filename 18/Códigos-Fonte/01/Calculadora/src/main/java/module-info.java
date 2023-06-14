module calculadora {
	exports br.com.softblue.java.calculadora;
	opens br.com.softblue.java.calculadora;
	requires transitive javafx.controls;
	requires javafx.fxml;
}