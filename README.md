# bare-geode-boot
Project to demonstrate connecting Spring Boot to Geode just like learned to connect your Java code to a Database


## How I spun up geode

downloaded from here:
https://geode.apache.org/releases/
At the time of writing grabbed 1.14.4
Grabbed the tgz file

geode 1.14 will not work with JDK 17

Followed the tgz instructions here:
https://geode.apache.org/docs/guide/114/getting_started/installation/install_standalone.html

started the locator

Had to add the `--bind-address=` on linux and this makes it more clear which interface to bind to. The doc says it binds to all interfaces but I was getting errors on Linux 
```commandline
gfsh 

start locator --name=mylocator --port=10336 --bind-address=127.0.0.1

start server --name=myserver --server-port=40405 --server-bind-address=127.0.0.1

create region --name=things --type=local
```

I used non-standard ports to allow me to show how to specify the entire connection string to a remote geode locator and cache server. 
To run this app you need to set environment variables:
1. locator_host=localhost;
2. locator_port=10336

I used a local region since we are only spinning up one caching server and we don't care about replication, distribution, and partitioning.
We also only care about an in-memory cache in this instance, no persistence demonstrated.

There are 4 classes in our entire application
1. BareGeodeBootApplication - This the main class of the Spring Boot application. It implements CommandLineRunner to enable to do a simple: create items to cache and put them in an ArrayList, iterate through the ArrayList and put them in the cache, and iterate through the ArrayList again but this time use the names in the list to pull the items out of the cache
2. ThingToCache - This is a POJO of the objects we will read and write to the cache. 
3. ApplicationConfiguration - This Spring configuration class is how we generate the handle to the GemfireTemplate to interact with our "things" region in Geode
4. CacheCommunicator - This class does all the interaction with the cache

TODO write some more and fill this out - the stuff below might contain links we want to use above. 













TODO now we are ready for gemfire integration
We are going to need this annotation on our main class


https://docs.spring.io/spring-boot-data-geode-build/current/reference/html5/#geode-configuration-declarative-annotations-productivity-enableclusteraware


and this one to configure the location of the locators
https://docs.spring.io/spring-data/geode/docs/current/reference/html/#bootstrap-annotation-config-client-server-applications

        John uses the
        org.springframework.data.repository.CrudRepository
        To handle all his puts and gets - therefore he annotates his business class with stuff to make it work with CRUD repository.
        We are NOT doing that. Our Pojo will just be a dumb pojo with no fancy annotations

        We will annotate in our our controller class when we want to push the return to the cache.
        https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache-annotations

        This is the most relevant example

        basically make a new method here that
        1. goes through each quote in the list returned
        2. For each put it in the cache
        3. Then a trigger in the cache will decide what do to with it

        This is the real article that shows how to wire it up
        https://tanzu.vmware.com/developer/data/tanzu-gemfire/guides/get-started-tgf4k8s-sbdg/



        https://docs.spring.io/spring-boot-data-geode-build/1.2.x/reference/html5/
        https://www.baeldung.com/spring-cache-tutorial
        https://docs.spring.io/spring-gemfire/docs/current/reference/html/#reference

        https://github.com/spring-projects/spring-data-geode



         