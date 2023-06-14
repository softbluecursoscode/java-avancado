package br.com.softblue.java.chat.common.utils;

import javafx.scene.control.TextField;


/**
 * TextField do JavaFX que s� aceita n�meros
 */
public class NumericTextField extends TextField {

	/**
	 * @see javafx.scene.control.TextInputControl#replaceText(int, int, java.lang.String)
	 */
	@Override
	public void replaceText(int start, int end, String text) {
		System.out.println(text);
		if (validate(text)) {
			super.replaceText(start, end, text);
		}
	}

	/**
	 * @see javafx.scene.control.TextInputControl#replaceSelection(java.lang.String)
	 */
	@Override
	public void replaceSelection(String text) {
		if (validate(text)) {
            super.replaceSelection(text);
        }
	}
	
	/**
	 * Verifica se foi digitado um n�mero
	 * @param text Texto a ser validado
	 * @return true se o texto � um n�mero; falso, caso contr�rio
	 */
	private boolean validate(String text) {
		// Se o texto estiver vazio, considera v�lido
		if (text.trim().length() == 0) {
			return true;
		}
		
		try {
			// Se conseguir converter o texto para inteiro, considera v�lido
			Integer.parseInt(text);
			return true;
		
		} catch (NumberFormatException e) {
			// Se a convers�o para inteiro n�o foi poss�vel, considera inv�lido
			return false;
		}
	}
}
