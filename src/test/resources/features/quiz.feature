Feature: Quiz Application
  As a user
  I want to interact with the quiz application
  So that I can test my knowledge

  Scenario: Starting a new quiz
    Given the application is running
    When I navigate to the quiz screen
    Then I should see the quiz interface with questions
