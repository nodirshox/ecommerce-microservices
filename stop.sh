#!/bin/bash

function printLine()
{
echo "|---------------------------"
}

printLine
echo "| 0. Stopping the all services"
echo "|"
echo "| I will clean all the trash :-)"

printLine
echo "| 1. Stopping services"
printLine
minikube kubectl -- delete -f kubernetes/services/auth-service.yaml
minikube kubectl -- delete -f kubernetes/services/catalog-service.yaml
minikube kubectl -- delete -f kubernetes/services/order-service.yaml
minikube kubectl -- delete -f kubernetes/services/payment-service.yaml
minikube kubectl -- delete -f kubernetes/services/shipping-service.yaml
minikube kubectl -- delete -f kubernetes/services/stock-service.yaml
minikube kubectl -- delete -f kubernetes/services/card-service.yaml
minikube kubectl -- delete -f kubernetes/services/bank-service.yaml
minikube kubectl -- delete -f kubernetes/services/postgres-service.yaml
minikube kubectl -- delete -f kubernetes/ingress.yaml

printLine
echo "| 2. Deleting config"
printLine
minikube kubectl -- delete -f kubernetes/configmap.yaml

printLine
echo "| 3. Deleting secret"
printLine
minikube kubectl -- delete -f kubernetes/postgres-secret.yaml

printLine
echo "| You are free now. Go to make Barbeque and enjoy the rest of day..."
echo "| "
echo "| Enjoy every moment of your life."
printLine