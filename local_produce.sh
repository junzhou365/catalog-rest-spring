#!/bin/bash
echo "Notice: need to kill process at first"
sed -i '13s/.*/\tpublic final static String imageFolerPath = \"\/home\/vagrant\/public_images\/\";/' src/main/java/junzhou365/config/WebConfigurer.java
sed -i '14s/.*/\tpublic final static String imageResourcePath = \"file:\/\/\/home\/vagrant\/public_images\/\";/' src/main/java/junzhou365/config/WebConfigurer.java
sed -i '15s/.*/\tpublic final static String SHAREDRESOURCES = \"file:\/\/\/home\/vagrant\/shared_resources\/\";/' src/main/java/junzhou365/config/WebConfigurer.java
sed -i '16s/.*/\tpublic final static String CATALOG_ANGULARJS_LOCATION = \"file:\/\/\/home\/vagrant\/webapp_catalog\/app\/\";/' src/main/java/junzhou365/config/WebConfigurer.java
sed -i '17s/.*/\tpublic final static String LIANPAGE_ANGULARJS_LOCATION = \"file:\/\/\/home\/vagrant\/lianpage\/app\/\";/' src/main/java/junzhou365/config/WebConfigurer.java
sed -i '3s/.*/server\.port=8000/' src/main/resources/application.properties
mvn clean install
sudo java -jar target/junzhou365-0.0.1-SNAPSHOT.jar &
