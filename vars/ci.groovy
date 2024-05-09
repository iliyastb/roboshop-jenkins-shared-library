def call() {
  if (!env.sonar_extra_opts) {
    env.sonar_extra_opts=""
  }
  pipeline {
    agent any

    stages {

      stage('Compile/Build') {
        steps {
          sh 'env'
          script {
            common.compile()
          }
        }
      }

      stage('Test Cases') {
        steps {
          script {
            common.testcases()
          }
        }
      }

      stage('Code Quality') {
        steps {
          script {
            common.codequality()
          }
        }
      }
    }

    post {
      failure {
        mail body: "${component} - Pipeline failed \n ${BUILD_URL}", from: 'skiliyas300@gmail.com', subject: "${component} - Pipeline failed", to: 'skiliyas300@gmail.com'
      }
    }
  }
}