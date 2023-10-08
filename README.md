# Demo project for Spring Boot API to find Nearest Parking Lot using Spring Data JPA

## Using maven to Run the System
```
cd forvy-app
```
to clear any compiled files you have, making sure that you're really compiling each module from scratch you can execute: 
```
mvn clean install
```
to run:
```
mvn spring-boot:run
```

## Endpoint
To update data simply access POST endpoint: ```/carparks/updateData```. **Warning**: request might be taking long time depending on your file data and external API responses.

Otherwise you can filter on GET endpoint: ```/carparks/nearest?latitude=1.37326&longitude=103.897&page=1&per_page=3```

## Using docker to Run the System
We can easily run the whole with only a single command:
```bash
docker compose up
```

Docker will pull the MySQL and Spring Boot images (if our machine does not have it before).

The services can be run on the background with command:
```bash
docker compose up -d
```

## Using docker to Stop the System
Stopping all the running containers is also simple with a single command:
```bash
docker compose down
```

If you need to stop and remove all containers, networks, and all images used by any service in <em>docker-compose.yml</em> file, use the command:
```bash
docker compose down --rmi all
```