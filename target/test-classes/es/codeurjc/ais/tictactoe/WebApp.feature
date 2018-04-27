Feature: WebApp
 
  I want to use the Tic-tac-toe game with 2 Chrome
  users.

  
  Scenario: first player wins
	    Given two registered browser players
	    When the combination of movements is
	       |0|1|3|2|6|
	    Then I should receive the following message, in both browsers:"player1 wins! player2 looses."
  
  Scenario: second player wins
	    Given two registered browser players
	    When the combination of movements is
	        |4|1|3|2|6|0|
	    Then I should receive the following message, in both browsers:"player2 wins! player1 looses."
	    		  
  Scenario: first player wins
	    Given two registered browser players
	    When the combination of movements is
			|1|0|3|2|5|4|6|7|8|
	    Then I should receive the following message, in both browsers:"Draw!"
	   
	
  


	   
	   