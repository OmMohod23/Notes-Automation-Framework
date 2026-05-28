Feature: Negative Validation Scenarios

  # Scenario: Validate Frontend Field Form Rejections (UI Errors)

  Scenario: Validate Empty Title Field Handling

    Given user is logged into notes application

    When user clicks add note button
    And user selects category "Home"
    And user leaves title field empty
    And user enters note description "Negative Test Description"
    And user clicks create note button

    Then title validation message should display


  Scenario: Validate Empty Description Field Handling

    Given user is logged into notes application

    When user clicks add note button
    And user selects category "Home"
    And user enters note title "Negative Title"
    And user leaves description field empty
    And user clicks create note button

    Then description validation message should display


  Scenario: Validate Oversized Input Boundary Handling

    Given user is logged into notes application

    When user clicks add note button
    And user selects category "Home"
    And user enters oversized title
    And user enters oversized description
    And user clicks create note button

    Then note should not be created successfully


  # Scenario: Validate Backend Payload Exceptions (API Errors)
  #api negative test cases from here

  Scenario: Validate HTTP 401 Unauthorized Response for Invalid Token

    When user sends GET notes request with invalid token

    Then unauthorized status code should be returned


  Scenario: Validate HTTP 400 Bad Request for Invalid Payload Submission

    Given user generates API authentication token

    When user sends invalid payload to create note API

    Then bad request status code should be returned


  Scenario: Validate HTTP 404 Handling for Non-Existent Note ID

    Given user generates API authentication token

    When user requests invalid note id

    Then not found status code should be returned