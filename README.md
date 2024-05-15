# Integração API da OpenAi com Quarkus

Nesse projeto integrei a API OpenAI em uma aplicação Quarkus, para gerar textos e imagens com IA.

- Atualização: Nesse meio tempo, o modelo text-davinci-003 foi disponibilizado. Você pode simplesmente atualizar o argumento de 002.

Documentação OpenAI [https://platform.openai.com/docs/introduction]
# quarkus-openai

Este projeto utiliza o Quarkus, o Framework Java Supersônico Subatômico.

Se você deseja saber mais sobre o Quarkus, por favor visite o site: [https://quarkus.io/](https://quarkus.io/) .

## Executando a aplicação em modo de desenvolvimento

Você pode executar sua aplicação em modo de desenvolvimento que permite codificação ao vivo usando:

```shell script
./mvnw compile quarkus:dev
```

> **_OBSERVAÇÃO:_**  O Quarkus vem com uma Interface de Desenvolvimento (Dev UI), disponível apenas no modo de desenvolvimento em [http://localhost:8080/q/dev/](http://localhost:8080/q/dev/).

## Empacotando e executando a aplicação

A aplicação pode ser empacotada usando:

```shell script
./mvnw package
```

Isso produz o arquivo `quarkus-run.jar` no diretório `target/quarkus-app/`.
Esteja ciente de que não é um _über-jar_ pois as dependências são copiadas para o diretório `target/quarkus-app/lib/`.

A aplicação agora pode ser executada usando `java -jar target/quarkus-app/quarkus-run.jar`.

Se você deseja construir um _über-jar_, execute o seguinte comando:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

A aplicação, empacotada como um _über-jar_, agora pode ser executada usando `java -jar target/*-runner.jar`.

## Criando um executável nativo

Você pode criar um executável nativo usando: 

```shell script
./mvnw package -Dnative
```

Ou, se você não tiver o GraalVM instalado, pode executar a compilação do executável nativo em um contêiner usando: 

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

Em seguida, você pode executar seu executável nativo com: `./target/quarkus-openai-1.0-runner`

Se você deseja saber mais sobre a construção de executáveis nativos, consulte [https://quarkus.io/guides/maven-tooling](https://quarkus.io/guides/maven-tooling).

## Guias Relacionados

- Hibernate Validator ([guia](https://quarkus.io/guides/validation)): Valide propriedades de objetos (campo, getter) e parâmetros de método para seus beans (REST, CDI, Jakarta Persistence)
