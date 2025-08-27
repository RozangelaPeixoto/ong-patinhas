# 👥 Equipes do Projeto

## 🎯 Gestão

**Participantes:**  
Eric

### 🛠️ Atividades

- Registro e atualização das tarefas no Jira
- Gestão e coordenação das equipes
- Monitoramento e acompanhamento da execução das tarefas
- Validação e aprovação das demandas

## 💻 Desenvolvimento

**Participantes:**  
Jefferson • Jonathan • Rozangela

### 🛠️ Atividades

**Frontend (Jefferson & Jonathan)**
- Criação das páginas institucionais: _Index_, _Quem Somos_, _Doação_, _Adoção_
- Layout responsivo com CSS customizado
- Formulário de contato/adoção com validação
- Efeitos visuais e animações (confete, hover, accordion de dúvidas)
- Otimização de imagens e assets

**Backend (Rozangela)**
- Modelagem das entidades JPA (_Dog_, _AdoptionInterest_, _Donation_)
- API RESTful com Spring Boot
- Endpoints para listagem de cães, registro de interesse em adoção e doações
- Integração com MySQL (produção/desenvolvimento) e H2 (testes)
- Migrações automáticas de schema com Flyway
- Integração com Mercado Pago para doações
- Validação de formulários e proteção com Google reCAPTCHA
- Testes automatizados (JUnit 5, Mockito)
- Configuração de _profiles_ para ambientes de desenvolvimento, teste e produção

## ⚙️ CI/CD

**Participantes:**  
Amanda • Jesus • Wanessa

### 🛠️ Atividades

**Amanda**
- Implementação do _job_ de **Test** no GitHub Actions
- Estruturação do arquivo de workflow `.yml`
- Configuração do banco de dados **H2** para testes
- Organização dos _jobs_ (test, build, deploy, release)
- Criação de arquivos de configuração do pipeline

**Wanessa**
- Configuração dos **pipelines** e dependências entre _jobs_
- Implementação do _job_ de **Build** (compilação com Maven, variáveis de ambiente)
- _Job_ de **Release** para releases automáticas no GitHub
- Gerenciamento dos **secrets** (Docker, banco, servidor, Mercado Pago, GitHub Token)
- Integração das credenciais do **Mercado Pago** nos _jobs_

**Jesus**
- Criação da **branch CI-CD**
- Desenvolvimento do **Dockerfile**
- Configuração do repositório no **DockerHub**
- Implementação do _job_ de **Deploy** (deploy via SSH, variáveis de ambiente)
- Documentação do pipeline CI/CD

## 🏗️ Infraestrutura

**Participantes:**  
Thiago • Nathan • Anderson

### 🛠️ Atividades

**Thiago / Nathan**
- Criação de conta na **AWS** e configuração de usuários
- Provisionamento de **EC2** para hospedagem
- Configuração do **MySQL (RDS)**
- Gerenciamento de acesso via **SSH**
- Liberação de portas (HTTP, HTTPS, SSH)
- Registro de domínio
- Preparação do ambiente de **containers Docker**
- Script de automação (GitHub Actions) para atualização de containers

**Nathan**
- Instalação de **Docker, Nginx e Certbot** na EC2
- Configuração do **Nginx** como proxy reverso
- Habilitação de **SSL** via Let's Encrypt

**Anderson**
- Instalação de **Monit, Prometheus e Grafana**
- Configuração de alertas e dashboards
- Monitoramento de containers Docker e serviços

## 🔐 Comunicação & Segurança

- Uso do **Discord** para reuniões e troca de informações
- Utilização de **GitHub Secrets** para variáveis sensíveis e credenciais
