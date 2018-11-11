pipeline {
	agent any
	
	stages {
		stage("Build") {
			steps {
				echo 'building FitnessViking'
				sh './gradlew clean build'
				archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
			}
		}
		
		stage("Deploy") {
			agent {
        		docker { 
        			image 'govau/cf-cli' 
        		}
    		}
			environment {
				CLOUD_FOUNDARY_LOGIN = credentials('CLOUD_FOUNDARY_LOGIN')
			}
			steps {
				echo 'Deploying FitnessViking'
				sh './deploy.sh'
			}
		}
	}
}