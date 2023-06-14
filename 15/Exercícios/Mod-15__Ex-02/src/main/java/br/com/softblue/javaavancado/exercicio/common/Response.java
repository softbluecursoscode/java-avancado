package br.com.softblue.javaavancado.exercicio.common;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

/**
 * Representa uma resposta a uma requisição do cliente
 */
public class Response {

	/**
	 * Resultado da operação
	 */
	private double resultado;

	/**
	 * Construtor privado
	 */
	private Response() {
	}

	/**
	 * Obtém o resultado
	 * @return Resultado
	 */
	public double getResultado() {
		return resultado;
	}
	
	/**
	 * Constroi o objeto Response usando o resultado da operação
	 * @param result Resultado da operação
	 * @return Objeto Response
	 */
	public static Response fromData(double result) {
		Response resp = new Response();
		resp.resultado = result;
		return resp;
	}
	
	/**
	 * 
	 * @param xmlResponse
	 * @return
	 * @throws CalcException
	 */
	public static Response fromXML(String xmlResponse) throws CalcException {
		try {
			Response resp = new Response();
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//Faz o parse da String de entrada
			Document doc = db.parse(new InputSource(new StringReader(xmlResponse)));
			
			Element rootElem = doc.getDocumentElement();
			
			// Lê o elemento 'result'
			Element resultElem = (Element) rootElem.getElementsByTagName("result").item(0);
			resp.resultado = Double.parseDouble(resultElem.getTextContent());
			
			return resp;
		
		} catch (Exception e) {
			throw new CalcException(e);
		}
	}
	
	/**
	 * Converte o objeto Response para o formato XML
	 * @return String no formato XML, que representa o objeto
	 * @throws CalcException
	 */
	public String toXML() throws CalcException {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//Cria um novo documento XML
			Document doc = db.newDocument();
			
			// Cria a tag raiz do XML
			Element rootElem = doc.createElement("response");
			doc.appendChild(rootElem);
			
			// Cria o elemento 'result'
			Element resultElem = doc.createElement("result");
			resultElem.setTextContent(String.valueOf(resultado));
			rootElem.appendChild(resultElem);
			
			// Cria um transformer, que gerará o arquivo XML
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer trans = tf.newTransformer();
			
			// Define a origem e destino da transformação
			StringWriter sw = new StringWriter();
			StreamResult sr = new StreamResult(sw);
			DOMSource source = new DOMSource(doc);
			
			//Gera o XML de saída
			trans.transform(source, sr);
			
			return sw.toString();
		
		} catch (Exception e) {
			throw new CalcException(e);
		}
	}
}
