def call() {

  node('workstation') {

    stage('Init') {
      sh 'terraform init -backend-config=env-prod/state.tfvars'
    }

    stage('Apply') {
      sh 'terraform apply -auto-approve -var-file=env-prod/main.tfvars'
    }

  }
}

//def call() {
//  pipeline {
//    agent any
//
//    parameters {
//      string(name: 'ENV', defaultValue: '', description: 'which environment?')
//    }
//
//    options {
//      ansiColor('xterm')
//    }
//
//    stages {
//
//      stage('Init') {
//        steps {
//          sh 'terraform init -backend-config=env-${ENV}/state.tfvars'
//        }
//      }
//
//      stage('Apply') {
//        steps {
//          sh 'terraform apply -auto-approve -var-file=env-${ENV}/main.tfvars'
////          sh 'echo'
//        }
//      }
//
//    }
//
//    post {
//      always {
//        cleasWs()
//      }
//    }
//
//  }
//}