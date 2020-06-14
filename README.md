# VIVO-TESTE

## Tecnologias utilizadas

- Java 8
- Spring Boot
- REST

## Exemplos de entrada válida

Essa seria uma entrada válida, que retornaria os jobs na ordem de execução

```json
{
  "dataInicio": "2019-11-10 09:00:00",
  "dataFim": "2019-11-11 12:00:00",
  "jobs": [
           {
              "id":1,
              "descricao":"Importação de arquivos de fundos",
              "dataMaxima":"2019-11-10 12:00:00",
              "horas":2
           },
           {
              "id":2,
              "descricao":"Importação de dados da Base Legada",
              "dataMaxima":"2019-11-11 12:00:00",
              "horas":4
           },
           {
              "id":3,
              "descricao":"Importação de dados de integração",
              "dataMaxima":"2019-11-11 08:00:00",
              "horas":6
           }
        ]
}
```

## Exemplos de entrada inválida

Essa seria uma entrada inválida, que retornaria uma exceção com a mensagem de "janela curta demais para executar todos os jobs"

```json
{
  "dataInicio": "2019-11-10 09:00:00",
  "dataFim": "2019-11-10 19:00:00",
  "jobs": [
           {
              "id":1,
              "descricao":"Importação de arquivos de fundos",
              "dataMaxima":"2019-11-10 12:00:00",
              "horas":2
           },
           {
              "id":2,
              "descricao":"Importação de dados da Base Legada",
              "dataMaxima":"2019-11-11 12:00:00",
              "horas":4
           },
           {
              "id":3,
              "descricao":"Importação de dados de integração",
              "dataMaxima":"2019-11-11 08:00:00",
              "horas":6
           }
        ]
}
```