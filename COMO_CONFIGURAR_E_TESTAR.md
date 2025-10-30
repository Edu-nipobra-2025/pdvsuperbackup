# 🚀 GUIA RÁPIDO: Como Configurar e Testar o Projeto

## 📥 PASSO 1: Baixar e Extrair

1. No GitHub, clique em **"Code"** → **"Download ZIP"**
2. Extraia o ZIP em uma pasta (ex: `C:\projetos\pdv-posto-combustivel`)

---

## 🔧 PASSO 2: Configurar a Senha do PostgreSQL

### ⚠️ ATENÇÃO: Você NÃO edita o `application.properties.example`!

Esse arquivo é apenas um **modelo/template**. Você deve:

### ✅ **Faça assim:**

1. **Abra a pasta:**
   ```
   pdv-posto-combustivel/src/main/resources/
   ```

2. **Copie o arquivo:**
   - De: `application.properties.example`
   - Para: `application.properties` (novo arquivo)

3. **Edite APENAS o `application.properties`:**
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/pdvpostocombustivel
   spring.datasource.username=postgres
   spring.datasource.password=SUA_SENHA_AQUI  ← COLOQUE SUA SENHA AQUI!
   ```

### 📝 **Exemplo:**
Se sua senha do PostgreSQL é `MinhaSenh@123`, ficaria:
```properties
spring.datasource.password=MinhaSenh@123
```

---

## 🗄️ PASSO 3: Criar o Banco de Dados no PostgreSQL

### Opção 1: Usando pgAdmin (Interface Gráfica)

1. **Abra o pgAdmin 4**
2. Conecte-se ao servidor PostgreSQL
3. Clique com botão direito em **"Databases"**
4. Selecione **"Create" → "Database..."**
5. Em **"Database"**, digite: `pdvpostocombustivel`
6. Clique em **"Save"**

### Opção 2: Usando SQL (Terminal/Query Tool)

1. Abra o **SQL Shell (psql)** ou **Query Tool** no pgAdmin
2. Execute o comando:
   ```sql
   CREATE DATABASE pdvpostocombustivel;
   ```

---

## ✅ PASSO 4: Como Saber Se Funcionou?

### 🔍 **Teste 1: Ver se o Banco Foi Criado**

**No pgAdmin:**
1. Olhe na lista de **"Databases"** no painel esquerdo
2. Você deve ver: `pdvpostocombustivel` ✅

**No psql (terminal):**
```sql
\l
```
Deve listar o banco `pdvpostocombustivel`

---

### 🔍 **Teste 2: Executar o Backend e Ver as Tabelas Sendo Criadas**

1. **Abra o IntelliJ IDEA**
2. **Abra o projeto** (pasta `pdvpostocombustivel`)
3. **Execute o Backend:**
   - Selecione **"Backend - PDV Posto Combustivel"** no dropdown superior
   - Clique no botão verde ▶️ (Run)

4. **Observe os logs!** Você vai ver algo assim:

   ```
   🟢 LOGS DE SUCESSO (PostgreSQL Funcionando):
   
   ✅ HikariPool-1 - Starting...
   ✅ HikariPool-1 - Added connection org.postgresql.jdbc.PgConnection@5c0e8484
   ✅ HikariPool-1 - Start completed.
   ✅ Started PdvpostocombustivelApplication in 3.2 seconds
   ```

5. **Se aparecer isso, funcionou!** 🎉

---

### 🔍 **Teste 3: Verificar as Tabelas no PostgreSQL**

**Depois que o Backend iniciar com sucesso:**

1. **No pgAdmin:**
   - Expanda: `pdvpostocombustivel` → `Schemas` → `public` → `Tables`
   
2. **Você deve ver essas tabelas criadas:**
   ```
   ✅ acesso
   ✅ contato
   ✅ custo
   ✅ estoque
   ✅ pessoa
   ✅ preco
   ✅ produto
   ```

3. **Para ver os dados (ainda vazios):**
   - Clique com botão direito em uma tabela (ex: `pessoa`)
   - Selecione **"View/Edit Data" → "All Rows"**

**Ou execute no Query Tool:**
```sql
-- Conectar ao banco
\c pdvpostocombustivel

-- Ver todas as tabelas
\dt

-- Ver dados de uma tabela específica
SELECT * FROM pessoa;
SELECT * FROM produto;
SELECT * FROM estoque;
```

---

### 🔍 **Teste 4: Acessar o Swagger UI (Interface da API)**

Com o Backend rodando:

1. **Abra o navegador**
2. **Acesse:** http://localhost:8080/swagger-ui.html
3. **Você vai ver a documentação completa da API** com todos os endpoints

4. **Teste um endpoint:**
   - Expanda **"pessoa-controller"**
   - Clique em **"GET /api/pessoas"**
   - Clique em **"Try it out"**
   - Clique em **"Execute"**
   - Vai retornar: `[]` (vazio, pois não tem dados ainda) ✅

---

### 🔍 **Teste 5: Usar o Frontend (Interface Gráfica)**

1. **No IntelliJ, selecione:** **"Frontend - MainApp"**
2. **Clique no botão verde** ▶️ (Run)
3. **A janela do aplicativo vai abrir** 🖥️
4. **Teste criando uma pessoa:**
   - Clique em **"Pessoas"**
   - Preencha os campos
   - Clique em **"Salvar"**
   
5. **Volte ao pgAdmin e execute:**
   ```sql
   SELECT * FROM pessoa;
   ```
   **A pessoa que você criou vai aparecer!** ✅

---

## ❌ Erros Comuns e Soluções

### ❌ Erro 1: "Connection refused to database"

**Causa:** PostgreSQL não está rodando ou senha está errada

**Solução:**
1. Abra o **"Services"** do Windows (Win + R → `services.msc`)
2. Procure por **"postgresql-x64-XX"**
3. Clique com botão direito → **"Start"** (Iniciar)
4. Verifique a senha em `application.properties`

---

### ❌ Erro 2: "Port 8080 already in use"

**Causa:** Já tem um backend rodando na porta 8080

**Solução:**
Execute no terminal:
```cmd
netstat -ano | findstr :8080
```
Anote o PID (último número) e execute:
```cmd
taskkill /F /PID [número_do_pid]
```

---

### ❌ Erro 3: "Failed to execute goal"

**Causa:** Dependências Maven não foram baixadas

**Solução:**
```cmd
cd pdv-posto-combustivel
mvn clean install -U
```

---

## 📊 Fluxo Completo de Teste (Resumido)

```
1. Baixar ZIP do GitHub ✅
   ↓
2. Criar banco no PostgreSQL: pdvpostocombustivel ✅
   ↓
3. Copiar application.properties.example → application.properties ✅
   ↓
4. Editar application.properties e colocar sua senha ✅
   ↓
5. Abrir projeto no IntelliJ ✅
   ↓
6. Executar Backend (porta 8080) ✅
   ↓
7. Ver logs: "Started PdvpostocombustivelApplication" ✅
   ↓
8. Verificar tabelas no pgAdmin ✅
   ↓
9. Acessar Swagger: http://localhost:8080/swagger-ui.html ✅
   ↓
10. Executar Frontend e testar CRUD ✅
   ↓
11. Verificar dados salvos no pgAdmin ✅
```

---

## 🎯 Checklist de Sucesso

Marque conforme for testando:

- [ ] Banco `pdvpostocombustivel` criado no PostgreSQL
- [ ] Arquivo `application.properties` criado com sua senha
- [ ] Backend iniciou sem erros
- [ ] Logs mostram conexões PostgreSQL (`PgConnection`)
- [ ] Tabelas criadas automaticamente no banco
- [ ] Swagger UI acessível em http://localhost:8080/swagger-ui.html
- [ ] Frontend abre a janela gráfica
- [ ] Consegue criar uma pessoa no Frontend
- [ ] Pessoa aparece no banco de dados (pgAdmin)

**Se marcou todos, está 100% funcionando!** 🎉

---

## 💡 Dica Extra: Ver em Tempo Real as Queries SQL

Quando você usa o Frontend ou Swagger, o IntelliJ vai mostrar nos logs todas as queries SQL que estão sendo executadas no PostgreSQL:

```sql
Hibernate: 
    insert 
    into pessoa (cpf_cnpj, email, nome, telefone, tipo, id) 
    values (?, ?, ?, ?, ?, default)
```

Isso é útil para aprender SQL e debugar! 📚

---

**Qualquer dúvida, me manda print do erro!** 🚀

