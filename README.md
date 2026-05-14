# 📦 Inventário API

API de gestão de inventário desenvolvida com **Spring Boot 3** e **MySQL**. O projeto permite o gerenciamento completo de produtos através de uma interface documentada e robusta.

---

## 🛠 Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.2.5**
* **Spring Data JPA** (Persistência de dados)
* **MySQL** (Banco de Dados)
* **SpringDoc OpenAPI (Swagger)** (Documentação da API)
* **Maven** (Gerenciamento de dependências)

---

## 🛡️ Diferenciais e Boas Práticas

Este projeto foi construído seguindo padrões de mercado para garantir segurança e manutenibilidade:

* **Padrão DTO (Data Transfer Object):** Uso de **Java Records** para isolar a camada de banco de dados da camada de exibição (`ProdutoRequestDTO` e `ProdutoResponseDTO`).
* **Tratamento Global de Erros:** Implementação de um `GlobalExceptionHandler` com `@RestControllerAdvice` para capturar exceções e retornar respostas limpas e padronizadas.
* **Validação de Dados:** Uso do **Bean Validation** (`jakarta.validation`) para garantir que campos obrigatórios e regras de negócio (como preço positivo) sejam respeitados.
* **Clean Code:** Separação clara de responsabilidades entre Controllers, Models, DTOs e Exceptions.

---

## 🚀 Como Rodar o Projeto

### Pré-requisitos
* Java 21 instalado.
* MySQL Server rodando localmente.

### Passos
1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/inventario-api.git](https://github.com/seu-usuario/inventario-api.git)
    ```
2.  **Configure o banco de dados** no arquivo `src/main/resources/application.properties` (ou `.yml`):
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/inventario_db
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
    ```
3.  **Execute o projeto** via terminal ou pela sua IDE:
    ```bash
    ./mvnw spring-boot:run
    ```

---

## 📖 Documentação da API (Swagger)

A API possui documentação interativa que permite testar todos os endpoints em tempo real.

**Acesse em:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

![Interface do Swagger](assets/swagger-ui.png)

---

## 🛡️ Tratamento de Erros de Validação

A API está preparada para lidar com requisições inválidas, retornando mensagens claras para o usuário. 

**Exemplo de erro ao tentar cadastrar produto sem nome:**
![Exemplo de Erro Tratado](assets/erro-validation.png)

---

## 🏗 Endpoints (CRUD)

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/produtos` | Cadastra um novo produto (Usa `ProdutoRequestDTO`) |
| `GET` | `/produtos` | Lista todos os produtos (Retorna `ProdutoResponseDTO`) |
| `GET` | `/produtos/{id}` | Busca um produto pelo ID |
| `PUT` | `/produtos/{id}` | Atualiza os dados de um produto |
| `DELETE` | `/produtos/{id}` | Remove um produto do sistema |

---

## 📂 Estrutura de Pastas

```text
src/main/java/com/gestao/inventario_api/
├── controller/   # Camada de controle (Endpoints)
├── dto/          # Objetos de transferência (Records)
├── exception/    # Tratamento global de erros
├── model/        # Entidades do banco de dados (JPA)
└── repository/   # Interface de comunicação com MySQL
