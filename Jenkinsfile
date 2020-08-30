pipeline{
    agent any
    tools {
    maven 'mvn_macOS'
    }
    stages{
        stage ('Compile Stage'){
            steps{
                withMaven(maven: 'mvn_macOS'){
                    sh 'mvn clean compile'
                }
            }
        }
        stage ('Testing Stage'){
            steps{
                withMaven(maven: 'mvn_macOS'){
                    sh 'mvn test'
                }
            }
        }
    }
    post{
        always {
            emailext body: 'A Test EMail',
            recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']],
            subject: 'Test Job Status'
        }
    }
}