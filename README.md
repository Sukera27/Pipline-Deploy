# 🧩 Spring Boot API - Ejemplo MVC 

Este proyecto es una API REST desarrollada con **Spring Boot**, diseñada como ejemplo educativo y práctico para comprender la **arquitectura MVC**, la **capa de persistencia (JPA/Hibernate)** y el consumo de datos mediante **servicios y controladores REST**.

Incluye ejemplos de relaciones entre entidades (`@OneToOne`, `@ManyToOne`, `@ManyToMany`), DTOs, servicios y controladores.

---

## 🚀 Tecnologías utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Web**
- **Spring Data JPA / Hibernate**
- **MySQL**
- **Lombok**
- **Jackson (para serialización JSON)**
- **Maven**

---

## 🧱 Arquitectura del Proyecto

El proyecto sigue el patrón **MVC (Model - View - Controller)**, con separación clara entre capas:

```
src/
 └── main/
     ├── java/com/example/Producto/
     │    ├── controller/      → Controladores REST
     │    ├── controller/converter/  → Entity to DTO 
     │    ├── controller/dto/  →  DTO 
     │    ├── service/         → Interfaces y lógica de negocio
     │    ├── service/impl/    → Implementaciones de servicios
     │    ├── persistance/
     │    │    ├── model/      → Entidades JPA
     │    │    └── repository/ → Repositorios JPA
     │    └── ProductoApplication.java
     └── resources/
          ├── application.properties
          └── data.sql / schema.sql (opcional)
```

---

## 📦 Entidades Principales

### 🧍‍♂️ `User`

```java
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    Long userId;

    @Column(name = "username", nullable = false, unique = true, length = 20)
    String username;
  
    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "email", nullable = false, unique = true, length = 90)
    String email;


    @OneToOne(mappedBy = "owner", fetch = FetchType.LAZY)
    Dni documentDni;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="users_bought_productos", 
               joinColumns={@JoinColumn(name="Users_user_id", referencedColumnName = "user_id")}, 
               inverseJoinColumns={@JoinColumn(name="productos_producto_id", referencedColumnName = "producto_id")})
    List<Product> products;
}
```

---

### 🪪 `Dni`

```java
@Entity
@Table(name = "dni")
public class Dni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dni_id", nullable = false, unique = true)
    Integer dniId;

    @Column(name = "number", nullable = false, unique = true, length = 9)
    String number;

    @Column(name = "front_img")
    String frontImg;

    @Column(name = "back_img")
    String backImg;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Users_user_id", referencedColumnName = "user_id")
    @JsonIgnore
    User owner;
}
```

---

### 🛒 `Product`

```java
@Entity
@Table(name = "productos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")    
    private  Long id;  
    @Column (name = "product_name" ) 
    private String name;
    @Column (name = "description" ) 
    private String description;
    @Column (name = "price" ) 
    private Double price;
    @Column (name = "image_url") 
    private String imageUrl;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Category category; 
    @ManyToMany(mappedBy="products", fetch = FetchType.LAZY)
    @JsonIgnore
    List<User> usersWhobought;
}
```

---

### 🏷️ `Category`

```java
@Entity
@Table(name = "categorias")
public class Category {
    @Id
    @Column (name = "category_id")
    private Long categoryId;

    @Column (name = "category_name")
    private String categoryName;    
    
     @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
     @JsonIgnore
    private List<Product> products;
}
```

---

## 🔗 Relaciones entre Entidades

| Entidad | Relación | Tipo | Descripción |
|----------|-----------|------|-------------|
| **User ↔ Dni** | 1 : 1 | Un usuario tiene un solo DNI |
| **User ↔ Product** | N : M | Un usuario puede comprar varios productos |
| **Product ↔ Category** | N : 1 | Un producto pertenece a una categoría |

---

## 🧭 Diagrama Entidad–Relación (ER)

```text
┌────────────┐       1 ──── 1       ┌────────────┐
│   User     │──────────────────────│    Dni     │
│ user_id    │                      │ dni_id     │
│ username   │                      │ number     │
│ email      │                      │ user_id FK │
└────────────┘                      └────────────┘
      │
      │ N
      │
      │ M
┌────────────┐       N ──── 1       ┌────────────┐
│  Product   │──────────────────────│  Category  │
│ producto_id│                      │ category_id│
│ name       │                      │ name       │
│ price      │                      │            │
└────────────┘                      └────────────┘
```

---

## 🧪 Endpoints de la API

### 📦 **ProductController**
Ruta base: `/api/v1/products`

| Método | Endpoint | Descripción |
|--------|-----------|-------------|
| `GET` | `/api/v1/products` | Obtiene todos los productos |
| `GET` | `/api/v1/products/{id}` | Obtiene un producto por ID |
| `POST` | `/api/v1/products` | Crea un nuevo producto |
| `PUT` | `/api/v1/products/{id}` | Edita un producto existente |
| `DELETE` | `/api/v1/products/{id}` | Elimina un producto por ID |

Ejemplo de respuesta JSON:
```json
{
    "id": 3,
    "name": "Reloj Apple Watch Series 9",
    "description": "Smartwatch con monitor de salud y GPS",
    "price": 449.0,
    "imageUrl": "https://example.com/images/watch1.jpg",
    "category": {
      "categoryId": 1,
      "categoryName": "Electrónica"
    }
  }
```

---

## 🧰 Ejecución del Proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tuusuario/springboot-apuntes.git
   ```
2. Configura la base de datos en `application.properties`
3. Ejecuta el proyecto:
   ```bash
   mvn spring-boot:run
   ```
4. Accede a la API en:
   ```bash
   http://localhost:8080/api/v1/products
   ```

---

## 🎯 Objetivo del Proyecto

Este repositorio sirve como **apunte práctico** de cómo implementar:
- Arquitectura **MVC** en Spring Boot  
- Capa de persistencia con **JPA y Hibernate**
- **Relaciones entre entidades** (1:1, 1:N, N:M)


---

## 🧾 Autor : Rafael Mancina Castro
 
© 2025 - Desarrollado por Rafael Mancina Castro
