💰 VaultX – Expense Tracker API

A modern expense tracking API that helps users manage their daily expenses, set budgets, and analyze spending patterns with ease. Built using Spring Boot for the backend and secured with JWT authentication.

📋 Table of Contents

🤖 Introduction

⚙️ Tech Stack

🔋 Features

🤸 Quick Start

🕸️ API Endpoints

🔗 Assets

🚀 More

⚠️ Future Enhancements

🤖 Introduction

VaultX is a personal finance management API designed to make budgeting and expense tracking seamless.
Users can register, log in securely, add their expenses, categorize them, and view financial insights.

This project is perfect for developers who want to learn how to build a real-world API with Spring Boot using JWT-based authentication and modular RESTful endpoints.

⚙️ Tech Stack

Spring Boot – Backend Framework

Spring Security + JWT – Authentication & Authorization

Hibernate / JPA – ORM & Database Handling

H2 Database (in-memory, for development & testing)

Maven – Dependency Management

🔋 Features

👉 User authentication with JWT tokens (secure login & signup)
👉 Add, update, and delete expenses
👉 Categorize expenses for better tracking
👉 Filter expenses by date and category
👉 Monthly budget tracking
👉 Spending insights & analytics-ready APIs
👉 Lightweight & developer-friendly design
👉 Scalable code structure for future enhancements

🤸 Quick Start

Clone the repository:

git clone https://github.com/shrutiapat/VaultX.git
cd VaultX


Build & run the app:

mvn spring-boot:run


The API will be available at:

http://localhost:8080/api

🕸️ API Endpoints

Here are some key endpoints:

🔐 Authentication

POST /api/auth/register – Register a new user

POST /api/auth/login – Login and get JWT token

💰 Expenses

POST /api/expenses – Add new expense

GET /api/expenses – Get all expenses of a user

GET /api/expenses/{id} – Get expense by ID

PUT /api/expenses/{id} – Update expense

DELETE /api/expenses/{id} – Delete expense

📊 Budgets

POST /api/budgets – Set monthly budget

GET /api/budgets – Get user’s budget

🔗 Assets

Postman collection (coming soon 🚀)

API documentation (Swagger integration planned)

🚀 More

This project can be extended with:

Charts & visualization using frontend (React/Angular)

Bank account integrations for real-time sync

AI-driven financial insights
