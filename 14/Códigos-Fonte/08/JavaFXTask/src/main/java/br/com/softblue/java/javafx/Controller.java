package br.com.softblue.java.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

public class Controller {

	@FXML
	private ProgressBar progress;

	@FXML
	private Label lblPercent;

	@FXML
	private Button btnIniciar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Text txtStatusBar;
	
	private DownloadService service;

	public void initialize() {
		service = new DownloadService();
		
		progress.progressProperty().bind(service.progressProperty());
		txtStatusBar.textProperty().bind(service.messageProperty());
	
		service.progressProperty().addListener((event, oldValue, newValue) -> {
			Double progress = (Double) newValue;
			
			if (progress < 0) {
				lblPercent.setText(null);
			} else {
				lblPercent.setText(String.format("%.0f%%", progress * 100));
			}
		});
		
		btnCancelar.disableProperty().bind(service.runningProperty().not());
		btnIniciar.disableProperty().bind(service.runningProperty());
	}

	@FXML
	public void start() {
		service.restart();
	}

	@FXML
	public void cancel() {
		service.cancel();
	}
}
