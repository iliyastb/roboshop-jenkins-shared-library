def call() {
  if (!env.sonar_extra_opts) {
    env.sonar_extra_opts = ""
  }
  node('workstation') {

    try {
      stage('Compile/Build') {
        sh 'env'
        common.compile()
      }

      stage('Test Cases') {
        common.testcases()
      }

      stage('Code Quality') {
        common.codequality()
      }

    } catch (e) {
      echo "ok"
//      mail body: "<h3>${component} - Pipeline failed \n ${BUILD_URL}</h3>", from: 'skiliyas300@gmail.com', mimeType: 'text/html', subject: "${component} - Pipeline failed", to: 'skiliyas300@gmail.com'
    }

  }
}