package com.vmware.tanzu.baregeodeboot.cache;

import com.vmware.tanzu.baregeodeboot.data.ThingToCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

//Annotate with a component so it gets added to the ApplicationContext
@Component
public class CacheCommunicator {

    // This gets us a handle to the automatically created template created by SpringBoot Data for Geode which we can use
    // to interact with with our cache
    // https://docs.spring.io/spring-data/gemfire/docs/current/api/org/springframework/data/gemfire/GemfireTemplate.html
    @Autowired
    @Qualifier("thingsTemplate")
    private GemfireTemplate thingsTemplate;


    //Put the thing in the chase and use the name as the key
    public Object storeInCache(ThingToCache cacheThis){
        Object result;
        result = thingsTemplate.put(cacheThis.getName(), cacheThis);
        return result;
    }


    // Read out the cache item add the timestamp to when we read it
    public ThingToCache getFromCache(String name){
        ThingToCache result = null;
        //Get the thing out of the cache
        result = thingsTemplate.get(name);

        //Fill in the time we got it out of the cache
        result.setOutCacheTime(Instant.ofEpochMilli(System.currentTimeMillis()));
        //add in the time we Instant we got from the cache
        return result;
    }
}
