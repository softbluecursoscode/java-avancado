module chatclient {
	exports br.com.softblue.java.chat.client.ui.model;
	exports br.com.softblue.java.chat.client.business;
	exports br.com.softblue.java.chat.client.config;
	exports br.com.softblue.java.chat.client.ui;
	exports br.com.softblue.java.chat.client.ui.controller;
	opens br.com.softblue.java.chat.client.ui.controller;
	requires chatcommon;
	requires java.rmi;
	requires transitive javafx.controls;
	requires javafx.fxml;
}