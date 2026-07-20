#!/bin/bash

echo "Buscando variáveis cadastradas no serviço da AWS Systems Manager Parameter Store..."

ENDPOINT_DB_RDS=$(aws ssm get-parameter --name "/cad-clientes-api/ENDPOINT_DB_RDS" --with-decryption --query Parameter.Value --output text)
USER_DB=$(aws ssm get-parameter --name "/cad-clientes-api/USER_DB" --with-decryption --query Parameter.Value --output text)
PASSWORD_DB=$(aws ssm get-parameter --name "/cad-clientes-api/PASSWORD_DB" --with-decryption --query Parameter.Value --output text)
JWT_SECRET=$(aws ssm get-parameter --name "/cad-clientes-api/JWT_SECRET" --with-decryption --query Parameter.Value --output text)

echo "Buildando imagem Docker..."
docker build -t cad-clientes-api:1.0 .

echo "Iniciando container..."
docker run -d \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e ENDPOINT_DB_RDS=$ENDPOINT_DB_RDS \
  -e USER_DB=$USER_DB \
  -e PASSWORD_DB=$PASSWORD_DB \
  -e JWT_SECRET=$JWT_SECRET \
  cad-clientes-api

echo "API no ar!"
