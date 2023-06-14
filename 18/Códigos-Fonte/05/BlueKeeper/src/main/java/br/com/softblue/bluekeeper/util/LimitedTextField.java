package br.com.softblue.bluekeeper.util;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TextField;

/**
 * TextField do JavaFX que permite limitação no número de caracteres
 */
public class LimitedTextField extends TextField {

	/**
	 * Propriedade que define o limite máximo de caracteres
	 */
	private IntegerProperty limit = new SimpleIntegerProperty();

	/**
	 * @see javafx.scene.control.TextInputControl#replaceText(int, int, java.lang.String)
	 */
	@Override
	public void replaceText(int start, int end, String text) {
		if (validate()) {
			super.replaceText(start, end, text);
		}
	}
	
	/**
	 * @see javafx.scene.control.TextInputControl#replaceSelection(java.lang.String)
	 */
	@Override
	public void replaceSelection(String replacement) {
		if (validate()) {
			super.replaceSelection(replacement);
		}
	}
	
	/**
	 * Valida se o número de caracteres ainda não ultrapassou o limite
	 * @return
	 */
	private boolean validate() {
		return lengthProperty().lessThan(limit).get();
	}

	public IntegerProperty limitProperty() {
		return this.limit;
	}

	public int getLimit() {
		return this.limitProperty().get();
	}

	public void setLimit(final int limit) {
		this.limitProperty().set(limit);
	}
}
