package com.exoreaction.xorcery.alchemy.examples.customjars;

import com.exoreaction.xorcery.alchemy.jar.RecipeConfiguration;
import com.exoreaction.xorcery.alchemy.jar.TransmuteJar;
import com.exoreaction.xorcery.configuration.Configuration;
import com.exoreaction.xorcery.reactivestreams.api.MetadataJsonNode;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.inject.Inject;
import org.jvnet.hk2.annotations.Service;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.util.context.ContextView;

import java.util.function.BiFunction;

@Service(name="jars.mycustomtransmute")
public class MyCustomTransmuteJar
    implements TransmuteJar
{

    private final Configuration configuration;

    @Inject
    public MyCustomTransmuteJar(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public BiFunction<Flux<MetadataJsonNode<JsonNode>>, ContextView, Publisher<MetadataJsonNode<JsonNode>>> newIngredient(Configuration configuration, RecipeConfiguration recipeConfiguration) {
        return (flux, context)-> flux.map(item -> item);
    }
}
