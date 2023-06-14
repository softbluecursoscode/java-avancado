package br.com.softblue.bluekeeper.controller;

import java.util.List;

import br.com.softblue.bluekeeper.dao.DAOFactory;
import br.com.softblue.bluekeeper.dao.SenhaServicoDAO;
import br.com.softblue.bluekeeper.model.SenhaServico;
import br.com.softblue.bluekeeper.util.StringUtils;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller da tela da aplicação
 */
public class Controller {

	@FXML
	private TableView<SenhaServico> table;
	@FXML
	private TextField txtServico;
	@FXML
	private TextField txtLogin;
	@FXML
	private TextField txtSenha;
	@FXML
	private TextArea txtObservacoes;
	@FXML
	private Button btnNovo;
	@FXML
	private Button btnEditar;
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnConfirm;
	@FXML
	private TextField txtSearch;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnClearSearch;

	/**
	 * Property que indica se o modo de edição de senha de serviço está ativo
	 */
	private BooleanProperty editMode = new SimpleBooleanProperty();
	
	/**
	 * Property que indica se os resultados da tabela estão filtrados 
	 */
	private BooleanProperty resultsFiltered = new SimpleBooleanProperty();

	/**
	 * Senha de serviço ativa no momento (selecionada ou em edição)
	 */
	private SenhaServico currentSenhaServico;

	/**
	 * Objeto DAO para manipulação dos dados
	 */
	private SenhaServicoDAO dao;

	/**
	 * Método chamado pelo JavaFX quando o controller é instanciado
	 */
	@FXML
	private void initialize() {
		// Obtém o objeto DAO
		dao = DAOFactory.getSenhaServicoDAO();
		
		// Carrega os dados sem filtragem
		loadData(false);

		// Adiciona um listener para ser notificado quando o usuário seleciona um item na tabela.
		// Dessa forma é possível definir os bindings corretamente.
		table.getSelectionModel().selectedItemProperty().addListener((event, oldValue, newValue) -> {
			unbindData(oldValue);
			bindData(newValue);
		});

		// Define os bindings gerais do modelo com a interface gráfica
		btnNovo.disableProperty().bind(editMode);
		btnEditar.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull().or(editMode));
		btnExcluir.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull().or(editMode));
		btnCancel.disableProperty().bind(editMode.not());
		btnConfirm.disableProperty().bind(editMode.not());
		btnSearch.disableProperty().bind(txtSearch.textProperty().isEmpty());
		btnClearSearch.disableProperty().bind(resultsFiltered.not());

		txtServico.editableProperty().bind(editMode);
		txtLogin.editableProperty().bind(editMode);
		txtSenha.editableProperty().bind(editMode);
		txtObservacoes.editableProperty().bind(editMode);

		table.disableProperty().bind(editMode);
	}

	/**
	 * Método chamado quando o usuário usa o menu Arquivo > Sair
	 */
	@FXML
	public void exit() {
		// Encerra a aplicação
		Platform.exit();
	}

	/**
	 * Faz o binding do objeto de senha de serviço com os campos do formulário
	 * @param senhaServico
	 */
	private void bindData(SenhaServico senhaServico) {
		if (senhaServico != null) {
			txtServico.textProperty().bindBidirectional(senhaServico.servicoProperty());
			txtLogin.textProperty().bindBidirectional(senhaServico.loginProperty());
			txtSenha.textProperty().bindBidirectional(senhaServico.senhaProperty());
			txtObservacoes.textProperty().bindBidirectional(senhaServico.observacoesProperty());
		}
	}

	/**
	 * Desfaz o binding do objeto de senha de serviço com os campos do formulário
	 * @param senhaServico
	 */
	private void unbindData(SenhaServico senhaServico) {
		if (senhaServico != null) {
			txtServico.textProperty().unbindBidirectional(senhaServico.servicoProperty());
			txtLogin.textProperty().unbindBidirectional(senhaServico.loginProperty());
			txtSenha.textProperty().unbindBidirectional(senhaServico.senhaProperty());
			txtObservacoes.textProperty().unbindBidirectional(senhaServico.observacoesProperty());

			// Limpa os campos do formulário
			clearForm();
		}
	}

	/**
	 * Método chamado na criação de um novo registro de senha de serviço
	 */
	@FXML
	public void onNew() {
		// Desmarca qualquer registro que esteja selecionado na tabela 
		table.getSelectionModel().clearSelection();
		
		// Habilita o modo de edição
		editMode.set(true);
		
		// Define a senha de serviço corrente como um novo objeto
		currentSenhaServico = new SenhaServico();
		
		// Faz o binding dos dados com o formulário
		bindData(currentSenhaServico);
		
		// Coloca o foco da interface no campo de serviço
		txtServico.requestFocus();
	}

	/**
	 * Método chamado na alteração de um registro de senha de serviço
	 */
	@FXML
	public void onEdit() {
		// Habilita o modo de edição
		editMode.set(true);
		
		// Define a senha de serviço corrente como a senha de serviço selecionada na tabela
		currentSenhaServico = table.getSelectionModel().getSelectedItem();
	}

	/**
	 * Método chamado na exclusão de um registro de senha de serviço
	 */
	@FXML
	public void onDelete() {
		// Remove o registro selecionado da lista de itens que compõem a tabela
		table.getItems().remove(table.getSelectionModel().getSelectedItem());
		
		// Faz com que essa mudança seja refletida nos dados armazenados 
		storeData();
	}

	/**
	 * Método chamado quando o cancelamento da edição ou criação é realizado
	 */
	@FXML
	public void onCancel() {
		// Desabilita o modo de edição
		editMode.set(false);

		// Se era uma criação, desfaz o biding e limpa o formulário
		if (currentSenhaServico.getId() == 0) {
			unbindData(currentSenhaServico);
			clearForm();
		}
	}

	/**
	 * Método chamado quando o usuário confirma a inserção ou alteração de uma senha de serviço
	 */
	@FXML
	public void onConfirm() {
		// Valida se os dados do formulário estão ok
		String errorMessage = validateForm();
		if (!errorMessage.isEmpty()) {
			// Caso não estejam, mostra o erro de validação e retorna
			showValidationError(errorMessage);
			return;
		}

		// Desabilita o modo de edição
		editMode.set(false);

		// Caso seja a inserção de um novo registro
		if (currentSenhaServico.getId() == 0) {
			// Gera um novo ID para o registro
			int newId = dao.generateId();
			currentSenhaServico.setId(newId);
			
			// Adiciona ele na lista de itens referentes à tabela
			table.getItems().add(currentSenhaServico);
			
			// Desfaz os bindings do formulário
			unbindData(currentSenhaServico);
			
			// Limpa o formulário
			clearForm();
			
			// Seleciona o registro recém criado na tabela
			table.getSelectionModel().select(currentSenhaServico);
		}

		// Faz com que essa mudança seja refletida nos dados armazenados
		storeData();
	}

	/**
	 * Método chamado quando uma filtragem é realizada
	 */
	@FXML
	public void search() {
		// Carrega os dados com a filtragem habilitada
		loadData(true);
		
		// Define os dados como estando filtrados
		resultsFiltered.set(true);
	}
	
	/**
	 * Método chamado quando uma filtragem é cancelada
	 */
	@FXML
	public void clearSearch() {
		// Carrega os dados sem filtragem
		loadData(false);
		
		// Limpa o campo de busca
		txtSearch.clear();
		
		// Define os dados como não filtrados
		resultsFiltered.set(false);
	}

	/**
	 * Limpa os campos do formulário
	 */
	private void clearForm() {
		txtServico.clear();
		txtLogin.clear();
		txtSenha.clear();
		txtObservacoes.clear();
	}

	/**
	 * Valida o formulário
	 * @return true se os dados são válidos; false, caso contrário
	 */
	private String validateForm() {
		StringBuilder errorMessage = new StringBuilder();

		// Verifica se o serviço foi preenchido
		if (StringUtils.isEmpty(currentSenhaServico.getServico())) {
			errorMessage.append("Preencha o site/serviço").append(StringUtils.newLine());
		}

		// Verifica se o login foi preenchido
		if (StringUtils.isEmpty(currentSenhaServico.getLogin())) {
			errorMessage.append("Preencha o login").append(StringUtils.newLine());
		}

		// Verifica se a senha foi preenchida
		if (StringUtils.isEmpty(currentSenhaServico.getSenha())) {
			errorMessage.append("Preencha a senha").append(StringUtils.newLine());
		}

		return errorMessage.toString();
	}

	/**
	 * Exibe um erro de validação na tela
	 * @param message
	 */
	private void showValidationError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro de validação");
		alert.setHeaderText("Informação incorreta");
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	/**
	 * Carrega os dados na tela
	 * @param filter true se é preciso filtrar os dados; false, caso contrário
	 */
	private void loadData(boolean filter) {
		List<SenhaServico> items;
		if (!filter) {
			items = dao.load();
		
		} else {
			items = dao.filter(txtSearch.getText());
		}
		
		ObservableList<SenhaServico> list = FXCollections.observableArrayList(items);
		table.setItems(list);
	}
	
	/**
	 * Grava os dados no meio de armazenamento definido pelo DAO
	 */
	private void storeData() {
		dao.store(table.getItems());
	}
}
