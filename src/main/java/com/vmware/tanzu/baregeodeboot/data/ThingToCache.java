package com.vmware.tanzu.baregeodeboot.data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This is just a simple object to cache that has a name, a weight, a time it is created, and a time it is read
 * from the cache. The only variable that is externally modifiable is outCacheTime which should be filled in when
 * object is read out of the cache
 */
public class ThingToCache {
    private final static Random random = new Random();
    private final static ArrayList<String> names = new ArrayList<String>(
            Arrays.asList("Shadow","Scout","Charlie","Luna","Milo","Tiger","Maya","Frankie","Callie","Nina", "Aya",
                    "Hedy", "Disco", "Bo", "Topaz"));

    private String name = names.get(random.nextInt(names.size())) + " The " + random.nextInt(1,1000) ;
    private int weight = random.nextInt(4,120);
    private Instant creationTime = Instant.ofEpochMilli(System.currentTimeMillis());
    private Instant outCacheTime = null;

    public ThingToCache() {
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public Instant getOutCacheTime() {
        return outCacheTime;
    }

    public void setOutCacheTime(Instant outCacheTime) {
        this.outCacheTime = outCacheTime;
    }

    @Override
    public String toString() {
        return "ThingToCache{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", creationTime=" + creationTime +
                ", outCacheTime=" + outCacheTime +
                '}';
    }
}
