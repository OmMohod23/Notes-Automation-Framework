Feature: Notes API Functionality

  Scenario: Validate API Authentication and Notes Retrieval

    Given user generates API authentication token
    Then authentication token should be generated

    When user creates note using API
    Then created note response status should be 200

    When user sends GET notes API request
    Then API response status should be 200
    And notes response should match JSON schema


  Scenario: Validate API DELETE Execution on Active Notes

    Given user generates API authentication token

    When user creates note using API
    Then created note response status should be 200

    When user deletes created note using API
    Then delete API response status should be 200