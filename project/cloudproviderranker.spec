Summary: Ranker for Clour Providers
Name: CloudProviderRanker
Version: %{extversion}
Release: %{extage}.%{extdist}
License: ASL 2.0
Vendor: Indigo
URL: https://indigo-dc.gitbooks.io/cloud-provider-ranker/content/
Group: Applications/Internet
BuildArch: %{_arch}
Requires: maven
Requires(post): chkconfig
Requires(preun): chkconfig
Requires(preun): initscripts
BuildRequires: %{!?extbuilddir: /usr/share/java/cpr/, } maven
BuildRoot: %{_tmppath}/%{name}-%{version}-%{release}-root-%(%{__id_u} -n)
AutoReqProv: yes
Source: %{name}-%{version}-%{release}.tar.gz

%global debug_package %{nil}

%description
Ranker for Clour Providers

%prep
 

%setup -c -q

%build
%{!?extbuilddir:%define extbuilddir "--"}
if test "x%{extbuilddir}" == "x--" ; then
  mvn site
  mvn compile
  mvn package
  mvn install
fi

%install
rm -rf %{buildroot}
mkdir -p %{buildroot}
%{!?extbuilddir:%define extbuilddir "--"}
if test "x%{extbuilddir}" == "x--" ; then
  mvn install
  cp target/CloudProviderRanker-0.3.0-SNAPSHOT-jar-with-dependencies.jar /usr/share/java/cpr/
else
  cp -R %{extbuilddir}/* %{buildroot}
fi
export QA_SKIP_BUILD_ROOT=yes

%clean
rm -rf %{buildroot}

%post 
/sbin/chkconfig --add glite-wms-ice

%preun
if [ $1 -eq 0 ] ; then
    /sbin/service glite-wms-ice stop >/dev/null 2>&1
    /sbin/chkconfig --del glite-wms-ice
fi

%files
%defattr(-,root,root)
/etc/rc.d/init.d/glite-wms-ice
/usr/share/java/cpr/CloudProviderRanker-0.3.0-SNAPSHOT-jar-with-dependencies.jar

%changelog
* %{extcdate} Alvise Dorigo <dorigoa@pd.infn.it> - %{extversion}-%{extage}.%{extdist}
- %{extclog}
