package com.example;

import io.micronaut.context.annotation.BootstrapContextCompatible;
import io.micronaut.context.env.Environment;
import io.micronaut.context.env.PropertySource;
import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.discovery.config.ConfigurationClient;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Singleton
@BootstrapContextCompatible
public class HttpServicesConfigurationClient implements ConfigurationClient {
    @Override
    public Publisher<PropertySource> getPropertySources(Environment environment) {
        Map<String, Object> remoteProperty = new HashMap<>();
        remoteProperty.put("micronaut.http.services.github.url", "https://api.github.com");
        return Publishers.just(new PropertySource() {
            @Override
            public String getName() {
                return "mycompanyproperties";
            }

            @Override
            public Object get(String key) {
                return remoteProperty.get(key);
            }

            @Override
            public Iterator<String> iterator() {
                return remoteProperty.keySet().iterator();
            }
        });
    }

    @Override
    public String getDescription() {
        return "fetches the urls of the other services in our company";
    }
}
