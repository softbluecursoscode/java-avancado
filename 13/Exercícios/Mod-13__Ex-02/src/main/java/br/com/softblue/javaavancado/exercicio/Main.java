package br.com.softblue.javaavancado.exercicio;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Application do JavaFX
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// BorderPane como raiz do layout
		BorderPane borderPane = new BorderPane();
		
		// Cria a barra de menus com o menu arquivo
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("Arquivo");
		menuBar.getMenus().add(menuFile);
		borderPane.setTop(menuBar); // Adiciona na parte superior do BorderPane
		
		// Cria a Label que representa a barra de status
		Label lblStatus = new Label("Barra de status");
		lblStatus.setPadding(new Insets(5));
		borderPane.setBottom(lblStatus); // Adiciona na parte inferior do BorderPane
		
		// Cria o TextArea central
		TextArea txtContent = new TextArea();
		borderPane.setCenter(txtContent); // Adiciona na parte central do Border Pane
		
		// Coloca uma margem para separar do ListView
		BorderPane.setMargin(txtContent, new Insets(0, 10, 0, 0));
		
		// Cria o ListView
		ListView<String> listView = new ListView<>();
		
		// Define alguns itens
		ObservableList<String> items = FXCollections.observableArrayList();
		items.addAll("A", "B", "C", "D", "E");
		listView.setItems(items);
		
		listView.setPrefWidth(100); // Define o tamanho como 100
		borderPane.setRight(listView); // Adiciona na lateral direita do BorderPane
		
		// Cria a cena
		Scene scene = new Scene(borderPane, 640, 480);

		// Define características da janela e exibe
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
