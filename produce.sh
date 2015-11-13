#!/bin/bash
echo "Notice: need to kill process at first"
sed -i '13s/.*/\tpublic final static String imageFolerPath = \"\/home\/ubuntu\/public_images\/\";/' src/main/java/junzhou365/config/WebConfig.java
sed -i '14s/.*/\tpublic final static String imageResourcePath = \"file:\/\/\/home\/ubuntu\/public_images\/\";/' src/main/java/junzhou365/config/WebConfig.java
sed -i '15s/.*/\tpublic final static String SHAREDRESOURCES = \"file:\/\/\/home\/ubuntu\/shared_resources\/\";/' src/main/java/junzhou365/config/WebConfig.java
sed -i '16s/.*/\tpublic final static String CATALOG_ANGULARJS_LOCATION = \"file:\/\/\/home\/ubuntu\/webapp_catalog\/app\/\";/' src/main/java/junzhou365/config/WebConfig.java
sed -i '17s/.*/\tpublic final static String LIANPAGE_ANGULARJS_LOCATION = \"file:\/\/\/home\/ubuntu\/lianpage\/app\/\";/' src/main/java/junzhou365/config/WebConfig.java
sed -i '18s/.*/\tpublic final static String FROGGER_LOCATION = \"file:\/\/\/home\/ubuntu\/frogger\/\";/' src/main/java/junzhou365/config/WebConfig.java
sed -i '3s/.*/server\.port=80/' src/main/resources/application.properties
sed -i '4s/.*/logging\.level\.junzhou365=INFO/' src/main/resources/application.properties
sed -i '5s/.*/logging\.level\.org\.springframework\.web=INFO/' src/main/resources/application.properties
mvn clean install -DskipTests
sudo java -jar target/junzhou365-0.0.1-SNAPSHOT.jar&
