# Sistema de Gestão

API REST desenvolvida em Java com Spring Boot. O sistema reúne operações de cadastro, consulta, atualização e exclusão de dados relacionados à gestão de clientes, animais, produtos, fornecedores, funcionários e serviços.

## Tecnologias utilizadas

<p align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="45" height="45" alt="Java" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="45" height="45" alt="Spring Boot" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/hibernate/hibernate-original.svg" width="45" height="45" alt="Hibernate" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" width="45" height="45" alt="MySQL" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/maven/maven-original.svg" width="45" height="45" alt="Maven" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg" width="45" height="45" alt="Git" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/github/github-original.svg" width="45" height="45" alt="GitHub" />
</p>

## Funcionalidades

O sistema está organizado nos seguintes módulos:

- **Clientes:** gerenciamento dos dados dos clientes.
- **Pets:** gerenciamento dos animais vinculados aos clientes.
- **Produtos:** gerenciamento de produtos e estoque.
- **Fornecedores:** cadastro e gerenciamento de fornecedores.
- **Funcionários:** gerenciamento dos dados dos funcionários.
- **Serviços:** cadastro e gerenciamento dos serviços oferecidos.

Os módulos disponibilizam operações REST para manipulação dos dados, conforme os endpoints implementados.

## Requisitos

Para executar o projeto, é necessário ter instalado:

- JDK 21
- MySQL ou MariaDB
- Maven, caso não utilize o Maven Wrapper incluído no projeto.

## Configuração do banco de dados

Crie o banco de dados no MySQL ou MariaDB:

```sql
CREATE DATABASE desafio_senai;
```

Confira as configurações em `src/main/resources/application.properties` e ajuste-as conforme o ambiente local:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/desafio_senai
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
server.port=8081
```

O exemplo considera um banco local com usuário `root` e senha vazia. Se o seu ambiente utilizar outras credenciais, atualize as propriedades correspondentes.

## Como executar

1. Clone o repositório:

```bash
git clone https://github.com/nicwxzz/desafio-desenvolvimento-sistemas.git
```

2. Entre na pasta do projeto:

```bash
cd desafio-desenvolvimento-sistemas
```

3. Confira a configuração do banco de dados.

4. Execute a aplicação pelo IntelliJ IDEA, iniciando a classe `DesafioApplication`, ou pelo Maven Wrapper no Windows:

```bash
mvnw.cmd spring-boot:run
```

Após a inicialização, a API estará disponível em:

`http://localhost:8081`

## Endpoints

| Módulo | Endpoint base |
|---|---|
| Clientes | `/api/clientes` |
| Pets | `/api/pets` |
| Produtos | `/api/produtos` |
| Fornecedores | `/api/fornecedores` |
| Funcionários | `/funcionarios` |
| Serviços | `/servicos` |

Os endpoints oferecem operações como criação, listagem, consulta por ID, atualização e exclusão, de acordo com a implementação de cada controller.

## Estrutura do projeto

```text
src/
└── main/
    ├── java/br/com/senai/desafio/
    │   ├── controller/
    │   ├── entity/
    │   ├── model/
    │   ├── repository/
    │   └── service/
    └── resources/
        ├── application.properties
        └── static/
```

## Execução e testes

A API pode ser testada utilizando ferramentas como Postman, Bruno ou Insomnia.

---

Desenvolvido em equipe como parte do desafio de Desenvolvimento de Sistemas do SENAI.
