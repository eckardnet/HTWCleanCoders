Feature: PlayerHearsBats

  Background: Cross map with frozen wumpus in Center
    Given cross map
    And the wumpus is in cavern Center
    And the wumpus is frozen

  Scenario Outline: player moves to cavern near wumpus and smells it
    Given player is in cavern <start>
    When player moves <dir>
    Then a SMELL_WUMPUS message is given
    Examples:
      | start        | dir   |
      | Westernmost  | east  |
      | Northernmost | south |
      | Easternmost  | west  |
      | Southernmost | north |

  Scenario Outline: player rests in cavern near wumpus and smells it
    Given player is in cavern <start>
    When player rests
    Then a SMELL_WUMPUS message is given
    Examples:
      | start    | dir   |
      | Western  | east  |
      | Northern | south |
      | Eastern  | west  |
      | Southern | north |

  Scenario Outline: player rests in cavern not near wumpus and smells it not
    Given player is in cavern <start>
    When player rests
    Then a SMELL_WUMPUS message is not given
    Examples:
      | start        |
      | Westernmost  |
      | Northernmost |
      | Easternmost  |
      | Southernmost |