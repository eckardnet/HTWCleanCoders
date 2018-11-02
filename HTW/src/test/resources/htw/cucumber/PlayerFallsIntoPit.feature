Feature: PlayerFallsIntoPit

  Scenario: PlayerFallsIntoPit
    Given cross map
    And cavern Center has a pit
    And player is in cavern Eastern
    When player moves west
    Then a FELL_IN_PIT message is given