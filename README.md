<img src="me.svg" width="200" alt="ME">

# Back-end Challenge

Este projeto consiste na minha implementação do desafio de back-end proposto aos novos estagiários pelo Mercado Eletrônico

## Table of Contents

- [Tecnologias](#tecnologias)
- [O Desafio](#o-desafio)
- [Informações finais](#informações-finais)
- [Sinta-se livre para contribuir](#sinta-se-livre-para-contribuir)


## :computer: Tecnologias

O código foi desenvolvido em Java 17, com Spring Boot como framework e H2 como banco de dados.

## :zap: O Desafio



Você deve construir uma API que terá dois endpoints:

###	Endpoint – Pedido

Sua aplicação deve expor em `http://localhost:{porta}/api/pedido` uma API RESTful. (GET, POST, PUT, DELETE)

O conteúdo de um Pedido possui o seguinte payload:

```json
{
  "pedido":"123456",
  "itens": [
  {
    "descricao": "Item A",
    "precoUnitario": 10,
    "qtd": 1
  },
  {
    "descricao": "Item B",
    "precoUnitario": 5,
    "qtd": 2
  }
  ]
}
```

O conteúdo desse Pedido e Itens deverá ser persistido em banco de dados. Fique à vontade para criar as validações que você considerar necessárias.

###	Endpoint – Mudança de Status de Pedido

Sua aplicação deve receber um POST em `http://localhost:{porta}/api/status` com o seguinte payload:

```json
{
  "status":"XXX",
  "itensAprovados":0,
  "valorAprovado":0,
  "pedido":"XXX"
}
```

E terá o seguinte response, baseado nas regras detalhadas a seguir:

```json
{
  "pedido":"123456",
  "status": ["STATUS_1", "STATUS_2", "STATUS_...n"]
}
```

O status não precisa ser persistido em banco de dados, basta retornar na API.

## Informações finais

Visite o arquivo [instructions.md](instructions.md) para ler as instruções de como rodar o projeto.

## Sinta-se livre para contribuir

Envie um PR caso encontre algum erro ou ponto de melhoria :)
