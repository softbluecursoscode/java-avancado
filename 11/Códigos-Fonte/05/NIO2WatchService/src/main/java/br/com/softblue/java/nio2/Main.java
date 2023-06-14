package br.com.softblue.java.nio2;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		
		Path dir = Paths.get("C:\\Temp\\Curso");
		
		WatchService watcher = FileSystems.getDefault().newWatchService();
		
		dir.register(watcher, 
				StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_MODIFY,
				StandardWatchEventKinds.ENTRY_DELETE);
		
		while (true) {
			WatchKey key = watcher.take();
			
			for (WatchEvent<?> event : key.pollEvents()) {
				Kind<?> kind = event.kind();
				
				if (kind == StandardWatchEventKinds.OVERFLOW) {
					continue;
				}
				
				WatchEvent<Path> we = (WatchEvent<Path>) event;
				Path fileName = we.context();
				
				System.out.println("Event: " + kind + "; File: " + fileName);
				
				if (!key.reset()) {
					break;
				}
			}
		}
	}
}
