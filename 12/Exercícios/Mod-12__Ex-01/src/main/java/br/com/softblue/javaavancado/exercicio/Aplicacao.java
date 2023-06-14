package br.com.softblue.javaavancado.exercicio;

import java.util.List;

import br.com.softblue.javaavancado.exercicio.dao.CDDAO;
import br.com.softblue.javaavancado.exercicio.entity.CD;
import br.com.softblue.javaavancado.exercicio.entity.Categoria;

/**
 * Classe inicial da aplica��o
 */
public class Aplicacao {

	/**
	 * M�todo main()
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		// DAO para manipular os CDs
		CDDAO dao = new CDDAO();
		
		// Cria um CD
		CD cd = new CD();
		cd.setNome("M�sicas - Rock");
		cd.setCategoria(Categoria.MUSICA);
		cd.setConteudo("Colet�nea de rock");
		dao.create(cd);

		// Procura por CDs cuja categoria � MUSICA
		List<CD> cds = dao.findCDsByCategoria(Categoria.MUSICA);
		
		for (CD cdMusica : cds) {
			// Para cada CD retornado, altera o conte�do
			cdMusica.setConteudo("M�sicas em geral");
			dao.update(cdMusica);
		}
	}
}
