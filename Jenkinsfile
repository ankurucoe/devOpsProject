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
        stage ('Deployment Stage'){
            steps{
                withMaven(maven: 'mvn_macOS'){
                     sh 'mvn deploy'
                }
            }
        }
    }
}