module chatcommon {
	exports br.com.softblue.java.chat.common.utils;
	exports br.com.softblue.java.chat.common;
	exports br.com.softblue.java.chat.common.rmi;
	requires java.rmi;
	requires javafx.fxml;
	requires transitive javafx.controls;
}