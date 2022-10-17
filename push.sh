#!/bin/bash

function printLine()
{
echo "|---------------------------"
}

printLine
echo "| Pushing all services to hub.docker.com"
printLine

cd auth-service && make hub && cd ..
cd catalog-service && make hub && cd ..
cd order-service && make hub && cd ..
cd payment-service && make hub && cd ..
cd shipping-service && make hub && cd ..
cd stock-service && make hub && cd ..
cd credit-card-service && make hub && cd ..
cd bank-service && make hub && cd ..

printLine
echo "| Done!"
printLine
