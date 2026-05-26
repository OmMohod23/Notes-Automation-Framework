Feature: Notes Functionality

  Scenario Outline: Create Note with Valid Fields

    Given user is logged into notes application
    When user clicks add note button
    And user selects category "<category>"
    And user enters note title "<title>"
    And user enters note description "<description>"
    And user clicks create note button
    Then newly created note should appear in notes list

    Examples:
      | category | title         | description               |
      | Home     | Home Note     | Home Category Testing     |
      | Work     | Work Note     | Work Category Testing     |
      | Personal | Personal Note | Personal Category Testing |


  Scenario: Validate Category Filter Functionality

    Given user is logged into notes application

    When user clicks "Home" category filter
    Then only "Home" category notes should display

    When user clicks "Work" category filter
    Then only "Work" category notes should display

    When user clicks "Personal" category filter
    Then only "Personal" category notes should display


  Scenario: Validate Edit Note Functionality

    Given user is logged into notes application
    When user clicks edit note button
    And user updates note title to "Updated Note"
    And user updates note description to "Updated Description"
    And user clicks save note button
    Then updated note should appear in notes list