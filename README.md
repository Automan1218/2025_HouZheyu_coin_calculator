# **Coin Calculator Backend**

## **Project Introduction**
The Coin Calculator Backend is a RESTful API designed to calculate the minimum number of coins required to achieve a target amount. It processes requests from the frontend or external clients and returns an optimized set of coins in ascending order. The backend is built using **Dropwizard**, a lightweight Java framework for building RESTful web services.

---

## **Technologies Used**
- **Programming Language**: Java 17
- **Framework**: Dropwizard
- **Build Tool**: Maven
- **Containerization**: Docker

---

## **How to Build and Run the Backend**

### **Using Docker**

1. **Clone the backend repository**:
   ```bash
   git clone https://github.com/<your-username>/coin_calculator.git
   cd coin_calculator
   ```

2. **Build the Docker image**:
   ```bash
   docker build -t coin_calculator:1.0 .
   ```

3. **Run the container**:
   ```bash
   docker run -d -p 8080:8080 --name coin_calculator coin_calculator:1.0
   ```

---

## **Features**
1. **Optimized Coin Calculation**:
   - Finds the minimum number of coins to reach the target amount.
   - Returns the coin denominations in ascending order.

2. **Validation**:
   - Rejects invalid coin values (e.g., letters, symbols).
   - Validates that coins are positive numbers.

3. **Error Handling**:
   - Provides descriptive error messages for invalid inputs.

---

## **Development Instructions**

### **Local Setup (Without Docker)**

1. **Clone the repository**:
   ```bash
   git clone https://github.com/<your-username>/coin_calculator.git
   cd coin_calculator
   ```

2. **Build the project**:
   - Ensure you have Maven installed.
   - Run the following command to build the project:
     ```bash
     mvn clean package
     ```

3. **Run the application**:
   - Use the following command to start the Dropwizard application:
     ```bash
     java -jar target/coin_calculator-1.0-SNAPSHOT.jar server config.yml
     ```

4. **Test the API**:
   - Use `curl` or Postman to test the API (refer to the examples above).
