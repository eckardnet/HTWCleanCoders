Feature: AvailablePassagesAreReported

  Background: Cross Map
    Given cross map

  Scenario: Resting in WesternMost gives only east passage
    Given player is in cavern Westernmost
    When player rests
    Then a EAST_PASSAGE message is given
    And a NORTH_PASSAGE message is not given
    And a SOUTH_PASSAGE message is not given
    And a WEST_PASSAGE message is not given

  Scenario: Moving east from WesternMost gives east and west passage
    Given player is in cavern Westernmost
    When player moves east
    Then a EAST_PASSAGE message is given
    And a NORTH_PASSAGE message is not given
    And a SOUTH_PASSAGE message is not given
    And a WEST_PASSAGE message is given

  Scenario: Moving east from Western gives passages in every direction
    Given player is in cavern Western
    When player moves east
    Then a EAST_PASSAGE message is given
    And a NORTH_PASSAGE message is given
    And a SOUTH_PASSAGE message is given
    And a WEST_PASSAGE message is given

  Scenario: Moving north from Center gives north and south passage
    Given player is in cavern Center
    When player moves north
    Then a EAST_PASSAGE message is not given
    And a NORTH_PASSAGE message is given
    And a SOUTH_PASSAGE message is given
    And a WEST_PASSAGE message is not given

  Scenario: Moving north from Northern gives south passage
    Given player is in cavern Northern
    When player moves north
    Then a EAST_PASSAGE message is not given
    And a NORTH_PASSAGE message is not given
    And a SOUTH_PASSAGE message is given
    And a WEST_PASSAGE message is not given
