pipeline {
	agent any
	stages {
		stage ('Clone Code') {
			steps {
				// get some code from a GitHub repository
				git (branch: 'continuous-integration', url: 'https://github.com:rebeccaAtRevature/Project-1')
			}
		}
		
		stage ('Build Code') {
			steps {
				sh 'mvn clean package'
			}
		}
		
		stage ('Staging') {
			steps {
				sh 'docker-compose down'
				sh 'docker-compose up -d'
			}
		}
	}
}