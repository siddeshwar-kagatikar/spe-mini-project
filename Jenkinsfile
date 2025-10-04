pipeline {
  agent any
  environment {
    IMAGE = "siddeshwarsk/scientific-calculator"
    TAG = "latest"         // or "1.0.${BUILD_NUMBER}"
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
        // run ansible-playbook; pass docker_image via extra-vars
        sh """
          sudo ansible-playbook -i ansible/inventories/inventory.ini ansible/playbook.yml
        """
      }
    }
  }
}
