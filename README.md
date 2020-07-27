## Development Setup

It is recommended that you clone [h2o-3](https://github.com/mware-solutions/h2o-3) and h2o-flow in the same parent directory. 

1. First build H2O-3  `cd h2o-3 && ./gradlew build -x test` (in h2o-E)

1. Install npm dependencies for h2o-flow `npm i` (in h2o-flow)

### Developing with live reload

1. Start H2O-3 with CORS checks disabled `java -Dsys.ai.h2o.disable.cors=true -jar build/h2o.jar` (in h2o-3)

1. Start webpack dev-server `npm run start` (in h2o-flow)

This will open a browser window with auto-refreshing dev server.


### Development within h2o-3 instance

1. Run `make` command. This will copy the build resources into the neighbouring h2o-3 directory.

2. Start h2o-3 from IDE without running gradle (which would write over your local flow build)

### Testing a new Flow Feature with Sparkling Water  

Flow can also be used with [Sparkling Water](https://github.com/h2oai/sparkling-water)  
Follow this guide develop and test new Sparkling Water features in Flow.  
adapted from the comments on this PR https://github.com/h2oai/h2o-flow/pull/13  

##### copy built js files from one place to another  
in the `h2o-3` directory run:  
`cp h2o-web/src/main/resources/www/flow/js/* h2o-web/lib/h2o-flow/build/js/`  

##### build h2o-3  
in the `h2o-3` directory run:  
`./gradlew publishToMavenLocal -x test`  

##### build sparkling water  
in `sparkling-water` directory run:  
`./gradlew clean build -x test -x integTest`  

##### open the Sparkling Water Shell  
in `sparkling-water` directory run:  
`bin/sparkling-shell`  

in the sparkling water shell  
at the `scala>` prompt run:  
`import org.apache.spark.h2o._`  
`H2OContext.getOrCreate(sc)`  

now open Flow at the IP address specified  
in the sparkling water shell  

now test your changes in Flow  
