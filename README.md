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


<h2>Documentação</h2>


