# BFHL REST API — Bajaj Finserv Health

A Spring Boot REST API that processes mixed data arrays (numbers, alphabets, special characters) and returns categorized results.

## 🚀 API Endpoints

### POST `/bfhl`
Processes input data array and returns categorized results.

**Request:**
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

**Response:**
```json
{
  "is_success": true,
  "user_id": "hemlata_kumawat_31052006",
  "email": "hemlatakumawat231001@acropolis.in",
  "roll_number": "0827IT231052",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

### GET `/bfhl`
Returns operation code.

```json
{
  "operation_code": 1
}
```

## 🛠️ Tech Stack
- Java 17
- Spring Boot 3.2.5
- Maven
- Docker

## 🏗️ Build & Run Locally

```bash
# Build
mvn clean package

# Run
java -jar target/bfhl-api-1.0.0.jar

# Or with Docker
docker build -t bfhl-api .
docker run -p 8080:8080 bfhl-api
```

## 🧪 Run Tests
```bash
mvn test
```

## 📦 Deploy on Render
1. Push this repo to GitHub
2. Create a new **Web Service** on Render
3. Connect your GitHub repo
4. Set **Environment** to **Docker**
5. Render will auto-detect the Dockerfile and deploy

## 👤 Author
- **Name:** Hemlata Kumawat
- **Email:** hemlatakumawat231001@acropolis.in
- **Roll Number:** 0827IT231052
