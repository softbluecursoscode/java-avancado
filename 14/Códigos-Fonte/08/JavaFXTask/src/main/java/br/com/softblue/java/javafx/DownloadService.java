package br.com.softblue.java.javafx;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class DownloadService extends Service<String> {

	@Override
	protected Task<String> createTask() {
		return new Task<String>() {

			@Override
			protected String call() throws Exception {
				for (int i = 0; i < 10; i++) {
					Thread.sleep(500);
					updateProgress(i + 1, 10);
					System.out.println("call(): " + i);
					
					if (isCancelled()) {
						break;
					}
				}
				
				return "OK";
			}
			
			@Override
			protected void running() {
				updateMessage("Download iniciado");
			}
			
			@Override
			protected void succeeded() {
				updateMessage("Download encerrado");
			}
			
			@Override
			protected void failed() {
				updateMessage("Download falhou");
			}
			
			@Override
			protected void cancelled() {
				updateMessage("Download cancelado");
			}
		};
	}

}
