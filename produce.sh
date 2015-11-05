#!/bin/bash
echo "Notice: need to kill process at first"
sed -i '12s/.*/\tpublic final static String imageFolerPath = \"\/home\/ubuntu\/public_images\/\";/' src/main/java/catalog/config/WebConfigurer.java
sed -i '13s/.*/\tpublic final static String imageResourcePath = \"file:\/\/\/home\/ubuntu\/public_images\/\";/' src/main/java/catalog/config/WebConfigurer.java
sed -i '14s/.*/\tpublic final static String CATALOG_ANGULARJS_LOCATION = \"file:\/\/\/home\/ubuntu\/webapp_catalog\/app\/\";/' src/main/java/catalog/config/WebConfigurer.java
sed -i '3s/.*/server\.tomcat\.basedir=logs/' src/main/resources/application.properties
sed -i '4s/.*/server\.port=80/' src/main/resources/application.properties
mvn clean install
sudo java -jar target/catalog-0.0.1-SNAPSHOT.jar &