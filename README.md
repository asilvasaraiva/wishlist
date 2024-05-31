

# WishList API

## Descrição da problemática e requisitos:
Criar um serviço HTTP resolvendo a funcionalidade de Wishlist do cliente devendo esta possuir as seguintes funcionalidades:

- Adicionar e/ou remover um produto informado na wishlist de um cliente.
- Listar todos os produtos na wishlist de um cliente.
- Consultar se determinado produto está/existe na wishlist do cliente
- O numero máximo de items na lista são 20 produtos.

**NOTA:**

Por convenção os nomes das funcões, métodos e
definições de tratamento de erros estão no idioma inglês.

## Requisitos Tecnológicos:

```Maven 3.6.3+```
```Spring Boot 3.0```
```MongoDB```
```Java 18```
```Docker```
```Docker-Compose```

## Rodando o projeto:

1 - Para rodar o projeto localmente primeiramente é necessário abrir um terminal na raíz do projeto e executar o comando
docker abaixo para criar um container com o banco de dados Mongo instalado.

- `$ docker-compose up -d` 

- Caso queira encerrar a utilização dos container utilizar o comando abaixo para interromper o serviço iniciado:
`$ docker-compose down`
  

2 - Ainda na raíz do projeto, no terminal rode o seguinte comando para instalar todas as dependencias necessárias:

`$ mvn clean install`

3- Após a instalação e build bem sucedida, para iniciar o serviço execute o comando abaixo :

`$ mvn spring-boot:run`

4 - **Health Check status endpoint**

Para testar se a aplicação subiu com sucesso sem precisar se atentar ao console, acesse:

`http://localhost:8080/api/actuator/health` e irá retornar o status da aplicação `up` caso sucesso
ou `down` em caso de falha interna.

## API Endpoints:

Para acessar a documentação iterativa com as descrições e parâmetros necessários para utilizar cada endpoint desta API, 
temos o seguinte link:

``` 
 http://localhost:8080/swagger-ui/index.html
```   

# 
Alexsandro da Silva Saraiva \
alex_saraiva14@hotmail.com\
2024

