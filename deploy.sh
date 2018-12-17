cf login -a https://api.run.pivotal.io -u ${USERNAME} -p ${PASSWORD}
if cf apps | grep -q fitnessviking
	then
		echo "Deleting existing fitnessviking Service"
		cf delete fitnessviking -r -f
fi
		
echo "Deploying fitnessviking Service"
cf push