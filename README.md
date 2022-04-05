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

```commandline
gfsh 

start locator --name=mylocator

start server --name=myserver

```


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
        https://docs.spring.io/spring-data/geode/docs/current/reference/html/#bootstrap-annotation-config-client-server-applications
        https://tanzu.vmware.com/developer/data/tanzu-gemfire/guides/get-started-tgf4k8s-sbdg/


        https://docs.spring.io/spring-data/geode/docs/current/reference/html/#bootstrap-annotation-config-client-server-applications
        https://docs.spring.io/spring-boot-data-geode-build/1.2.x/reference/html5/
        https://www.baeldung.com/spring-cache-tutorial
        https://docs.spring.io/spring-gemfire/docs/current/reference/html/#reference

        https://github.com/spring-projects/spring-data-geode



         