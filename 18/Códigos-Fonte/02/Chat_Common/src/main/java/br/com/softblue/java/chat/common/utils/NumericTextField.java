package br.com.softblue.java.chat.common.utils;

import javafx.scene.control.TextField;


/**
 * TextField do JavaFX que só aceita números
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
	 * Verifica se foi digitado um número
	 * @param text Texto a ser validado
	 * @return true se o texto é um número; falso, caso contrário
	 */
	private boolean validate(String text) {
		// Se o texto estiver vazio, considera válido
		if (text.trim().length() == 0) {
			return true;
		}
		
		try {
			// Se conseguir converter o texto para inteiro, considera válido
			Integer.parseInt(text);
			return true;
		
		} catch (NumberFormatException e) {
			// Se a conversão para inteiro não foi possível, considera inválido
			return false;
		}
	}
}
