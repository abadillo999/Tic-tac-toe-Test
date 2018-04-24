Feature: WebApp
 
  I want to use the Tic-tac-toe game with 2 Chrome
  users.
  
  Scenario: first player wins
	    Given I have a WebApp and two registered browser players
	    When The combination of movements is
	       |0|1|3|2|6|
	    Then I should receive a message with the winner, first player, in both browsers
  
  Scenario: second player wins
	    Given I have a WebApp and two registered browser players
	    When The combination of movements is
	       |4|1|3|2|6|0|
	    Then I should receive a message with the winner, second player, in both browsers
	  
  Scenario: first player wins
	    Given I have a WebApp and two registered browser players
	    When The combination of movements is
			|1|0|3|2|5|4|6|7|8|
	    Then I should receive a message with draw
	
  


	   
	   