module com.ismaillagouilly {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.ismaillagouilly to javafx.fxml;
    exports com.ismaillagouilly;
}
