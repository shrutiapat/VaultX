# VaultX Expense Tracker (Spring Boot)
Resume-grade backend for an Indian-focused expense tracker supporting UPI, OTT subscriptions, budgets, JWT auth (access + refresh), export, and scheduled reminders.

## Quick start (dev)
1. Configure PostgreSQL and update `src/main/resources/application.properties`.
2. Build: `mvn clean package`
3. Run: `java -jar target/vaultx-expensetracker-0.0.1-SNAPSHOT.jar`
4. Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## Included
- Core entities, repos, services, controllers
- JWT utils and security filter
- Dockerfile + docker-compose for Postgres
- Example scheduler and export skeletons

Use this repo as the backend for your VaultX project; expand features, tests and CI as needed for production readiness.
