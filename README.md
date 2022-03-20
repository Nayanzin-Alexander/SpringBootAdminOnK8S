# Spring Boot Admin on K8S example
## Used materials
* https://codecentric.github.io/spring-boot-admin/current/
* https://kubernetes.io/docs/reference/access-authn-authz/rbac/
* https://nieldw.medium.com/rbac-and-spring-cloud-kubernetes-847dd0f245e4
* https://docs.spring.io/spring-cloud-kubernetes/docs/current/reference/html/
* https://capgemini.github.io/engineering/externalising-spring-boot-config-with-kubernetes/
* https://piotrminkowski.com/2020/02/18/spring-boot-admin-on-kubernetes/

## Prerequisites
* jdk-17
* minikube
* maven

## Deploy
```bash
# Use Minikube Docker daemon
eval $(minikube docker-env)

# Build & deploy Spring Boot App
mvn clean package -f SBAClient-1
docker build -t sba-client-1 SBAClient-1
kubectl apply -f SBAClient-1/k8s/deployment.yaml

# Check deployment
kubectl port-forward service/client-1 7071:8080 -n n-1 # run in separate terminal
curl localhost:7071 # Expecting 'Hello World from client-1-config.yml'

# Build & deploy Spring Boot Admin Server
mvn clean package -f SBAServer
docker build -t sba-admin SBAServer
kubectl apply -f SBAServer/k8s/deployment.yaml
```

## Test
```bash
# Check Spring Boot Admin Server
kubectl port-forward service/admin 7075:8080 -n n-1 # run in separate terminal
open http://localhost:7075
# Expecting 2 applications and 4 instances to seen in Spring Boot Admin
```

## Delete
```bash
kubectl delete -f SBAServer/k8s/deployment.yaml
kubectl delete -f SBAClient-1/k8s/deployment.yaml
# Manually remove docker images
eval $(minikube docker-env) # Use Minikube Docker daemon
docker images
docker rmi -f <image hash>
```
