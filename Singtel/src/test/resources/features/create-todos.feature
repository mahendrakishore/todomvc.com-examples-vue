Feature: Create Todos with BDD Cucumber Java

@addToDoList @toDo
Scenario: User adds task in todo list
  Given user is on the vuetodo page
  When user creat new vuetodo list
     | Milk         |
     | Bread        |
     | Egg          |
  Then task should be added sucessfully     
  
 @deleteToDoList @toDo
 Scenario: User delete task in todo list
  Given user is on the vuetodo page
  When user creat new vuetodo list
     | Milk         |
     | Bread        |
     | Egg          |
  Then task should be added sucessfully
  And user delete the task

 @deleteAllToDoListTogether @toDo
 Scenario: User delete task all in todo list together
  Given user is on the vuetodo page
  When user creat new vuetodo list
     | Milk         |
     | Bread        |
     | Egg          |
  Then task should be added sucessfully
  And user delete all the task together

