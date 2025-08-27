# 🐾 ONG Patinhas - Desenvolvimento

![Java](https://img.shields.io/badge/Java-21-blue?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.6+-orange?style=for-the-badge&logo=apachemaven&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1.2-green?style=for-the-badge&logo=thymeleaf&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-5.5+-blue?style=for-the-badge&logo=mysql&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-10.9.1-brightgreen?style=for-the-badge&logo=flyway&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-5-red?style=for-the-badge&logo=junit5&logoColor=white)
![Mockito](https://img.shields.io/badge/Mockito-4.11.0-yellow?style=for-the-badge&logo=mockito&logoColor=white)

---

## 🚀 Tecnologias Utilizadas

### Backend
- **Java 21** - Linguagem de programação
- **Spring Boot 3.5.4** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Flyway** - Versionamento do banco de dados
- **Maven** - Gerenciamento de dependências

### Frontend
- **Thymeleaf** - Template engine
- **HTML5/CSS3** - Estrutura e estilização
- **JavaScript** - Interatividade

### Banco de Dados
- **MySQL** - Banco de dados principal
- **H2** - Banco para testes

### Integrações
- **Mercado Pago SDK** - Processamento de pagamentos
- **Google reCAPTCHA** - Validação de segurança

### Ferramentas de Desenvolvimento
- **Lombok** - Redução de boilerplate
- **Spring DevTools** - Hot reload durante desenvolvimento
- **JUnit 5** - Testes unitários
- **Mockito** - Mocks para testes

---

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/org/ongpatinhas/
│   │   ├── config/          # Configurações da aplicação
│   │   ├── controller/      # Controladores REST e Web
│   │   ├── dto/             # Objetos de transferência de dados
│   │   ├── mapper/          # Mapeadores de entidades
│   │   ├── model/           # Entidades JPA
│   │   ├── repository/      # Repositórios de dados
│   │   └── service/         # Lógica de negócio
│   └── resources/
│       ├── db/migration/    # Scripts de migração Flyway
│       ├── static/          # Arquivos estáticos (CSS, JS, imagens)
│       ├── templates/       # Templates Thymeleaf
│       └── application.yml  # Configurações da aplicação
└── test/                    # Testes unitários e de integração
```

---

## ⚙️ Configuração do Ambiente

### Pré-requisitos
- Java 21 ou superior
- Maven 3.6 ou superior
- MySQL 5.5 ou superior
- Git

### Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis:

```env
# Banco de Dados
DATABASE_URL=jdbc:mysql://localhost:3306/ongpatinhas
DATABASE_USERNAME=seu_usuario
DATABASE_PASSWORD=sua_senha

# Mercado Pago
MP_ACCESS_TOKEN=seu_access_token_mp
MP_PUBLIC_KEY=sua_public_key_mp

# Google reCAPTCHA
RC_SECRET_KEY=sua_secret_key_recaptcha
```

### Configuração do Banco de Dados

1. Crie o banco de dados MySQL:
```sql
CREATE DATABASE ongpatinhas CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. As tabelas serão criadas automaticamente pelo Flyway na primeira execução.

---

## 🔧 Instalação e Execução

1. **Clone o repositório:**
```bash
git clone https://github.com/seu-usuario/ong-patinhas.git
cd ong-patinhas
```

2. **Configure as variáveis de ambiente** (veja seção anterior)

3. **Execute a aplicação:**
```bash
mvn spring-boot:run
```

4. **Acesse a aplicação:**
   - URL: http://localhost:8080
   - A aplicação estará disponível no navegador

---

## 🧪 Executando os Testes

### Todos os testes:
```bash
mvn test
```

### Teste específico:
```bash
mvn test -Dtest=NomeDoTeste
```

### Executar testes com verbose:
```bash
mvn test -Dtest=NomeDoTeste -X
```

---

## 📊 Funcionalidades Principais

### 🏠 Página Inicial
- Apresentação da ONG
- Informações de contato

### 🐕 Sistema de Adoção
- Visualização de cães cadastrados
- Formulário de interesse em adoção
- Validação com reCAPTCHA

### 💰 Sistema de Doações
- Integração com Mercado Pago
- Processamento seguro de pagamentos
- Webhooks para confirmação automática

### 🔒 Segurança
- Validação de formulários
- Proteção contra spam com reCAPTCHA
- Sanitização de dados de entrada

---

## 🗃️ Estrutura do Banco de Dados

### Tabelas Principais:
- **dogs** - Informações dos cães
- **adoption_interest** - Manifestações de interesse
- **donations** - Registro de doações

As migrações estão localizadas em `src/main/resources/db/migration/`.

---

## 🔄 API Endpoints

### Frontend (Thymeleaf)
- `GET /` - Página inicial
- `GET /adocao` - Listagem de cães para adoção
- `GET /quemsomos` - Página "Quem Somos"
- `GET /doacao` - Formulário de doação
- `GET /formulario-adocao/{id}` - Formulário de interesse em adoção
- `GET /mensagem` - Página de mensagem após envio de interesse
- `GET /sucesso, GET /cancelado, GET /pendente` - Retorno do pagamento (doação)

### API REST / Webhook
- `POST /formulario-adocao` — Envia interesse em adoção (via formulário)
- `POST /doacao` — Inicia processo de doação (via formulário)
- `POST /webhook` — Webhook Mercado Pago (REST, recebe notificações)

---

## 🚢 Deploy

### Docker
```bash
# Build da imagem
docker build -t ong-patinhas .

# Execução do container
docker run -p 8080:8080 --env-file .env ong-patinhas
```

### Configurações de Produção
- Configure as variáveis de ambiente no servidor
- Use um banco MySQL dedicado
- Configure proxy reverso (Nginx/Apache)
- Habilite HTTPS

---

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

---

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
