# 🏋️‍♂️ Personal Trainer System

Sistema de gestão para **Personal Trainers** e **Atletas**, desenvolvido com **arquitetura de microsserviços**, foco em **Clean Code**, **boas práticas de design**, **segurança** e **escalabilidade**.

O projeto tem como objetivo facilitar o acompanhamento de atletas, avaliações físicas, recomendações personalizadas e a gestão do dia a dia do personal trainer.

---

## 📌 Visão Geral

O **Personal Trainer System** é composto por múltiplos microsserviços independentes, cada um responsável por um contexto específico do domínio, seguindo princípios de:

- **DDD (Domain-Driven Design)**
- **Arquitetura Hexagonal (Ports & Adapters)**
- **Clean Code e SOLID**
- **Comunicação assíncrona e desacoplada**

Principais funcionalidades:
- Gestão de atletas e personal trainers
- Avaliações físicas (bioimpedância)
- Recomendações automáticas baseadas em avaliações
- Autenticação e autorização centralizadas
- Comunicação assíncrona entre serviços

---

## 🧱 Arquitetura

### 📐 Padrões e Conceitos Utilizados
- Microsserviços
- Arquitetura Hexagonal
- DDD
- APIs RESTful
- Event-driven architecture
- Clean Code e boas práticas

### 🔌 Comunicação entre serviços
- Comunicação **assíncrona via eventos** utilizando **RabbitMQ**
- Redução de acoplamento entre microsserviços
- Processamento orientado a eventos de domínio

---

## 🧩 Microsserviços

### 🔐 Auth Service
Responsável por autenticação e autorização.
- Integração com **Keycloak**
- Emissão e validação de tokens JWT
- Controle de roles (`ATLETA`, `PERSONAL_TRAINER`)

---

### 👤 Gestão (Management Service)
Responsável pelo core do sistema.
- Cadastro e gerenciamento de atletas
- Associação atleta ↔ personal trainer
- Ativação e desativação de usuários
- Dados para menu e dashboard

---

### 📊 Bioimpedance Assessment Service
- Registro de avaliações físicas
- Armazenamento de dados de bioimpedância
- Publicação de eventos de avaliação criada

---

### 🧠 Bioimpedance Recommendation Service
- Consome eventos de avaliações físicas
- Gera recomendações automáticas
- Processamento desacoplado do serviço de avaliação

---

## 🔒 Segurança

- Autenticação baseada em **JWT**
- Integração com **Keycloak**
- Autorização baseada em **roles**
- Validação de acesso centralizada no backend

Padrões adotados:
- Endpoint `/me` para dados do usuário logado
- Separação clara de permissões por perfil

---

## 🗄️ Persistência

- **MySQL** como banco de dados relacional
- **JPA / Hibernate**
- Cada microsserviço possui seu próprio banco
- Mapeamento ORM seguindo boas práticas

---

## 🧪 Testes

- **JUnit 5**
- **Mockito**

Testes focados em:
- Regras de negócio
- Services
- Controllers

Objetivo:
> Garantir confiabilidade, segurança em refatorações e qualidade do domínio.

---

## 📦 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web (MVC)
- Spring Data JPA
- Spring Security
- RabbitMQ
- MySQL
- Keycloak
- Docker & Docker Compose
- Lombok
- Swagger / OpenAPI

---

## 📑 Documentação da API

- Documentação gerada com **Swagger / OpenAPI**
- Endpoints organizados por contexto
- Exemplos de request e response

Acesso padrão:
