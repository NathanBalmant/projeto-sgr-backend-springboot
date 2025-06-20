# Sistema de Gerenciamento de República (SGR) - Backend

API REST desenvolvida com Spring Boot para gerenciamento de finanças e despesas em repúblicas estudantis ou residências compartilhadas.

---

## 📌 Descrição do Projeto

O Sistema de Gerenciamento de República (SGR) tem como objetivo **facilitar o controle de despesas compartilhadas**, permitindo que moradores de uma mesma residência possam:

- Cadastrar moradores
- Registrar contas e despesas mensais
- Realizar o rateio dos valores entre os moradores
- Acompanhar o saldo individual de cada morador
- Visualizar extratos e painéis de informações

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security**
- **JWT (JSON Web Token) para autenticação**
- **Banco de Dados H2 (em desenvolvimento)**  
*(vai ser alterado para MySQL/PostgreSQL em produção)*

---

## ✅ Funcionalidades principais

- **Cadastro de Moradores**
- **Autenticação e Controle de Acesso**
- **Cadastro de Tipos de Conta**
- **Registro de Contas e Rateio**
- **Histórico de Pagamentos**
- **Replicação de Contas**
- **Visualização de Saldos**
- **Geração de Extratos**
- **Dashboard com informações de gastos**

---

## 📂 Estrutura do Projeto

```
src/
 ├─ main/
 │    ├─ java/
 │    │    └─ com/
 │    │         └─ sgr/
 │    │              ├─ controller/
 │    │              ├─ service/
 │    │              ├─ repository/
 │    │              └─ model/
 │    └─ resources/
 │         └─ application.properties
 └─ test/
```

---

## 🔐 Autenticação

A API utiliza **JWT (JSON Web Token)** para autenticação.

### Fluxo básico:

1. O usuário realiza o login.
2. O sistema gera um token JWT.
3. As requisições subsequentes devem incluir o token no header `Authorization: Bearer <token>`.

---

## 📋 Como Executar o Projeto Localmente

1. Clone o repositório:

```bash
git clone https://github.com/NathanBalmant/projeto-sgr-backend-springboot.git
```

2. Acesse o diretório do projeto:

```bash
cd projeto-sgr-backend-springboot
```

3. Garanta que você tem o **Java 17** e o **Maven** instalados.

4. Rode a aplicação:

```bash
mvn spring-boot:run
```

5. A API estará disponível em:

```
http://localhost:8080
```

---

## 🛠️ Endpoints Principais (exemplos)

| Método | Endpoint | Descrição |
|---|---|---|
| POST | /moradores | Cadastro de morador |
| POST | /auth/login | Login e geração de token |
| GET | /contas | Listagem de contas |
| POST | /contas | Cadastro de nova conta |
| GET | /saldo | Exibição de saldos por morador |

*(Endpoints completos serão documentados com Swagger em breve.)*

---

## 📈 Status do Projeto

✅ Em desenvolvimento  
✅ Implementação de endpoints principais em andamento  
✅ Testes de autenticação funcionando  
🛠️ Próxima etapa: Implementação completa dos casos de uso (RF-001 a RF-008)

---

## 👥 Contribuidores

- [Nathan Balmant](https://github.com/NathanBalmant)
- [Samuel Oliveira](https://github.com/SamuelOliveira-lab)
- [Mateus Cunha](https://github.com/MateusCunha-Dev)

---

## 📄 Licença

Este projeto é de uso livre para fins educacionais e acadêmicos.  
Para uso comercial, entre em contato com os desenvolvedores.
