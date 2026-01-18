# 📄 CV Analyzer

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange? style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-3.3-brightgreen?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Spring%20AI-1.0-brightgreen?style=for-the-badge&logo=spring&logoColor=white" alt="Spring AI"/>
  <img src="https://img.shields.io/badge/Ollama-Llama%203.2-blue?style=for-the-badge&logo=meta&logoColor=white" alt="Ollama"/>
  <img src="https://img.shields.io/badge/PostgreSQL-16-336791?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL"/>
  <img src="https://img.shields.io/badge/Docker-24-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker"/>
</p>

<p align="center">
  Sistema inteligente de análise de currículos que utiliza IA local para extrair informações, avaliar perfis profissionais e calcular compatibilidade com vagas de emprego.
</p>

---

## 📋 Sobre o Projeto

O **CV Analyzer** é uma API REST que automatiza a análise de currículos usando Inteligência Artificial. O sistema recebe um currículo em PDF, extrai o texto, e utiliza um modelo de linguagem (LLM) rodando localmente para: 

- ✅ **Analisar** o perfil profissional do candidato
- ✅ **Extrair** habilidades técnicas automaticamente
- ✅ **Sugerir** melhorias específicas para o currículo
- ✅ **Calcular** compatibilidade com vagas cadastradas

### 🎯 Problema que Resolve

| Sem o CV Analyzer | Com o CV Analyzer |
|-------------------|-------------------|
| ⏰ Horas analisando currículos manualmente | ⚡ Análise em segundos |
| 😵 Análise inconsistente por cansaço | 🎯 Análise padronizada e objetiva |
| 🤷 Candidato não sabe se o CV está bom | 📊 Feedback detalhado com sugestões |
| ❓ Difícil identificar match com vagas | 🔗 Compatibilidade calculada automaticamente |

---

## 🛠️ Tecnologias

### Backend
- **Java 21** - Linguagem principal
- **Spring Boot 3.3** - Framework web
- **Spring AI** - Integração com modelos de IA
- **Spring Data JPA** - Persistência de dados
- **Apache PDFBox** - Extração de texto de PDFs
- **Lombok** - Redução de boilerplate

### Banco de Dados
- **PostgreSQL 16** - Banco de dados relacional
- **Docker** - Containerização

### Inteligência Artificial
- **Ollama** - Servidor local de IA
- **Llama 3.2** - Modelo de linguagem (LLM)

---

## 🏗️ Arquitetura

```
┌─────────────────────────────────────────────────────────────┐
│                         Cliente                             │
│                    (Postman / Frontend)                     │
└─────────────────────────┬───────────────────────────────────┘
                          │
                          ▼
┌─────────────────────────────────────────────────────────────┐
│                      Controllers                            │
│              ResumeController | JobController               │
└─────────────────────────┬───────────────────────────────────┘
                          │
                          ▼
┌─────────────────────────────────────────────────────────────┐
│                       Services                              │
│         ResumeService | JobService | IAService              │
└───────────┬───────���─────────────────────────┬───────────────┘
            │                                 │
            ▼                                 ▼
┌───────────────────────┐         ┌───────────────────────────┐
│      PdfService       │         │     Ollama (Llama 3.2)    │
│   (Apache PDFBox)     │         │      localhost:11434      │
└───────────────────────┘         └───────────────────────────┘
            │
            ▼
┌─────────────────────────────────────────────────────────────┐
│                      Repositories                           │
│       ResumeRepository | JobRepository | CandidateRepo      │
└─────────────────────────┬───────────────────────────────────┘
                          │
                          ▼
┌─────────────────────────────────────────────────────────────┐
│                      PostgreSQL                             │
│                    localhost:5432                           │
└─────────────────────────────────────────────────────────────┘
```

---

## 🚀 Como Executar

### Pré-requisitos

- [Java 21+](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/)
- [Ollama](https://ollama.com/)

### 1. Clone o repositório

```bash
git clone https://github.com/gustavoallves/cv-analyzer.git
cd cv-analyzer
```

### 2. Configure as variáveis de ambiente

```bash
cp .env.example .env
# Edite o arquivo .env com suas configurações
```

### 3. Inicie o PostgreSQL

```bash
docker-compose up -d
```

### 4. Inicie o Ollama e baixe o modelo

```bash
# Verifique se o Ollama está rodando
curl http://localhost:11434

# Baixe o modelo (se ainda não tiver)
ollama pull llama3.2
```

### 5. Execute a aplicação

```bash
./mvnw spring-boot:run
```

### 6. Teste a API

```bash
# Health check
curl http://localhost:8080/api/resumes/health

# Analisar currículo
curl -X POST http://localhost:8080/api/resumes/analyze \
  -F "file=@/caminho/para/curriculo.pdf"
```

---

## 📡 Endpoints da API

### Currículos (Resumes)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/resumes/health` | Health check da API |
| `POST` | `/api/resumes/analyze` | Analisa um currículo (PDF) |
| `GET` | `/api/resumes` | Lista todas as análises |
| `GET` | `/api/resumes/{id}` | Busca análise por ID |
| `POST` | `/api/resumes/{id}/match/{jobId}` | Calcula compatibilidade com vaga |

### Vagas (Jobs)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/jobs` | Cria uma nova vaga |
| `GET` | `/api/jobs` | Lista todas as vagas |
| `GET` | `/api/jobs/{id}` | Busca vaga por ID |
| `PUT` | `/api/jobs/{id}` | Atualiza uma vaga |
| `DELETE` | `/api/jobs/{id}` | Remove uma vaga |

---

## 📦 Exemplo de Uso

### Request:  Analisar Currículo

```http
POST /api/resumes/analyze
Content-Type: multipart/form-data

file:  curriculo.pdf
```

### Response

```json
{
  "analysis": "Profissional júnior com formação em Ciência da Computação, demonstrando sólido conhecimento em desenvolvimento backend com Java e Spring Boot.",
  "skills": "Java, Spring Boot, PostgreSQL, Git, Docker, API REST, JUnit",
  "improvements": "1. Adicionar link do GitHub com projetos pessoais\n2. Incluir métricas e resultados nas experiências\n3. Adicionar certificações relevantes",
  "analyzedAt": "2026-01-11T14:30:00"
}
```

---

## 🗃️ Estrutura do Projeto

```
cv-analyzer/
├── src/
│   ├── main/
│   │   ├── java/com/usr/cvanalyzer/
│   │   │   ├── controller/
│   │   │   │   ├── ResumeController.java
│   │   │   │   └── JobController.java
│   │   │   ├── service/
│   │   │   │   ├── ResumeService.java
│   │   │   │   ├── PdfService.java
│   │   │   │   ├── IAService.java
│   │   │   │   └── JobService.java
│   │   │   ├── model/
│   │   │   │   ├── Candidate. java
│   │   │   │   ├── Resume.java
│   │   │   │   └── Job.java
│   │   │   ├── repository/
│   │   │   │   ├── CandidateRepository.java
│   │   │   │   ├── ResumeRepository.java
│   │   │   │   └── JobRepository.java
│   │   │   ├── dto/
│   │   │   │   └── AnalysisResponseDTO. java
│   │   │   └── CvAnalyzerApplication.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── prompts/
│   │           └── resume-analysis. txt
│   └── test/
├── . env.example
├── . gitignore
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

## ⚙️ Configuração

### Variáveis de Ambiente (. env)

```env
# Banco de Dados
POSTGRES_USER=seu_usuario
POSTGRES_PASSWORD=sua_senha
POSTGRES_DB=cvanalyzer_db
POSTGRES_PORT=5432
```

### application.yml

```yaml
spring:
  ai:
    ollama:
      base-url: http://localhost:11434
      chat: 
        model: llama3.2
        options:
          temperature: 0.3
```

---

## 🧪 Testando

### Com Postman

1.  Importe a collection (em breve)
2. Configure a variável `base_url` para `http://localhost:8080`
3. Execute os requests

### Com cURL

```bash
# Analisar currículo
curl -X POST http://localhost:8080/api/resumes/analyze \
  -F "file=@curriculo. pdf"

# Listar análises
curl http://localhost:8080/api/resumes

# Criar vaga
curl -X POST http://localhost:8080/api/jobs \
  -H "Content-Type: application/json" \
  -d '{"title": "Dev Java Jr", "description": ".. .", "requirements": "Java, Spring", "level": "JUNIOR"}'
```

---

## 📈 Roadmap

- [x] Setup inicial do projeto
- [x] Configuração do Docker + PostgreSQL
- [x] Integração com Ollama
- [ ] CRUD de Currículos
- [ ] CRUD de Vagas
- [ ] Match Currículo x Vaga
- [ ] Tratamento de erros
- [ ] Documentação Swagger
- [ ] Testes unitários
- [ ] Frontend (futuro)

## 👤 Autor

Feito por **Gustavo Alves**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/gustavo-allves)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/gustavoallves)

---

<p align="center">
  ⭐ Se este projeto te ajudou, deixe uma estrela! 
</p>
