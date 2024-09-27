Feature: Validate the Student service CRUD is valid thru the REST API

  Background:
    Given the service is running

  Scenario Outline: Student successfully found
    Given student id is <id>
    When student exists
    Then student has first name "<first name>"
    And student has last name "<last name>"
    And student has birth date "<birth date>"
    And student has email "<email>"

    Examples:
    |id |first name|last name|birth date|email                 |
    |1  |Sam       |Smith    |2012-03-12|sammysmith@live.com   |
    |2  |Ralph     |Thomas   |2010-11-01|RalphThomas@gmail.com |

  Scenario: Student successfully added
    Given student id is 3
    When student does not exist
    Then student first name is "Steve"
    And student last name is "Miller"
    And student birth date is "2015-09-30"
    And student email is "stevie.miller@yahoo.com"
    And student is added
    And student exists

  Scenario: Student successfully updated
    Given student id is 1
    When student exists
    Then student email is "samsmith@gmail.com"
    And student 1 is updated
    And student exists
    And student has email "samsmith@gmail.com"

  Scenario: Student successfully deleted
    Given student id is 2
    When student exists
    Then student 2 is deleted
    And student does not exist