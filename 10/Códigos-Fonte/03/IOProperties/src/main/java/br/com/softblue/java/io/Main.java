package br.com.softblue.java.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Main {

	public static void main(String[] args) throws Exception {
		// Properties props = new Properties();
		// props.setProperty("msg", "oi, tudo bem?");
		//
		// String value = props.getProperty("msg");
		// System.out.println(value);

		Properties props = new Properties();

		try (InputStream in = new FileInputStream("props.txt")) {
			props.load(in);

			System.out.println("msg1 = " + props.getProperty("msg1"));
			System.out.println("msg2 = " + props.getProperty("msg2"));
		}
		
		props.setProperty("msg3", "C");
		
		try (OutputStream out = new FileOutputStream("props2.txt")) {
			props.store(out, "Este arquivo foi gerado automaticamente");
		}
	}
}
