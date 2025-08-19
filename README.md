# 📊 JSON GroupBy & Sorting API (Spring Boot)

A Spring Boot backend project that allows storing JSON datasets dynamically and provides flexible querying with **`groupBy`** and **sorting (ASC/DESC)`** features.  Database used is PostgreSQL.
Tested and demonstrated using **Postman**.

---

## 🚀 Features
- ✅ Insert JSON records into a dataset  
- ✅ Retrieve all records from a dataset  
- ✅ Group records dynamically by a JSON field  
- ✅ Sort records by a field (ascending/descending)  
- ✅ Exception handling with meaningful error responses  

---

## ⚡ API Endpoints

### 1️⃣ Insert a Record  
**POST** `/api/dataset/{datasetName}`  

📌 Example Request:
```http
POST http://localhost:8080/api/dataset/employee_dataset


2️⃣ Fetch All Records

GET /api/dataset/{datasetName}

📌 Example Request:

GET http://localhost:8080/api/dataset/employee_dataset

✅ Example Response:

[
  { "name": "Rishabh", "city": "Delhi", "age": 25 },
  { "name": "Amit", "city": "Mumbai", "age": 30 }
]

3️⃣ Group Records by Field

GET /api/dataset/{datasetName}?groupBy=fieldName

📌 Example Request:

GET http://localhost:8080/api/dataset/employee_dataset?groupBy=city


✅ Example Response:

{
  "Delhi": [
    { "name": "Rishabh", "city": "Delhi", "age": 25 }
  ],
  "Mumbai": [
    { "name": "Amit", "city": "Mumbai", "age": 30 }
  ]
}

4️⃣ Sort Records (Ascending)

GET /api/dataset/{datasetName}?sortBy=fieldName&order=asc

📌 Example Request:

GET http://localhost:8080/api/dataset/employee_dataset?sortBy=age&order=asc


✅ Example Response:

[
  { "name": "Rishabh", "city": "Delhi", "age": 25 },
  { "name": "Amit", "city": "Mumbai", "age": 30 }
]

5️⃣ Sort Records (Descending)

GET /api/dataset/{datasetName}?sortBy=fieldName&order=desc

📌 Example Request:

GET http://localhost:8080/api/dataset/employee_dataset?sortBy=age&order=desc


✅ Example Response:

[
  { "name": "Amit", "city": "Mumbai", "age": 30 },
  { "name": "Rishabh", "city": "Delhi", "age": 25 }
]

▶️ Run Locally

Clone the repo:

git clone https://github.com/Rp228/JsonGroupBySortBy.git
cd JsonGroupBySortBy


Run the app:

mvn spring-boot:run


Test APIs with Postman at:

http://localhost:8080/api/dataset
