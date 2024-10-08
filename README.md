# Makersharks Manufacturer Search API

This project is a proof of concept for a search API that allows buyers to search for manufacturers based on customized requirements such as location, nature of business, and manufacturing processes.

## Technology Stack

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- MySQL (or any relational database)
- Maven
- Swagger for API documentation

## Getting Started

### Prerequisites

- Java 17 or later
- Maven 3.x or later
- MySQL (or any other compatible relational database)
- IDE (IntelliJ IDEA, Eclipse, etc.)

### Setup Instructions

1. **Clone the repository:**

    ```bash
    git clone https://github.com/srinibash-sai/makersharks-manufacturer-search-api.git
    cd makersharks-manufacturer-search-api
    ```

2. **Set up the database:**

    - Create a MySQL database named `makersharks_db` (or any other name, but ensure you update `application.properties` accordingly).
    - Run the following SQL commands to create the necessary tables and insert test data:

    ```sql
    CREATE TABLE suppliers (
        supplier_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        company_name VARCHAR(255) NOT NULL,
        website VARCHAR(255),
        location VARCHAR(100),
        nature_of_business VARCHAR(50)
    );

    CREATE TABLE manufacturing_processes (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        supplier_id BIGINT,
        process VARCHAR(100),
        FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
    );

    INSERT INTO suppliers (company_name, website, location, nature_of_business) VALUES
    ('ABC Manufacturing', 'http://www.abcmfg.com', 'Mumbai', 'small_scale'),
    ('XYZ Industries', 'http://www.xyzindustries.com', 'Delhi', 'medium_scale'),
    ('PQR Corp', 'http://www.pqrcorp.com', 'Bangalore', 'large_scale'),
    ('LMN Pvt Ltd', 'http://www.lmnpvtltd.com', 'Mumbai', 'small_scale'),
    ('DEF Works', 'http://www.defworks.com', 'Delhi', 'medium_scale');

    INSERT INTO manufacturing_processes (supplier_id, process) VALUES
    (1, 'moulding'),
    (1, 'casting'),
    (2, '3d_printing'),
    (3, 'coating'),
    (4, '3d_printing'),
    (4, 'casting'),
    (5, 'moulding'),
    (5, 'coating');
    ```

3. **Configure the application:**

    - Update the `src/main/resources/application.properties` file with your database credentials:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/makersharks_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

4. **Build and run the application:**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

5. **Access the API documentation:**

   Open your browser and navigate to [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) to view the API documentation.

## API Usage

### Sample CURL Command

- **Retrieve small-scale manufacturers in Mumbai capable of 3D printing:**

    ```bash
  curl -X 'POST' \
  'http://localhost:8080/api/supplier/query?location=Mumbai&natureOfBusiness=SMALL_SCALE&process=3d_printing&pageNumber=0&pageSize=10' \
  -H 'accept: */*' \
  -d ''
    ```

### API Endpoints

- **POST /api/supplier/query**

  This endpoint retrieves a list of manufacturers based on location, nature of business, and manufacturing processes.

    - **Request Parameters:**
        - `location`: The location of the supplier (e.g., `Mumbai`).
        - `natureOfBusiness`: The nature of the business (e.g., `small_scale`, `medium_scale`, `large_scale`).
        - `process`: The manufacturing process the supplier should have (e.g., `3d_printing`, `moulding`, `casting`, `coating`).
        - `page`: The page number for pagination (default is `0`).
        - `size`: The number of records per page (default is `10`).

    - **Response:**
      A paginated list of manufacturers matching the search criteria.


