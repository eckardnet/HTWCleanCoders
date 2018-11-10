Feature: Player can move through the caverns

  Scenario Outline: Unidirectional connections

  Connections are unidirectional, so a connection connecting cavern 1 to cavern 2
  going east does not connect cavern 2 to cavern 1 going west.
  If a player tries to move in a direction where no connection exists,
  he gets a message and stays in the cavern he started in.

    Given cavern 1 is connected to cavern 2 going east
    And player is in cavern <startCavern>
    When player moves <dir>
    Then player <getsAMessage?>
    And player ends in cavern <endCavern>

    Examples: start in cavern 1
      | startCavern | dir   | getsAMessage?                       | endCavern |
      | 1           | west  | gets a no passage message           | 1         |
      | 1           | north | gets a no passage message           | 1         |
      | 1           | south | gets a no passage message           | 1         |
      | 1           | east  | does not get a no passage message   | 2         |
      | 1           | east  | does not get a west passage message | 2         |

    Examples: start in cavern 2
      | startCavern | dir   | getsAMessage?             | endCavern |
      | 2           | west  | gets a no passage message | 2         |
      | 2           | north | gets a no passage message | 2         |
      | 2           | south | gets a no passage message | 2         |
      | 2           | east  | gets a no passage message | 2         |

  Scenario Outline: Available passages are reported
    Given cross map
    And player is in cavern <startCavern>
    When player <restsOrMoves>
    Then player ends in cavern <endCavern>
    And player <getsEast?> a east passage message
    And player <getsNorth?> a north passage message
    And player <getsSouth?> a south passage message
    And player <getsWest?> a west passage message
    Examples:
      | startCavern | restsOrMoves | getsEast?    | getsNorth?   | getsSouth?   | getsWest?    | endCavern    |
      | Westernmost | rests        | gets         | does not get | does not get | does not get | Westernmost  |
      | Westernmost | moves east   | gets         | does not get | does not get | gets         | Western      |
      | Western     | moves east   | gets         | gets         | gets         | gets         | Center       |
      | Center      | moves north  | does not get | gets         | gets         | does not get | Northern     |
      | Northern    | moves north  | does not get | does not get | gets         | does not get | Northernmost |


  Scenario Outline: Donut map walk through

    Donut Map
    [3]<-[2]<-[1]
    |         A
    V         |
    [4]       [8]
    |         A
    V         |
    [5]->[6]->[7]
    Player can move around anti clockwise starting from 1 and ending up in 1

    Given donut map
    And player is in cavern <start>
    When player moves <dir>
    Then player ends in cavern <end>

    Examples:
      | start | dir   | end |
      | 1     | west  | 2   |
      | 2     | west  | 3   |
      | 3     | south | 4   |
      | 4     | south | 5   |
      | 5     | east  | 6   |
      | 6     | east  | 7   |
      | 7     | north | 8   |
      | 8     | north | 1   |