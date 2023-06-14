package br.com.softblue.java.json;

import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) throws Exception {
		CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
		
		carrinho.adicionar(new Produto(1, "Arroz"));
		carrinho.adicionar(new Produto(2, "Feijão"));
		carrinho.adicionar(new Produto(3, "Café"));
		carrinho.imprimir();
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(carrinho);
		System.out.println(jsonStr);
		
		CarrinhoDeCompras carrinho2 = gson.fromJson(jsonStr, CarrinhoDeCompras.class);
		carrinho2.imprimir();
	}
}
