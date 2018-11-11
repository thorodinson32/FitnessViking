cf login -a https://api.run.pivotal.io -u ${CLOUD_FOUNDARY_LOGIN_USR} -p ${CLOUD_FOUNDARY_LOGIN_PSW}
if cf apps | grep -q FitnessViking
	then
		echo "Deleting existing FitnessViking Service"
		cf delete FitnessViking -r -f
fi
		
echo "Deploying FitnessViking Service"
cf push FitnessViking -p /build/libs/fitness-viking-1.0.0.jar