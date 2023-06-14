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
 * Controller da tela da aplica��o
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
	 * Property que indica se o modo de edi��o de senha de servi�o est� ativo
	 */
	private BooleanProperty editMode = new SimpleBooleanProperty();
	
	/**
	 * Property que indica se os resultados da tabela est�o filtrados 
	 */
	private BooleanProperty resultsFiltered = new SimpleBooleanProperty();

	/**
	 * Senha de servi�o ativa no momento (selecionada ou em edi��o)
	 */
	private SenhaServico currentSenhaServico;

	/**
	 * Objeto DAO para manipula��o dos dados
	 */
	private SenhaServicoDAO dao;

	/**
	 * M�todo chamado pelo JavaFX quando o controller � instanciado
	 */
	@FXML
	private void initialize() {
		// Obt�m o objeto DAO
		dao = DAOFactory.getSenhaServicoDAO();
		
		// Carrega os dados sem filtragem
		loadData(false);

		// Adiciona um listener para ser notificado quando o usu�rio seleciona um item na tabela.
		// Dessa forma � poss�vel definir os bindings corretamente.
		table.getSelectionModel().selectedItemProperty().addListener((event, oldValue, newValue) -> {
			unbindData(oldValue);
			bindData(newValue);
		});

		// Define os bindings gerais do modelo com a interface gr�fica
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
	 * M�todo chamado quando o usu�rio usa o menu Arquivo > Sair
	 */
	@FXML
	public void exit() {
		// Encerra a aplica��o
		Platform.exit();
	}

	/**
	 * Faz o binding do objeto de senha de servi�o com os campos do formul�rio
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
	 * Desfaz o binding do objeto de senha de servi�o com os campos do formul�rio
	 * @param senhaServico
	 */
	private void unbindData(SenhaServico senhaServico) {
		if (senhaServico != null) {
			txtServico.textProperty().unbindBidirectional(senhaServico.servicoProperty());
			txtLogin.textProperty().unbindBidirectional(senhaServico.loginProperty());
			txtSenha.textProperty().unbindBidirectional(senhaServico.senhaProperty());
			txtObservacoes.textProperty().unbindBidirectional(senhaServico.observacoesProperty());

			// Limpa os campos do formul�rio
			clearForm();
		}
	}

	/**
	 * M�todo chamado na cria��o de um novo registro de senha de servi�o
	 */
	@FXML
	public void onNew() {
		// Desmarca qualquer registro que esteja selecionado na tabela 
		table.getSelectionModel().clearSelection();
		
		// Habilita o modo de edi��o
		editMode.set(true);
		
		// Define a senha de servi�o corrente como um novo objeto
		currentSenhaServico = new SenhaServico();
		
		// Faz o binding dos dados com o formul�rio
		bindData(currentSenhaServico);
		
		// Coloca o foco da interface no campo de servi�o
		txtServico.requestFocus();
	}

	/**
	 * M�todo chamado na altera��o de um registro de senha de servi�o
	 */
	@FXML
	public void onEdit() {
		// Habilita o modo de edi��o
		editMode.set(true);
		
		// Define a senha de servi�o corrente como a senha de servi�o selecionada na tabela
		currentSenhaServico = table.getSelectionModel().getSelectedItem();
	}

	/**
	 * M�todo chamado na exclus�o de um registro de senha de servi�o
	 */
	@FXML
	public void onDelete() {
		// Remove o registro selecionado da lista de itens que comp�em a tabela
		table.getItems().remove(table.getSelectionModel().getSelectedItem());
		
		// Faz com que essa mudan�a seja refletida nos dados armazenados 
		storeData();
	}

	/**
	 * M�todo chamado quando o cancelamento da edi��o ou cria��o � realizado
	 */
	@FXML
	public void onCancel() {
		// Desabilita o modo de edi��o
		editMode.set(false);

		// Se era uma cria��o, desfaz o biding e limpa o formul�rio
		if (currentSenhaServico.getId() == 0) {
			unbindData(currentSenhaServico);
			clearForm();
		}
	}

	/**
	 * M�todo chamado quando o usu�rio confirma a inser��o ou altera��o de uma senha de servi�o
	 */
	@FXML
	public void onConfirm() {
		// Valida se os dados do formul�rio est�o ok
		String errorMessage = validateForm();
		if (!errorMessage.isEmpty()) {
			// Caso n�o estejam, mostra o erro de valida��o e retorna
			showValidationError(errorMessage);
			return;
		}

		// Desabilita o modo de edi��o
		editMode.set(false);

		// Caso seja a inser��o de um novo registro
		if (currentSenhaServico.getId() == 0) {
			// Gera um novo ID para o registro
			int newId = dao.generateId();
			currentSenhaServico.setId(newId);
			
			// Adiciona ele na lista de itens referentes � tabela
			table.getItems().add(currentSenhaServico);
			
			// Desfaz os bindings do formul�rio
			unbindData(currentSenhaServico);
			
			// Limpa o formul�rio
			clearForm();
			
			// Seleciona o registro rec�m criado na tabela
			table.getSelectionModel().select(currentSenhaServico);
		}

		// Faz com que essa mudan�a seja refletida nos dados armazenados
		storeData();
	}

	/**
	 * M�todo chamado quando uma filtragem � realizada
	 */
	@FXML
	public void search() {
		// Carrega os dados com a filtragem habilitada
		loadData(true);
		
		// Define os dados como estando filtrados
		resultsFiltered.set(true);
	}
	
	/**
	 * M�todo chamado quando uma filtragem � cancelada
	 */
	@FXML
	public void clearSearch() {
		// Carrega os dados sem filtragem
		loadData(false);
		
		// Limpa o campo de busca
		txtSearch.clear();
		
		// Define os dados como n�o filtrados
		resultsFiltered.set(false);
	}

	/**
	 * Limpa os campos do formul�rio
	 */
	private void clearForm() {
		txtServico.clear();
		txtLogin.clear();
		txtSenha.clear();
		txtObservacoes.clear();
	}

	/**
	 * Valida o formul�rio
	 * @return true se os dados s�o v�lidos; false, caso contr�rio
	 */
	private String validateForm() {
		StringBuilder errorMessage = new StringBuilder();

		// Verifica se o servi�o foi preenchido
		if (StringUtils.isEmpty(currentSenhaServico.getServico())) {
			errorMessage.append("Preencha o site/servi�o").append(StringUtils.newLine());
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
	 * Exibe um erro de valida��o na tela
	 * @param message
	 */
	private void showValidationError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro de valida��o");
		alert.setHeaderText("Informa��o incorreta");
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	/**
	 * Carrega os dados na tela
	 * @param filter true se � preciso filtrar os dados; false, caso contr�rio
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
