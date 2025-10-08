pipeline {
  agent any
  environment {
    IMAGE = "siddeshwarsk/scientific-calculator"
    TAG = "latest"  // or "1.0.${BUILD_NUMBER}"
  }

  stages {
    stage('Build & Test') {
      steps {
        sh 'mvn -B clean test package'
      }
    }

    stage('Docker Build') {
      steps {
        sh "docker build -t ${IMAGE}:${TAG} ."
      }
    }

    stage('Docker Push') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds',
                                          usernameVariable: 'DH_USER',
                                          passwordVariable: 'DH_PASS')]) {
          sh 'echo $DH_PASS | docker login -u $DH_USER --password-stdin'
          sh "docker push ${IMAGE}:${TAG}"
        }
      }
    }

    stage('Deploy with Ansible') {
      steps {
        sh """
          sudo ansible-playbook -i ansible/inventories/inventory.ini ansible/playbook.yml
        """
      }
    }
  }

  post {
    success {
      mail to: 'siddeshwar2004@gmail.com',
           subject: "SUCCESS: Jenkins Build #${BUILD_NUMBER} for ${JOB_NAME}",
           body: """\
Build succeeded!

Job: ${JOB_NAME}
Build Number: ${BUILD_NUMBER}
Docker Image: ${IMAGE}:${TAG}
Build URL: ${BUILD_URL}

All stages completed successfully.
"""
    }

    failure {
      mail to: 'siddeshwar2004@gmail.com',
           subject: "FAILURE: Jenkins Build #${BUILD_NUMBER} for ${JOB_NAME}",
           body: """\
Build failed

Job: ${JOB_NAME}
Build Number: ${BUILD_NUMBER}
Check the build logs here: ${BUILD_URL}

Please investigate the issue.
"""
    }
  }
}
