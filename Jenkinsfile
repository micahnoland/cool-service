#!/usr/bin/env groovy

pipeline {
  agent none
  environment {  
  	DEPLOYMENT_ID_APP_NAME = 'CoolService'
  	DOCKER_IMAGE = micahnoland/cool-service
  }
  options {
	timeout(time: 1, unit: 'HOURS') 
  }
  parameters {
    booleanParam(name: 'RELEASE_BUILD', defaultValue: false, description: 'Is this a -RELEASE build?')
  }
  stages {
    stage ('Setup') {
      agent any
      steps {
      	script {
     		checkout scm
	    }	 
	    stash name: 'work', useDefaultExcludes: false
      }
    }  
    stage ('Maven Test') { 
      agent any  
      steps {
        deleteDir()
      	unstash 'work'
        script {
    	  try {
    	  	sh('mvnw clean test')
          } catch (e) {
            currentBuild.result = 'FAILURE'
            throw e 
          } finally {
            junit "**/target/surefire-reports/*.xml"
          }		  
		}
		stash name: 'work', useDefaultExcludes: false
      } 
    }
    stage ('Maven Install') {
      agent any
      steps {
        deleteDir()
      	unstash 'work'
        sh 'mvnw clean install'
		stash name: 'work', useDefaultExcludes: false
      }
    }
    stage ('Docker') {
      steps {
      	deleteDir()
      	unstash 'work'
      	script {
        	docker.withRegistry('https://hub.docker.com', 'docker-hub') {
        		docker.build("${env.DOCKER_IMAGE}:${env.BUILD_ID}").push()
    		}
        }
        stash name: 'work', useDefaultExcludes: false
      }
    }
    stage ('Dev Deployments') {
      agent any
      steps {
      	script {
      	  //deploymentApi(env.DEPLOYMENT_ID_APP_NAME, 'dev', "${DOCKER_IMAGE}")
      	}
      }   
    }
  }    
}