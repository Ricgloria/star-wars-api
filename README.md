# MS star-wars-api

Serviço Java responsável pela gestão de filmes Star Wars.

### Pré-requisitos

- Java JDK 17
- Maven 3.8+

## Comandos Maven

- Compilar project

  `mvn compile`

- Limpar diretorio Target

  `mvn clean`

- Executar testes do projeto

  `mvn test`

- Executar projeto

  `mvn spring-boot:run`

- Instalar pacotes

  `mvn install`

### Swagger

- http://localhost:8080/swagger-ui/index.html

##Como subir o projeto localmente

0. A API está com a validação de SSL desativada na branch "no-ssl" devido ao vencimento do certificado na API swapi dev.
1. Executar a classe StarWarsApiApplication
2. Comece os testes por: curl --location 'http://localhost:8080/v1/star-wars/all-movies'
3. Para ver detalhes de um filme especifico, busque por "url" no body de retorno que lista todos os filmes
4. Para mais detalhes da API veja o Swagger