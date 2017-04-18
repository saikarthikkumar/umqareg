Feature: SignOut

  
  As a user
  I want click on Logout 
  so that i can get back from the application

 
  @ORPHAN @SignOut @execute @smoke
  Scenario: SignOut

    And click on Logout button
    Then verify Login page title

