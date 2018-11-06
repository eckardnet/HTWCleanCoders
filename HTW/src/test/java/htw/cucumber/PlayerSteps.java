package htw.cucumber;
import cucumber.api.java8.En;
import htw.HuntTheWumpus;
import htw.fixtures.TestContext;

import java.util.Map;

import static htw.fixtures.TestContext.game;
import static htw.fixtures.TestContext.messages;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerSteps implements En {

    public PlayerSteps() {
        super();
        When("player rests", () -> game.makeRestCommand().execute());
        Given("player is in cavern {word}", (String c) -> game.setPlayerCavern(c));
        When("player moves {direction}", (HuntTheWumpus.Direction direction) -> game.makeMoveCommand(direction).execute());
        Then("player ends in cavern {word}", (String c) -> assertThat(game.getPlayerCavern()).isEqualTo(c));
        Then("a {word} message is given", (String message) -> assertThat(messages).contains(message));
        Then("a {word} message is not given", (String message) -> assertThat(messages).doesNotContain(message));
    }
}
