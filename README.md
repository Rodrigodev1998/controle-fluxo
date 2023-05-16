# API Fluxo Caixa
Este é um projeto de API RESTful para gerenciamento de fluxo de caixa de uma empresa. Com ele, é possível cadastrar lançamentos de crédito e débito e verificar o saldo diário.

## Desenho da arquitetura
![api-fluxo-caixa drawio](imagens/diagrama%20de%20fluxo%20de%20caixa.png)

## Padrões de microserviço utilizados
* Separação em camadas (Controller, Service e Repository)
* Injeção de dependências com Spring
* Uso de DTOs para transferência de dados entre a API e o banco de dados

## Padrões de projetos utilizados
- `Repository`: utilizado para abstrair a camada de acesso ao banco de dados.
- `DTO`: utilizado para transferência de dados entre as camadas.


## Serviços
* Serviço que controla os lançamentos.
* Serviço do consolidado diario.

## ✔️ Tecnologias e bibliotecas usadas
- ``Java 17``
- ``Spring Boot 3``
- ``Spring Data JPA``
- ``Spring Boot Actuator``
- ``H2 Database``
- ``Swagger 3``
- ``Lombok``
- ``JUnit 5``
- ``Mockito``
- ``ModelMapper``

## Endpoints
- `POST /lancamentos`: endpoint para adicionar um novo lançamento.
- `GET /lancamentos`: endpoint para listar todos os lançamentos.
- `GET /saldo-consolidado/{data}`: endpoint para calcular o saldo diário de uma determinada data.

##  Execução do projeto
Para executar o projeto, é necessário ter o Java 17 e o Maven instalados.

##  Como usar
Para usar a API, é necessário rodar local e ter o Docker e o Docker Compose instalados.
## 📁 Entre na pasta do projeto:
```
cd controle-fluxo-caixa
```
### Execute o comando abaixo para compilar e empacotar o projeto:
```bash
mvn clean package
```
## 🐳 Execute o docker-compose:
```bash
docker-compose up --build
```
A API estará disponível em http://localhost:8080.

