package br.com.softblue.javaavancado.exercicio;

import java.io.FileInputStream;
import java.io.FileOutputStream;


/**
 * Classe inicial da aplicação
 */
public class Aplicacao {

	/**
	 * Método main()
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// Cria 2 produtos e grava em arquivo
		Produto p1 = new Produto("Produto 1", 2, 100.5, false);
		XMLWriter.write(p1, new FileOutputStream("produto_1.xml"));
		
		Produto p2 = new Produto("Produto 2", 3, 30.25, true);
		XMLWriter.write(p2, new FileOutputStream("produto_2.xml"));
		
		// Cria novamente os objetos com base nos dados gravados no arquivo e exibe os produtos
		Produto p3 = (Produto) XMLReader.read(new FileInputStream("produto_1.xml"));
		Produto p4 = (Produto) XMLReader.read(new FileInputStream("produto_2.xml"));
		System.out.println(p3);
		System.out.println(p4);
	}
}
