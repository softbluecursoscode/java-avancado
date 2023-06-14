package br.com.softblue.javaavancado.exercicio;

import java.util.Date;

/**
 * Classe que representa uma pessoa.
 * Possui atributos e métodos getter/setters para eles.
 */
public class Pessoa {

	private String nome;
	private double altura;
	private Date dataNascimento;
	private int numFilhos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getNumFilhos() {
		return numFilhos;
	}

	public void setNumFilhos(int numFilhos) {
		this.numFilhos = numFilhos;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", altura=" + altura
				+ ", dataNascimento=" + dataNascimento + ", numFilhos="
				+ numFilhos + "]";
	}

}
