package br.com.softblue.java.xml;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Main {

	public static void main(String[] args) throws Exception {
		
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema();
		Validator validator = schema.newValidator();
		validator.validate(new StreamSource(new File("usuarios.xml")));
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new File("usuarios.xml"));
		
		Element rootTag = doc.getDocumentElement();
		
		NodeList usuarioTagList = rootTag.getElementsByTagName("usuario");
		
		for (int i = 0; i < usuarioTagList.getLength(); i++) {
			Element usuarioTag = (Element) usuarioTagList.item(i);
			
			String id = usuarioTag.getAttribute("id");
			String nome = usuarioTag.getElementsByTagName("nome").item(0).getTextContent();
			String idade = usuarioTag.getElementsByTagName("idade").item(0).getTextContent();
			String email = usuarioTag.getElementsByTagName("email").item(0).getTextContent();
			
			System.out.println(id + ", " + nome + ", " + idade + ", " + email);
		}
	}
}
