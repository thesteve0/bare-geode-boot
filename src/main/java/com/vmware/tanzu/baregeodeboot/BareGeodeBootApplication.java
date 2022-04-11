package com.vmware.tanzu.baregeodeboot;

import com.vmware.tanzu.baregeodeboot.cache.CacheCommunicator;
import com.vmware.tanzu.baregeodeboot.data.ThingToCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableClusterDefinedRegions;
import org.springframework.geode.config.annotation.EnableClusterAware;

import java.util.ArrayList;



@SpringBootApplication

// By default if we put the SB Data for Geode in the class path it will start up cache on localhost and automatically connect
// but the point of this sample is to teach you how to connect to an external Locator

// To enforce the external geode instance we use the @EnableClusterAware annotation.
// Read more here https://docs.spring.io/spring-boot-data-geode-build/current/reference/html5/#geode-configuration-declarative-annotations-productivity-enableclusteraware-strictmatch
@EnableClusterAware(strictMatch = true)

// WE DO NOT want to add @ClientCacheApplication to specify the connection information. If we use this annotation it prevents the
// Autoconfiguration of a bunch more required connection information. Instead we want to just over the host and port information in
// the application.properties file

// We are going to use this pattern in the properties file to allow for ease of moving between hosting platforms
// https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config.files.property-placeholders

// We add the EnableClusterDefinedRegions since we are defining our regions in gfsh. This annotation basically tells our app to
// map region names from our server to "names" attribute in our annotations.
@EnableClusterDefinedRegions

//Implementing CommandLineRunner allows us to add the run method below
public class BareGeodeBootApplication implements CommandLineRunner {

	//Add the Class we made for talking to and from the cache
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
			System.out.println("Just cached: " + thingToCache.toString());
		});

		System.out.println("Alright, let's call them back out");
		//Call them back out of the cache
		thingsToCache.forEach(thingToCache -> {
			System.out.println(cacheCommunicator.getFromCache(thingToCache.getName()));
		});



	}

}
