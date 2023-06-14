package br.com.softblue.java.chat.client.ui.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Representa os dados da tela principal
 */
public class MainUIModel {

	/**
	 * Indica se o cliente está conectado
	 */
	private BooleanProperty connected = new SimpleBooleanProperty();
	/**
	 * Nome do cliente no chat
	 */
	private StringProperty name = new SimpleStringProperty();
	
	/**
	 * Mensagem enviada pelo cliente para o chat 
	 */
	private StringProperty message = new SimpleStringProperty();
	
	/**
	 * Mensagens já recebidas pelo cliente 
	 */
	private StringProperty messages = new SimpleStringProperty();
	
	/**
	 * Título da janela
	 */
	private StringProperty windowTitle = new SimpleStringProperty();

	public BooleanProperty connectedProperty() {
		return this.connected;
	}

	public boolean isConnected() {
		return this.connectedProperty().get();
	}

	public void setConnected(boolean connected) {
		this.connectedProperty().set(connected);
	}

	public StringProperty nameProperty() {
		return this.name;
	}

	public String getName() {
		return this.nameProperty().get();
	}

	public void setName(String name) {
		this.nameProperty().set(name);
	}

	public StringProperty messageProperty() {
		return this.message;
	}

	public String getMessage() {
		return this.messageProperty().get();
	}

	public void setMessage(String message) {
		this.messageProperty().set(message);
	}

	public StringProperty messagesProperty() {
		return this.messages;
	}

	public String getMessages() {
		return this.messagesProperty().get();
	}

	public void setMessages(String messages) {
		this.messagesProperty().set(messages);
	}

	public StringProperty windowTitleProperty() {
		return this.windowTitle;
	}

	public String getWindowTitle() {
		return this.windowTitleProperty().get();
	}

	public void setWindowTitle(String windowTitle) {
		this.windowTitleProperty().set(windowTitle);
	}
	
	/**
	 * Adiciona a mensagem no final das mensagens já existentes. O '\n' garante a quebra de linha no final
	 * @param message Mensagem a ser concatenada no final
	 */
	public void appendMessage(String message) {
		setMessages(getMessages() + message + "\n");
	}
}
