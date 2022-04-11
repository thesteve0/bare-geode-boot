package com.vmware.tanzu.baregeodeboot.cache;

import com.vmware.tanzu.baregeodeboot.data.ThingToCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

//Annotate with component which adds it to the Spring ApplicationContext
@Component
public class CacheCommunicator {

    // Inject the GemfireTemplate that corresponds to our Geode region. All interactions with the region will
    // occur through method calls on this class
    // https://docs.spring.io/spring-data/gemfire/docs/current/api/org/springframework/data/gemfire/GemfireTemplate.html
    @Autowired
    @Qualifier("thingsTemplate")
    private GemfireTemplate thingsTemplate;


    // Put the thing in the chase and use the name as the key
    // We could return void here as well
    // Why
    public void storeInCache(ThingToCache cacheThis){
        // ??It looks like the method call should return the Value cached but when all I am getting returned is null??
        // https://docs.spring.io/spring-data/gemfire/docs/current/api/org/springframework/data/gemfire/GemfireTemplate.html#put-K-V-
        thingsTemplate.put(cacheThis.getName(), cacheThis);
    }


    // Read out the cache item add the timestamp to when we read it
    public ThingToCache getFromCache(String name){
        ThingToCache result = null;

        //Get the thing out of the cache
        result = thingsTemplate.get(name);

        //Fill in the time we got it out of the cache
        result.setOutCacheTime(Instant.ofEpochMilli(System.currentTimeMillis()));

        return result;
    }
}
