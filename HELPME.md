# bet-service API with Spring Boot

#### Prerequisite 

Installed:   
[Docker](https://www.docker.com/)   
[git](https://www.digitalocean.com/community/tutorials/how-to-contribute-to-open-source-getting-started-with-git)  

## Steps to Setup

**1. Clone the application**

```
$  git clone  https://github.com/atanu2110/bet-service.git
```

**2. Build project using Maven**

```
$ mvn clean install -DskipTests
```

**3. Build docker image**

```
docker build -t bet-service .
```

**4. Run docker image**

```
docker run -p 8086:8086 bet-service
```

The app will start running at <http://localhost:8086>

## Api Documentation ( Swagger )

<http://localhost:8086/swagger-ui/#/>