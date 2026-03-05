 # mallpractice：線上商城後端練習

 利用 Spring Boot 建立簡單的商城後端服務，提供商品查詢 / 新增 / 修改 / 刪除以及會員註冊與登入等功能。

 ---

 ### 前置需求

 - **JDK 21**
 - **Maven**
 - **MySQL 8+**

 ---

 ### 資料庫設定

 1. **建立資料庫 `mall`：**

    ```sql
    CREATE DATABASE mall CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
    ```

 2. **建立資料表與測試資料（可選，但建議執行）：**  
    在 MySQL 連到 `mall` 資料庫後，執行 `src/test/resources/schema.sql` 與 `src/test/resources/data.sql` 內容：

    ```sql
    -- 建表
    -- 內容位於 src/test/resources/schema.sql
    -- 包含 product / user 等表結構

    -- 測試資料
    -- 內容位於 src/test/resources/data.sql
    -- 會插入多筆商品資料
    ```

 3. **連線設定（已預設於 `src/main/resources/application.properties`）：**

    ```properties
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/mall?serverTimezone=Asia/Taipei&characterEncoding=utf-8
    spring.datasource.username=root
    spring.datasource.password=springboot
    ```
    請依照自己本機的 MySQL 帳號密碼調整 `username` / `password`。

 ---

 ### 啟動方式

 在專案根目錄 `mallpractice/` 下執行：

 **Windows：**

 ```bash
 .\mvnw.cmd spring-boot:run
 ```

 **Mac / Linux：**

 ```bash
 ./mvnw spring-boot:run
 ```

 預設啟動後服務位於：

 - `http://localhost:8080`

 ---

 ### 主要功能與 API 範例

 #### 商品（Product）

 - **查詢商品列表（含分類、關鍵字、排序與分頁）**

   ```http
   GET /products
   ```

   **Query Parameters：**

   - `category`（可選）：商品分類，例如 `FOOD`、`CAR`
   - `search`（可選）：關鍵字（會比對商品名稱等）
   - `orderBy`（預設：`created_date`）：排序欄位
   - `sort`（預設：`desc`）：`asc` 或 `desc`
   - `limit`（預設：`5`）：每頁筆數（最大 1000）
   - `offset`（預設：`0`）：起始位置

   回傳型別為分頁物件 `Page<Product>`，包含 `limit` / `offset` / `total` / `results`。

 - **查詢單一商品**

   ```http
   GET /products/{productId}
   ```
   
   - 存在時回傳 `200 OK` + `Product`
   - 不存在時回傳 `404 NOT FOUND`

 - **新增商品**

   ```http
   POST /products
   Content-Type: application/json
   ```

   Request Body 會對應到 `ProductRequest`，例如：

   ```json
   {
     "productName": "蘋果（澳洲）",
     "category": "FOOD",
     "imageUrl": "https://example.com/apple.jpg",
     "price": 30,
     "stock": 10,
     "description": "這是來自澳洲的蘋果！"
   }
   ```

   - 新增成功回傳 `201 CREATED` + 新建立的 `Product`

 - **修改商品**

   ```http
   PUT /products/{productId}
   Content-Type: application/json
   ```

   - 商品存在 → 更新後回傳 `200 OK` + 更新後的 `Product`
   - 商品不存在 → 回傳 `404 NOT FOUND`

 - **刪除商品**

   ```http
   DELETE /products/{productId}
   ```

   - 執行刪除後回傳 `204 NO CONTENT`

 #### 會員（User）

 - **註冊**

   ```http
   POST /users/register
   Content-Type: application/json
   ```

   Request Body 對應 `UserRegisterRequest`，例如：

   ```json
   {
     "email": "test@example.com",
     "password": "123456"
   }
   ```

   - 註冊成功會建立新用戶並回傳 `201 CREATED` + `User`

 - **登入**

   ```http
   POST /users/login
   Content-Type: application/json
   ```

   Request Body 對應 `UserLoginRequest`，例如：

   ```json
   {
     "email": "test@example.com",
     "password": "123456"
   }
   ```

   - 登入成功回傳 `200 OK` + `User`
   - 登入失敗時會依服務實作回傳對應狀態碼（例如 `400` / `401`）

 ---

 ### 技術棧

 - **Java 21**
 - **Spring Boot 4（Spring Web MVC）**
 - **Spring Data JDBC**
 - **MySQL**（正式執行用）
 - **H2**（測試用）
 - **Lombok**
 - **Maven / mvnw**

 ---

 ### 專案結構概觀（簡要）

 ```text
 mallpractice/
 ├── src/
 │   ├── main/
 │   │   ├── java/com/practice/mallpractice/
 │   │   │   ├── controller/    # Product / User REST API
 │   │   │   ├── service/       # 商業邏輯
 │   │   │   ├── dao/           # 資料存取（JDBC）
 │   │   │   ├── dto/           # Request / Query 物件
 │   │   │   ├── model/         # 資料模型（Product, User...）
 │   │   │   └── util/          # 分頁 Page 等工具類
 │   │   └── resources/
 │   │       └── application.properties
 │   └── test/
 │       └── resources/
 │           ├── schema.sql     # 測試用建表 SQL
 │           └── data.sql       # 測試資料
 ├── pom.xml
 └── README.md

