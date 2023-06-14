package br.com.softblue.javaavancado.exercicio;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Application do JavaFX
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// GridPane principal
		GridPane grid = new GridPane();
		grid.setVgap(5); // Espaçamento vertical entre células
		grid.setHgap(5); // Espaçamento horizontal entre células
		grid.setPadding(new Insets(5)); // Padding
		grid.setAlignment(Pos.CENTER); // Centralizado na tela
		
		// Label na posição 0,0 do grid
		Label lblUser = new Label("Usuário:");
		GridPane.setRowIndex(lblUser, 0);
		GridPane.setColumnIndex(lblUser, 0);
		
		// TextField na posição 0,1 do grid
		TextField txtUser = new TextField();
		txtUser.setPrefWidth(190);
		GridPane.setRowIndex(txtUser, 0);
		GridPane.setColumnIndex(txtUser, 1);
		
		// Adiciona os componentes da primeira linha do grid
		grid.getChildren().addAll(lblUser, txtUser);
		
		// Label na posição 1,0 do grid
		Label lblPassword = new Label("Senha:");
		GridPane.setRowIndex(lblPassword, 1);
		GridPane.setColumnIndex(lblPassword, 0);
		
		// TextField na posição 1,1 do grid
		TextField txtPassword = new TextField();
		txtUser.setPrefWidth(190);
		GridPane.setRowIndex(txtPassword, 1);
		GridPane.setColumnIndex(txtPassword, 1);
		
		// Adiciona os componentes da segunda linha do grid
		grid.getChildren().addAll(lblPassword, txtPassword);
		
		// HBox para conter os botões da parte inferior
		HBox hbox = new HBox(5);
		hbox.setAlignment(Pos.CENTER_RIGHT); // Elementos alinhados à direita
		
		// O HBox ocupa a terceira linha do grid e ocupa duas colunas
		GridPane.setRowIndex(hbox, 2);
		GridPane.setColumnIndex(hbox, 0);
		GridPane.setColumnSpan(hbox, 2);
		
		// Criação de botões e inserção no HBox
		Button btnLogin = new Button("Login");
		btnLogin.setPrefWidth(80);
		Button btnCancel = new Button("Cancelar");
		btnCancel.setPrefWidth(80);
		hbox.getChildren().addAll(btnLogin, btnCancel);
		
		// Adiciona o HBox no grid
		grid.getChildren().add(hbox);
		
		// Cria a cena
		Scene scene = new Scene(grid, 280, 180);

		// Define características da janela e exibe
		primaryStage.setTitle("Login");
		primaryStage.setResizable(false); // A janela não pode ser redimensionada
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
