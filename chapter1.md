# Build from source

To build the JAR artifact, you need JDK, Maven and Git installed on your system. 

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

# Build RPM and DEB packages
To produce the RPM and DEB packages just issue the command
* `mvn package`

from within the CloudProviderRanker main directory. The packages are produced under the `target/` subdirectory:
```
$ ls -l target/*.deb
-rw-rw-r--. 1 centos centos 21831812 Sep 13 10:33 target/CloudProviderRanker-0.4.5-1_all.deb
$ ls -l target/CloudProviderRanker/RPMS/noarch/*.rpm
-rw-rw-r--. 1 centos centos 21818988 Sep 13 10:33 target/CloudProviderRanker/RPMS/noarch/CloudProviderRanker-0.4.5-1.noarch.rpm
[centos@indigo-ranker-priv CloudProviderRanker]$ 

```