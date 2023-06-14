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
			// Obt�m o objeto Class da classe fornecido pelo usu�rio
			Class<?> clazz = Class.forName(txtClassName.getText());
			
			// Obt�m a lista de m�todos da classe
			Method[] methods = clazz.getDeclaredMethods();
			
			// Cria uma ObservableList com base na lista de m�todos
			ObservableList<Method> list = FXCollections.observableArrayList(methods);
			
			// Coloca a lista de itens
			lstClasses.setItems(list);
		
		} catch (ClassNotFoundException e) {
			// A classe n�o foi encontrada, ent�o exibe o alerta de erro
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("A classe " + txtClassName.getText() + " n�o foi encontrada");
			alert.showAndWait();
		}
	}
}
