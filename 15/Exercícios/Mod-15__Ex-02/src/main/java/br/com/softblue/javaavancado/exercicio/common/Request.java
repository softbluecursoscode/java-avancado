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
 * Representa uma requisição de um cliente para excutar alguma operação
 */
public class Request {

	/**
	 * Operações disponíveis
	 */
	public enum Op {
		SOMAR,
		SUBTRAIR,
		MULTIPLICAR,
		DIVIDIR
	}
	
	/**
	 * Operação a ser executada
	 */
	private Op operacao;
	
	/**
	 * Valor 1
	 */
	private double n1;
	
	/**
	 * Valor 2
	 */
	private double n2;
	
	/**
	 * Construtor privado
	 */
	private Request() {
	}
	
	/**
	 * Constroi um objeto Request e o popula com os dados passados como parâmetro
	 * @param op Operação
	 * @param n1 Valor 1
	 * @param n2 Valor 2
	 * @return Objeto Request
	 */
	public static Request fromData(Op op, double n1, double n2) {
		Request req = new Request();
		req.operacao = op;
		req.n1 = n1;
		req.n2 = n2;
		return req;
	}
	
	/**
	 * Constroi um objeto Request com base nos dados XML
	 * @param xmlRequest XML com os dados a serem utilizados na criação do objeto
	 * @return Objeto Request
	 * @throws CalcException
	 */
	public static Request fromXML(String xmlRequest) throws CalcException {
		try {
			Request req = new Request();
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//Faz o parse da String de entrada
			Document doc = db.parse(new InputSource(new StringReader(xmlRequest)));
			
			Element rootElem = doc.getDocumentElement();
			
			// Lê o elemento 'op'
			Element opElem = (Element) rootElem.getElementsByTagName("op").item(0);
			req.operacao = Op.valueOf(opElem.getTextContent());
			
			// Lê o elemento 'valor1'
			Element n1Elem = (Element) rootElem.getElementsByTagName("valor1").item(0);
			req.n1 = Double.parseDouble(n1Elem.getTextContent());
			
			// Lê o elemento 'valor2'
			Element n2Elem = (Element) rootElem.getElementsByTagName("valor2").item(0);
			req.n2 = Double.parseDouble(n2Elem.getTextContent());
			
			return req;
		
		} catch (Exception e) {
			throw new CalcException(e);
		}
	}
	
	/**
	 * Converte o objeto Request para o formato XML
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
			Element rootElem = doc.createElement("request");
			doc.appendChild(rootElem);
			
			// Cria o elemento 'op'
			Element opElem = doc.createElement("op");
			opElem.setTextContent(operacao.toString());
			rootElem.appendChild(opElem);
			
			// Cria o elemento 'valor1'
			Element n1Elem = doc.createElement("valor1");
			n1Elem.setTextContent(String.valueOf(n1));
			rootElem.appendChild(n1Elem);
			
			// Cria o elemento 'valor2'
			Element n2Elem = doc.createElement("valor2");
			n2Elem.setTextContent(String.valueOf(n2));
			rootElem.appendChild(n2Elem);
			
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

	/**
	 * Obtém a operação
	 * @return Operação
	 */
	public Op getOperacao() {
		return operacao;
	}

	/**
	 * Obtém o valor 1
	 * @return Valor 1
	 */
	public double getN1() {
		return n1;
	}

	/**
	 * Obtém o valor 2
	 * @return Valor 2
	 */
	public double getN2() {
		return n2;
	}
}
