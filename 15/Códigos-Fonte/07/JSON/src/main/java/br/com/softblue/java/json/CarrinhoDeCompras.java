package br.com.softblue.java.json;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {

	private List<Produto> produtos = new ArrayList<>();
	
	public void adicionar(Produto p) {
		produtos.add(p);
	}
	
	public void imprimir() {
		produtos.forEach(System.out::println);
	}
}
