# Jankenpon (Pedra, Papel e Tesoura) com Amazon SQS

Amazon SQS é um serviço da AWS para messageria.

https://aws.amazon.com/pt/sqs/

Desenvolvido em Java com base em Quarkus, https://quarkus.io/, a aplicação tem dois serviços, um produtor e outro consumidor que simulam um jogo de Jankenpon com entradas aleatórias de um dos lados.

Para compilar:

```
./mvnw compile quarkus:dev
```

Para subir o serviço de messageria eu utilizei o Docker, https://www.docker.com/, baixando uma imagem e criando um container com o comando:

```
docker run --rm --name local-sqs -p 8010:4576 -e SERVICES=sqs -e START_WEB=0 -d localstack/localstack:0.11.1
```

O comando vai expor a porta 8010 para conexão.

Depois foi criado um perfil Amazon via cli:

```
aws configure --profile perfilDaFila
```

Com os seguintes parâmetros:

AWS Access Key ID [None]: chave<br>
AWS Secret Access Key [None]: segredo<br>
Default region name [None]: us-east-1<br>
Default output format [None]:<br>

Assim que o serviço SQS estiver de pé, crie uma fila

```
aws sqs create-queue --queue-name=minhaFila --profile perfilDaFila --endpoint-url=http://localhost:8010
```

Para executar, faça uma requisição POST no endereço:

```
http://localhost:8080/jankenpon/mao1/play
```

com o payload:


```javascript
"Tesoura"

```
por exemplo.

Para os resultados:

```
http://localhost:8080/jankenpon/mao2/play
```

Uma saída:

```javascript
["Empate com Papel!","Venci jogador 2 jogando Pedra e ele Tesoura =P ","Venci jogador 2 jogando Tesoura e ele Papel =P ","Venci jogador 2 jogando Tesoura e ele Papel =P ","Perdi do jogador 2 jogando Papel e ele Tesoura =***( ","Empate com Papel!","Venci jogador 2 jogando Pedra e ele Tesoura =P ","Empate com Pedra!","Empate com Tesoura!","Perdi do jogador 2 jogando Papel e ele Tesoura =***( "]
```

Caso tenha alguma dúvida, segue a documentação do Quarkus:

https://quarkus.io/support/

