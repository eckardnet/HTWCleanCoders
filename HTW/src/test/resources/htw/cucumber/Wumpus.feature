Feature: Wumpus

  Background: Cross map with frozen wumpus in Center
    Given cross map
    And the wumpus is in cavern Center

  Scenario Outline: Player moves..
    Given player is in cavern <start>
    And the wumpus is frozen
    When player moves <dir>
    Then player <smellsWumpus?>

    Examples: ..and smells the wumpus
      | start        | dir   | smellsWumpus?     |
      | Westernmost  | east  | smells the wumpus |
      | Northernmost | south | smells the wumpus |
      | Easternmost  | west  | smells the wumpus |
      | Southernmost | north | smells the wumpus |

    Examples:
      | start    | dir   | smellsWumpus?             |
      | Western  | west  | does not smell the wumpus |
      | Northern | north | does not smell the wumpus |
      | Eastern  | east  | does not smell the wumpus |
      | Southern | south | does not smell the wumpus |

  Scenario Outline: player rests..
    Given player is in cavern <start>
    And the wumpus is frozen
    When player rests
    Then player <smellsWumpus?>

    Examples: ..and smells the wumpus
      | start    | smellsWumpus?     |
      | Western  | smells the wumpus |
      | Northern | smells the wumpus |
      | Eastern  | smells the wumpus |
      | Southern | smells the wumpus |

    Examples: ..and does not smell the wumpus
      | start        | smellsWumpus?             |
      | Westernmost  | does not smell the wumpus |
      | Northernmost | does not smell the wumpus |
      | Easternmost  | does not smell the wumpus |
      | Southernmost | does not smell the wumpus |

  Scenario Outline: The wumpus moves randomly each turn
    When player rests 1000 times with wumpus in Center each time
    Then wumpus ended in <cavern> between <min> and <max> times

    Examples:
      | cavern       | min | max |
      | Center       | 150 | 250 |
      | Northernmost |   0 |   0 |
      | Northern     | 150 | 250 |
      | Easternmost  |   0 |   0 |
      | Eastern      | 150 | 250 |
      | Southernmost |   0 |   0 |
      | Southern     | 150 | 250 |
      | Westernmost  |   0 |   0 |
      | Western      | 150 | 250 |