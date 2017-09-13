#!/usr/bin/env groovy
@Library('deployment-api') _

pipeline {
 agent none
 environment {
  DEPLOYMENT_API_APP_NAME = 'CoolService'
  DEPLOYMENT_API_URL = 'http://deployment-api-service.marathon.l4lb.thisdcos.directory:8080'
  DOCKER_REGISTRY = 'registry.marathon.l4lb.thisdcos.directory:5000'
  DOCKER_IMAGE = 'micahnoland/cool-service'
 }
 options {
  timeout(time: 1, unit: 'HOURS')
 }
 stages {
  stage('Setup') {
   agent any
   steps {
    script {
     checkout scm
    }
    stash name: 'work', useDefaultExcludes: false
   }
  }
  stage('Maven Test') {
   agent any
   steps {
    deleteDir()
    unstash 'work'
    script {
     try {
      sh('./mvnw clean test')
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
  stage('Maven Install') {
   agent any
   steps {
    deleteDir()
    unstash 'work'
    sh './mvnw clean install -DskipTests'
    stash name: 'work', useDefaultExcludes: false
   }
  }
  stage('Docker') {
   agent any
   steps {
    deleteDir()
    unstash 'work'
    script {
     docker.withRegistry("http://${env.DOCKER_REGISTRY}") {
      docker.build("${env.DOCKER_IMAGE}:${env.BUILD_ID}").push()
     }
    }
    stash name: 'work', useDefaultExcludes: false
   }
  }
  stage('Dev Deployment') {
   agent any
   steps {
    script {
     deploymentApi(env.DEPLOYMENT_API_APP_NAME, 'dev', "${env.DOCKER_REGISTRY}/${env.DOCKER_IMAGE}:${env.BUILD_ID}")
    }
   }
  }
  stage('Deploy to Prod?') {
   agent none
   when {
    expression {
     boolean deploy = false
     try {
      // Should have a timeout here if one is not defined in the pipeline { options } section
      input message: 'Deploy to Production?'
      deploy = true
     } catch (final ignore) {
      deploy = false
     }
     return deploy
    }
   }
   steps {
    script {
     env.DEPLOY_TO_PROD = 'true'
    }
   }
  }
  stage('Prod Deployment') {
   agent any
   when {
    expression {
     if (env.DEPLOY_TO_PROD == 'true') {
      return true
     }
    }
   }
   steps {
    script {
     deploymentApi(env.DEPLOYMENT_API_APP_NAME, 'prod', "${env.DOCKER_REGISTRY}/${env.DOCKER_IMAGE}:${env.BUILD_ID}")
    }
   }
  }
 }
}