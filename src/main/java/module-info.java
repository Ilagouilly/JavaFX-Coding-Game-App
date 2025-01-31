module com.ismaillagouilly {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;
    requires dotenv.java;

    opens com.ismaillagouilly.controller to javafx.fxml;
    exports com.ismaillagouilly.controller;
    exports com.ismaillagouilly.app;
    opens com.ismaillagouilly.app to javafx.fxml;
    exports com.ismaillagouilly.model;
    opens com.ismaillagouilly.model to javafx.fxml;
}
