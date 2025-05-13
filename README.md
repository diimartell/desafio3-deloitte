# 📅 Reserva de Salas API

API REST desenvolvida com Spring Boot para gerenciamento de reservas de salas de reunião. Permite o cadastro de salas, agendamento de reservas, cancelamentos e listagem de reservas futuras, com validações para evitar conflitos de horários.

---

## 🚀 Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* H2 Database (banco de dados em memória)
* Bean Validation (JSR 380)
* MapStruct

---

## ⚙️ Como executar o projeto

### Pré-requisitos

* Java 17+
* Maven 3.8+

### Passos

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/reserva-salas-api.git
cd reserva-salas-api
```

2. Compile e rode o projeto:

```bash
./mvnw spring-boot:run
```

3. Acesse a aplicação via:

```
http://localhost:8080
```

4. Acesse o banco H2 (console):

```
http://localhost:8080/h2-console
```

* **JDBC URL:** `jdbc:h2:mem:testdb`
* **Username:** `sa`
* **Password:** *(em branco)*

---

## 🧪 Executando os testes

Execute os testes com:

```bash
./mvnw test
```

Inclui:

* Testes de unidade com JUnit 5 e Mockito
* Testes de controladores com Spring MockMvc
* Testes de exceções customizadas

---

## 📌 Endpoints disponíveis

### Salas

| Método | Endpoint      | Descrição            |
| ------ | ------------- | -------------------- |
| POST   | `/salas`      | Cadastra nova sala   |
| GET    | `/salas`      | Lista todas as salas |
| GET    | `/salas/{id}` | Busca sala por ID    |
| PUT    | `/salas/{id}` | Edita uma sala       |
| DELETE | `/salas/{id}` | Remove uma sala      |

### Reservas

| Método | Endpoint              | Descrição                     |
| ------ | --------------------- | ----------------------------- |
| POST   | `/reservas`           | Cria uma nova reserva         |
| GET    | `/reservas/futuras`   | Lista reservas futuras        |
| GET    | `/reservas/sala/{id}` | Lista reservas por ID da sala |
| DELETE | `/reservas/{id}`      | Cancela uma reserva           |

---

## ✅ Regras de Negócio

* Não é permitido reservar uma sala com horários sobrepostos.
* A data/hora de início deve ser anterior à data/hora de término.
* Não é possível criar reservas para datas/hora no passado.
* O nome da sala deve ser único.
