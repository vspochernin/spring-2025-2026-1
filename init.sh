# 1) Eureka Server
curl https://start.spring.io/starter.tgz \
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
  | tar -xzvf -

# 2) Hotel Management Service
curl https://start.spring.io/starter.tgz \
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
  | tar -xzvf -

# 3) Booking Service
curl https://start.spring.io/starter.tgz \
  -d type=maven-project \
  -d language=java \
  -d bootVersion=3.5.6 \
  -d groupId=ru.vspochernin \
  -d artifactId=booking-service \
  -d name=booking-service \
  -d description="Booking and user management service of the Hotel Booking System" \
  -d javaVersion=21 \
  -d packaging=jar \
  -d dependencies=web,data-jpa,h2,validation,security,oauth2-resource-server,cloud-eureka,cloud-openfeign,actuator,lombok \
  | tar -xzvf -

# 4) API Gateway
curl https://start.spring.io/starter.tgz \
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
  | tar -xzvf -
