package br.com.softblue.java.xml;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

public class Main {
	
	private static final String XML_JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

	public static void main(String[] args) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		
		SAXParser parser = factory.newSAXParser();
		parser.setProperty(XML_JAXP_SCHEMA_LANGUAGE, XMLConstants.W3C_XML_SCHEMA_NS_URI);

		try (InputStreamReader isr = new InputStreamReader(new FileInputStream("usuarios.xml"), "UTF-8")) {
			InputSource source = new InputSource("usuarios.xml");
			parser.parse(source, new XMLHandler());
		}
	}
}
