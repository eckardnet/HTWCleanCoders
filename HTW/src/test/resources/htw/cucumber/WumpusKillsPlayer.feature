Feature: Wumpus kills player

  Background:
    Given player is in cavern 1
    And the wumpus is in cavern 2

  Scenario: Player moves to wumpus
    Given cavern 1 is connected to cavern 2 going east
    When player moves east
    Then player runs into the wumpus

  Scenario: Wumpus moves to player
    Given cavern 2 is connected to cavern 1 going east
    When player rests until killed
    Then wumpus ended in cavern 1
    And the wumpus gets the player