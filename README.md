Spring Boot Rest Api MongoDB
============================

## Spring Boot Rest API with MongoDB

- [x] Spring Boot
- [x] Spring Data
- [x] MongoDB Cluster
- [x] Swagger2

## Prequisites
- JDK 8+
- Maven
- docker-compose

### Visual Studio Code

필수는 아니지만 편하게 사용할 수 있어서 추천

install plugins :
- Project Dashboard 2.3.1
- Kafka 0.11.0
- MongoDB for VS Code -.5.0
- Remote - Containers 0.163.2
- Spring Boot Dashboard 0.2.0
- Workspace Color 0.0.3



## API
[Swager2](http://localhost:8080/swagger-ui/)

## Build and run application

1. Clone this project
    ```
    git clone 
    ```

2. Start MongoDb with
    ```
    docker-compose up
    ```

3. mongodb master 구성
    ```
    docker-compose exec mongo1 mongo --eval 'rs.initiate({_id : "r0", members: [{ _id : 0, host : "mongo1:27017", priority : 1 },{ _id : 1, host :"mongo2:27017", priority : 0 },{ _id : 2, host : "mongo3:27017", priority : 0, arbiterOnly: true }]})'
    ```

4. Run spring boot
    ```
    mvn clean install spring-boot:run
    ```

5. Go to browser type `http://localhost:8080/swagger-ui/`




## 참고

* Workspace Color 0.0.3

VSCODE에서 `settings.json`에서 칼러 설정 추가

```
    "workbench.colorCustomizations": {
        "activityBar.background": "#8b1163"
      }
```

* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-mongodb)
* [Apache Kafka Streams Support](https://docs.spring.io/spring-kafka/docs/current/reference/html/_reference.html#kafka-streams)
* [Apache Kafka Streams Binding Capabilities of Spring Cloud Stream](https://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/#_kafka_streams_binding_capabilities_of_spring_cloud_stream)

## 가이드
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
* [Samples for using Apache Kafka Streams with Spring Cloud stream](https://github.com/spring-cloud/spring-cloud-stream-samples/tree/master/kafka-streams-samples)
