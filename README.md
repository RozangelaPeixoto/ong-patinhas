# 🐾 ONG Patinhas - Sistema de Adoção e Doações

> Plataforma desenvolvida como parte da disciplina de DevOps, com o objetivo de promover a adoção de animais e facilitar doações para ONGs. O sistema contempla frontend e backend integrados, além de infraestrutura em nuvem e pipelines automatizados de CI/CD.

---

## 📖 Sumário
- [Visão Geral](#-visão-geral)
- [Estrutura da Documentação](#-estrutura-da-documentação)
- [Quick Start: Executando Localmente](#-quick-start-executando-localmente)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Equipe](#-equipe)

---

## 🔎 Visão Geral

- Usuário pode visualizar cães disponíveis, manifestar interesse em adoção e realizar doações online via Mercado Pago.
- Interface responsiva desenvolvida com Thymeleaf, HTML, CSS e JavaScript.
- Backend robusto em Java com Spring Boot, persistência via JPA e migrações automáticas com Flyway.
- Banco de dados MySQL em ambiente seguro na nuvem (AWS RDS).
- Deploy automatizado com Docker e pipelines CI/CD via GitHub Actions.
- Monitoramento, alta disponibilidade e segurança garantidos por ferramentas como Nginx, SSL, Prometheus, Grafana e Monit.
- Integração de serviços externos para pagamentos e validação de segurança (reCAPTCHA).

---

## 📂 Estrutura da Documentação
A documentação detalhada foi dividida em arquivos específicos:

- [📘 Desenvolvimento (`dev.md`)](./docs/dev.md)  
  Frontend, backend, instalação, testes e instruções para desenvolvedores.

- [⚙️ CI/CD (`ci-cd.md`)](./docs/ci-cd.md)  
  Pipelines de automação, integração e deploy contínuo.

- [🖥️ Infraestrutura (`infra.md`)](./docs/infra.md)  
  Servidores, monitoramento, operações e arquitetura.

- [👥 Equipe (`team.md`)](./docs/team.md)  
  Divisão da equipe, papéis e tarefas. 

---

## 🚀 Quick Start: Executando Localmente

1. **Pré-requisitos**
    - Java 21+
    - Maven 3.6+
    - MySQL instalado e rodando

2. **Clone o repositório**
   ```bash
   git clone https://github.com/seu-usuario/ong-patinhas.git
   cd ong-patinhas
   ```

3. **Configure o banco de dados**
    - Crie o banco:
      ```sql
      CREATE DATABASE ongpatinhas CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
      ```
    - Ajuste as credenciais em `.env` ou `application.yml`

4. **Configure variáveis de ambiente**
    - Copie e edite o arquivo `.env` conforme exemplo em `docs/dev.md`

5. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```

6. **Acesse no navegador**
    - [http://localhost:8080](http://localhost:8080)

> Para instruções detalhadas de configuração, testes e deploy, consulte o arquivo [`docs/dev.md`](./docs/dev.md).

---

## 🛠️ Tecnologias Utilizadas

- Frontend: Thymeleaf, HTML, CSS, JS
- Backend: Java, Spring Boot
- Banco de Dados: MySQL, H2
- Infra: Docker, AWS EC2, RDS, CloudWatch
- CI/CD: GitHub Actions

---

## 👥 Equipe

- Frontend
- Backend
- CI/CD
- Infraestrutura

> A lista detalhada de integrantes e papéis está em [`docs/team.md`](./docs/team.md).


