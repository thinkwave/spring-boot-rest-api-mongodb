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
install plugins :
- Project Dashboard 2.3.1
- Kafka 0.11.0
- MongoDB for VS Code -.5.0
- Remote - Containers 0.163.2
- Spring Boot Dashboard 0.2.0
- Workspace Color 0.0.3



## API
(Swager2)[http://localhost:8080/swagger-ui/]

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

### settings.json
```
    "workbench.colorCustomizations": {
        "activityBar.background": "#8b1163"
      }
```