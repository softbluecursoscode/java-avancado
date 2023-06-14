module myproject {
	exports br.com.softblue.java.javafx;
	requires transitive javafx.controls;
	requires javafx.fxml;
}