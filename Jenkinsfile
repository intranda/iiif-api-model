    pipeline {

      agent {
        docker {
          image 'maven:3-eclipse-temurin-21'
          args '-v $HOME/.m2:/var/maven/.m2:z -u 1000 -ti -e _JAVA_OPTIONS=-Duser.home=/var/maven -e MAVEN_CONFIG=/var/maven/.m2'
        }
      }

      options {
        buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '15', daysToKeepStr: '90', numToKeepStr: '')
      }

      stages {
        stage('prepare') {
          steps {
            sh 'git clean -fdx'
          }
        }
        stage('build') {
          steps {
                  sh 'mvn -f iiif-api-model/pom.xml clean install'
                  recordIssues enabledForFailure: true, aggregatingResults: true, tools: [java(), javaDoc()]
          }
        }
        stage('deployment to maven repository') {
          when {
            anyOf {
            branch 'master'
			      branch 'develop'
			      branch 'feature_tomcat10'
            }
          }
          steps {
            sh 'mvn -f iiif-api-model/pom.xml -DskipTests=true deploy'
          }
        }
      }
      post {
        always {
          junit "**/target/surefire-reports/*.xml"
          step([
            $class           : 'JacocoPublisher',
            execPattern      : 'goobi-viewer-core/target/jacoco.exec',
            classPattern     : 'goobi-viewer-core/target/classes/',
            sourcePattern    : 'goobi-viewer-core/src/main/java',
            exclusionPattern : '**/*Test.class'
          ])
        }
        success {
          archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
        changed {
          emailext(
            subject: '${DEFAULT_SUBJECT}',
            body: '${DEFAULT_CONTENT}',
            recipientProviders: [requestor(),culprits()],
            attachLog: true
          )
        }
      }
    }
    /* vim: set ts=2 sw=2 tw=120 et :*/
