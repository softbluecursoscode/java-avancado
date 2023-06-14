package br.com.softblue.java.javafx;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {

	private StringProperty nome = new SimpleStringProperty();
	private IntegerProperty idade = new SimpleIntegerProperty();
	private BooleanProperty filhos = new SimpleBooleanProperty();

	public String getNome() {
		return nome.get();
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}

	public int getIdade() {
		return idade.get();
	}

	public void setIdade(int idade) {
		this.idade.set(idade);
	}

	public boolean getFilhos() {
		return filhos.get();
	}

	public void setFilhos(boolean filhos) {
		this.filhos.set(filhos);
	}
	
	public StringProperty nomeProperty() {
		return nome;
	}

	public IntegerProperty idadeProperty() {
		return idade;
	}
	
	public BooleanProperty filhosProperty() {
		return filhos;
	}
	
	public void clear() {
		setNome(null);
		setIdade(0);
		setFilhos(false);
	}
	
	@Override
	public String toString() {
		return String.format("Nome: %s\nIdade: %d\nFilhos: %s", getNome(), getIdade(), getFilhos() ? "Sim" : "Não");
	}
}
