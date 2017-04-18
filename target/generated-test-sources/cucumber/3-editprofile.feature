Feature: EditProfile

  
  As a user 
  I want access Edit Profile and ChangePassword
  so that can update details and passowrd

 
  @ORPHAN @EditProfile
  Scenario: Edit Profile details

    Given user can update profile details
    Then verify the updated message


  @ORPHAN @ChangePWD
  Scenario: Change Password details

    Then Enter Valid Credentials in old,new and confirm password fields
    Then click on change passowrd button
    Then Verify the status message

