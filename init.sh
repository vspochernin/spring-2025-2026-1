#!/usr/bin/env bash
set -euo pipefail

# 1) Eureka Server
mkdir -p eureka-server
curl -fsS https://start.spring.io/starter.tgz \
  -d type=maven-project \
  -d language=java \
  -d bootVersion=3.5.6 \
  -d groupId=ru.vspochernin \
  -d artifactId=eureka-server \
  -d name=eureka-server \
  -d description="Service registry of the Hotel Booking System" \
  -d javaVersion=21 \
  -d packaging=jar \
  -d dependencies=cloud-eureka-server,actuator,lombok \
  | tar -xz -C eureka-server --strip-components=1

# 2) Hotel Management Service
mkdir -p hotel-service
curl -fsS https://start.spring.io/starter.tgz \
  -d type=maven-project \
  -d language=java \
  -d bootVersion=3.5.6 \
  -d groupId=ru.vspochernin \
  -d artifactId=hotel-service \
  -d name=hotel-service \
  -d description="Hotel and Room management service of the Hotel Booking System" \
  -d javaVersion=21 \
  -d packaging=jar \
  -d dependencies=web,data-jpa,h2,validation,security,cloud-eureka,actuator,lombok \
  | tar -xz -C hotel-service --strip-components=1

# 3) Booking Service (fix: cloud-openfeign -> cloud-feign)
mkdir -p booking-service
curl -fsS https://start.spring.io/starter.tgz \
  -d type=maven-project \
  -d language=java \
  -d bootVersion=3.5.6 \
  -d groupId=ru.vspochernin \
  -d artifactId=booking-service \
  -d name=booking-service \
  -d description="Booking and user management service of the Hotel Booking System" \
  -d javaVersion=21 \
  -d packaging=jar \
  -d dependencies=web,data-jpa,h2,validation,security,oauth2-resource-server,cloud-eureka,cloud-feign,actuator,lombok \
  | tar -xz -C booking-service --strip-components=1

# 4) API Gateway
mkdir -p api-gateway
curl -fsS https://start.spring.io/starter.tgz \
  -d type=maven-project \
  -d language=java \
  -d bootVersion=3.5.6 \
  -d groupId=ru.vspochernin \
  -d artifactId=api-gateway \
  -d name=api-gateway \
  -d description="API Gateway for routing and JWT propagation in the Hotel Booking System" \
  -d javaVersion=21 \
  -d packaging=jar \
  -d dependencies=cloud-gateway,security,cloud-eureka,actuator,lombok \
  | tar -xz -C api-gateway --strip-components=1

echo "All four services initialized in separate folders"
