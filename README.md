# 🎓 Student Attendance API – Spring Boot Project

A backend system to manage student check-in and check-out activities for educational environments.
Developed as part of the **Junior Back End Spring Boot Developer Certification** from ArutalaLab.

> ✅ Built with Spring Boot 3, Java 17, and MySQL – following industry-ready backend architecture.

---

## 🌟 Purpose

This project was developed as:

* A real-world use case of a RESTful backend for attendance tracking
* A learning tool for students and juniors learning Spring Boot
* A deliverable for official certification evaluation (Project-Based Learning)

---

## 🚀 Features

* ✅ Digital attendance system (Check-In / Check-Out)
* 👩‍🏫 Admin monitoring dashboard
* 🔐 JWT authentication & Spring Security
* 📂 MySQL database integration via Spring Data JPA
* 📁 Layered structure (Controller → Service → Repository → Entity)
* 🧪 Basic testing setup using JUnit

---

## ⚙️ Tech Stack

| Layer      | Technology            |
| ---------- | --------------------- |
| Language   | Java 17               |
| Framework  | Spring Boot 3.x       |
| ORM        | Spring Data JPA       |
| Auth       | Spring Security + JWT |
| DBMS       | MySQL                 |
| Build Tool | Maven                 |
| Dev Tools  | Spring Boot DevTools  |

---

## ⚙️ Getting Started

### 🔧 Prerequisites

Make sure the following are installed:

* Java 17+
* Apache Maven
* MySQL 8.x
* Git
* IDE (IntelliJ / VS Code / Eclipse)

### 🛠️ Setup

1. Clone the project:

   ```bash
   git clone https://github.com/Quineeryn/student-attendance-api.git
   cd student-attendance-api
   ```

2. Configure MySQL:

   * Create a database, e.g. `pelatihan_db`
   * Copy the config file:

     ```bash
     cp src/main/resources/application-example.properties src/main/resources/application.properties
     ```
   * Edit your credentials inside `application.properties`

3. Build the project:

   ```bash
   mvn clean install
   ```

4. Run the project:

   ```bash
   mvn spring-boot:run
   ```

5. App will be running at:

   ```
   http://localhost:8080
   ```

---

## 📃 Sample Configuration

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pelatihan_db?createDatabaseIfNotExist=true
spring.datasource.username=your-db-username
spring.datasource.password=your-db-password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

app.jwt.secret=your-jwt-secret
app.jwt.expiration-ms=86400000
```

> Don't commit `application.properties` to version control. Use `.gitignore` + `application-example.properties`.

---

## 📜 Certification

This project was submitted as part of the:

> **Junior Back End Spring Boot Developer Certification**
> Organized by [ArutalaLab](https://arutala.id) — Project-Based Learning

* 👤 **Name:** Canandra Eka Mukti
* 📄 **Certificate ID:** TC2025.J.SB.C.07.006
* 🗓️ **Issued:** July 12, 2025
* ⏳ **Valid Until:** July 12, 2028

---

## 📄 License

This project is licensed under the MIT License.
See the [LICENSE](LICENSE) file for details.

---

## 💡 Educational Tips

* Understand the request flow: Controller → Service → Repository → DB
* Use debugger to inspect and trace logic
* Explore official Spring Boot & JPA documentation
* Don’t be afraid to break things — that's how you learn!

---

> Built with ❤️ and Java by **Canandra Eka Mukti**
