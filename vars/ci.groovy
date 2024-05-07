def call() {
  pipeline {
    agent any

    stages {

      stage('Compile/Build') {
        steps {
          echo 'compile/build'
        }
      }

      stage('Test Cases') {
        steps {
          echo 'test cases'
        }
      }
    }
  }
}