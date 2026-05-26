Feature: Login Functionality

  # Scenario 1: Validate Successful User Authentication via UI

  Scenario: Successful Login with Valid Credentials

    Given user is on login page
    When user enters valid email
    And user enters valid password
    And user clicks login button
    Then user should login successfully


  # Scenario 2: Validate UI Authentication Error Handling

  Scenario: Login with Invalid Password

    Given user is on login page
    When user enters valid email
    And user enters invalid password
    And user clicks login button
    Then proper login error message should display


  Scenario: Login with Unregistered Email

    Given user is on login page
    When user enters unregistered email
    And user enters valid password
    And user clicks login button
    Then proper login error message should display


  Scenario: Login with Empty Email

    Given user is on login page
    When user leaves email field empty
    And user enters valid password
    And user clicks login button
    Then email required validation message should display


  Scenario: Login with Empty Password

    Given user is on login page
    When user enters valid email
    And user leaves password field empty
    And user clicks login button
    Then password required validation message should display