PreRequisit to run this project

**Start Kafka locally**
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties

kafka runs on following url:
localhost:9092

Rest Client runs on :
http://localhost:8081


**Setup & Run**

mvn clean install
mvn spring-boot:run

**Test**
mvn test


