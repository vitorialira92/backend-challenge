# Instruções

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![H2](https://img.shields.io/badge/h2-%2300f.svg?style=for-the-badge&logo=h2&logoColor=white)

## Table of Contents

- [Pré-requisitos](#pré-requisitos)
- [Configuração](#configuração)
- [Instalação de dependências](#instalação-de-dependências)
- [Execução](#execução)
- [Swagger](#swagger)
- [Testes](#testes)


## Pré-requisitos

Este projeto requer Java 17 e Maven instalados. Você pode instalar através dos links abaixo:

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

## Configuração

Clone o repositório e entre no diretório do projeto:

```bash
git clone https://github.com/vitorialira92/backend-challenge.git
cd backend-challenge
```

## Instalação de dependências

Rode o seguinte comando para instalar as dependências:

```bash
mvn clean install
```

## Execução

Execute este comando para inicializar a aplicação
 
```bash
mvn spring-boot:run
```

Após isso, a api estará disponível no link http://localhost:8080

## Swagger 

Para acessar a documentação da API, abre o seguinte link no navegador: http://localhost:8080/swagger-ui/index.html


## Testes

Para executar os testes, rode o seguinte comando:
 
```bash
mvn test
```
