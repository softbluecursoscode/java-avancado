package br.com.softblue.javaavancado.exercicio;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Representa os dados de uma pessoa
 */
public class Pessoa {
	/**
	 * Opções de sexo
	 */
	public enum Sexo {
		MASCULINO, FEMININO
	}
	
	private StringProperty nome = new SimpleStringProperty();
	private IntegerProperty idade = new SimpleIntegerProperty();
	private ObjectProperty<Sexo> sexo = new SimpleObjectProperty<>();
	private BooleanProperty praticaEsportes = new SimpleBooleanProperty();

	public StringProperty nomeProperty() {
		return this.nome;
	}

	public String getNome() {
		return this.nomeProperty().get();
	}

	public void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}

	public IntegerProperty idadeProperty() {
		return this.idade;
	}

	public int getIdade() {
		return this.idadeProperty().get();
	}

	public void setIdade(final int idade) {
		this.idadeProperty().set(idade);
	}

	public ObjectProperty<Sexo> sexoProperty() {
		return this.sexo;
	}

	public br.com.softblue.javaavancado.exercicio.Pessoa.Sexo getSexo() {
		return this.sexoProperty().get();
	}

	public void setSexo(final br.com.softblue.javaavancado.exercicio.Pessoa.Sexo sexo) {
		this.sexoProperty().set(sexo);
	}

	public BooleanProperty praticaEsportesProperty() {
		return this.praticaEsportes;
	}

	public boolean isPraticaEsportes() {
		return this.praticaEsportesProperty().get();
	}

	public void setPraticaEsportes(final boolean praticaEsportes) {
		this.praticaEsportesProperty().set(praticaEsportes);
	}

}
