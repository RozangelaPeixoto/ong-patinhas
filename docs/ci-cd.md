# 🐾 ONG Patinhas - CI/CD

![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.6+-orange?style=for-the-badge&logo=apachemaven)
![Java](https://img.shields.io/badge/Java-21-blue?style=for-the-badge&logo=java)

## 🎯 Objetivo

Automatizar testes, build, publicação de imagem Docker, deploy no servidor e criação de release para o projeto **Ong Patinhas**.

## 🔄 Fluxo do Pipeline

1. **Testes**
   - Disparado em qualquer push para qualquer branch e em pull requests para a branch `main`.
   - Executa testes automatizados com Maven usando o profile `test`.

2. **Build**
   - Executado após os testes, apenas em pushes ou PRs para a branch `main`.
   - Constrói o projeto com Maven (pulando testes).
   - Faz login no Docker Hub.
   - Constrói e publica a imagem Docker multi-stage.

3. **Deploy**
   - Executado após o build, somente em pushes para a branch `main`.
   - Conecta via SSH ao servidor.
   - Atualiza o arquivo `.env` remoto com variáveis do GitHub Secrets.
   - Executa o script de deploy (`deploy.sh`).

4. **Release**
   - Executado após o deploy, somente na branch `main`.
   - Cria uma Release no GitHub usando o número do workflow como tag.

## 📁 Arquivos Importantes

### 🐳 Dockerfile

Exemplo de build multi-stage:

```dockerfile
# Stage 1: build com Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: runtime com JDK
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### ⚙️ Workflow do GitHub Actions

Arquivo: `.github/workflows/build-and-test.yml`

**Principais Secrets:**
- `MP_ACCESS_TOKEN`, `MP_PUBLIC_KEY`
- `DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD`
- `DOCKERHUB_USERNAME`, `DOCKERHUB_TOKEN`
- `SERVER_IP`, `SERVER_USER`, `SERVER_SSH_KEY`
- `GITHUB_TOKEN`

## 🛠️ Como Funciona

- **Qualquer push:** Executa testes automatizados.
- **Pull request para main:** Executa testes e build.
- **Push ou merge na main:**
    1. Testes e build.
    2. Publica imagem Docker.
    3. Deploy automático via SSH.
    4. Cria release no GitHub.

## 📝 Configuração

1. **Secrets**
    - Configure todos os secrets em _Settings > Secrets and variables_ no GitHub.

2. **Docker Hub**
    - Crie conta e repositório.
    - Gere token de acesso e cadastre como secret.

3. **Servidor**
    - Gere chave SSH e adicione ao servidor.
    - Cadastre IP, usuário e chave privada como secrets.
    - O script `deploy.sh` deve estar em `/home/ubuntu` no servidor.

## 📊 Monitoramento & Erros

- Acompanhe o progresso e logs na aba **Actions** do GitHub.
- Falhas são detalhadas em cada etapa.
- Verifique permissões dos secrets, versão do JDK/Maven, configuração do Dockerfile e chave SSH.

## 💡 Dicas

- Atualize variáveis de ambiente e secrets sempre que necessário.
- Mantenha o Dockerfile simples e otimizado.
- Documente scripts de deploy para facilitar manutenção.
