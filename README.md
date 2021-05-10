# Appgate Calculator

This repo will show a demo of the Appgate test, to solve this problem it was used 
H2 database to manage the store and the stack of the operation to do for each env.


<!-- GETTING STARTED -->
## Getting Started

It has a Dockerfile to deploy the app easily.
Total time demanded: 10 hours

### Prerequisites


* It requires Docker installed


### Installation

### 1. To review the code, please clone from repo:
   ```sh
   git clone https://github.com/will89pinilla/appgate-calculator.git   
   ```

### 2. To execute the test:
   ```sh
   ./gradlew build test   
   ```
### 3. Run container locally
   ```sh
    docker run -p 8080:8080 wills89pinilla/appgate-calculator:latest
   ```
### 4. Consume services:
 Create Environment:

   ```sh
   curl http://localhost:8080/api/calculator/new
   ```

Add operand:

   ```sh
   curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"id" : 1, "operand" : 5}' \
  http://localhost:8080/api/calculator/add
   ```

Execute operation:

   ```sh
   curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"id" : 1, "operation" : "ADD"}' \
  http://localhost:8080/api/calculator/operate
   ```

### 5. Architecture
![Alt text](architecture.png?raw=true "Title")

### 6. Pipeline strategy
![Alt text](cicd-strategy.png?raw=true "Title")
