# Documentação do Pipeline CI/CD

## Objetivo
Este pipeline automatiza o teste, build, publicação de imagem Docker, deploy em servidor e criação de release para o projeto **ong-patinhas-app**.

## Fluxo do Pipeline

1. **Testes**
   - Disparado em qualquer push para qualquer branch.
   - Também em pull requests para a branch `main`.
   - Executa testes automatizados com Maven usando o perfil `test`.

2. **Build**
   - Executado após os testes.
   - Só ocorre em pushes ou PRs direcionados à branch `main`.
   - Constrói o projeto com Maven (pulando testes).
   - Faz login no Docker Hub.
   - Constrói a imagem Docker multi-stage e publica no Docker Hub.

3. **Deploy**
   - Executado após o build, somente em pushes para a branch `main` (após merge).
   - Realiza conexão SSH com o servidor.
   - Atualiza o arquivo `.env` remoto com variáveis de ambiente do GitHub Secrets.
   - Executa o script de deploy (`deploy.sh`).

4. **Release**
   - Executado após o deploy, somente na branch `main`.
   - Cria uma Release no GitHub, usando o número do workflow como tag.

## Arquivos Importantes

### 1. **Dockerfile**
Descreve como a imagem do app é construída. Exemplo típico:

```
# Stage 1: build com Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: imagem runtime com JDK
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]OINT ["java", "-jar", "/app.jar"]
```

### 2. **Workflow do GitHub Actions**
Arquivo: `.github/workflows/build-and-test.yml`

#### Principais Secrets Usados
- `MP_ACCESS_TOKEN`, `MP_PUBLIC_KEY` (para testes e deploy)
- `DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD` (para build e deploy)
- `DOCKERHUB_USERNAME`, `DOCKERHUB_TOKEN` (para publicar imagem Docker)
- `SERVER_IP`, `SERVER_USER`, `SERVER_SSH_KEY` (para deploy via SSH)
- `GITHUB_TOKEN` (para criar releases)

## Como Funciona

- **Qualquer push**: Roda os testes automatizados.
- **Pull request para main**: Roda testes e build.
- **Push ou merge na main**:
  1. Roda testes e build.
  2. Publica imagem Docker no Docker Hub.
  3. Faz deploy automático no servidor via SSH.
  4. Cria uma release no GitHub.

## Como Configurar

1. **Secrets**
   - Configure todos os secrets citados acima em Settings > Secrets and variables
2. **Docker Hub**
   - Crie uma conta e repositório.
   - Gere um token de acesso e cadastre como secret.
3. **Servidor**
   - Gere chave SSH e adicione ao servidor.
   - Cadastre IP, usuário e chave privada como secrets.
   - O script `deploy.sh` deve estar no diretório `/home/ubuntu` no servidor.

## Monitoramento e Erros

- O progresso e logs do pipeline podem ser acompanhados na aba **Actions** do GitHub.
- Falhas são mostradas com detalhes em cada etapa.
- Permissões dos secrets.
- Versão do JDK e Maven.
- Configuração do Dockerfile.
- Chave SSH correta para deploy.

## Dicas

- **Atualize as variáveis de ambiente e secrets sempre que necessário.**
- **Mantenha o Dockerfile bem simples e otimizado para produção.**
- **Documente scripts de deploy para facilitar manutenção.**
