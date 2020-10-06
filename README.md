# Teste de API de cadastro.
Teste API Rest de Cadastro de pessoa.

Requisitos:

    JDK 8
    Maven 3

Executando projeto:

    Clone o projeto: https://github.com/DaniloOC/apicadastro.git

    Entre na pasta apicadastro e execute: 
        mvn spring-boot:run

    Swagger: http://localhost:8080/swagger-ui.html

    H2: http://localhost:8080/h2
        JDBC URL: jdbc:h2:file:./data/cadastro
        USER NAME: sa

Tecnologias utilizadas:
    
    Java 8, Spring Boot, Spring MVC, Maven 3, Swagger, JUnit5, Database H2
    
## Exemplos chamadas REST:

Listar todas pessoas paginando:

    curl --request GET \
      --url http://localhost:8080/api/v1/cadastro/pessoa  

Retorna pessoa pelo cpf:

    curl --request GET \
      --url http://localhost:8080/api/v1/cadastro/pessoa/75939992030
      
Cria uma nova pessoa:

    curl --request POST \
      --url http://localhost:8080/api/v1/cadastro/pessoa \
      --header 'content-type: application/json' \
      --data '{
        "nome": "Danilo Cruz",
        "apelido": "Dan",
        "cpf": "75939992030",
        "enderecos": [
            {
                "tipoEndereco": "Comercial",
                "tipo": "Rua",
                "nome": "Castelo Branco",
                "numero": 312,
                "complemento": "2º andar",
                "cep": "09321000",
                "bairro": "Matriz",
                "cidade": "Mauá",
                "estado": "São Paulo",
                "pais": "Brasil"
            }, {
                "tipoEndereco": "Residencial",
                "tipo": "Rua",
                "nome": "Castelo Branco",
                "numero": 312,
                "complemento": "2º andar",
                "cep": "09321000",
                "bairro": "Matriz",
                "cidade": "Mauá",
                "estado": "São Paulo",
                "pais": "Brasil"
            }
        ],
        "profissao": "Desenvolvedor",
        "salario": 1000.39,
        "dependentes": [
            {
                "cpf": "86044484033",
                "nome": "Outra Pessoa",
                "tipo": "Filho"
            }
        ],
        "dataNascimento": "1987-12-11",
        "telefones": [
            {
                "codigoPais": 55,
                "ddd": 11,
                "numero": "97000-0000",
                "tipo": "Residencial"
            }
        ]
    }
    '

Atualiza pessoa pelo cpf:
    
    curl --request PUT \
      --url http://localhost:8080/api/v1/cadastro/pessoa/75939992030 \
      --header 'content-type: application/json' \
      --data '{
        "nome": "Danilo Cruz 2",
        "dataNascimento": "1987-10-10",
        "telefones": [
            {
                "codigoPais": 55,
                "ddd": 11,
                "numero": "97777-0000",
                "tipo": "Residencial"
            }
        ]
    }'

Remove uma pessoa pelo cpf:

    curl --request DELETE \
      --url http://localhost:8080/api/v1/cadastro/pessoa/75939992030    
