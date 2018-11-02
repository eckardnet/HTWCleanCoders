Feature: CanNotMoveThroughUndeclaredConnections

  Background: 1 -> 2
    Given cavern 1 is connected to cavern 2 going east

  Scenario Outline: start in cavern 1
    Given player is in cavern 1
    When player moves <dir>
    Then a NO_PASSAGE message is given
    And player ends in cavern 1

    Examples:
      | dir   |
      | west  |
      | north |
      | south |

  Scenario Outline: start in cavern 2
    Given player is in cavern 2
    When player moves <dir>
    Then a NO_PASSAGE message is given
    And player ends in cavern 2

    Examples:
      | dir   |
      | west  |
      | north |
      | south |
      | east  |