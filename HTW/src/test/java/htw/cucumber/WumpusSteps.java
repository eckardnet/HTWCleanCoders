package htw.cucumber;
import cucumber.api.java8.En;
import htw.HuntTheWumpus;
import htw.fixtures.TestContext;

import java.util.Map;

import static htw.fixtures.TestContext.game;
import static htw.fixtures.TestContext.messages;
import static org.assertj.core.api.Assertions.assertThat;

public class WumpusSteps implements En {

    private static final String SMELL_WUMPUS_MESSAGE = "SMELL_WUMPUS";
    private static final String PLAYER_MOVES_TO_WUMPUS_MESSAGE = "PLAYER_MOVES_TO_WUMPUS";
    private static final String WUMPUS_MOVES_TO_PLAYER_MESSAGE = "WUMPUS_MOVES_TO_PLAYER";

    public WumpusSteps() {
        super();
        Given("the wumpus is in cavern {word}", (String c) -> game.setWumpusCavern(c));
        Given("the wumpus is frozen", () -> game.freezeWumpus());
        When("player rests until killed", () -> {
            while (!game.getPlayerCavern().equals(game.getWumpusCavern()))
                game.makeRestCommand().execute();

        });
        When("player rests {int} times with wumpus in {word} each time", (Integer times, String wumpusCavern) -> {
            for (int i=0; i<times; i++) {
                game.setWumpusCavern(wumpusCavern);
                game.makeRestCommand().execute();
                incrementCounter(TestContext.wumpusCaverns, game.getWumpusCavern());
            }
        });
        Then("wumpus ended in {word} between {int} and {int} times", (String c, Integer min, Integer max) ->
                assertThat(zeroIfNull(TestContext.wumpusCaverns.get(c))).isBetween(min, max));
        Then("wumpus ended in cavern {word}", (String c) -> assertThat(game.getWumpusCavern()).isEqualTo(c));
        Then("player smells the wumpus", () -> assertThat(messages).contains(SMELL_WUMPUS_MESSAGE));
        Then("player does not smell the wumpus", () -> assertThat(messages).doesNotContain(SMELL_WUMPUS_MESSAGE));
        Then("player runs into the wumpus", () -> assertThat(messages).contains(PLAYER_MOVES_TO_WUMPUS_MESSAGE));
        Then("the wumpus gets the player", () -> assertThat(messages).contains(WUMPUS_MOVES_TO_PLAYER_MESSAGE));
    }

    private void incrementCounter(Map<String, Integer> counterMap, String cavern) {
        counterMap.put(cavern, zeroIfNull(counterMap.get(cavern)) + 1);
    }

    private int zeroIfNull(Integer integer) {
        return integer == null ? 0 : integer;
    }
}
