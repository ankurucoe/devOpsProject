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
            mail (
            body: """Check console output at "${env.BUILD_URL}" for ${env.JOB_NAME}""",
            body: "Ankur_SDET"
            subject: "Job '${env.JOB_NAME} ${env.BUILD_NUMBER}'",
            to: "speektoankur@gmail.com",
            )
        }
    }
}