package com.ismaillagouilly;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        QuizApp.setRoot("primary");
    }
}