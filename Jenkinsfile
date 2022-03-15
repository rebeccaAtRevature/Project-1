pipeline {
	agent { docker { image 'maven:3.3.3' } }
	steps {
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