pipeline {
  environment {
    registry = "wills89pinilla/appgate-calculator"
    registryCredential = 'dockerhub_will'
  }

  agent any

  stages {
    stage('Checkout Source') {
      steps {
        git branch: 'main', credentialsId: 'github_will', url: 'https://github.com/will89pinilla/appgate-calculator.git'
      }
    }
  }
}