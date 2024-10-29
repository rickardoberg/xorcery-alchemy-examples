package com.exoreaction.xorcery.alchemy.examples.customjars;

import com.exoreaction.xorcery.alchemy.jar.RecipeConfiguration;
import com.exoreaction.xorcery.alchemy.jar.ResultJar;
import com.exoreaction.xorcery.configuration.Configuration;
import com.exoreaction.xorcery.reactivestreams.api.MetadataJsonNode;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.inject.Inject;
import org.apache.logging.log4j.Logger;
import org.jvnet.hk2.annotations.Service;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.util.context.ContextView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@Service(name="jars.mycustomresult")
public class MyCustomResultJar
    implements ResultJar
{
    private final Configuration configuration;
    private final Logger logger;
    private final List<MetadataJsonNode<JsonNode>> result = new ArrayList<>();

    @Inject
    public MyCustomResultJar(Configuration configuration, Logger logger) {
        this.configuration = configuration;
        this.logger = logger;
    }

    @Override
    public BiFunction<Flux<MetadataJsonNode<JsonNode>>, ContextView, Publisher<MetadataJsonNode<JsonNode>>> newResult(Configuration configuration, RecipeConfiguration recipeConfiguration) {
        return (flux, context)-> flux.doOnNext(result::add).doOnComplete(()->logger.info("{} items received", result.size()));
    }
}
