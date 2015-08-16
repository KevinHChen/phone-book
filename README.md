## Background

## Portable
To make it portable, **h2database** is used to avoid unnecessary data schema initialization

## Start the server
mvn spring-boot:run

## Try it yourself
### 1. Query endpoint
curl http://localhost:8080/customer

### 2. Create new entity
curl -i -X POST -H "Content-Type:application/json" -d '{  "name" : "Kevin H1",  "address" : "Browns Bay", "phone" : "0222222222" }' http://localhost:8080/customer

### 3. Update the entity
curl-i -X PATCH -H "Content-Type:application/json" -d '{  "name" : "Kevin H2",  "address" : "Browns Bay 2", "phone" : "033333333" }' http://localhost:8080/customer/1

### 4. Delete the entity
curl -i -X DELETE -H "Content-Type:application/json" http://localhost:8080/customer/1