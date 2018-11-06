Feature: Pit

  Background: Cross map with pit in center
    Given cross map
    And cavern Center has a pit

  Scenario Outline: PlayerFallsIntoPit
    And player is in cavern Eastern
    When player moves <direction>
    Then player <fallsIntoPit?>

    Examples:
      | direction | fallsIntoPit?          |
      | west      | falls into pit         |
      | east      | does not fall into pit |

  Scenario Outline: Player moves..
    Given player is in cavern <start>
    When player moves <dir>
    Then player <hearsPit?>

    Examples: ..and hears pit
      | start        | dir   | hearsPit? |
      | Westernmost  | east  | hears pit |
      | Northernmost | south | hears pit |
      | Easternmost  | west  | hears pit |
      | Southernmost | north | hears pit |

    Examples: ..and does not hear pit
      | start    | dir   | hearsPit?         |
      | Western  | west  | does not hear pit |
      | Northern | north | does not hear pit |
      | Eastern  | east  | does not hear pit |
      | Southern | south | does not hear pit |

  Scenario Outline: Player rests..
    Given player is in cavern <start>
    When player rests
    Then player <hearsPit?>

    Examples: ..and hears pit
      | start    | hearsPit? |
      | Western  | hears pit |
      | Northern | hears pit |
      | Eastern  | hears pit |
      | Southern | hears pit |

    Examples: ..and does not hear pit
      | start        | hearsPit?         |
      | Westernmost  | does not hear pit |
      | Northernmost | does not hear pit |
      | Easternmost  | does not hear pit |
      | Southernmost | does not hear pit |
