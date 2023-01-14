Feature: User Creation

  As a user I want add, edit and delete details

  Scenario Outline: CRUD Test

    Given I create a new user by providing the information name "<name>" email "<email>" gender "<gender>" status "<status>"
    Then I get user information by id
    And Update user details by providing the information name "<name>" email "<email>" gender "<gender>" status "<status>"
    Then The user id deleted successfully

    Examples:
      | name | email               | gender | status |
      | Cyas | cvyas128@gmail.com | Male   | Active |
