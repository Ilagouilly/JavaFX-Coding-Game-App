open module com.ismaillagouilly.test {
    requires com.ismaillagouilly;
    requires org.junit.jupiter.api;
    requires org.junit.platform.suite.api;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.testfx;
    requires org.testfx.junit5;
    requires io.cucumber.java;
    requires io.cucumber.junit.platform.engine;

    exports com.ismaillagouilly.runners;
    exports com.ismaillagouilly.stepdefs;
    exports com.ismaillagouilly.testfx;
}