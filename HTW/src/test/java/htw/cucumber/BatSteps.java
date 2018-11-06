package htw.cucumber;
import cucumber.api.java8.En;
import htw.HuntTheWumpus;
import htw.fixtures.TestContext;

import java.util.Map;

import static htw.fixtures.TestContext.game;
import static htw.fixtures.TestContext.messages;
import static org.assertj.core.api.Assertions.assertThat;

public class BatSteps implements En {

    private static final String HEAR_BATS_MESSAGE = "HEAR_BATS";
    private static final String BAT_TRANSPORT_MESSAGE ="BAT_TRANSPORT";

    public BatSteps() {
        super();
        Given("cavern {word} has bats", (String c) -> game.addBatCavern(c));
        When("player moves {direction} {int} times starting from {word} each time", (HuntTheWumpus.Direction direction, Integer times, String startingCavern) -> {
            for (int i=0; i<times; i++) {
                game.setPlayerCavern(startingCavern);
                game.makeMoveCommand(direction).execute();
                incrementCounter(TestContext.batTransportCaverns, game.getPlayerCavern());
            }
        });
        Then("player landed in {word} between {int} and {int} times", (String c, Integer min, Integer max) ->
            assertThat(zeroIfNull(TestContext.batTransportCaverns.get(c))).isBetween(min, max));
        Then("player hears bats", () -> assertThat(messages).contains(HEAR_BATS_MESSAGE));
        Then("player does not hear bats", () -> assertThat(messages).doesNotContain(HEAR_BATS_MESSAGE));
        Then("player is transported by bats", () -> assertThat(messages).contains(BAT_TRANSPORT_MESSAGE));
        Then("player is not transported by bats", () -> assertThat(messages).doesNotContain(BAT_TRANSPORT_MESSAGE));
    }

    private void incrementCounter(Map<String, Integer> counterMap, String cavern) {
        counterMap.put(cavern, zeroIfNull(counterMap.get(cavern)) + 1);
    }

    private int zeroIfNull(Integer integer) {
        return integer == null ? 0 : integer;
    }
}
