## Simples CRUD de usuários

Crud de usuário básico (nome, e-mail, senha e telefone)

Um timeline com o log de operações realizados nos registros do crud, por exemplo :

Usuário Lucas Israel:

- Usuário criado em 20:00 15/03/2018

- Usuário alterado em 16:00 16/03/2018

- Usuário visualizado em 17:00 17/03/2018 

### Ambiente
Este software foi desenvolvido em java 8 com spring boot (https://spring.io/projects/spring-boot) e com VueJS(https://vuejs.org/) para criação das telas.
Para distribuição, recomendamos o uso do docker. Este repositório possui tudo que precisa para criar a imagem e iniciar a aplicação.

#### JAVA
Para compilar, primeiro garanta que tenha java corretamente instalado em seu ambiente. Para validar, abra o terminal de seu sistema operacional e digite o comando

```bash
java -version
```
Para a correta instalação do JDK, siga as instruções deste link: https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html

#### Node
A parte de telas, foi desenvolvida usando VueJS. Para iniciar o desenvolvimento, precisa ter instalado o NodeJS.
Para validar, abra o terminal de seu sistema operacional e digite o comando

```bash
npm --version
```
Para a correta instalação do node, siga as intruções deste link: https://nodejs.org/en/download/


### VueCLI
CLI criado para desenvolver VueJS.
Para a correta instalação do CLI, siga as intruções deste link: https://cli.vuejs.org/guide/installation.html


### Docker
O deploy da aplicação é fornecido com o banco (postgres), o admin do postgres e a aplicação numa imagem docker.
Para validar se possui o docker instalado, abra o terminal do seu sistema operacional e digite o seguinte comando:

```bash
docker --version
```
Para instalar o docker, siga as instruções deste link: https://docs.docker.com/install/
Escolha o seu sistema operacional e prossiga conforme instruções

### Docker Compose
Para facilitar a integração dos vários serviços (gestão containers), vamos usar o docker-compose. 
É simples e atende facilmente esta demanda, para projetos maiores e mais complexos - com outras necessidades de garantias de infraestruturas - considerar o uso do kubernetes

Para validar se o docker compose está corretamente instalado em seu sistema operacional, abra o terminal digite o comando:
```bash
docker-compose --version
```
Para instalar o docker-compose, siga as instruções deste link: https://docs.docker.com/compose/install/


### maven
Para compilar a aplicação, você irá precisar do maven instalado. Para verificar se já possui o maven, abra o terminal de seu sistema operacional e digite o seguinte comando:

```bash
mvn -version
```
Caso precise instalar o maven, siga as instruções deste link: https://maven.apache.org/


### Código fonte
Este software é dividido em duas partes. 
A primeira é o backend, feito em java com spring boot. 
A segunda é o frontend, feito em VueJS.

#### BackEnd
- Abra o terminal e execute o seguinte comando:
```bash
mvn install eclipse:eclipse
```
Após concluir a instalação, você pode importar o projeto normalmente no eclipse.
Se preferir, pode utilizar o plugin maven de qualquer editor para importar este projeto.
A classe principal do projeto é 'com.lucasisrael.usercrud.Application'

#### FrontEnd
- Abra o terminal, navegue até o subdiretório view e execute o seguinte comando:
```bash
npm i
```
O comando acima irá instalar as dependências necessárias para o projeto.
Para subir em modo desenvolvimento, use o comando
```bash
npm run serve
```
O comando acima irá iniciar um server http com o código html sem minificar para facilitar o desenvolvimento, além disto, é hotdeploy. 
Se abrir os arquivos em seu editor preferido, altera o arquivo as páginas já irão refletir suas alterações.
Para compilar para distribuição, utilize o comando:
```bash
npm run build
```
Os artefatos gerados serão minificados e estarão dentro das boas práticas para fornecimento de conteúdos HTML.
Está configurado para gerar o conteúdo no diretório 'src/main/resources/static' o qual o spring boot está configurado para fornecer conteúdo HTTP.
O artefato final deste app é o arquivo .jar gerado no diretório target.


### Criando imagem docker
Para criar a imagem docker, é necessário informar o tag (nome da imagem), a versão do aplicativo (atualmente na versão 0.1-SNAPHOT) e o local do arquivo Dockerfile (atualmente na raíz do repositório).
O comando abaixo gera uma imagem docker com o artefato compilado localmente.

```bash
docker build -t lucasisrael/user-crud --build-arg versao=0.1-SNAPSHOT .
```
No comando acima, o nome da imagem será lucasisrael/user-crud


### Executando a aplicação
Edite o arquivo docker-compose.yml e faça a correção do arquivo application.properties para o local onde o reposiório está em sua infra.

ver linha com o conteúdo ```- ~/projects/crudusuario/application.properties:/opt/user-crud/application.properties```
Este arquivo possui configurações do spring boot para a aplicação. Para ver detalhes, ver documentação: https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
O banco já está configurado, pode ser facilmente trocado, mas precisará adicionar o driver para outros SGBDs no arquivo pom.xml


Para subir a stack de serviço completa (irá subir um postgres, um adminer e a aplicação), utilize o seguinte comando:

```bash
docker-compose up -d
```

- A aplicação estará disponível na porta 9000
- O postgres estará disponível na porta 5432
- O adminer estará disponível na porta 8080

Banco de dados criado: usercrud
Usuário do banco: usercrud
Senha do banco: usercrud
