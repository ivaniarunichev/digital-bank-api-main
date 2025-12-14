pipeline {
  agent any

  tools {
      jdk 'jdk-17'
      maven 'Maven3.9.11'
  }

  environment {
    VERSION = '1.0.0'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build') {
      steps {
        sh 'mvn -B clean compile'
      }
    }

    stage('Test') {
      steps {
        sh 'mvn -B test'
      }
    }

    stage('Package') {
      steps {
        sh 'mvn -B package'
      }
    }

    stage('Move jar') {
      steps {
        sh 'echo "Eliminando directorio versiones...."'
        sh 'if [ -d "versiones" ]; then rm -rf versiones; fi'
      }
      post {
        success {
          sh 'echo "Se crea el directorio versiones con la última versión de la api"'
          sh 'mkdir -p versiones'
          sh "cp target/<ARTIFACT_ID>-${VERSION}.jar versiones/"
        }
      }
    }
  }
}
