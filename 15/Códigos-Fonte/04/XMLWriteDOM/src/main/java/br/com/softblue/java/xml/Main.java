package br.com.softblue.java.xml;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Main {

	public static void main(String[] args) throws Exception {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();

		Element usuariosTag = doc.createElement("usuarios");
		doc.appendChild(usuariosTag);

		Element usuarioTag = doc.createElement("usuario");
		usuarioTag.setAttribute("id", "1");
		usuariosTag.appendChild(usuarioTag);

		Element nomeTag = doc.createElement("nome");
		nomeTag.setTextContent("José da Silva");
		usuarioTag.appendChild(nomeTag);

		Element idadeTag = doc.createElement("idade");
		idadeTag.setTextContent("30");
		usuarioTag.appendChild(idadeTag);

		Element emailTag = doc.createElement("email");
		emailTag.setTextContent("abc@xyz.com");
		usuarioTag.appendChild(emailTag);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();

		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		//trans.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

		DOMSource source = new DOMSource(doc);

		try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("usuarios.xml"), "UTF-8")) {
			StreamResult result = new StreamResult(osw);
			trans.transform(source, result);
		}
	}
}
