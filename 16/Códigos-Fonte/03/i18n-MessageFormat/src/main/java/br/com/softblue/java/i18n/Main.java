package br.com.softblue.java.i18n;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {

	public static void main(String[] args) {
		
		Locale loc = new Locale("en", "US");
		
		ResourceBundle bundle = ResourceBundle.getBundle("Messages", loc);
		String value = bundle.getString("msg");
		System.out.println(value);
		
		MessageFormat mf = new MessageFormat(value, loc);
		Object[] params = { "José", new Date() };
		String finalMsg = mf.format(params);
		System.out.println(finalMsg);
	}
}
