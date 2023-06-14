package br.com.softblue.javaavancado.exercicio;

import java.io.InputStream;
import java.lang.reflect.Field;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * Lê objetos armazenados em XML
 */
public class XMLReader {

	/**
	 * Lê um objeto
	 * @param is Stream de entrada de dados
	 * @return Objeto criado
	 * @throws Exception
	 */
	public static Object read(InputStream is) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		//Faz o parse da input stream
		Document doc = db.parse(is);
		
		Element rootElem = doc.getDocumentElement();
		
		// O nome da classe é o nome da tag raiz do XML
		String className = rootElem.getNodeName();
		
		// Cria um objeto da classe
		Class<?> clazz = Class.forName(className);
		Object obj = clazz.getDeclaredConstructor().newInstance();
		
		// Obtém a lista de atributos da classe
		Field[] attributes = clazz.getDeclaredFields();
		
		for (Field attribute : attributes) {
			// Para cada atributo, lê o seu nome e extrai do XML a tag que corresponde ao nome do atributo
			String name = attribute.getName();
			Element nameElem = (Element) rootElem.getElementsByTagName(name).item(0);
			
			// Obtém o tipo de dado do atributo
			Class<?> type = attribute.getType();	
			
			// Habilita o acesso ao atributo (necessário se o atributo for private)
			attribute.setAccessible(true);
			
			Object value;
			// Dependendo do tipo do atributo, é preciso efetuar a conversão do dado vindo do XML
			if (type == String.class) {
				value = nameElem.getTextContent();
			} else if (type == int.class) {
				value = Integer.parseInt(nameElem.getTextContent());
			} else if (type == double.class) {
				value = Double.parseDouble(nameElem.getTextContent());
			} else if (type == boolean.class) {
				value = Boolean.parseBoolean(nameElem.getTextContent());
			} else {
				value = null;
			}
			
			// Atribui o valor lido do XML ao atributo
			attribute.set(obj, value);
		}
		
		// Retorna o objeto já com os dados lidos do XML
		return obj;
	}
}
