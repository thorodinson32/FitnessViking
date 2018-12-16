pipeline {
	agent any
	
	stages {
		stage("Build") {
			steps {
				echo 'building FitnessViking'
				sh './gradlew clean build'
				stash includes: 'build/libs/*.jar', name: 'buildfiles'
			}
		}
		stage("Deploy"){
			agent {
                docker { image 'governmentpaas/cf-cli' }
            }
            environment {
            	CLOUD_FOUNDARY_LOGIN_USR = 'nothingfights@gmail.com'
        		CLOUD_FOUNDARY_LOGIN_PSW = credentials('CF_PASSWORD')
    		}
			steps {
				unstash 'buildfiles'
				sh './deploy.sh'
			}
		}
	}
}