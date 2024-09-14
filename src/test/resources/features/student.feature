Feature: Student Added
  Scenario: Student successfully added
    Given student id is "112"
    When student does not exist
    Then student is added