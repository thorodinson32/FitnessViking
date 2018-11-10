pipeline {
	agent any
	
	stages {
		stage("Build") {
			steps {
				echo 'building FitnessViking'
				sh './gradlew clean build'
			}
		}
	}
}