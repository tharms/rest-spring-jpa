****Maven archetype for restful spring-boot application with jpa****

This archetype generates a simple model along with it's REST resources consisting of simple `Item`s grouped by `Group`s that are stored in-memory. 

**To install**

- download repository
```
cd rest-spring-jpa
mvn clean install
```

**To apply and run**
```
cd <your-workspace>
mvn archetype:generate -DarchetypeGroupId=com.tharms -DarchetypeArtifactId=rest-spring-jpa -DarchetypeVersion=1.0-SNAPSHOT -DgroupId=<com.company.package> -DartifactId=<your-artifact> -Dversion=1.0-SNAPSHOT
mvn clean package
java -jar target/<your-artifact>.jar
```

**To use**
```
curl -X GET  'http://localhost:8080/groups' | jq
curl -X GET  'http://localhost:8080/groups/all/items' | jq
curl -v --header "Content-Type: application/json"   --request POST   --data '{ "name":"tobias harms", "description":"another item"}'   http://localhost:8080/groups/2/items/
curl -X GET  'http://localhost:8080/groups/all/items' | jq
curl -v --header "Content-Type: application/json"   --request DELETE http://localhost:8080/groups/2/items/1
curl -X GET  'http://localhost:8080/groups/all/items' | jq
```
