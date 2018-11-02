Feature: PlayerHearsPit

  Background: Cross map with pit in Center
    Given cross map
    And cavern Center has a pit

  Scenario Outline: player moves to cavern near pit and hears them
    Given player is in cavern <start>
    When player moves <dir>
    Then a HEAR_PIT message is given
    Examples:
      | start        | dir   |
      | Westernmost  | east  |
      | Northernmost | south |
      | Easternmost  | west  |
      | Southernmost | north |

  Scenario Outline: player rests in cavern near pit and hears them
    Given player is in cavern <start>
    When player rests
    Then a HEAR_PIT message is given
    Examples:
      | start    | dir   |
      | Western  | east  |
      | Northern | south |
      | Eastern  | west  |
      | Southern | north |

  Scenario Outline: player rests in cavern not near pit and hears them not
    Given player is in cavern <start>
    When player rests
    Then a HEAR_PIT message is not given
    Examples:
      | start        |
      | Westernmost  |
      | Northernmost |
      | Easternmost  |
      | Southernmost |