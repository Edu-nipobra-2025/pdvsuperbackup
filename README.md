# 🏪 PDV Posto de Combustível

Sistema de Ponto de Venda (PDV) para Posto de Combustível desenvolvido em Java com Spring Boot (Backend) e Swing (Frontend).

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-18.0-blue)
![Maven](https://img.shields.io/badge/Maven-3.8+-red)

## 📋 Índice

- [Características](#características)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Configuração do Banco de Dados](#configuração-do-banco-de-dados)
- [Como Executar](#como-executar)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [API Endpoints](#api-endpoints)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)

## ✨ Características

- ✅ **Backend REST API** completo com Spring Boot
- ✅ **Frontend Desktop** com Java Swing
- ✅ **Banco de Dados PostgreSQL** 18.0
- ✅ **Documentação API** com Swagger UI
- ✅ **CRUD Completo** para todas as entidades
- ✅ **Validação** de CPF/CNPJ
- ✅ **Maven** para gerenciamento de dependências

## 📦 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

### ✅ Obrigatório:
- **Java 17** ou superior ([Download JDK 17](https://www.oracle.com/java/technologies/downloads/#java17))
- **Maven 3.8+** ([Download Maven](https://maven.apache.org/download.cgi))
- **PostgreSQL 16+** ([Download PostgreSQL](https://www.postgresql.org/download/))
- **Git** ([Download Git](https://git-scm.com/downloads))

### ✅ Recomendado:
- **IntelliJ IDEA** (Community ou Ultimate) ([Download](https://www.jetbrains.com/idea/download/))
- **pgAdmin 4** para gerenciar o PostgreSQL

## 🚀 Instalação

### 1. Clone o Repositório

```bash
git clone https://github.com/Sidney-Emanuel-Oliveira/pdv-posto-combustivel.git
cd pdv-posto-combustivel
```

### 2. Verifique as Versões Instaladas

```bash
java -version    # Deve mostrar Java 17+
mvn -version     # Deve mostrar Maven 3.8+
psql --version   # Deve mostrar PostgreSQL 16+
```

### 3. Configure o Projeto no IntelliJ IDEA

**⚠️ IMPORTANTE - Leia antes de abrir o projeto:**

1. **Abra o IntelliJ IDEA**
2. Clique em `File` → `Open`
3. **Selecione a pasta RAIZ** `pdvpostocombustivel` (onde está o pom.xml pai)
4. Clique em **"Open as Project"**
5. O IntelliJ vai detectar automaticamente:
   - ✅ O projeto Maven multi-módulo
   - ✅ Os dois módulos: `pdv-posto-combustivel` (Backend) e `JavaPoo-Front-End-main` (Frontend)
   - ✅ Todas as dependências serão baixadas automaticamente

**Se o IntelliJ não reconhecer automaticamente:**
1. Clique com botão direito no `pom.xml` da raiz → `Add as Maven Project`
2. Aguarde o download das dependências (canto inferior direito)
3. Vá em `File` → `Project Structure` → `Modules`
4. Verifique se ambos os módulos aparecem corretamente

**Estrutura de Módulos esperada:**
```
📁 pdv-posto-combustivel-parent (raiz)
  ├── 📦 pdv-posto-combustivel (Backend)
  └── 📦 JavaPoo-Front-End-main (Frontend)
```

## 🗄️ Configuração do Banco de Dados

### Passo 1: Criar o Banco de Dados

Abra o **pgAdmin** ou o **psql** e execute:

```sql
-- Criar o banco de dados
CREATE DATABASE pdvpostocombustivel;
```

### Passo 2: Executar os Scripts de Inicialização

Execute os scripts SQL na ordem:

```bash
# No terminal do PostgreSQL (psql):
\c pdvpostocombustivel
\i _docs_e_scripts/PASSO1_CRIAR_BANCO.sql
\i _docs_e_scripts/PASSO2_CRIAR_TABELAS_E_DADOS.sql
```

**Ou usando pgAdmin:**
- Conecte-se ao banco `pdvpostocombustivel`
- Abra os arquivos SQL em `_docs_e_scripts/`
- Execute na ordem: `PASSO1_CRIAR_BANCO.sql` → `PASSO2_CRIAR_TABELAS_E_DADOS.sql`

### Passo 3: Configurar Credenciais do Banco

1. Navegue até: `pdv-posto-combustivel/src/main/resources/`
2. Copie o arquivo `application.properties.example` para `application.properties`
3. Edite `application.properties` e configure sua senha:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/pdvpostocombustivel
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA_AQUI  # ← Coloque sua senha do PostgreSQL
```

**⚠️ IMPORTANTE:** Nunca faça commit do arquivo `application.properties` com sua senha real!

## 💻 Como Executar

### Opção 1: Usando IntelliJ IDEA (Recomendado)

1. **Abra o projeto** no IntelliJ IDEA
   - `File` → `Open` → Selecione a pasta `pdvpostocombustivel`

2. **Aguarde** o IntelliJ baixar as dependências Maven (pode levar alguns minutos)

3. **Execute o Backend:**
   - No canto superior direito, selecione a configuração **"Backend - PDV Posto Combustivel"**
   - Clique no botão verde ▶️ (Run)
   - Aguarde até ver: `Started PdvpostocombustivelApplication`
   - ✅ Backend rodando em: http://localhost:8080
   - ✅ Swagger UI: http://localhost:8080/swagger-ui.html

4. **Execute o Frontend:**
   - Selecione a configuração **"Frontend - MainApp"**
   - Clique no botão verde ▶️ (Run)
   - A interface gráfica será aberta automaticamente

### Opção 2: Usando Linha de Comando

**Backend:**
```bash
cd pdv-posto-combustivel
mvn clean install
mvn spring-boot:run
```

**Frontend (em outro terminal):**
```bash
cd JavaPoo-Front-End-main
mvn clean install
mvn exec:java -Dexec.mainClass="br.com.pdvposto.view.MainApp"
```

### Opção 3: Usando os Scripts .bat (Windows)

**Backend:**
```bash
_docs_e_scripts\EXECUTAR_BACKEND.bat
```

**Frontend:**
```bash
_docs_e_scripts\EXECUTAR_FRONTEND.bat
```

## 📁 Estrutura do Projeto

```
pdvpostocombustivel/
├── pdv-posto-combustivel/        # 🔧 Backend (Spring Boot)
│   ├── src/main/java/
│   │   └── com/br/pdvpostocombustivel/
│   │       ├── api/              # Controllers REST
│   │       ├── domain/           # Entidades e Repositórios
│   │       ├── config/           # Configurações (CORS, etc)
│   │       └── util/             # Utilitários (Validações)
│   ├── src/main/resources/
│   │   ├── application.properties         # Configuração principal
│   │   └── application.properties.example # Template de configuração
│   └── pom.xml                   # Dependências Maven
│
├── JavaPoo-Front-End-main/       # 🖥️ Frontend (Swing)
│   ├── src/main/java/
│   │   └── br/com/PdvFrontEnd/
│   │       ├── view/             # Telas GUI
│   │       ├── service/          # Comunicação com Backend
│   │       ├── model/            # Modelos de dados
│   │       └── dto/              # Data Transfer Objects
│   └── pom.xml
│
└── _docs_e_scripts/              # 📚 Documentação e Scripts
    ├── PASSO1_CRIAR_BANCO.sql
    ├── PASSO2_CRIAR_TABELAS_E_DADOS.sql
    ├── EXECUTAR_BACKEND.bat
    ├── EXECUTAR_FRONTEND.bat
    └── [outros guias e scripts]
```

## 🌐 API Endpoints

Acesse a documentação completa da API em:
**http://localhost:8080/swagger-ui.html**

### Principais Endpoints:

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/pessoas` | Lista todas as pessoas |
| POST | `/api/pessoas` | Cria nova pessoa |
| GET | `/api/produtos` | Lista todos os produtos |
| POST | `/api/produtos` | Cria novo produto |
| GET | `/api/estoque` | Lista estoque |
| POST | `/api/estoque` | Adiciona ao estoque |
| GET | `/api/precos` | Lista preços |
| POST | `/api/precos` | Define novo preço |

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Data JPA**
- **PostgreSQL 18.0**
- **Hibernate**
- **Swagger/OpenAPI**
- **Maven**

### Frontend
- **Java Swing**
- **HTTP Client** para comunicação com API

### Banco de Dados
- **PostgreSQL 18.0**
- **pgAdmin 4**

## ❓ Problemas Comuns

### ❌ Erro: "Port 8080 already in use"
**Solução:** Execute o script para liberar a porta:
```bash
_docs_e_scripts\LIBERAR_PORTA_8080.bat
```

### ❌ Erro: "Connection refused to PostgreSQL"
**Solução:** 
1. Verifique se o PostgreSQL está rodando
2. Confirme as credenciais em `application.properties`
3. Teste a conexão com pgAdmin

### ❌ Erro: "Failed to execute goal"
**Solução:** Limpe o Maven e recompile:
```bash
mvn clean install -U
```

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👤 Autor

**Sidney Emanuel Oliveira**
- GitHub: [@Sidney-Emanuel-Oliveira](https://github.com/Sidney-Emanuel-Oliveira)

## 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.

---

⭐ Se este projeto foi útil, considere dar uma estrela!
# pdvsuperbackup
