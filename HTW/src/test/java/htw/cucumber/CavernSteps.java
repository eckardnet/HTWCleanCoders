package htw.cucumber;
import cucumber.api.java8.En;
import htw.HuntTheWumpus;
import htw.fixtures.TestContext;

import java.util.Map;

import static htw.fixtures.TestContext.game;
import static htw.fixtures.TestContext.messages;
import static org.assertj.core.api.Assertions.assertThat;

public class CavernSteps implements En {

    private final TestContext testContext;

    public CavernSteps() {
        super();
        testContext = new TestContext();
        //Cross Map
        //''Links are bidirectional''
        //
        //
        //                        [Northernmost]
        //
        //                        [Northern    ]
        //
        //[Westernmost] [Western] [Center      ] [Eastern] [Easternmost]
        //
        //                        [Southern    ]
        //
        //                        [SouthernMost]
        Given("cross map", () -> {
            game.connectCavern("Westernmost", "Western", HuntTheWumpus.Direction.EAST);
            game.connectCavern("Western", "Westernmost", HuntTheWumpus.Direction.WEST);
            game.connectCavern("Western", "Center", HuntTheWumpus.Direction.EAST);
            game.connectCavern("Center", "Western", HuntTheWumpus.Direction.WEST);
            game.connectCavern("Center", "Eastern", HuntTheWumpus.Direction.EAST);
            game.connectCavern("Eastern", "Center", HuntTheWumpus.Direction.WEST);
            game.connectCavern("Eastern", "Easternmost", HuntTheWumpus.Direction.EAST);
            game.connectCavern("Easternmost", "Eastern", HuntTheWumpus.Direction.WEST);
            game.connectCavern("Northernmost", "Northern", HuntTheWumpus.Direction.SOUTH);
            game.connectCavern("Northern", "Northernmost", HuntTheWumpus.Direction.NORTH);
            game.connectCavern("Northern", "Center", HuntTheWumpus.Direction.SOUTH);
            game.connectCavern("Center", "Northern", HuntTheWumpus.Direction.NORTH);
            game.connectCavern("Center", "Southern", HuntTheWumpus.Direction.SOUTH);
            game.connectCavern("Southern", "Center", HuntTheWumpus.Direction.NORTH);
            game.connectCavern("Southern", "Southernmost", HuntTheWumpus.Direction.SOUTH);
            game.connectCavern("Southernmost", "Southern", HuntTheWumpus.Direction.NORTH);
        });
        //Donut Map
        //
        //[3]<-[2]<-[1]
        // |         A
        // V         |
        //[4]       [8]
        // |         A
        // V         |
        //[5]->[6]->[7]
        Given("donut map", () -> {
            game.connectCavern("1", "2", HuntTheWumpus.Direction.WEST);
            game.connectCavern("2", "3", HuntTheWumpus.Direction.WEST);
            game.connectCavern("3", "4", HuntTheWumpus.Direction.SOUTH);
            game.connectCavern("4", "5", HuntTheWumpus.Direction.SOUTH);
            game.connectCavern("5", "6", HuntTheWumpus.Direction.EAST);
            game.connectCavern("6", "7", HuntTheWumpus.Direction.EAST);
            game.connectCavern("7", "8", HuntTheWumpus.Direction.NORTH);
            game.connectCavern("8", "1", HuntTheWumpus.Direction.NORTH);
        });
        Given("cavern {word} is connected to cavern {word} going {direction}", (String c1, String c2, HuntTheWumpus.Direction direction) -> {
            game.connectCavern(c1,c2,direction);
        });
        When("player rests", () -> game.makeRestCommand().execute());
        Given("player is in cavern {word}", (String c) -> game.setPlayerCavern(c));
        Given("cavern {word} has a pit", (String c) -> game.addPitCavern(c));
        Given("cavern {word} has bats", (String c) -> game.addBatCavern(c));
        Given("the wumpus is in cavern {word}", (String c) -> game.setWumpusCavern(c));
        Given("the wumpus is frozen", () -> game.freezeWumpus());
        When("player moves {direction}", (HuntTheWumpus.Direction direction) -> game.makeMoveCommand(direction).execute());
        Then("player ends in cavern {word}", (String c) -> assertThat(game.getPlayerCavern()).isEqualTo(c));
        Then("a {word} message is given", (String message) -> assertThat(messages).contains(message));
        Then("a {word} message is not given", (String message) -> assertThat(messages).doesNotContain(message));
        When("player moves {direction} {int} times starting from {word} each time", (HuntTheWumpus.Direction direction, Integer times, String startingCavern) -> {
            for (int i=0; i<times; i++) {
                game.setPlayerCavern(startingCavern);
                game.makeMoveCommand(direction).execute();
                incrementCounter(TestContext.batTransportCaverns, game.getPlayerCavern());
            }
        });
        Then("player landed in {word} between {int} and {int} times", (String c, Integer min, Integer max) -> {
            assertThat(zeroIfNull(TestContext.batTransportCaverns.get(c))).isBetween(min, max);
        });
    }

    private void incrementCounter(Map<String, Integer> counterMap, String cavern) {
        counterMap.put(cavern, zeroIfNull(counterMap.get(cavern)) + 1);
    }

    private int zeroIfNull(Integer integer) {
        return integer == null ? 0 : integer;
    }
}
