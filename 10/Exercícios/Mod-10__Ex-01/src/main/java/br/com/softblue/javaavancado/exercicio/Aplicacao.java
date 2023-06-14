package br.com.softblue.javaavancado.exercicio;

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
		
		// Cria o objeto config que aponta para o arquivo 'info.properties'
		Config c = new Config("info.properties");
		
		// Exemplo de leitura/gravação de uma propriedade String
		String versao = c.getPropertyAsString("appVersion");
		if (versao == null) {
			c.setProperty("appVersion", "1.0.1");
			System.out.println("Propriedade adicionada: 'appVersion'");
		} else {
			System.out.println("Propriedade 'appVersion' lida: " + versao);
		}
		
		// Exemplo de leitura/gravação de uma propriedade Boolean
		Boolean enviarEmail = c.getPropertyAsBoolean("sendEmail");
		if (enviarEmail == null) {
			c.setProperty("sendEmail", true);
			System.out.println("Propriedade adicionada: 'sendEmail'");
		} else {
			System.out.println("Propriedade 'sendEmail' lida: " + enviarEmail);
		}
		
		// Exemplo de leitura/gravação de uma propriedade Integer
		Integer tempoEspera = c.getPropertyAsInteger("wait");
		if (tempoEspera == null) {
			c.setProperty("wait", 1000);
			System.out.println("Propriedade adicionada: 'wait'");
		} else {
			System.out.println("Propriedade 'wait' lida: " + tempoEspera);
		}
	}
}
