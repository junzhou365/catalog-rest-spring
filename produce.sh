#!/bin/bash
sed -i '12s/.*/public final static String imageFolerPath = \"\/home\/ubuntu\/public_images\/\";/' src/main/java/catalog/config/WebConfigurer.java
sed -i '13s/.*/public final static String imageResourcePath = \"file:\/\/\/home\/ubuntu\/public_images\/\";/' src/main/java/catalog/config/WebConfigurer.java
sed -i '14s/.*/public final static String CATALOG_ANGULARJS_LOCATION = \"file:\/\/\/home\/ubuntu\/webapp_catalog\/app\/\";/' src/main/java/catalog/config/WebConfigurer.java
sed -i '4s/.*/server\.port=8080' src/main/resources/application.properties