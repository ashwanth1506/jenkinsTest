pipeline {

    agent {
        label 'windows'
    }

    parameters {
        choice(
            name: 'ENVIRONMENT',
            choices: ['dev', 'test', 'prod'],
            description: 'Select the deployment environment'
        )
    }

    environment {
        APP_NAME = 'maven-jenkins-demo'
        VERSION = '1.0'

        // Secret Text credential
        MY_SECRET = credentials('my-secret')

        // Username + Password credential
        GIT_CREDENTIALS = credentials('c69a7bc2-a195-4b27-b4b7-4f044dc62ab2')
    }

    tools {
        maven 'Maven-3.9'
    }

    stages {

        stage('Checkout') {
            steps {
                git(
                    branch: 'main',
                    url: 'https://github.com/ashwanth1506/jenkinsTest.git'
                )
            }
        }

        stage('Check Agent') {
            steps {
                bat '''
                    echo ===== AGENT INFORMATION =====
                    echo Agent:
                    hostname

                    echo Workspace:
                    echo %WORKSPACE%

                    echo Java:
                    java -version

                    echo Maven:
                    mvn -version
                '''
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Tests') {
            parallel {

                stage('Unit Tests') {
                    steps {
                        bat 'mvn test'
                    }
                }

                stage('Java Check') {
                    steps {
                        bat 'java -version'
                    }
                }
            }
        }

        stage('Environment Info') {
            steps {
                bat '''
                    echo ===== ENVIRONMENT =====
                    echo Application: %APP_NAME%
                    echo Version: %VERSION%
                    echo Environment: %ENVIRONMENT%
                    echo Job: %JOB_NAME%
                    echo Build Number: %BUILD_NUMBER%
                    echo Workspace: %WORKSPACE%
                '''
            }
        }

        stage('Credential Test') {
            steps {
                bat '''
                    echo Username: %GIT_CREDENTIALS_USR%
                    echo Password: %GIT_CREDENTIALS_PSW%
                    echo Secret: %MY_SECRET%
                '''
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }

        stage('Production Approval') {
            when {
                expression {
                    params.ENVIRONMENT == 'prod'
                }
            }

            steps {
                input(
                    message: 'Deploy to production?',
                    ok: 'Approve'
                )
            }
        }

        stage('Deploy') {
            when {
                expression {
                    params.ENVIRONMENT == 'prod'
                }
            }

            steps {
                bat '''
                    echo =========================
                    echo Deploying application
                    echo Environment: %ENVIRONMENT%
                    echo Application: %APP_NAME%
                    echo =========================
                '''
            }
        }
    }

    post {

        success {
            echo 'Pipeline completed successfully!'

            archiveArtifacts artifacts: 'target/*.jar'
        }

        failure {
            echo 'Pipeline failed!  '
        }

        always {
            echo 'Pipeline has finished. '
        }
    }
}