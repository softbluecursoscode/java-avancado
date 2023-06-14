package br.com.softblue.java.javafx;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Controller {

	@FXML
	private TextField txtNome;

	@FXML
	private Spinner<Integer> spnIdade;

	@FXML
	private CheckBox chkFilhos;

	@FXML
	private Button btnLimpar;

	@FXML
	private Button btnConfirmar;
	
	private ObjectProperty<Integer> idadeProperty;
	
	private Usuario usuario;
	
	public void initialize() {
		spnIdade.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
		
		usuario = new Usuario();
		usuario.setNome("José Costa");
		usuario.setIdade(43);
		usuario.setFilhos(true);
		
		//txtNome.textProperty().bind(usuario.nomeProperty());
		txtNome.textProperty().bindBidirectional(usuario.nomeProperty());
		
		idadeProperty = usuario.idadeProperty().asObject();
		spnIdade.getValueFactory().valueProperty().bindBidirectional(idadeProperty);
		
		chkFilhos.selectedProperty().bindBidirectional(usuario.filhosProperty());
		
		btnConfirmar.disableProperty().bind(usuario.nomeProperty().isEmpty().or(usuario.idadeProperty().lessThan(30)));
	}

	@FXML
	public void confirmar() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Confirmação");
		alert.setHeaderText("Alterações confirmadas");
		alert.setContentText(usuario.toString());
		alert.showAndWait();
	}

	@FXML
	public void limpar() {
		usuario.clear();
	}
}
