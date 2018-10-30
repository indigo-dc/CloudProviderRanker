#!/usr/bin/groovy

@Library(['github.com/indigo-dc/jenkins-pipeline-library']) _

pipeline {
    agent {
        label 'java'
    }
    
    environment {
        dockerhub_repo = "indigodatacloud/cloudproviderranker"
        dockerhub_image_id  = ''
    }

    stages {
            
        stage('Code fetching') {
            steps {
                checkout scm
            }
        }

        stage('Style analysis') {
            steps {
            	MavenRun('checkstyle:check')
            }
            post {
                always {
                    CheckstyleReport()
                    dir("$WORKSPACE/target") {
                        deleteDir()
                    }
                }
            }
        }
        
        stage('Unit testing coverage') {
            steps {
                MavenRun('cobertura')
            }
            post {
                success {
                    CoberturaReport('**/target/site/cobertura/coverage.xml')
                    JUnitReport()
                    dir("$WORKSPACE/target") {
                        deleteDir()
                    }
                }
            }
        }

        stage('Metrics gathering') {
            agent {
                label 'sloc'
            }
            steps {
               	checkout scm 
           	SLOCRun()
            }
            post {
                success {
                    SLOCPublish()
                }
            }
        }
        
        stage('Dependency check') {
            agent {
                label 'docker-build'
            }
            steps {
                checkout scm
                OWASPDependencyCheckRun("$WORKSPACE/CloudProviderRanker/src", project="CloudProviderRanker")
            }
            post {
                always {
                    OWASPDependencyCheckPublish()
                    HTMLReport('src', 'dependency-check-report.html', 'OWASP Dependency Report')
                    deleteDir()
                }
            }
        }
        
        stage('DockerHub delivery') {
            when {
                anyOf {
                    branch 'master'
                    buildingTag()
                }
            }
            agent {
                label 'docker-build'
            }
            steps {
                checkout scm
                script {
                    dockerhub_image_id = DockerBuild(dockerhub_repo, env.BRANCH_NAME, dockerhub_repo+":latest")
                }
            }
            post {
                success {
                    echo "Pushing Docker image ${dockerhub_image_id}.."
                    DockerPush(dockerhub_image_id)
                }
                failure {
                    echo 'Docker image building failed, removing dangling images..'
                    DockerClean()
                }
                always {
                    cleanWs()
                }
            }
        }

        stage('RPM/DEB package building') {
            when {
                anyOf {
                    branch 'master'
                    buildingTag()
                }
            }
	    agent {
		label 'bcentos7'
	    }
	    steps {
		checkout scm
		MavenRun('package')
	    }
	    post {
		success {
		    archiveArtifacts artifacts: '**/RPMS/noarch/*.rpm'
		}
	    }
        }

	stage('Notifications') {
            when {
                buildingTag()
            }
            steps {
                JiraIssueNotification(
                    'DEEP',
                    'DPM',
                    '10204',
                    "[preview-testbed] New CloudProviderRanker version ${env.BRANCH_NAME} available",
                    "Check new artifacts at:\n\t- Docker image: [${dockerhub_image_id}:${env.BRANCH_NAME}|https://hub.docker.com/r/${dockerhub_image_id}/tags/]\n\t- RPM package/s: ${env.BUILD_URL}",
                    ['wp3', 'preview-testbed', "CloudProviderRanker-${env.BRANCH_NAME}"],
		    'Task',
		    'mariojmdavid'
	        )
            }
        }
    }
}
