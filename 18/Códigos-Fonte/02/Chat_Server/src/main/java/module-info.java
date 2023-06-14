module chatserver {
	exports br.com.softblue.java.chat.server.business;
	exports br.com.softblue.java.chat.server.ui.controller;
	exports br.com.softblue.java.chat.server.ui;
	opens br.com.softblue.java.chat.server.ui.controller;
	requires transitive javafx.controls;
	requires chatcommon;
	requires java.rmi;
	requires javafx.fxml;
}