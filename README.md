## eCommerce-microservices

### Team members
1. Javokhir Buvanazarov (614693)

2. Nodirbek Ergashev (614644)

### Instruction

1. Make sure you have installed **minikube**:
    ```
    minikube version
    ```
2. Deleting old minikube cluster (Don't worry, we will start new cluster in next step)
   ```
   minikube delete
   ```
3. Run minikube (if you see error on limitations of CPU and memory, just run **minikube start**)
   ```
   minikube start --cpus 4 --memory 6144
   ```

4. Copy command and paste in terminal (project folder). Wait 2-3 minutes until pulls Docker images. Make sure you have storing internet connection.
    ```
    bash start.sh
    ```
5. Copy command and paste in terminal. Wait until all pods Status should be Running. If you see Error status, just wait, it will restart. If all pods Running, then press **CTRL+C**
   ```
   minikube kubectl -- get pods --watch
   ```
6. Copy command and paste in terminal
   ```
   minikube addons enable ingress
   ```
7. Copy command and paste in terminal (If you got errors, wait, try again 2-3 times)
    ```
    minikube kubectl -- apply -f kubernetes/ingress.yaml
    ```
8. Copy command and paste in terminal. See local IP address for project
    ```
    minikube ip
    ```
9. Copy IP and use it BASE_URL for request in next steps

10. [POST] Checking Login user and save 'ACCESS_TOKEN' temporarily for future requests
   ```
   http://BASE_URL/auth/login 
   ```
   ```
   {
       "email": "user@mail.com",
       "password": "123"
   }
   ```
11. [GET] Check all products
   ```
   http://BASE_URL/catalog/products
   ```
12. [POST] Create order. Add header: 'Authorization': 'ACCESS_TOKEN'
   ```
   http://BASE_URL/order/orders
   ```
   ```
   {
       "userId": 1002,
       "address": {
           "state": "NY",
           "street": "Burlington 4th street",
           "zipcode": "52557"
       },
       "products": [
           {
               "productId": 1001,
               "quantity": 2
           }
       ],
       "paymentMethod": "CC"
   }
   ```
13. [POST] Make payment. Add header: 'Authorization': 'ACCESS_TOKEN'
   ```
   http://BASE_URL/payment/payments
   ```
   ```
   {
       "orderId": 1001,
       "amount": 1998,
       "paymentMethod": "CC"
   }
   ```
14. Stop and clear all services. Copy and paste in terminal.
   ```
   bash stop.sh
   ```
