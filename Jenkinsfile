#!/usr/bin/groovy

@Library(['github.com/indigo-dc/jenkins-pipeline-library']) _

pipeline {
    agent {
        label 'java'
    }
    
    environment {
        dockerhub_repo = "indigodatacloud/cloudproviderranker"
    }

    stages {
            
        stage('Code fetching') {
            steps {
                checkout scm
            }
        }

        stage('Style analysis') {
            steps {
                dir("$WORKSPACE/CloudProviderRanker") {
                    MavenRun('checkstyle:check')
                }
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
                sh 'git clone https://github.com/indigo-dc/CloudProviderRanker'
                dir("$WORKSPACE/CloudProviderRanker") {
                    SLOCRun()
                }
            }
            post {
                success {
                    dir("$WORKSPACE/CloudProviderRanker") {
                        SLOCPublish()
                    }
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
                    image_id = DockerBuild(dockerhub_repo, env.BRANCH_NAME, dockerhub_repo+":latest")
                }
            }
            post {
                success {
                    echo "Pushing Docker image ${image_id}.."
                    DockerPush(image_id)
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
    }
}
