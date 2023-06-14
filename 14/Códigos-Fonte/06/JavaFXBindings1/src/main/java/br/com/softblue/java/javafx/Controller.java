package br.com.softblue.java.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class Controller {

	@FXML
	Slider slider;

	@FXML
	TextField text;

	@FXML
	Label label;

	@FXML
	CheckBox checkbox;

	public <T> void initialize() {
		
		//label.textProperty().bind(slider.valueProperty().asString());
		
		slider.valueProperty().addListener((o, ov, nv) -> {
			Double value = (Double) nv;
			
			if (value < 30.0) {
				label.setText("BAIXO");
			} else if (value > 70.0) {
				label.setText("ALTO");
			} else {
				label.setText("MÉDIO");
			}
		});
		
		//text.textProperty().bind(slider.valueProperty().asString());
		text.textProperty().bindBidirectional(slider.valueProperty(), new NumberStringConverter());
		
		checkbox.selectedProperty().bind(slider.valueProperty().greaterThanOrEqualTo(50));
	}
}
