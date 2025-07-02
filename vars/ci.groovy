def call() {
  if (!env.sonar_extra_opts) {
    env.sonar_extra_opts = ""
  }
  pipeline {
    agent any

    stages {

      stage('Compile/Build') {
        when { not { branch 'master' } }
      }
      steps {
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
          echo "ok"
        }
      }
    }

//    post {
//      failure {
//        mail body: "<h3>${component} - Pipeline failed \n ${BUILD_URL}</h3>", from: 'skiliyas300@gmail.com', mimeType: 'text/html', subject: "${component} - Pipeline failed", to: 'skiliyas300@gmail.com'
//      }
//    }
  }
}