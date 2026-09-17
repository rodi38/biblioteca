# ADR — Registro de Decisões Arquiteturais

Registro resumido das escolhas que guiam a modernização da Biblioteca. Releia
aqui sempre que uma decisão futura parecer contradizer o que já foi combinado.

| Decisão | Escolha | Por quê |
|---|---|---|
| Topologia de serviços | Monólito modular (Clean Architecture) | 3 entidades centrais fortemente relacionadas; microsserviços cobrariam service discovery, múltiplos bancos e observabilidade distribuída sem ganho real nesta escala. |
| Único componente extraído | Worker de notificação, consumindo RabbitMQ | Único subdomínio de fato independente (não compartilha transação com Book/Student/Loan) — dá experiência real com mensageria entre processos sem o custo de ir full microsserviços. |
| Autenticação | Spring Security + JWT emitido pela própria API, sem provedor externo por enquanto | Entrega login real com dois papéis sem subir um serviço pesado a mais; as regras de autorização por cargo ficam desenhadas do mesmo jeito que ficariam com Keycloak, então trocar depois é só trocar quem emite/valida o token. |
| Modelo de usuário | `User` como entidade própria com campo `cargo`; `Student` vira um perfil vinculado, só existe quando o cargo é `STUDENT` | `ADMIN` não é um tipo de `Student` — são conceitos separados desde o início, o que evita gambiarra quando um cargo novo (ex. `LIBRARIAN`) aparecer no futuro. |
| Adoção do Keycloak | Adiada para um projeto futuro | Só compensa o custo de rodar Keycloak (JVM pesada) quando vários projetos pessoais forem migrar para SSO ao mesmo tempo; hoje cada projeto novo pagaria esse custo sozinho. Por isso o `docker-compose.yml` local não inclui Keycloak. |
| Fila de mensagens | RabbitMQ para eventos de empréstimo | Desacopla envio de notificação do fluxo síncrono de criar/devolver empréstimo. |
| Cache | Redis para leitura do catálogo | Reduz carga em consultas de livros; uso independente do RabbitMQ. |
| Migração de schema | Flyway substitui `ddl-auto: update` | Histórico de mudanças versionado, seguro para produção. |
| Infraestrutura | Docker Compose + reverse proxy com TLS automático | Operação simples o suficiente para manter sozinho numa VPS pessoal. |
| Ciclo de vida dos serviços | Postgres e reverse proxy sempre no ar; a API de cada projeto entra em rotação | A API é stateless por causa do JWT — o segredo de assinatura fica fora do processo, então ela pode subir e descer sem invalidar token de ninguém nem afetar outros projetos na mesma VPS. |
| Camadas | Clean Architecture aplicada ao domínio atual | A regra de dependência é a única coisa que não pode quebrar: código de fora só pode depender de código mais para dentro, nunca o contrário. `Book`, `Student` e `Loan` viram o núcleo puro — sem anotação de framework nenhuma. |

## Camadas

| Camada | Contém | Exemplo de pacote |
|---|---|---|
| Domain | Entidades puras, value objects (`Isbn`, `Email`), regras como métodos (`Loan.markReturned()`), interfaces de repositório | `domain.loan`, `domain.book` |
| Application | Um caso de uso por operação: `CreateLoanUseCase`, `ReturnLoanUseCase`, `DeleteBookUseCase` | `application.loan.usecase` |
| Interfaces | Controllers REST, DTOs de request/response, `GlobalExceptionHandler` | `interfaces.rest` |
| Infrastructure | Entidades JPA, implementação dos repositórios, publisher/consumer RabbitMQ, cliente Redis, filtro JWT do Spring Security | `infrastructure.persistence`, `infrastructure.messaging` |

## Autorização (a partir da Fase 3)

| Recurso | ADMIN | STUDENT |
|---|---|---|
| Livros | CRUD completo | Somente leitura do catálogo |
| Estudantes | CRUD completo de qualquer cadastro | Leitura/edição só do próprio cadastro |
| Empréstimos | Cria, devolve e deleta qualquer empréstimo | Cria, consulta e devolve apenas os próprios |
| Auditoria (`/audit`) | Acesso total | Sem acesso |
