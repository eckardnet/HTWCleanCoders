package htw.cucumber;

import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import htw.HuntTheWumpus;
import io.cucumber.cucumberexpressions.ParameterType;
import io.cucumber.cucumberexpressions.Transformer;

import java.util.Locale;

public class TypeRegistryConfiguration implements TypeRegistryConfigurer {

    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineParameterType(new ParameterType<>(
                "direction",
                "north|east|south|west",
                HuntTheWumpus.Direction.class,
                new Transformer<HuntTheWumpus.Direction>() {
                    @Override
                    public HuntTheWumpus.Direction transform(String direction) {
                        return HuntTheWumpus.Direction.valueOf(direction.toUpperCase());
                    }
                })
        );
    }
}