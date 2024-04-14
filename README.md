
---

# ConsumingRest

## Overview
This repository contains the source code for a client application designed to interact with the Quoters RESTful service. This client application makes RESTful API calls to retrieve quotes from the Quoters service.

## Prerequisites
Before running this application, you will need:
- Docker installed on your machine
- Kubernetes cluster or minikube
- kubectl command-line tool configured to interact with your Kubernetes cluster

## Setup Instructions

### Step 1: Clone the Repository
Clone this repository to your local machine:
```bash
git clone https://github.com/yourusername/ConsumingRest.git
cd ConsumingRest
```

### Step 2: Start Quoters Service
You need to have the Quoters RESTful service running in your Kubernetes cluster to make RESTful calls. Run the following commands to deploy Quoters service:
```bash
kubectl run quoters --image=javajon/quoters:1.0.0 --port=8080
kubectl expose pod quoters --name=quoters --port=8080
```

### Step 3: Forward Quoters Service
To access the Quoters service from your local machine, forward the port of the Quoters service to your localhost:
```bash
kubectl port-forward service/quoters 8080:8080
```
Leave this command running as long as you are using the service.

### Step 4: Build and Run the Client Application

#### Build the Docker Image
Ensure Docker is running on your machine. Build the Docker image using:
```bash
docker image build -t yourusername/consumingrest:latest .
```

#### Push the Image to Docker Hub
```bash
docker push yourusername/consumingrest:latest
```

#### Deploy the Client Application on Kubernetes
Set the `quoters_base_url` to the internal service URL of the Quoters service:
```bash
kubectl run consumingrest --image=yourusername/consumingrest:latest --env="quoters_base_url=http://quoters:8080"
```

### Step 5: Verify the Application
Check the logs of the pod to ensure it is making the RESTful calls successfully:
```bash
kubectl logs consumingrest
```
Look for logs that show the output of the four RESTful calls, confirming that the application is functioning as expected.

## Important Files
- `Dockerfile`: Contains all the Docker configuration required to containerize the application.
- `.gitignore`: Specifies intentionally untracked files to ignore.
- `src/main/java/ConsumingRestApplication.java`: Source code for the Spring Boot application.

## Tips and Techniques
Ensure the `quoters_base_url` is correctly configured in your application's properties. This URL is critical for the application to correctly connect to the Quoters service.

---

### Customization Tips
- Replace `yourusername` with your actual Docker Hub username.
- Ensure that any URL or credentials specific to your environment are correctly set up.
- Modify the application properties and Kubernetes configurations as needed to suit your deployment environment.

This README is designed to guide users through setting up and verifying the application in a typical Kubernetes and Docker environment, providing a seamless workflow from initial clone to verification.