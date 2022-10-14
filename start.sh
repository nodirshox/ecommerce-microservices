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
echo "| 1. Applying config"
printLine
minikube kubectl -- apply -f kubernetes/configmap.yaml

printLine
echo "| 2. Applying secret"
printLine
minikube kubectl -- apply -f kubernetes/postgres-secret.yaml

printLine
echo "| 3. Starting Microservices"
printLine
minikube kubectl -- apply -f kubernetes/services/postgres-service.yaml
minikube kubectl -- apply -f kubernetes/services/auth-service.yaml
minikube kubectl -- apply -f kubernetes/services/catalog-service.yaml
minikube kubectl -- apply -f kubernetes/services/order-service.yaml
minikube kubectl -- apply -f kubernetes/services/payment-service.yaml
minikube kubectl -- apply -f kubernetes/services/shipping-service.yaml
minikube kubectl -- apply -f kubernetes/services/stock-service.yaml
minikube kubectl -- apply -f kubernetes/services/card-service.yaml
minikube kubectl -- apply -f kubernetes/services/bank-service.yaml

printLine
echo "| Wait 20-30 seconds, until IP is assigned. Now you can check..."
printLine
