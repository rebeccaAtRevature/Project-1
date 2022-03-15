pipeline {
	agent { docker { image 'maven:3.3.3' } }
	stages {
		stage ('Compile Stage') {
			steps {
				sh 'mvn clean compile'
			}
		}
		
		stage ('Install Stage') {
			steps {
				sh 'mvn install'
			}
		}
	}
}