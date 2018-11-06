package htw.cucumber;
import cucumber.api.java8.En;
import htw.HuntTheWumpus;
import htw.fixtures.TestContext;

import java.util.Map;

import static htw.fixtures.TestContext.game;
import static htw.fixtures.TestContext.messages;
import static org.assertj.core.api.Assertions.assertThat;

public class PitSteps implements En {

    public static final String HEAR_PIT_MESSAGE = "HEAR_PIT";
    public static final String FELL_IN_PIT_MESSAGE = "FELL_IN_PIT";

    public PitSteps() {
        super();
        Given("cavern {word} has a pit", (String c) -> game.addPitCavern(c));
        Then("player hears pit", () ->  assertThat(messages).contains(HEAR_PIT_MESSAGE));
        Then("player does not hear pit", () ->  assertThat(messages).doesNotContain(HEAR_PIT_MESSAGE));
        Then("player falls into pit", () -> assertThat(messages).contains(FELL_IN_PIT_MESSAGE));
        Then("player does not fall into pit", () -> assertThat(messages).doesNotContain(FELL_IN_PIT_MESSAGE));
    }
}
