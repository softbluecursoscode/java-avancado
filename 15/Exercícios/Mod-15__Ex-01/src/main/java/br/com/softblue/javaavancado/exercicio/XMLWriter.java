package br.com.softblue.javaavancado.exercicio;

import java.io.OutputStream;
import java.lang.reflect.Field;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * Grava objetos no formato XML
 */
public class XMLWriter {

	/**
	 * Grava um objeto
	 * @param obj Objeto a ser gravado
	 * @param os Stream de saída dos dados
	 * @throws Exception
	 */
	public static void write(Object obj, OutputStream os) throws Exception {
		// Obtém o objeto Class
		Class<?> clazz = obj.getClass();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		//Cria um novo documento XML
		Document doc = db.newDocument();
		
		// Cria a tag raiz do XML com o mesmo nome da classe
		Element rootElem = doc.createElement(clazz.getName());
		doc.appendChild(rootElem);
		
		// Obtém a lista dos atributos da classe
		Field[] attributes = clazz.getDeclaredFields();
		for (Field attribute : attributes) {
			// Habilita a leitura das informações do atributo (necessário se o atributo for private)
			attribute.setAccessible(true);
			
			// Obtém o nome do atributo
			String name = attribute.getName();
			
			// Obtém o valor atual do atributo no objeto
			Object value = attribute.get(obj);
			
			// Cria uma tag no XML com o mesmo nome do atributo
			Element nameElem = doc.createElement(name);
			rootElem.appendChild(nameElem);
			
			// Dentro da tag do atributo, adiciona o valor do atributo como texto
			Text valueText = doc.createTextNode(value.toString());
			nameElem.appendChild(valueText);
		}

		// Cria um transformer, que gerará o arquivo XML
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		
		// Define a codificação do XML para UTF-8 (já é a codificação padrão)
		trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		
		// Define a origem e destino da transformação
		StreamResult sr = new StreamResult(os);
		DOMSource source = new DOMSource(doc);
		
		//Gera o XML de saída
		trans.transform(source, sr);
	}
}
