Feature: Hybrid UI API Synchronization

  Scenario: Validate E2E Hybrid Synchronization (UI Write to API Read)

    Given user is on login page
    When user enters valid email
    And user enters valid password
    And user clicks login button
    Then user should login successfully

    When user creates note from UI

    And user fetches notes using API

    Then created UI note should exist in API response


  Scenario: Validate Dynamic DOM Synchronization After API Deletion

    Given user is on login page
    When user enters valid email
    And user enters valid password
    And user clicks login button
    Then user should login successfully

    When user creates note from UI

    And user fetches notes using API

    Then created UI note should exist in API response

    When user deletes created note using API

    Then deleted note should not appear in UI