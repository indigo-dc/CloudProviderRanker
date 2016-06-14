# Comments start with hash sign. Caution: macros in comments are still
 # expanded, which can lead to unexpected results. To stop macro from expanding,
 # double its percent sign (e.g. %%mvn_install)

 # First part usually consists of metadata such as package name, version, and
 # much more
Name: CloudProviderRanker
Version: 0.3.0
Release: 0%{?dist}
Summary: This is a minimal spec file example

License: GPLv2+
# Homepage URL of the project
URL: https://indigo-dc.gitbooks.io/cloud-provider-ranker/content

# Our source archive. %{name} expands to 'helloworld' so the resulting
# tarball name would be 'helloworld.tar.gz'.
Source0: /home/centos/%{name}-%{version}.tar.gz
BuildRoot: %{_tmppath}/%{name}-${version}-%{release}-root-%(%{__id_u} -n)
#Prefix: /usr/share/java/cpr

# Packages that contain only architecture independent files, such as shell
# scripts or regular Java programs (not JNI libraries), should be marked as 'noarch'
BuildArch: noarch

# Project's build time dependency. We don't really need JUnit, it just
# serves as and example
BuildRequires: maven
BuildRequires: junit
BuildRequires: java-1.8.0-openjdk-devel
BuildRequires: coreutils

Provides: cloudprovider-ranker = %{version}

%description
Ranker for Clour Providers selected by Indigo's Orchestrator

%prep
# section for preparation of sources, applying patches
# or other things which can be done before running the build
# The macro setup is used to unpack sources
#%setup -q

%build
# Section for compiling and generally assembling the final pieces.
# Our Makefile builds the project JAR file
#make {?_smp_flags}
mvn -DskipTests -U package

%install
# Installation into directory prepared by RPM expressed as %{buildroot}
rm -rf $RPM_BUILD_ROOT
install -p -m 644 sla_priorities.json $RPM_BUILD_ROOT%{_javadir}
install -p -m 644 paasmetric_normalization.json $RPM_BUILD_ROOT%{_javadir}
install -p -m 644 CloudProviderRanker-jar-with-dependencies.jar $RPM_BUILD_ROOT%{_javadir}

# We use macro %jpackage_script to generate wrapper script for our JAR
# Will be explained in later sections
#%jpackage_script org.fedoraproject.helloworld.HelloWorld "" "" %{name} helloworld true

%clean
rm -rf $RPM_BUILD_ROOT


# List of files that this package installs on the system
#%files
#%{_javadir}/CloudProviderRanker-0.3.0-SNAPSHOT-jar-with-dependencies.jar
#%{_javadir}/sla_priorities.json
#%{_javadir}/paasmetric_normalization.json
# %{_bindir}/helloworld

