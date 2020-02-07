# HibernateOGM
This sample can be used with hibernate OGM 5.4.1 Or 5.1.0

# Configuration
Modify **pom.xml** to choose version

```
<version>5.4.1.Final</version>
<!--  <version>5.1.0.Final</version> -->
...
...
<artifactId>hibernate-ogm-infinispan-embedded</artifactId>
<!-- When using 5.1.0.Final version -->
<!-- <artifactId>hibernate-ogm-infinispan-embedded</artifactId> -->
```      
Modify the **persitence.properties** to be compatible with ogm vesion
```
#To be used with 5.1.0 vesion
hibernate.ogm.infinispan.configuration_resource_name=ems-model-infinispan-v8.xml
#To be used with veresion 5.4.1
hibernate.ogm.infinispan.configuration_resource_name=ems-model-infinispan.xml
````
# Build project
```
mvn clean install
```
# Install
```
cp ./target/helloWolrd-1.0.0-SNAPSHOT.war on /opt/wildfly-17.0.1.Final/standalone/deployments/ 
```

# Launch
```
/opt/wildfly-17.0.1.Final/bin/standalone.sh
```
# Test

```
#create on Foo
curl -i -X POST http://localhost:8080/helloWolrd-1.0.0-SNAPSHOT/foo/create/Myfoo
#get Foo
curl -i -X POST http://localhost:8080/helloWolrd-1.0.0-SNAPSHOT/foo/get/Myfoo
```
