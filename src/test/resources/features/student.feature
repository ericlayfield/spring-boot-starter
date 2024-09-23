Feature: Student CRUD

  @RegressionTest
  Scenario Outline: Student successfully found
    Given student id is <id>
    When student exists
    Then student has first name "<first name>"
    And student has last name "<last name>"
    And student has birth date "<birth date>"

    Examples:
    |id |first name|last name|birth date|
    |1  |Sam       |Smith    |2012-03-12|
    |2  |Ralph     |Thomas   |2010-11-01|


  @RegressionTest
  Scenario: Student successfully added
    Given student id is 3
    When student does not exist
    Then student is added