Feature: API Performance Testing

  Scenario: Validate API Performance Threshold Benchmarks

    Given valid authentication token is generated

    When user sends performant POST notes request

    And user sends performant GET notes request

    And user sends performant PUT notes request

    And user sends performant DELETE notes request

    Then all API response times should be under 2 seconds