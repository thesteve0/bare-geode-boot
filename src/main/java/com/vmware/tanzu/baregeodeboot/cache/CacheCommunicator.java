package com.vmware.tanzu.baregeodeboot.cache;

import com.vmware.tanzu.baregeodeboot.data.ThingToCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.stereotype.Component;

//Annotate with a component so it gets added to the ApplicationContext
@Component
public class CacheCommunicator {

    // This gets us a handle to the automatically created template created by SpringBoot Data for Geode which we can use
    // to interact with with our cache
    // https://docs.spring.io/spring-data/gemfire/docs/current/api/org/springframework/data/gemfire/GemfireTemplate.html
    /*@Autowired
    @Qualifier("thingsTemplate")
    private GemfireTemplate thingsTemplate;

     */

    //We are using cachePut rather than cacheable because we always want the method to executed
    //https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/cache.html#cache-annotations-put
    @CachePut(value = "things", key = "#cacheThis.name")
    public ThingToCache storeInCache(ThingToCache cacheThis){
        return cacheThis;
    }
    // Read add the date of the read to the ThingToCache
    public ThingToCache getFromCache(String name){
        ThingToCache result = null;

        //add in the time we Instant we got from the cache
        return result;
    }
}
