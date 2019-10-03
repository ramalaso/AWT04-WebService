pipeline {
    agent any
    environment {
        USERNAME = '4514812'
        PASSWORD = 'r4514812L'
        NAME = 'appweb'
    }
    stages {
        stage('Build') {
            agent {
                docker { image 'openjdk:11.0.4' }
            }
            steps{
                sh 'chmod +x gradlew'
                sh './gradlew build'
            }
            post{
                always {
                    junit 'build/test-results/test/*.xml'
                    publishHTML([allowMissing: false,
                                alwaysLinkToLastBuild: false,
                                keepAll: false,
                                reportDir: 'build/reports/tests/test',
                                reportFiles: 'index.html',
                                reportName: 'Unit TestHTML Report',
                                reportTitles: ''])
                    emailext body: 'Test',
                    subject: '${JOB_NAME}-Build# ${BUILD_NUMBER} ${currentBuild.result}',
                    to: 'raul.laredo@fundacion-jala.org'
                }
                failure {
                    emailext body: 'Test',
                    subject: 'Failure',
                    to: 'raul.laredo@fundacion-jala.org'
                }
                success {
                  archiveArtifacts 'build/libs/*.jar'
                }
            }
        }  
        stage('Build docker'){
            when {branch 'develop'}
            steps {
                // copyArtifacts filter: '**/*/*.jar',
                // fingerprintArtifacts: true,
                // projectName: '${JOB_NAME}',
                // selector: lastWithArtifacts()
                sh 'docker build -t ${USERNAME}/${NAME}:v1.${BUILD_NUMBER} .'
                sh 'docker login -u ${USERNAME} -p ${PASSWORD}'
                sh 'docker push ${USERNAME}/${NAME}:v1.${BUILD_NUMBER}'
                sh 'echo "develop"'
            }
        }
        stage('SonarQube') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew sonarqube \
                -Dsonar.projectKey=JenkinsFile \
                -Dsonar.organization=ramalaso \
                -Dsonar.host.url=https://sonarcloud.io \
                -Dsonar.login=02bae6586821f9257fc57a4a601c3fccee980787'
            }
        }
        stage('Deployment'){
            steps {
                sh 'printenv'
                sh 'echo $GIT_BRANCH'
                sh 'echo $GIT_COMMIT'
                echo 'Install non-dev composer packages and test a java app'
                sh 'ls -la'
                sh 'docker-compose -f docker-compose.yaml up --build'
                echo 'Building the docker images with the current git commit'
            }
        }
        stage('Test1'){
            steps {
                echo 'test is running'
            }
        }
        stage('Promote'){
            steps {
                echo 'promote is running'
            }
        }
        stage('Test2'){
            steps {
                echo 'test is running'
            }
        }
    }
        post {
            always {
                cleanWs deleteDirs: true, notFailBuild: true
                emailext attachLog: true, compressLog: true, body: 'Process of ${BRANCH_NAME} is starting. Log with the info is attached ',
                        subject: 'Build Notification: ${JOB_NAME}-Build# ${BUILD_NUMBER} ${currentBuild.result}',
                        to: 'raul.laredo@fundacion-jala.org'
            }
            failure {
                echo 'Execute when it fails'
            }
            success {
                echo 'Execute when it success'
            }
        }
}