package br.com.softblue.java.chat.common.utils;

import javafx.stage.Stage;

/**
 * Interface implementada pelos controllers do JavaFX que desejam ser notificados sobre o Stage
 * associado.
 */
public interface StageAwareController {
	
	/**
	 * Indica que um Stage foi associado ao controller.
	 * @param stage Stage associado.
	 */
	void onStageDefined(Stage stage);
}
