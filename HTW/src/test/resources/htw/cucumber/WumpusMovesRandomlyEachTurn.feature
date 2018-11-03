Feature: Wumpus moves randomly each turn

  Scenario Outline: Cross map with wumpus in Center
    Given cross map
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