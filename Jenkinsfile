pipeline {
  agent any

  tools {
    jdk 'jdk-17'
    maven 'Maven3.9.11'
  }

  environment {
    VERSION = '0.0.1-SNAPSHOT'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build') {
      steps {
        bat 'mvn -B clean compile'
      }
    }

    stage('Test') {
      steps {
        bat 'mvn -B test'
      }
    }

    stage('Package') {
      steps {
        bat 'mvn -B package'
      }
    }

    stage('Move jar') {
      steps {
        bat 'echo Eliminando directorio versiones....'
        bat 'if exist versiones rmdir /S /Q versiones'
      }
      post {
        success {
          bat 'echo Se crea el directorio versiones con la última versión de la api'
          bat 'mkdir versiones'
          bat "copy target\\digital-bank-api-${VERSION}.jar versiones\\"
        }
      }
    }
  }
}