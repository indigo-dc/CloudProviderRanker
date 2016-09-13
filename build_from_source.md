# Build from source

To build the JAR artifact, you need JDK, Maven and Git installed on your desktop. 
First Step - Download the code from GIT:
* git clone git@github.com:indigo-dc/CloudProviderRanker.git

Second Step - Produce the JAR
* cd CloudProviderRanker
* mvn compile

The artifact is written in the `target/` subdirectory:

```$ ll target/*.jar```
```
-rw-rw-r--. 1 centos centos    27196 Sep 13 10:33 target/CloudProviderRanker.jar
-rw-rw-r--. 1 centos centos 24095292 Sep 13 10:33 target/CloudProviderRanker-jar-with-dependencies.jar```

You must only consider the "`-jar-with-dependencies.jar`" one, which is dependency free (it contains all it needs).