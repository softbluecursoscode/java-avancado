module bluekeeper {
	exports br.com.softblue.bluekeeper.application;
	exports br.com.softblue.bluekeeper.util;
	opens br.com.softblue.bluekeeper.controller;
	opens br.com.softblue.bluekeeper.model;
	requires java.sql;
	requires java.xml;
	requires transitive javafx.controls;
	requires javafx.fxml;
}