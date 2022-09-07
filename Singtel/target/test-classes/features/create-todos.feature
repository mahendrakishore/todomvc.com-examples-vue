Feature: Create Todos with BDD Cucumber Java

@getToDoList
Scenario: Check if the todo list is empty
  Given user is on the vuetodo page
  When user check the vuetodo list
  Then user gets the vuetodo list details

Scenario: Create multiple todos @bdd
  Given I have these todos on my list
    | name         |
    | Milk         |
    | Butter       |
    | Bread        |
  When I add 2 more todos
  Then  I see 4 todos on my list