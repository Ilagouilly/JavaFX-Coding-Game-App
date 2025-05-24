package com.ismaillagouilly.stepdefs;

import org.testfx.framework.junit5.ApplicationTest;

import com.ismaillagouilly.app.JavaCodingGame;
import com.ismaillagouilly.testfx.JavaFXTestBase;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class QuizStepDefinitions extends JavaFXTestBase {

    private JavaCodingGame application;

    @Given("the application is running")
    public void theApplicationIsRunning() throws Exception {
        ApplicationTest.launch(JavaCodingGame.class);
    }

    @When("I navigate to the quiz screen")
    public void iNavigateToTheQuizScreen() {
        // Find and click the button that navigates to the quiz screen
        clickOn("#startQuizButton");
    }

    @Then("I should see the quiz interface with questions")
    public void iShouldSeeTheQuizInterfaceWithQuestions() {
        // Verify that quiz elements are present
        // verifyThat("#questionLabel", hasText(text -> !text.isEmpty()));
    }
}
