package br.com.softblue.java.xml;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

public class Main {

	public static void main(String[] args) throws Exception {

		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser parser = spf.newSAXParser();

		try (InputStreamReader isr = new InputStreamReader(new FileInputStream("usuarios.xml"), "UTF-8")) {
			InputSource source = new InputSource(isr);
			XMLHandler handler = new XMLHandler();
			parser.parse(source, handler);
		}
	}
}
