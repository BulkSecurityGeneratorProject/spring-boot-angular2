## Spring boot and angular 2 workshop

> Frontend with Angular2


```bash
# start the spring boot
$ cd backend
$ mvn spring-boot:run

# start front server
$ cd ../frontend

# install the dependencies with npm
$ npm install

# start the server
$ npm run start
```

The Backend is start in [http://localhost:8080](http://localhost:8080) 

Go to [http://localhost:8081](http://localhost:8081) in your browser.

## One jar for frontend and backend
```bash
# build the front and set result files to ../backend/src/main/resources/static
$ cd frontend
$ npm run build

# package backend and generate jar file
$ cd ../backend
$ mvn clean package
```

## Dockerize

#### Copy  the generated jar to docker folder and rename it to backend.jar

```bash
$ cp ./backend/target/backend-0.0.1-SNAPSHOT.jar ./docker/backend.jar
```
#### Create docker image

```bash
$ cd ./docker
$ docker build -t naddame/spring-boot-angular2 ./
$ docker tag naddame/spring-boot-angular2 naddame/spring-boot-angular2:$VERSION-TAG
$ docker login -u YOUR-LOGIN -p YOUR-PASSWORD HUB-URL
$ docker push naddame/spring-boot-angular2:$VERSION-TAG
```
#### If you want to remove local images

```bash 
$ docker rmi naddame/spring-boot-angular2
$ docker rmi naddame/spring-boot-angular2:$VERSION-TAG
```

#### Run the docker image

```bash
$ docker run -d --name backend -e "JAVA_OPT=-Dspring.profiles.active=prod" -p 8080:8080 naddame/spring-boot-angular2:$VERSION-TAG
```

#### Run the docker image with lincked mongodb container

```bash
# you can create mongodb container and run it and link it as follwoing
$ docker run -d --name mongo -v /localpath/db/mongo:/data/db mongo
$ docker run -d --name backend -e "JAVA_OPT=-Dspring.profiles.active=prod" -p 8080:8080 --link mongo:mongo naddame/spring-boot-angular2:$VERSION-TAG
```

> Used technologies in frontnd

- Angular2
- Webpack


> Used technologies in backend

- Spring boot
- JPA Hibernate
- Spring Security
- JWT
- Swagger
- Mongobee
- REST
- Embeded MongoDB

> Email me if somthing work wrong
 - djamelhamas@gmail.com