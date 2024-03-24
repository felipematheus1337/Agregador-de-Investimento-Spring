# Agregador de Investimento Spring
Este é um projeto de Agregador de Investimento desenvolvido em Java 21, utilizando tecnologias como Spring Web, Spring Cloud Open Feign, Docker, MySQL. O sistema consome a API de investimentos do Brapi e segue o padrão SOLID, utilizando records.

Requisitos
Java 21
Docker
MySQL
Tecnologias Utilizadas
Spring Web
Spring Cloud Open Feign
Docker
MySQL
Como Executar
Pré-requisitos
Certifique-se de ter Docker e MySQL instalados em sua máquina.

## Clone este repositório para sua máquina local.
Navegue até o diretório do projeto.
Configuração do MySQL
Execute os seguintes comandos para configurar o banco de dados MySQL:

bash
Copy code
docker run -d -p 3306:3306 --name mysql-db -e MYSQL_ROOT_PASSWORD=password mysql:latest
Depois que o contêiner MySQL estiver em execução, você precisa criar o banco de dados necessário para o projeto.

bash
Copy code
docker exec -it mysql-db mysql -uroot -p

## Digite sua senha (password)

CREATE DATABASE investimentos;
Compilação e Execução
Execute os seguintes comandos para compilar e executar o projeto:

bash
Copy code
./mvnw clean package
docker build -t agregador-investimento-spring .
docker run -p 8080:8080 --name agregador-app --link mysql-db:mysql -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-db:3306/investimentos -e SPRING_DATASOURCE_USERNAME=root -e SPRING_DATASOURCE_PASSWORD=password agregador-investimento-spring
O aplicativo estará disponível em http://localhost:8080.



Autor
Este projeto foi desenvolvido por Felipe Matheus.

Licença
Este projeto está licenciado sob a Licença MIT - consulte o arquivo LICENSE.md para obter detalhes.
