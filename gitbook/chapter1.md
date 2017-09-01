# Build from source

To build the java `.jar` artifact, you need `JDK`, `Maven` and `Git`
installed on your system.

## Get the source code

Download the source code repository and optionally checkout a specific
tag:

```
git clone https://github.com/indigo-dc/CloudProviderRanker.git
cd CloudProviderRanker
git checkout v0.6.0
```

## Build the java `.jar`
Build the java `.jar` by running:

```
mvn compile
```

After the compilation is done, the artifact can be found in the file
`target/CloudProviderRanker-jar-with-dependencies.jar`. The artifact is
dependency free as it contains all it needs.


## Build RPM and DEB packages

To produce the `.rpm` and `.deb` packages just issue the command:

```
mvn package
```

The packages can then be found at:

  * `target/CloudProviderRanker-<version>_all.deb`
  * `target/CloudProviderRanker/RPMS/noarch/CloudProviderRanker-<version>.noarch.rpm`

For example, the packages for tag `v0.6.0` can be found at:

  * `target/CloudProviderRanker-0.6.0-1_all.deb`
  * `target/CloudProviderRanker/RPMS/noarch/CloudProviderRanker-0.6.0-1.noarch.rpm`
