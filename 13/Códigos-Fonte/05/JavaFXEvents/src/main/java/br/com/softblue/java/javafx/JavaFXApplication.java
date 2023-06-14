package br.com.softblue.java.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXApplication extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Eventos");
		
		primaryStage.setOnCloseRequest(event -> {
			showDialog("Tchau!");
			event.consume();
		});
		
		String qm = "?????";
		
		VBox vbox = new VBox(5.0);
		vbox.setPadding(new Insets(10.0));
		
		Button btn1 = new Button(qm);
		btn1.setOnAction(event -> showDialog("Botão 1 foi pressionado!"));
		btn1.setOnMouseEntered(event -> btn1.setText("Botão 1"));
		btn1.setOnMouseExited(event -> btn1.setText(qm));
		
		btn1.setPrefWidth(100);
		
		Button btn2 = new Button(qm);
		btn2.setOnAction(event -> showDialog("Botão 2 foi pressionado!"));
		btn2.setOnMouseEntered(event -> btn2.setText("Botão 2"));
		btn2.setOnMouseExited(event -> btn2.setText(qm));
		btn2.setPrefWidth(100);
		
		vbox.getChildren().addAll(btn1, btn2);
		
		TextField text = new TextField();
		text.setOnKeyTyped(event -> System.out.println(event.getCharacter()));
		vbox.getChildren().add(text);
		
		Scene scene = new Scene(vbox, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void showDialog(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Informação");
		alert.setHeaderText("Mensagem do sistema");
		alert.setContentText(msg);
		alert.showAndWait();
	}
}
