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
      emailext(
        to: 'siddeshwar2004@gmail.com',
        subject: "✅ SUCCESS: Jenkins Build #${BUILD_NUMBER} for ${JOB_NAME}",
        body: """
        <h3>Build Success 🎉</h3>
        <p>The Jenkins build <b>#${BUILD_NUMBER}</b> for job <b>${JOB_NAME}</b> completed successfully.</p>
        <ul>
          <li><b>Docker Image:</b> ${IMAGE}:${TAG}</li>
          <li><b>Branch:</b> ${GIT_BRANCH}</li>
          <li><b>Build URL:</b> <a href="${BUILD_URL}">${BUILD_URL}</a></li>
        </ul>
        """
      )
    }

    failure {
      emailext(
        to: 'siddeshwar2004@gmail.com',
        subject: "❌ FAILURE: Jenkins Build #${BUILD_NUMBER} for ${JOB_NAME}",
        body: """
        <h3>Build Failed ❗</h3>
        <p>The Jenkins build <b>#${BUILD_NUMBER}</b> for job <b>${JOB_NAME}</b> has failed.</p>
        <p>Check the build logs here: <a href="${BUILD_URL}">${BUILD_URL}</a></p>
        """
      )
    }
  }
}
