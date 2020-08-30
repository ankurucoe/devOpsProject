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
            mail to: 'speektoankur@gmail.com',
            subject: "Status of Automation Project: ${currentBuild.fullDisplayName}",
            body: "$(env.BUILD_URL) has result ${currentBuild.result}"
        }
    }
}