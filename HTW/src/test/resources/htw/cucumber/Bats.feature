Feature: Bats

  Background: Cross map with bats in Center
    Given cross map
    And cavern Center has bats

  Scenario: Player moves into cavern with bats
    Given player is in cavern Western
    When player moves east
    Then player is transported by bats

  Scenario Outline: Player moves..
    Given player is in cavern <start>
    When player moves <dir>
    Then player <hearsBats?>

    Examples: ..and hears bats
      | start        | dir   | hearsBats? |
      | Westernmost  | east  | hears bats |
      | Northernmost | south | hears bats |
      | Easternmost  | west  | hears bats |
      | Southernmost | north | hears bats |

    Examples: ..and does not hear bats
      | start    | dir   | hearsBats?         |
      | Western  | west  | does not hear bats |
      | Northern | north | does not hear bats |
      | Eastern  | east  | does not hear bats |
      | Southern | south | does not hear bats |

  Scenario Outline: player rests..
    Given player is in cavern <start>
    When player rests
    Then player <hearsBats?>

    Examples: ..and hears bats
      | start    | hearsBats?  |
      | Western  | hears bats |
      | Northern | hears bats |
      | Eastern  | hears bats |
      | Southern | hears bats |

    Examples: ..and does not hear bats
      | start        | hearsBats?         |
      | Westernmost  | does not hear bats |
      | Northernmost | does not hear bats |
      | Easternmost  | does not hear bats |
      | Southernmost | does not hear bats |

  Scenario Outline:
    When player moves south 1000 times starting from Northern each time
    Then player landed in <cavern> between <min> and <max> times

    Examples:
      | cavern       | min | max |
      | Center       |   0 |   0 |
      | Northernmost | 75  | 175 |
      | Northern     | 75  | 175 |
      | Easternmost  | 75  | 175 |
      | Eastern      | 75  | 175 |
      | Southernmost | 75  | 175 |
      | Southern     | 75  | 175 |
      | Westernmost  | 75  | 175 |
      | Western      | 75  | 175 |