Feature: PlayerIsInformedOfEnteringBatCavern

  Scenario:
    Given cross map
    And cavern Center has bats
    And player is in cavern Western
    When player moves east
    Then a BAT_TRANSPORT message is given