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

      stage('Compile/Build') {
        common.testcases()
      }

      stage('Compile/Build') {
        common.codequality()
      }

    } catch (e) {
      echo "ok"
//      mail body: "<h3>${component} - Pipeline failed \n ${BUILD_URL}</h3>", from: 'skiliyas300@gmail.com', mimeType: 'text/html', subject: "${component} - Pipeline failed", to: 'skiliyas300@gmail.com'
    }

  }
}