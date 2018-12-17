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
			steps {
				withCredentials([usernamePassword(credentialsId: 'CF_LOGIN', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
					unstash 'buildfiles'
					sh './deploy.sh'
				}
			}
		}
	}
}