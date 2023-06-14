module myproject {
	exports br.com.softblue.javaavancado.exercicio;
	opens br.com.softblue.javaavancado.exercicio;
	requires transitive javafx.controls;
	requires javafx.fxml;
}