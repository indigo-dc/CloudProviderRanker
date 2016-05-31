FROM centos
EXPOSE 22 7443
MAINTAINER Alvise Dorigo <alvise.dorigo@pd.infn.it>
RUN yum -y update 
RUN yum -y install iproute \
	net-tools \
	telnet \
	bind-utils \
	hostname \
	less \
	vim \
	more \
	gawk \
	sed \
	grep \
	sudo \
	bash \
	tcsh \
	git \
	make \
	gcc-c++ \
	perl \
	cmake \
	coreutils \
	ntp \
	iputils \
	emacs \
	cloud-init \
	curl \
	openssh-server \
	passwd \
	initscripts \
	bash \
	java-1.8.0-openjdk.x86_64 \
	curl \
	wget \
	unzip
RUN groupadd -g 500 centos
RUN useradd -u 500 -g 500 -m -p centos centos
RUN echo "centos ALL=(ALL)       ALL" >> /etc/sudoers
RUN wget http://download.jboss.org/drools/release/6.3.0.Final/droolsjbpm-tools-distribution-6.3.0.Final.zip
RUN unzip droolsjbpm-tools-distribution-6.3.0.Final.zip
RUN mkdir -p /usr/local/share/java/drools/
RUN mv droolsjbpm-tools-distribution-6.3.0.Final/binaries/*.jar /usr/local/share/java/drools/
RUN rm -rf droolsjbpm-tools-distribution-6.3.0.Final droolsjbpm-tools-distribution-6.3.0.Final.zip
RUN mkdir -p /usr/local/share/java/gson
RUN wget -O /usr/local/share/java/gson/gson-2.6.2.jar http://search.maven.org/remotecontent?filepath=com/google/code/gson/gson/2.6.2/gson-2.6.2.jar
RUN ssh-keygen -f /etc/ssh/ssh_host_rsa_key -N '' -t rsa
RUN ssh-keygen -f /etc/ssh/ssh_host_ecdsa_key -N '' -t ecdsa
RUN mv /etc/ntp.conf /etc/ntp.conf.old
RUN echo "server ntp.pd.infn.it" > /etc/ntp.conf
ADD target/CloudProviderRanker-0.1.jar /home/centos/CloudProviderRanker-0.1.jar
ADD ./startup-cpre /etc/startup-cpre
RUN chmod +x /etc/startup-cpre
CMD /etc/startup-cpre
