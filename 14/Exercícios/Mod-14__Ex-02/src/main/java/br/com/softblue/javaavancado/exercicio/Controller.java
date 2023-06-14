package br.com.softblue.javaavancado.exercicio;

import java.lang.reflect.Method;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {

	@FXML
	private TextField txtClassName;

	@FXML
	private ListView<Method> lstClasses;
	
	@FXML
	public void search() {
		try {
			// Obtém o objeto Class da classe fornecido pelo usuário
			Class<?> clazz = Class.forName(txtClassName.getText());
			
			// Obtém a lista de métodos da classe
			Method[] methods = clazz.getDeclaredMethods();
			
			// Cria uma ObservableList com base na lista de métodos
			ObservableList<Method> list = FXCollections.observableArrayList(methods);
			
			// Coloca a lista de itens
			lstClasses.setItems(list);
		
		} catch (ClassNotFoundException e) {
			// A classe não foi encontrada, então exibe o alerta de erro
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("A classe " + txtClassName.getText() + " não foi encontrada");
			alert.showAndWait();
		}
	}
}
