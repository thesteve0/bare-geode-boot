package com.vmware.tanzu.baregeodeboot.config;

import org.apache.geode.cache.GemFireCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.data.gemfire.cache.GemfireCache;

@Configuration
public class ApplicationConfiguration {

    @Bean("thingsTemplate")
    GemfireTemplate thingsTemplate(GemFireCache cache){
        return new GemfireTemplate(cache.getRegion("/things"));
    }
}
