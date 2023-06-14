package br.com.softblue.javaavancado.exercicio;

import java.util.Arrays;

import br.com.softblue.javaavancado.exercicio.Pessoa.Sexo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class EditorDados extends Application {

	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtIdade;
	@FXML
	private ChoiceBox<Sexo> choSexo;
	@FXML
	private CheckBox chkEsportes;

	private Pessoa pessoa;

	@FXML
	public void initialize() {
		// Monta o ChoiceBox com as opções de sexo
		ObservableList<Sexo> sexoList = FXCollections.observableArrayList(Arrays.asList(Sexo.values()));
		choSexo.setItems(sexoList);

		// Carrega os dados da pessoa
		loadPessoa();

		// Faz os bindings para que o JavaFX exiba os dados da pessoa e permita alteração no objeto
		txtNome.textProperty().bindBidirectional(pessoa.nomeProperty());
		txtIdade.textProperty().bindBidirectional(pessoa.idadeProperty(), new NumberStringConverter());
		choSexo.valueProperty().bindBidirectional(pessoa.sexoProperty());
		chkEsportes.selectedProperty().bindBidirectional(pessoa.praticaEsportesProperty());
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = FXMLLoader.load(getClass().getResource("Layout.fxml"));

		Scene scene = new Scene(root, 400, 200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Editor de Dados");
		primaryStage.show();
	}

	/**
	 * Chamado no clique do botão OK
	 */
	@FXML
	public void onConfirm() {
		// Grava os dados da pessoa no arquivo
		storePessoa();
		
		// Sai da aplicação
		Platform.exit();
	}

	/**
	 * Chamado no clique do botão Cancelar
	 */
	public void onCancel() {
		// Sai da aplicação
		Platform.exit();
	}

	/**
	 * Carrega os dados da pessoa a partir do arquivo de propriedades
	 */
	private void loadPessoa() {
		pessoa = new Pessoa();

		// Só carrega se o arquivo de propriedades existir
		if (ConfigFile.hasProperties()) {
			pessoa.setNome(ConfigFile.getProperty(ConfigFile.PROP_NOME));
			pessoa.setIdade(Integer.parseInt(ConfigFile.getProperty(ConfigFile.PROP_IDADE)));
			pessoa.setSexo(Sexo.valueOf(ConfigFile.getProperty(ConfigFile.PROP_SEXO)));
			pessoa.setPraticaEsportes(Boolean.parseBoolean(ConfigFile.getProperty(ConfigFile.PROP_ESPORTES)));
		}
	}

	/**
	 * Grava os dados da pessoa no arquivo de propriedades
	 */
	private void storePessoa() {
		ConfigFile.setProperty(ConfigFile.PROP_NOME, pessoa.getNome());
		ConfigFile.setProperty(ConfigFile.PROP_IDADE, String.valueOf(pessoa.getIdade()));
		ConfigFile.setProperty(ConfigFile.PROP_SEXO, pessoa.getSexo().toString());
		ConfigFile.setProperty(ConfigFile.PROP_ESPORTES, String.valueOf(pessoa.isPraticaEsportes()));
		
		// Faz a gravação no arquivo
		ConfigFile.saveProperties();
	}

	/**
	 * Início da execução
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
