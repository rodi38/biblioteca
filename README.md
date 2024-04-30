<h1>Local Bibliotca API</h1>

Requisitos do projeto: 


Java jdk 17 ou superior <br>
PostgreSQL 16  <br>
Apache-maven-3.9.6 <br>

É esperado que o usuário tenha a <code>JAVA_HOME</code> e <code>MAVEN_HOME</code> corretamente configurados no seu computador. <br>
É necessário que o caminho para a pasta 'bin' do PostgreSQL esteja adicionado à variável de ambiente 'Path' do sistema.

A aplicação foi desenvolvida utilizando o IntelliJ IDEA, mas os usuários têm a liberdade de escolher a IDE que mais lhes convier.

<h2>Instalação local</h2>

<h4>Passo 1</h4>
Baixe instale o banco de dados Postgresql ou tenha uma imagem do postgresql no docker instalada.

Crie um banco de dados com o nome <b>library</b> ou de nome preferido(neste caso terá de mudar as configurações do application.yaml) com o seguinte comando no terminal:
<code>createdb -U usuarioDoBanco nomeDoBanco</code>

Ps: Nome de usuário e senha são escolhidos no momento da instalação, por padrão o usuário normalmente é postgres, fique atento no momento de instalação!

PS: Para rodar o comando anterior é necessário adicionar o caminho de instalação do Postgresql na variavel path do sistema!


Caso esteja fazendo via docker, utilize os seguintes comandos para baixar a imagem do Postgresql e criar um container: <br>
<code>docker pull postgres</code>  <code>docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres</code>

<h4>Passo 2</h4>

Clone o repositório com o seguinte comando no terminal: <code>git clone https://github.com/rodi38/biblioteca.git </code>

Se preferir, você pode simplesmente baixar o repositório em formato ZIP. Basta clicar em 'Code', selecionar 'Download ZIP' e depois descompactar o arquivo baixado.


<h4>Passo 3</h4>

No arquivo <code>application.yaml</code> localizado em: <code>src/main/resources/application.yaml</code> será necessário mudar o <b>password</b> para a senha escolhida na criação do banco bem como o nome de usuário. <br>

Troque também o nome do banco caso o nome do seu banco não seja library: <br> <code>url: jdbc:postgresql://localhost:5432/NomeDoSeuBanco</code>

<h4>Passo 4</h4>

Antes de rodar o projeto será necessário carregar todas dependências, no intellij basta seguir estes passos: <br><code>Project > Biblioteca > click direito > Maven > Reload project</code> <br>

Tendo feito todos procedimentos anteriores é hora de rodar o projeto, no IntelliJ IDEA basta apertar <code>shift + F10 </code>

Se fez todos passos corretamente o projeto rodará sem nenhum erro, parabéns.


<h2>Rotas</h2>

<h3>Book</h3>

getAllBooks: http://localhost:8080/book

em caso de sucesso receberá um json contendo todos livros cadastrados: 
```json
{
    "success": true,
    "message": "Successfully get all books",
    "data": [
        {
            "id": 6,
            "stockQuantity": 1333,
            "title": "livro 2",
            "author": "rodrigo",
            "category": "goat",
            "isbn": "1231231",
            "publisher": "da boa",
            "publishedYear": 1998
        },
        {
            "id": 3,
            "stockQuantity": 11,
            "title": "wilson da nave",
            "author": "wilson evangelista",
            "category": "scfi",
            "isbn": "978-3-16-148410-0",
            "publisher": "Microsoft",
            "publishedYear": 2025
        }
    ]
}
```

getBookById: http://localhost:8080/book/6

em caso de sucesso receberá um json contendo o livro com o id mencionado: 

```json
{
    "success": true,
    "message": "Successfully get the book",
    "data": {
        "id": 6,
        "stockQuantity": 1333,
        "title": "livro 2",
        "author": "rodrigo",
        "category": "goat",
        "isbn": "1231231",
        "publisher": "da boa",
        "publishedYear": 1998
    }
}
```

updateBook:  http://localhost:8080/book/6

em caso de sucesso receberá um json contendo as informações do livro atualizado: 

```json
{
    "success": true,
    "message": "Book has been updated",
    "data": {
        "id": 6,
        "stockQuantity": 29,
        "title": "asdsd",
        "author": "Rodrigo Doe",
        "category": "atumalaca",
        "isbn": "978-3-16-148410-0",
        "publisher": "sadasd XYZ",
        "publishedYear": 2021
    }
}
```

deleteBook:  http://localhost:8080/book/6

em caso de sucesso receberá um json contendo as informações do livro deletado: 

```json
{
    "success": true,
    "message": "Successfully deleted the book",
    "data": {
        "id": 7,
        "stockQuantity": 11,
        "title": "wilson da nave",
        "author": "wilson evangelista",
        "category": "scfi",
        "isbn": "978-3-16-148410-0",
        "publisher": "Microsoft",
        "publishedYear": 2025
    }
}
```
