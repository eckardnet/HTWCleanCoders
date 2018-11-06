Feature: Hunting with arrows

  Scenario Outline: After shooting players arrow leaves quiver and lands in farthest cavern
    Given cross map
    And player is in cavern Center
    And player has 1 arrows in his quiver
    When player shoots <direction>
    Then there are 0 arrows left in the players quiver
    And there are 1 arrows in cavern <cavernWithArrows>
    And a ARROW_SHOT message is given

    Examples:
      | direction | cavernWithArrows |
      | north     | Northernmost     |
      | east      | Easternmost      |
      | south     | Southernmost     |
      | west      | Westernmost      |

  Scenario Outline: Arrow kills wumpus
    Given cross map
    And player is in cavern <playersCavern>
    And the wumpus is in cavern Center
    And player has 1 arrows in his quiver
    When player shoots <direction>
    Then there are 0 arrows in cavern <oppositeCavern>
    And there are 0 arrows in cavern Center
    And a WUMPUS_KILLED message is given

    Examples:
      | playersCavern | direction | oppositeCavern |
      | Southernmost  | north     | Northernmost   |
      | Westernmost   | east      | Easternmost    |
      | Northernmost  | south     | Southernmost   |
      | Easternmost   | west      | Westernmost    |

  Scenario: Arrows accumulate in farthest cavern
    Given cross map
    And player is in cavern Northernmost
    And player has 3 arrows in his quiver
    When player shoots south 3 times
    Then there are 3 arrows in cavern Southernmost
    And there are 0 arrows left in the players quiver

  Scenario: Player can not shoot without arrows
    Given cross map
    And player is in cavern Center
    And player has 0 arrows in his quiver
    When player shoots west
    Then a NO_ARROWS message is given

   Scenario Outline: Player picks up arrows
     Given cross map
     And player is in cavern Center
     And player has <startQuiver> arrows in his quiver
     And cavern Western contains <arrows> arrows
     When player moves west
     Then there are <endQuiver> arrows left in the players quiver
     And there are 0 arrows in cavern Western
     And  a <messageGiven>

     Examples:
       | startQuiver | arrows | endQuiver | messageGiven                       |
       | 0           | 2      | 2         | 2_ARROW_FOUND message is given     |
       | 0           | 0      | 0         | 0_ARROW_FOUND message is not given |
       | 1           | 1      | 2         | 1_ARROW_FOUND message is given     |
       | 1           | 0      | 1         | 0_ARROW_FOUND message is not given |

  Scenario Outline: Player killed by shooting
    Given cross map
    And cavern Northernmost is connected to cavern Southernmost going north
    And player is in cavern Northern
    And player has 1 arrows in his quiver
    When player shoots <direction>
    Then a <messageGiven>

    Examples: player shoots self in back
      | direction | messageGiven                                    |
      | north     | PLAYER_SHOOTS_SELF_IN_BACK message is given     |
      | south     | PLAYER_SHOOTS_SELF_IN_BACK message is not given |

    Examples: player shoots wall
      | direction | messageGiven                            |
      | west      | PLAYER_SHOOTS_WALL message is given     |
      | east      | PLAYER_SHOOTS_WALL message is given     |
      | south     | PLAYER_SHOOTS_WALL message is not given |