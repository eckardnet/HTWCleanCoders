package htw.cucumber;
import cucumber.api.java8.En;
import htw.HuntTheWumpus;
import htw.fixtures.TestContext;

import java.util.Map;

import static htw.fixtures.TestContext.game;
import static htw.fixtures.TestContext.messages;
import static org.assertj.core.api.Assertions.assertThat;

public class ArrowSteps implements En {

    public ArrowSteps() {
        super();
        Given("player has {int} arrows in his quiver", (Integer a) -> game.setQuiver(a));
        When("player shoots {direction}", (HuntTheWumpus.Direction dir) -> game.makeShootCommand(dir).execute());
        Then("there are {int} arrows left in the players quiver", (Integer a) -> assertThat(game.getQuiver()).isEqualTo(a));
        Then("there are {int} arrows in cavern {word}", (Integer a, String c) -> assertThat(game.getArrowsInCavern(c)).isEqualTo(a));
        When("player shoots {direction} {int} times", (HuntTheWumpus.Direction dir, Integer times) -> {
            for (int i= 0; i < times; i++) {
                game.makeShootCommand(dir).execute();
            }
        });
        Given("cavern {word} contains {int} arrows", (String c, Integer arrows) -> game.setArrowsInCavern(c, arrows));
    }
}
