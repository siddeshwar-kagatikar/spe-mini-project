pipeline {
  agent any

  // Trigger build automatically when code is pushed to GitHub
  triggers {
    githubPush()
  }

  // Use Jenkins Maven tool installation
  tools {
    maven '3.8.7'
  }

  environment {
    IMAGE = "siddeshwarsk/scientific-calculator"
    TAG = "latest"  // or "1.0.${BUILD_NUMBER}"
  }

  stages {

    stage('Checkout') {
      steps {
        // Pull the latest code from GitHub
        git branch: 'main', url: 'https://github.com/siddeshwar-kagatikar/spe-mini-project.git'
      }
    }

    stage('Build & Test') {
      steps {
        // Build and run tests using Maven
        sh 'mvn -B clean test package'
      }
    }

    stage('Build Docker Image') {
      steps {
        script {
          // Build Docker image and capture its ID
          def dockerImage = docker.build("${IMAGE}:${TAG}", ".")
          env.DOCKER_IMAGE_ID = dockerImage.id
        }
      }
    }

    stage('Push Docker Image to Docker Hub') {
      steps {
        script {
          // Authenticate and push image to Docker Hub
          docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-creds') {
            docker.image(env.IMAGE).push(env.TAG)
          }
        }
      }
    }

    stage('Clean Docker Resources') {
      steps {
        // Clean up unused containers and images
        sh 'docker container prune -f'
        sh 'docker image prune -f'
      }
    }

    stage('Deploy with Ansible') {
      steps {
        // Deploy the Docker image using Ansible
        sh '''
          sudo ansible-playbook -i ansible/inventories/inventory.ini ansible/playbook.yml
        '''
      }
    }
  }

  // Post actions (notifications)
  post {
    success {
      mail to: 'siddeshwar2004@gmail.com',
           subject: "SUCCESS: Jenkins Build #${BUILD_NUMBER} for ${JOB_NAME}",
           body: """\
The Jenkins pipeline ${JOB_NAME} build #${BUILD_NUMBER} completed successfully.

Docker Image: ${IMAGE}:${TAG}
Build URL: ${BUILD_URL}
"""
    }

    failure {
      mail to: 'siddeshwar2004@gmail.com',
           subject: "FAILURE: Jenkins Build #${BUILD_NUMBER} for ${JOB_NAME}",
           body: """\
The Jenkins pipeline ${JOB_NAME} build #${BUILD_NUMBER} has failed.

Please check the logs for more details:
${BUILD_URL}
"""
    }
  }
}
