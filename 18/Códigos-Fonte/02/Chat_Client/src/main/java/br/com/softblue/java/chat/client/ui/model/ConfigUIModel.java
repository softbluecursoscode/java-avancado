package br.com.softblue.java.chat.client.ui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Representa os dados da tela de preferências
 */
public class ConfigUIModel {

	/**
	 * Nome do servidor
	 */
	private StringProperty server = new SimpleStringProperty();
	
	/**
	 * Porta
	 */
	private IntegerProperty port = new SimpleIntegerProperty();

	public StringProperty serverProperty() {
		return this.server;
	}

	public String getServer() {
		return this.serverProperty().get();
	}

	public void setServer(String server) {
		this.serverProperty().set(server);
	}

	public IntegerProperty portProperty() {
		return this.port;
	}

	public int getPort() {
		return this.portProperty().get();
	}

	public void setPort(int port) {
		this.portProperty().set(port);
	}

}
