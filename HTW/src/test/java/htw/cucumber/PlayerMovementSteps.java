package htw.cucumber;
import cucumber.api.java8.En;
import htw.HuntTheWumpus;
import htw.fixtures.TestContext;

import java.util.Map;

import static htw.fixtures.TestContext.game;
import static htw.fixtures.TestContext.messages;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerMovementSteps implements En {

    private static final String NO_PASSAGE_MESSAGE = "NO_PASSAGE";
    private static final String DIR_PASSAGE_MESSAGE_SUFFIX = "_PASSAGE";

    public PlayerMovementSteps() {
        super();
        When("player rests", () -> game.makeRestCommand().execute());
        Given("player is in cavern {word}", (String c) -> game.setPlayerCavern(c));
        When("player moves {direction}", (HuntTheWumpus.Direction direction) -> game.makeMoveCommand(direction).execute());
        Then("player ends in cavern {word}", (String c) -> assertThat(game.getPlayerCavern()).isEqualTo(c));
        Then("player gets a no passage message", () -> assertThat(messages).contains(NO_PASSAGE_MESSAGE));
        Then("player does not get a no passage message", () -> assertThat(messages).doesNotContain(NO_PASSAGE_MESSAGE));
        Then("player gets a {direction} passage message", (HuntTheWumpus.Direction dir) -> assertThat(messages).contains(createPassageMessage(dir)));
        Then("player does not get a {direction} passage message", (HuntTheWumpus.Direction dir) -> assertThat(messages).doesNotContain(createPassageMessage(dir)));
        Then("a {word} message is given", (String message) -> assertThat(messages).contains(message));
        Then("a {word} message is not given", (String message) -> assertThat(messages).doesNotContain(message));
    }

    private String createPassageMessage(HuntTheWumpus.Direction dir) {
        return dir.toString() + DIR_PASSAGE_MESSAGE_SUFFIX;
    }
}
