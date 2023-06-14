package br.com.softblue.java.i18n;

import java.util.ListResourceBundle;

public class Messages_ja_JP extends ListResourceBundle {

	private Object[][] content;
	
	public Messages_ja_JP() {
		content = new Object[1][2];
		content[0][0] = "msg";
		content[0][1] = "*****";
	}
	
	@Override
	protected Object[][] getContents() {
		return content;
	}

}
