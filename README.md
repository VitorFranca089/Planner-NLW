# ✈ Plann.er ✈

Bem-vindo(a) ao meu repositório! Aqui você encontrará o backend do Plann.er desenvolvido durante a NLW Journey da Rocketseat!

## 📖 Sobre o Projeto

O Plann.er é uma API para um aplicativo gerenciador de viagens, projetado para ajudar na organização e planejamento de viagens. Este projeto aplica conhecimentos sobre Spring Boot, banco de dados SQL e segue boas práticas e convenções do ecossistema Java.

## ✔ Funcionalidades Implementadas

- Criar e gerenciar viagens. 
- Convidar participantes. 
- Adicionar links relacionados. 
- Adicionar atividades. 
- Confirmar participação via email.

## 🔲 Funcionalidades Futuras

- [ ] Extração do core das trips pra dentro de uma classe Service.
- [ ] Validação nos campos de data.
- [ ] Mapeamento das exceções da aplicação.
- [ ] Criação de um serviço de envio efetivo de emails.
- [ ] Adicionar Swagger para documentação da API.

## ⚙ Tecnologias utilizadas

- Java 17.
- Spring Boot.
- Maven.
- Banco de dados SQL (H2 database).

## 💻 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior.
- [Maven](https://maven.apache.org/download.cgi).

## 🌐 Instalação da API Plann.er

1. Clone o repositório:
```bash
git clone https://github.com/VitorFranca089/Planner-NLW.git
```

2. Navegue até o diretório do projeto:
```bash
cd <diretorio do projeto>
```

3. Instale as dependências do Maven:

```bash
mvn clean install
```

## ▶ Executando a Aplicação
Para executar a aplicação Spring Boot, use o seguinte comando:

```bash
mvn spring-boot:run
```

## 📋 API Endpoints

A API provê os seguintes endpoints:

```markdown
#### Trips

- `POST /trips` - Cria uma nova viagem e convida os participantes.
- `GET /trips/{id}` - Retorna os detalhes de uma viagem específica.
- `PUT /trips/{id}` - Atualiza os detalhes de uma viagem específica.
- `GET /trips/{id}/confirm` - Confirma a viagem e envia emails de confirmação para os participantes.

#### Activities

- `POST /trips/{id}/activities` - Adiciona uma nova atividade à viagem.
- `GET /trips/{id}/activities` - Retorna todas as atividades de uma viagem específica.

#### Participants

- `POST /trips/{id}/invite` - Convida um novo participante para a viagem.
- `GET /trips/{id}/participants` - Retorna todos os participantes de uma viagem específica.

#### Links

- `POST /trips/{id}/links` - Adiciona um novo link relacionado à viagem.
- `GET /trips/{id}/links` - Retorna todos os links de uma viagem específica.

#### Confirmação de Participante

- `POST /participants/{id}/confirm` - Confirma a participação de um participante na viagem.
```
