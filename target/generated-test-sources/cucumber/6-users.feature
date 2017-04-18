Feature: Users

  
  As a user 
  I want access Users
  so that can Add New User or Edit/Delete Users

 
  @ORPHAN @Users @smoke
  Scenario Outline: Create New Users

    Given Registering a user by Admin "<UserName>","<Password>","<ConfirmPassword>","<Role>","<FirstName>","<MiddleName>","<LastName>"," <Emailaddress>","<MobileNumber>","<AddressLine1>","<AddressLine2>","<City>","<State>","<Country>","<PostalCode>"7
    Examples:
      | UserName | Password | ConfirmPassword | Role       | FirstName | MiddleName | LastName | Emailaddress     | MobileNumber  | AddressLine1 | Address Line2 | City      | State     | Country | PostalCode |
      | test-Sp7 | P@55werd | P@55werd        | Super User | Super     | test       | user     | scipqt@gmail.com | 1-54251251251 | address1     | address2      | Hyderabad | Telangana | India   | 500082     |


  @ORPHAN @Modify
  Scenario: Modify Users

    And Modify and Deletes the "STQ one" user and perform "edit" and using details "STP", "Second", "User", "saikarthik.vodela@sciits.com", "1-54251251251", "address1", "address2", "Hyderabad", "Telangana", "India", "500082"

