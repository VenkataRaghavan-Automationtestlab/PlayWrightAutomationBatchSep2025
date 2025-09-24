Feature: Login Functionality of Swag Labs

Background:
Given load the application URL

Scenario: TC001_Login with valid credentials

When enter the valid username "standard_user"
And enter the valid password "secret_sauce"
And Click on login button
Then Page navigated to Homepage and verify title

Scenario Outline:  TC002_Login with Invalid credentials

When enter the valid username "<username>"
And enter the valid password "<password>"
And Click on login button
But Error Message will be displayed

Examples: 
        |username|password|
        |locked_out_user|secret_sauce|
        |problem_use|secret_sauce|