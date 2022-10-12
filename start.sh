#!/bin/bash

function printLine()
{
echo "|---------------------------"
}

printLine
echo "| 0. Deploying has started"
echo "|"
echo "| I will do my best :-). Take a cup of Tea, it will take some time (1~2 minutes)"

printLine
echo "| 1. Checking version of Minikube"
printLine
minikube version

printLine
echo "| 2. Starting Minikube"
printLine
minikube start

printLine
echo "| 3. Applying config"
printLine
minikube kubectl -- apply -f kubernetes/configmap.yaml

printLine
echo "| 4. Applying secret"
minikube kubectl -- apply -f kubernetes/postgres-secret.yaml

printLine
echo "| 5. Starting Microservices"
minikube kubectl -- apply -f kubernetes/services/auth-service.yaml
minikube kubectl -- apply -f kubernetes/services/catalog-service.yaml

printLine
echo "| 6. Starting Services"
minikube service ecommerce-auth-service --url
minikube service ecommerce-auth-service --url

printLine
echo "| Wait 20-30 seconds, until IP is assigned. Now you can check..."
printLine