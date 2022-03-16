pipeline {
	agent any
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