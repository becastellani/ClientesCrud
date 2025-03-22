# Sistema de Cadastro de Clientes

Este é um sistema de cadastro de clientes desenvolvido em Java 8, utilizando Hibernate para a interação com o banco de dados PostgreSQL. O sistema permite adicionar, atualizar, excluir e listar clientes, e faz uso de uma camada de controle para gerenciar as operações de CRUD (Create, Read, Update, Delete).

## Funcionalidades

- **Adicionar Cliente**: Permite adicionar um novo cliente ao sistema, persistindo os dados no banco de dados.
- **Atualizar Cliente**: Permite atualizar as informações de um cliente existente, identificando-o pelo CPF.
- **Excluir Cliente**: Permite excluir um cliente do banco de dados utilizando o CPF.
- **Listar Clientes**: Exibe todos os clientes cadastrados no banco de dados.

## Tecnologias Utilizadas

- **Java 8**: Linguagem de programação utilizada para a implementação do sistema.
- **Hibernate**: Framework de mapeamento objeto-relacional (ORM) para gerenciar a persistência de dados no banco de dados.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar os dados dos clientes.
- **JDBC**: Usado pelo Hibernate para a conexão com o banco de dados.
- **Maven**: Para gerenciamento de dependências e build do projeto.
- **JUnit**: Framework utilizado para escrever e rodar os testes automatizados.
- **Mockito**: Framework utilizado para criar mocks e testar a interação com dependências externas.

## Pré-requisitos

Antes de rodar o projeto, verifique se você tem os seguintes pré-requisitos instalados:

- [JDK 8 ou superior](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Maven](https://maven.apache.org/) (opcional, se estiver utilizando Maven para dependências)
- [Driver JDBC do PostgreSQL](https://jdbc.postgresql.org/download.html) (Será adicionado via dependência Maven)

## Como Configurar e Executar o Projeto

### Passo 1: Configurar o Banco de Dados

1. **Instale e inicie o PostgreSQL** em sua máquina. 
2. **Crie um banco de dados** para o sistema. Por exemplo:
   ```sql
   CREATE DATABASE sistema_clientes;
   ```

### Passo 2: Configuração do Hibernate

Edite o arquivo `hibernate.cfg.xml` localizado no diretório `src/main/resources` e configure a conexão com o seu banco de dados PostgreSQL. Exemplo:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <!-- JDBC Database connection settings -->
        <property name="hibernate.dialect">org.hibernate.dialect.PostgreSQLDialect</property>
        <property name="hibernate.connection.driver_class">org.postgresql.Driver</property>
        <property name="hibernate.connection.url">jdbc:postgresql://localhost:5432/sistema_clientes</property>
        <property name="hibernate.connection.username">seu_usuario</property>
        <property name="hibernate.connection.password">sua_senha</property>

        <!-- JDBC connection pool settings -->
        <property name="hibernate.c3p0.min_size">5</property>
        <property name="hibernate.c3p0.max_size">20</property>
        <property name="hibernate.c3p0.timeout">300</property>
        <property name="hibernate.c3p0.max_statements">50</property>
        <property name="hibernate.c3p0.idle_test_period">3000</property>

        <!-- Specify dialect -->
        <property name="hibernate.dialect">org.hibernate.dialect.PostgreSQLDialect</property>

        <!-- Enable Hibernate's automatic session context management -->
        <property name="hibernate.current_session_context_class">thread</property>

        <!-- Echo all executed SQL to stdout -->
        <property name="hibernate.show_sql">true</property>

        <!-- Drop and re-create the database schema on startup -->
        <property name="hibernate.hbm2ddl.auto">update</property>

        <!-- Disable the second-level cache -->
        <property name="hibernate.cache.provider_class">org.hibernate.cache.NoCacheProvider</property>

        <!-- Echo all executed SQL to stdout -->
        <property name="hibernate.format_sql">true</property>
    </session-factory>
</hibernate-configuration>
```

### Passo 3: Clone o Repositório

Clone o repositório para sua máquina local:

```bash
git clone https://github.com/becastellani/ClientesCrud.git
```

### Passo 4: Adicionar Dependências do Maven

No arquivo `pom.xml`, adicione as dependências necessárias para o Hibernate e PostgreSQL. Exemplo:

```xml
<dependencies>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.2.23</version>
        </dependency>

        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>5.4.33.Final</version>
        </dependency>

        <dependency>
            <groupId>org.mindrot</groupId>
            <artifactId>jbcrypt</artifactId>
            <version>0.4</version>
        </dependency>

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.7.2</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.7.2</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>3.7.7</version>
            <scope>test</scope>
        </dependency>
</dependencies>
```

### Passo 5: Compile e Execute o Projeto

Compile o projeto com Maven (caso esteja utilizando Maven):

```bash
mvn clean install
```

Depois, execute a classe principal (`Main.java`) para rodar o sistema:

O sistema será executado no terminal e você poderá interagir com ele conforme as opções fornecidas.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

```
ClientesCrud/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── org/
│   │   │   │   ├── example/
│   │   │   │   │   ├── Main.java             # Classe principal que executa o sistema
│   │   │   │   │   ├── controller/
│   │   │   │   │   │   └── ClienteController.java  # Controlador para gerenciar operações de cliente
│   │   │   │   │   ├── model/
│   │   │   │   │   │   └── Cliente.java        # Modelo de dados do cliente
│   │   │   │   │   ├── dao/
│   │   │   │   │   │   └── ClienteDAO.java     # Classe para acesso ao banco de dados
│   │   │   │   │   └── util/
│   │   │   │   │       └── HibernateUtil.java  # Utilitário para configurar e gerenciar a sessão Hibernate
│   │   └── resources/  # Arquivo de configuração Hibernate (hibernate.cfg.xml)
├── pom.xml              # Arquivo de configuração Maven
└── README.md           
```

## Explicação das Escolhas Técnicas

- **Java 8**: A escolha do Java 8 é devido à sua estabilidade.
  
- **Hibernate**: O Hibernate foi escolhido como framework ORM para facilitar a persistência de dados no PostgreSQL, eliminando a necessidade de escrever SQL manualmente para operações CRUD.

- **PostgreSQL**: PostgreSQL foi utilizado como banco de dados relacional devido à sua robustez, escalabilidade e conformidade com os padrões SQL.

- **Estrutura MVC**: O projeto segue a arquitetura **Model-View-Controller (MVC)**, onde:
  - **Model**: A classe `Cliente` representa os dados do cliente.
  - **View**: A interação com o usuário é feita via terminal utilizando `Scanner`.
  - **Controller**: A classe `ClienteController` gerencia as operações de CRUD no modelo de cliente.
  - **DAO**: A classe `ClienteDAO` é responsável pela interação com o banco de dados utilizando Hibernate.
 

## Testes Automatizados

O sistema conta com uma suíte de testes automatizados utilizando **JUnit** e **Mockito** para garantir a confiabilidade das operações. Os testes são realizados sobre o controlador `ClienteController`, verificando as funcionalidades de **adicionar**, **atualizar**, **excluir** e **listar clientes**. Além disso, também são testadas situações de erro, como CPF inválido e cliente já existente.

### Principais Casos de Teste:

- **Adicionar Cliente**: Verifica a criação de um novo cliente, incluindo validações como CPF válido e cliente não existente.
- **Atualizar Cliente**: Testa a atualização de um cliente existente, garantindo que os dados sejam corretamente alterados.
- **Excluir Cliente**: Verifica a exclusão de um cliente do banco de dados.
- **Listar Clientes**: Testa o retorno correto da lista de todos os clientes cadastrados.


## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---
