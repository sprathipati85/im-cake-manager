node {
    checkout scm
    env.PATH = "${tool 'Maven3'}/bin:${env.PATH}"

    stages {
        stage('Compile & Run tests') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Package') {
             steps {
                   sh 'mvn -DskipTests clean package'
             }

        }
    }
}