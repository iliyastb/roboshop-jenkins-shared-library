def call() {
  pipeline {
    agent any

    parameters {
      string(name: 'ENV', defaultValue: '', description: 'which Environment?')
      string(name: 'ACTION', defaultValue: '', description: 'which Action?')
    }

    options {
      ansiColor('xterm')
    }

    stages {

      stage('Init') {
        steps {
          sh "terraform init -backend-config=env-${params.ENV}/state.tfvars"
        }
      }

      stage('Apply') {
        steps {
          sh "terraform ${params.ACTION} -auto-approve -var-file=env-${params.ENV}/main.tfvars"
        }
      }

    }

    post {
      always {
        cleanWs()
      }
    }

  }
}