#!/usr/bin/env bash

cf cf login -a https://api.local.pcfdev.io --skip-ssl-validation -u ${USERNAME} -p ${PASSWORD}
if cf apps | grep -q fitnessviking
	then
		echo "Deleting existing fitnessviking Service"
		cf delete fitnessviking -r -f
fi
		
echo "Deploying fitnessviking Service"
cf push