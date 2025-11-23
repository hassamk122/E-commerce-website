# 🛒 E-Commerce Website

A full-stack e-commerce web application built with **Spring Boot**, **Thymeleaf**, and **PostgreSQL**.  
It provides a smooth shopping experience for customers and powerful management tools for administrators.

---

## 📌 Overview

This project aims to build a complete e-commerce website where:

- Customers can browse, search, and view products easily.
- Items can be added to the cart **without logging in** (session-based cart).
- Login is only required at **checkout**.
- Orders and customer information are stored securely in the database.
- Admins have a dedicated dashboard to manage products, categories, users, and orders.

The system uses Spring Boot for backend logic, Thymeleaf for UI rendering, and PostgreSQL for data storage.

---

## 🧱 Tech Stack

### Backend
- Java 17+
- Spring Boot (Web, Security, JPA, Validation)
- Hibernate ORM
- PostgreSQL

### Frontend
- Thymeleaf
- HTML5, CSS3, JavaScript
- Bootstrap

### Build Tool
- Maven

---

## 🚀 Features

### 🛍️ Customer Features
- Browse all products  
- Search bar with instant results  
- Category-based product filtering  
- Product detail page  
- Add to cart without logging in  
- User registration & login  
- Checkout & place orders  
- User profile + order history  

### 🛠️ Admin Features
- Admin login  
- Admin dashboard  
- Add, update, and delete products  
- Manage categories  
- View and update order statuses  
- Manage users  

### 🔐 Security
- Spring Security authentication  
- BCrypt password encryption  
- Role-based access:
  - `USER` → Shopping features
  - `ADMIN` → Management dashboard

---

## 📁 Project Structure
src/
├── main/
│ ├── java/com/bsse5a/EcommerceWeb
│ │ ├── controllers/
│ │ ├── models/
│ │ ├── repositories/
│ │ ├── services/
│ │ ├── security/
│ │ └── EcommerceWebApplication.java
│ └── resources/
│ ├── templates/
│ ├── static/



---

## 🛠️ Setup & Installation

### 1. Clone the repository
```bash
git clone https://github.com/your-username/ecommerce-website.git
cd ecommerce-website

2. Configure PostgreSQL

Create a database:
CREATE DATABASE ecommerce;


Update application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


3. Run the project
mvn spring-boot:run


App runs at:
👉 http://localhost:8080/




