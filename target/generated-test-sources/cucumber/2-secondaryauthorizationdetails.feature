Feature: SecondaryAuthorizationDetails

 
  @ORPHAN @execute @secondaryauth
  Scenario: Update Secondary Authorization Details

    Given user is on update secondary authorization details screen
    When i fill the authorization Details
    Then i should be able to save the details successfully


  @ORPHAN @validatesecondaryauth
  Scenario: Validating the update secondary authorization screen

    Given user is on update secondary authorization details screen
    When i validate the authorization fields
    Then i should display the error messages on-screen

