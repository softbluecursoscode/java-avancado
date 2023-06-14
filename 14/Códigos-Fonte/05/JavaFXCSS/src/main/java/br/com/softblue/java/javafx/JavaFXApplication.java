package br.com.softblue.java.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JavaFXApplication extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("CSS");
		
		Pane root = FXMLLoader.load(getClass().getResource("/Layout.fxml"));
		
		Scene scene = new Scene(root, 400, 200);
		scene.getStylesheets().add("styles.css");
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
