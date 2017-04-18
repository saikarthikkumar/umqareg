Feature: SignIn

  
  As a user
  I want Login to PQ Tool
  so that can perform necessary actions

 
  @ORPHAN @Signin @execute @smoke
  Scenario: SignIn
    Given user is redirected to PQ Tool Login page
    And enters valid credentials and click on Signin button

