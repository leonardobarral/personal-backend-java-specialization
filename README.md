# API JAVA REST FULL COM SPRING FRAMEWORK

**Genesis for Health** é uma plataforma modular de questionários digitais focada em saúde mental. O projeto está sendo desenvolvido em etapas com o objetivo de maximizar meu aprendizado, modularidade e escalabilidade. Eventualmente, será migrado para uma arquitetura de microserviços e contará com a utilização de Docker, bem como será hospedado na AWS.

## Objetivos do Projeto

- Implementar uma plataforma de questionários digitais que evolua continuamente.
- Modularidade no código para facilitar a manutenção e evolução do projeto.
- Migração futura para uma arquitetura de microserviços.
- Integração com Docker para gerenciamento de containers.
- Hospedagem na AWS para garantir escalabilidade e confiabilidade.

## Tecnologias Utilizadas

- **Java (Spring Boot)**: Desenvolvimento do backend.
- **Spring Security**: Gerenciamento de autenticação e autorização.
- **JWT**: Implementação de autenticação baseada em tokens.
- **MySQL**: Banco de dados relacional para armazenamento de dados.
- **JPA/Hibernate**: Mapeamento objeto-relacional.
- **JUnit/Mockito**: Testes unitários e mockagem.
- **Maven**: Gerenciamento de dependências.
- **Docker**: Containerização do banco de dados e outros serviços.
- **AWS**: Hospedagem da aplicação e banco de dados.


## Funcionalidades já Implementadas

- **Autenticação e Autorização**: Baseada em JWT e Spring Security.
- **Gestão de Usuários e Permissões**: Criação, edição e exclusão de usuários e permissões com controle de segurança granular.
- **Autorização baseada em banco de dados**: O projeto preve permissões em Papeis e Authorities, com maior flexibilidade de controle de acesso.
- **Escalabilidade Modular**: O projeto está preparado para futuras expansões e migração para microserviços.

## Como Executar o Projeto

### Pré-requisitos

- **JDK 21**
- **Maven**
- **Docker**
- **MySQL**

### Passos

1. Clone este repositório:
   ```bash
   git clone https://github.com/leonardobarral/personal-backend-java-specialization.git
   ```
   
2. Na raiz do projeto, compile e instale as dependências:
   ```bash
   mvn clean install
   ```

3. Execute o Docker, Instale a Imagem do MySQL e Inicie um Container:
    Após abrir o aplicativo Docker, execute os seguintes comandos no pronpt
    ```bash
    docker pull mysql:8.0
    ```
    ```bash
    docker run --name mysql_questionaries -e MYSQL_ROOT_PASSWORD=root_password -e MYSQL_DATABASE=mydb -p 3306:3306 -d mysql:8.0
    ```
5. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```
6. Acesse a aplicação através do Postman (http://localhost:8080) no navegador ou utilize o arquivo JSON abaixo, no Postman:
https://github.com/leonardobarral/personal-backend-java-specialization/tree/main/Testes_Postman

## Observações

- O banco de dados `mydb` será armazenado localmente no caminho, evitando a perda de dados caso o container seja reiniciado.
- Certifique-se de que as credenciais no arquivo `application.properties` correspondam às configuradas no `docker-compose.yml`.

## Próximos Passos

- Incluir novas features
- Migrar a arquitetura para microserviços.
- Automatizar o pipeline de CI/CD com Jenkins.
- Escalar a aplicação e o banco de dados usando AWS.
- Implementar autenticação e autorização baseadas em OAuth2.


##Muito obrigado por esta visita!!!

    
