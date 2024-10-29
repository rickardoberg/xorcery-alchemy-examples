package com.exoreaction.xorcery.alchemy.examples.customjars;

import com.exoreaction.xorcery.alchemy.jar.RecipeConfiguration;
import com.exoreaction.xorcery.alchemy.jar.SourceJar;
import com.exoreaction.xorcery.configuration.Configuration;
import com.exoreaction.xorcery.metadata.Metadata;
import com.exoreaction.xorcery.reactivestreams.api.MetadataJsonNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.inject.Inject;
import org.jvnet.hk2.annotations.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service(name="jars.mycustomsource")
public class MyCustomSourceJar
    implements SourceJar
{
    private final Configuration configuration;

    @Inject
    public MyCustomSourceJar(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Flux<MetadataJsonNode<JsonNode>> newSource(Configuration configuration, RecipeConfiguration recipeConfiguration) {
        MyCustomSourceConfiguration conf = new MyCustomSourceConfiguration(configuration);
        List<MetadataJsonNode<JsonNode>> dataList = new ArrayList<>();
        for (int i = 0; i < conf.getCount(); i++) {
            Metadata metadata = new Metadata.Builder().build();
            ObjectNode data = JsonNodeFactory.instance.objectNode();
            dataList.add(new MetadataJsonNode<>(metadata, data));
        }
        return Flux.fromIterable(dataList);
    }

    record MyCustomSourceConfiguration(Configuration configuration)
    {
        int getCount()
        {
            return configuration.getInteger("count").orElse(10);
        }
    }
}
