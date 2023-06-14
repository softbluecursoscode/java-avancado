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
	 * @param os Stream de sa�da dos dados
	 * @throws Exception
	 */
	public static void write(Object obj, OutputStream os) throws Exception {
		// Obt�m o objeto Class
		Class<?> clazz = obj.getClass();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		//Cria um novo documento XML
		Document doc = db.newDocument();
		
		// Cria a tag raiz do XML com o mesmo nome da classe
		Element rootElem = doc.createElement(clazz.getName());
		doc.appendChild(rootElem);
		
		// Obt�m a lista dos atributos da classe
		Field[] attributes = clazz.getDeclaredFields();
		for (Field attribute : attributes) {
			// Habilita a leitura das informa��es do atributo (necess�rio se o atributo for private)
			attribute.setAccessible(true);
			
			// Obt�m o nome do atributo
			String name = attribute.getName();
			
			// Obt�m o valor atual do atributo no objeto
			Object value = attribute.get(obj);
			
			// Cria uma tag no XML com o mesmo nome do atributo
			Element nameElem = doc.createElement(name);
			rootElem.appendChild(nameElem);
			
			// Dentro da tag do atributo, adiciona o valor do atributo como texto
			Text valueText = doc.createTextNode(value.toString());
			nameElem.appendChild(valueText);
		}

		// Cria um transformer, que gerar� o arquivo XML
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		
		// Define a codifica��o do XML para UTF-8 (j� � a codifica��o padr�o)
		trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		
		// Define a origem e destino da transforma��o
		StreamResult sr = new StreamResult(os);
		DOMSource source = new DOMSource(doc);
		
		//Gera o XML de sa�da
		trans.transform(source, sr);
	}
}
