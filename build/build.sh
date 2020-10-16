#!/bin/sh

### build web client
echo 'compiling web client'
npx -p  @angular/cli npm run  --prefix  ../web-client  build -- --configuration=production
mv ../web-client/dist/web-client  .

echo 'building docker image for web client'
docker build -t javierdavidsantos/trivia-game/web-client . 

echo 'deleting artifactors generated'
rm -R web-client



### build core services
echo 'building core'
mvn  -f ../core/pom.xml  clean install
java -Djarmode=layertools -jar ../core/target/core-0.0.1-SNAPSHOT.jar extract

echo 'building docker image for core'
docker build -t javierdavidsantos/trivia-game/core-service -f Dockerfile-Core .

echo 'deleting artifactors generated'
rm -R application
rm -R dependencies
rm -R snapshot-dependencies
rm -R spring-boot-loader

