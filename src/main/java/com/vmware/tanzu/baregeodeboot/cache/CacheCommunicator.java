package com.vmware.tanzu.baregeodeboot.cache;

import com.vmware.tanzu.baregeodeboot.data.ThingToCache;
import org.springframework.stereotype.Component;

//Annotate with a component so it gets added to the ApplicationContext
@Component
public class CacheCommunicator {


    //todo need a read and write to cache method
    public void storeInCache(ThingToCache cacheThis){

    }
    // Read add the date of the read to the ThingToCache
    public ThingToCache getFromCache(String name){
        ThingToCache result = null;

        //add in the time we Instant we got from the cache
        return result;
    }
}
