Feature: Player can move through the donut map
  Donut Map
  [3]<-[2]<-[1]
   |         A
   V         |
  [4]       [8]
   |         A
   V         |
  [5]->[6]->[7]
  Player can move around anti clockwise starting from 1 and ending up in 1

  Scenario Outline: Donut map walk through
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