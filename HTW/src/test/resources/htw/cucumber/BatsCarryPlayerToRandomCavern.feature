Feature: Bats carry player to random cavern

  Scenario Outline: Cross map with bats in Center
    Given cross map
    And cavern Center has bats
    When player moves south 1000 times starting from Northern each time
    Then player landed in <cavern> between <min> and <max> times

    Examples:
      | cavern       | min | max |
      | Center       | 0   | 0   |
      | Northernmost | 75 | 175 |
      | Northern     | 75 | 175 |
      | Easternmost  | 75 | 175 |
      | Eastern      | 75 | 175 |
      | Southernmost | 75 | 175 |
      | Southern     | 75 | 175 |
      | Westernmost  | 75 | 175 |
      | Western      | 75 | 175 |