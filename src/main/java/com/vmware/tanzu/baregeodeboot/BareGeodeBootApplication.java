package com.vmware.tanzu.baregeodeboot;

import com.vmware.tanzu.baregeodeboot.cache.CacheCommunicator;
import com.vmware.tanzu.baregeodeboot.data.ThingToCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

//Implementing CommandLineRunner allows us to add the run method below
//TODO Need to annotate with the connection information for the cache
@SpringBootApplication
public class BareGeodeBootApplication implements CommandLineRunner {

	@Autowired
	CacheCommunicator cacheCommunicator;


	public static void main(String[] args) {
		SpringApplication.run(BareGeodeBootApplication.class, args);
	}

	@Override
	public void run (String... args) throws Exception {

		//Create an ArrayList with 10 ThingToCache in them
		ArrayList<ThingToCache> thingsToCache = new ArrayList<ThingToCache>();
		for (int i = 0; i < 10; i++) {
			thingsToCache.add(new ThingToCache());
		}
		//Put them in the cache
		thingsToCache.forEach(thingToCache -> {
			cacheCommunicator.storeInCache(thingToCache);
			System.out.println("Just cached: " + thingsToCache.toString());
		});

		System.out.println("Alright, let's call them back out");
		//Call them back out of the cache
		thingsToCache.forEach(thingToCache -> {
			System.out.println(cacheCommunicator.getFromCache(thingToCache.getName()));
		});



	}

}
