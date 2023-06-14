package br.com.softblue.java.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main {

	public static void main(String[] args) {
		
		//Locale loc = Locale.getDefault();
		//Locale loc = new Locale("es", "ES");
		Locale loc = new Locale("ja", "JP");
		System.out.println(loc);
		
		ResourceBundle bundle = ResourceBundle.getBundle("br.com.softblue.java.i18n.Messages", loc);
		
		//String msg = bundle.getString("msg");
		String msg = (String) bundle.getObject("msg");
		System.out.println(msg);
	}
}
