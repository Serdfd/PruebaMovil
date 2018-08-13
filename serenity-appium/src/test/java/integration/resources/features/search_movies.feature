Feature: Search for movies

Scenario: User want to save all ballet,opera and teatro listed movies
Given User is on home page
When Enter invalid credentials
Then  User is shown error message