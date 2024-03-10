#!/usr/bin/bash

mvn install:install-file \
    -Dfile=lib/javaml/lib/ajt-2.9.jar \
    -DgroupId=be.abeel \
    -DartifactId=ajt \
    -Dversion=2.9 \
    -Dpackaging=jar

mvn install:install-file \
    -Dfile=lib/javaml/javaml-0.1.7.jar \
    -DgroupId=net.sf.javaml \
    -DartifactId=javaml \
    -Dversion=0.1.7 \
    -Dpackaging=jar


mvn install:install-file \
    -Dfile=lib/javaml/lib/Jama-1.0.2.jar \
    -DgroupId=gov.nist.math \
    -DartifactId=jama \
    -Dversion=1.0.3 \
    -Dpackaging=jar

mvn install:install-file \
    -Dfile=lib/javaml/lib/commons-math-1.2.jar \
    -DgroupId=org.apache.commons \
    -DartifactId=math \
    -Dversion=1.2 \
    -Dpackaging=jar
