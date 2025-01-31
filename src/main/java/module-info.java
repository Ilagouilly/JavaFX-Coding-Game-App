module com.ismaillagouilly {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;
    requires dotenv.java;

    opens com.ismaillagouilly to javafx.fxml;
    exports com.ismaillagouilly;
}
