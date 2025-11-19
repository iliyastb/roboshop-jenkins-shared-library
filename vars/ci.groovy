def call() {
  if (!env.sonar_extra_opts) {
    env.sonar_extra_opts = ""
  }

  if (env.TAG_NAME ==~ ".*") {
    env.GTAG = "true"
  }

  node('workstation') {

    try {

      stage('Check out Code') {
        cleanWs()
        git branch: 'main', url: "https://github.com/iliyastb/${component}"
        credentialsId: 'github-ssh-key'
      }

      if (env.BRANCH_NAME != "main") {
        stage('Compile/Build') {
          common.compile()
        }
      }

      if (env.GTAG != "true" && env.BRANCH_NAME != "main") {
        stage('Test Cases') {
          common.testcases()
        }
      }

      if (BRANCH_NAME ==~ "PR-.*"){
        stage('Code Quality') {
          common.codequality()
        }
      }

      if (env.GTAG == "true") {
        stage('Package') {
          common.prepareArtifacts()
        }
        stage('Artifact Upload') {
          common.artifactUpload()
        }
      }

    } catch (e) {
      echo "ok"
//      mail body: "<h3>${component} - Pipeline failed \n ${BUILD_URL}</h3>", from: 'skiliyas300@gmail.com', mimeType: 'text/html', subject: "${component} - Pipeline failed", to: 'skiliyas300@gmail.com'
    }

  }
}