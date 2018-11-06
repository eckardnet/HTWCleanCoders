package htw.cucumber;
import cucumber.api.java8.En;
import htw.HuntTheWumpus;
import htw.fixtures.TestContext;

import static htw.fixtures.TestContext.game;
import static htw.fixtures.TestContext.messages;
import static org.assertj.core.api.Assertions.assertThat;

public class MapSteps implements En {

    private final TestContext testContext;

    public MapSteps() {
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
        Given("cavern {word} is connected to cavern {word} going {direction}", (String c1, String c2, HuntTheWumpus.Direction direction) ->
            game.connectCavern(c1,c2,direction));
    }
}
