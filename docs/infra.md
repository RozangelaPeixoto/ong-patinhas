# 🐾 ONG Patinhas - Infraestrutura

![AWS](https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Nginx](https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=nginx&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Grafana](https://img.shields.io/badge/Grafana-F46800?style=for-the-badge&logo=grafana&logoColor=white)
![Prometheus](https://img.shields.io/badge/Prometheus-E6522C?style=for-the-badge&logo=prometheus&logoColor=white)
![Monit](https://img.shields.io/badge/Monit-2C3E50?style=for-the-badge)
![Certbot](https://img.shields.io/badge/Certbot-3A833C?style=for-the-badge&logo=letsencrypt&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)

## 🏗️ Arquitetura

### 📊 Diagrama da Arquitetura
<img width="530" height="481" alt="arquitetura" src="https://github.com/user-attachments/assets/06f4b139-5fde-4306-afd9-e5a95bdb4182" />

## ☁️ Hospedagem na AWS

### 🖥️ EC2
| Recurso       | Configuração           |
|---------------|------------------------|
| Tipo          | c7i-flex.large         |
| vCPUs         | 2                      |
| Memória RAM   | 4 GB                   |
| Armazenamento | 16 GB SSD              |
| SO            | Ubuntu Server 24.04    |

### 🗄️ RDS (Banco de Dados)
- Engine: **MySQL**  
- Segurança: **Subnet privada + grupo de segurança com portas liberadas**  
- Apenas a **EC2** tem acesso liberado ao banco

### 🌐 Domínio
- Registrado no **NoIP** (gratuito)  
- SSL configurado via **Let's Encrypt** (gratuito)

## ⚙️ Serviços

| Serviço        | Função |
|----------------|--------|
| **Docker**     | Containers: Ong-Patinhas, phpMyAdmin |
| **Nginx**      | Proxy reverso + SSL |
| **Certbot**    | Certificado SSL com Let's Encrypt |
| **Prometheus** | Coleta de métricas |
| **Grafana**    | Dashboards de monitoramento |
| **Monit**      | Reinício automático em falhas |
| **NoIP**       | Atualização dinâmica de DNS |

### 🌍 Endereços de Acesso

- **Inseguro:**  
  - `http://13.58.120.219:8080`

- **Seguro (HTTPS):**  
  - [https://ong-patinhas.ddns.net](https://ong-patinhas.ddns.net)  
  - [https://ong-patinhas.ddns.net/bancodedados](https://ong-patinhas.ddns.net/bancodedados)  
  - [https://ong-patinhas.ddns.net/monitoramento](https://ong-patinhas.ddns.net/monitoramento)

## 🔐 Extras de Segurança
- **Autostart**: Containers iniciam automaticamente com o sistema  
- **Alta disponibilidade**: Restart automático em falhas  
- **Acesso restrito**: Somente colaboradores autorizados na AWS  
- **Chaves SSH**: Autenticação segura sem senhas  
- **Firewall**: Apenas portas essenciais liberadas  
- **SSL/HTTPS**: Conexão criptografada em todos os serviços  
- **GitHub Secrets**: Armazenamento seguro de variáveis de ambiente e credenciais

## ✅ Resumo
A equipe **Infra** estruturou um ambiente **robusto, seguro e automatizado** para a hospedagem da aplicação **Ong-Patinhas** na **AWS**, garantindo:  
- **Disponibilidade**  
- **Monitoramento em tempo real**  
- **Segurança de dados e acessos**  
- **Automação de deploy e recuperação de falhas**
