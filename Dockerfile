FROM openjdk:17 as build

WORKDIR /build

COPY . .

RUN ./gradlew build -x test 

FROM openjdk:17

COPY --from=build /build/build/libs/forseti-0.0.1-SNAPSHOT.jar .
    
EXPOSE 8080
ENTRYPOINT exec java -jar forseti-0.0.1-SNAPSHOT.jar