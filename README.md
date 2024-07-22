TestSmartApp

**Технології:**

- Programming Language: Java 22;
  
- UI Libare: Java Swing;
  
- DataBase: Postgresql(PgAdmin4);
  
- IDE: NetBeans;

Links:

- Java 22 https://www.oracle.com/java/technologies/downloads/

- NetBeans IDE https://netbeans.apache.org/front/main/download/

- PgAdmin 4 & Postgresql https://www.postgresql.org/download/

- rs2xml.jar https://sourceforge.net/projects/finalangelsanddemons/files/rs2xml.jar/download
  
Database: 

1) Створюємо БД з назвою "test_db"
   
2) Створюємо таблиці за допомогою запитів:
   
-- Create Subdivision table
CREATE TABLE Subdivision (
    ID SERIAL PRIMARY KEY,
    Name TEXT NOT NULL UNIQUE
);

-- Create Position table
CREATE TABLE Position (
    ID SERIAL PRIMARY KEY,
    Name TEXT NOT NULL UNIQUE
);

-- Create AbsenceReason table
CREATE TABLE AbsenceReason (
    ID SERIAL PRIMARY KEY,
    Reason TEXT NOT NULL UNIQUE
);

-- Create ProjectType table
CREATE TABLE ProjectType (
    ID SERIAL PRIMARY KEY,
    TypeName TEXT NOT NULL UNIQUE
);

-- Create Employee table
CREATE TABLE Employee (
    ID SERIAL PRIMARY KEY, 
    FullName TEXT NOT NULL, 
    Password TEXT, 
    SubdivisionID INT NOT NULL REFERENCES Subdivision(ID), 
    PositionID INT NOT NULL REFERENCES Position(ID), 
    Status BOOLEAN NOT NULL, 
    PeoplePartnerID INT REFERENCES Employee(ID), 
    OutOfOfficeBalance INT NOT NULL, 
    Photo BYTEA 
);

-- Create LeaveRequest table
CREATE TABLE LeaveRequest (
    ID SERIAL PRIMARY KEY, 
    EmployeeID INT NOT NULL REFERENCES Employee(ID), 
    AbsenceReasonID INT NOT NULL REFERENCES AbsenceReason(ID), 
    StartDate DATE NOT NULL, 
    EndDate DATE NOT NULL, 
    Comment TEXT, 
    Status BOOLEAN NOT NULL DEFAULT FALSE 
);

-- Create ApprovalRequest table
CREATE TABLE ApprovalRequest (
    ID SERIAL PRIMARY KEY, 
    ApproverID INT NOT NULL REFERENCES Employee(ID), 
    LeaveRequestID INT NOT NULL REFERENCES LeaveRequest(ID), 
    Status BOOLEAN NOT NULL DEFAULT TRUE, 
    Comment TEXT 
);

-- Create Project table
CREATE TABLE Project (
    ID SERIAL PRIMARY KEY, 
    ProjectTypeID INT NOT NULL REFERENCES ProjectType(ID),
    StartDate DATE NOT NULL, 
    EndDate DATE, 
    ProjectManagerID INT NOT NULL REFERENCES Employee(ID), 
    Comment TEXT, 
    Status BOOLEAN NOT NULL DEFAULT TRUE 
);
-- Create EmployeeProject table
CREATE TABLE EmployeeProject (
    ID SERIAL PRIMARY KEY,
    EmployeeID INT NOT NULL,
    ProjectID INT NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES Employee(ID),
    FOREIGN KEY (ProjectID) REFERENCES Project(ID)
);

3) Результат виконання SQL запитів, повинен дати такий результат (ERD діаграмма):

<img width="985" alt="Снимок экрана 2024-07-10 в 17 05 19" src="https://github.com/Ace-Red/TestSmartApp/assets/54742708/39cee9ef-8d1a-4dde-ad23-146f04b29f05">

4) Після чого клонуємо репозиторій в IDE Netbeans та натискаємо правой кнопкою миші на Libary та Add Libary та обираємо "Postgresql JDBC Driver":
   
<img width="221" alt="Снимок экрана 2024-07-10 в 17 14 15" src="https://github.com/Ace-Red/TestSmartApp/assets/54742708/013a8fb7-0874-4378-88b1-5a69adb86c93">

5) Також натискаємо правой кнопкою миші на Libary та Add JAR та додаємо попередньо завантажений "rs2xml.jar":

6) Запускаємо додаток через файл Login.java (Попередньо зробити запис в табличку Employee та заповнити такі поля своєю інформацією (клас ConnectionDB)):
   
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/test_db";
    static final String USER = "postgres";
    static final String PASS = "..";)
   
7) Також в таблиці Position важливо зробити такі записи, дотримуючись id та назви посад:
   
<img width="272" alt="Снимок экрана 2024-07-10 в 17 25 30" src="https://github.com/Ace-Red/TestSmartApp/assets/54742708/8f7e1592-ef1c-4d3e-878f-70c4dac4b556">

**Скріншоти та інструкція користувача:**

Авторизація:

<img width="1112" alt="Login" src="https://github.com/user-attachments/assets/521eca2d-1665-48cf-89a9-399f33cfa484">

- Користувач повинен заповнити поля та натиснути кнопку "Login"

**PM**

1) Home page:

<img width="1312" alt="Main Page pm" src="https://github.com/user-attachments/assets/6a1bf103-082b-45f3-ae9a-253b0854217e">

- PM обирає, що саме він хоче обрати та натискає відповідну кнопку

2) Інформація про співробітників:

<img width="1112" alt="Employee List PM" src="https://github.com/user-attachments/assets/88975d8a-8252-4271-a5b7-6a75a525c69d">

- PM може обрати бажаного співробітника та поставити його на певний проект, знайти запис про нього, побачити про нього детальну інформацію

3) Детальна інформація про обраного співробітника:

<img width="1012" alt="Detail Employee PM" src="https://github.com/user-attachments/assets/4a42af2d-28e2-4039-a6e4-7c01c21b2094">

- PM може побачити детальну інформацію про співробітника

4) Екран перегляду списку проектів:

<img width="1112" alt="List Project PM" src="https://github.com/user-attachments/assets/f903062f-1fa9-45e1-8cf2-310143090675">

- PM може побачити список своїх проектів та деактивувати їх, оновити певний або додати новий
  
5) Екран оновлення обраного проекту:
   
<img width="1012" alt="Update pr PM" src="https://github.com/user-attachments/assets/2138ed9b-d8c4-44f6-b68c-545575326c2d">

6) Екран додавання нового проекту:

<img width="1012" alt="Add project PM" src="https://github.com/user-attachments/assets/9e7e3907-67c9-4af7-b128-20e8618e5519">

7) Перегляд списку approval requests:

<img width="1112" alt="Approval r PM" src="https://github.com/user-attachments/assets/7b7a3a28-a606-4289-a53a-9d047f939cd5">

- PM може побачити детальну інформацію про leave request співробітника його проекту, який він погодив

<img width="1012" alt="Detail Appr r PM" src="https://github.com/user-attachments/assets/27cd34e3-dcce-4cd8-a8e1-693bb8c2db3f">

8) Екран перегляду leave requests співробітників:

<img width="1112" alt="Leave r PM" src="https://github.com/user-attachments/assets/cdec42c6-916c-45a6-a71e-7c843cc4206f">

- (Дивитися розділ "HR" там є доступні записи та показаний увесь функціонал)

**Employee**

1) Home Page:

<img width="1312" alt="Home page" src="https://github.com/user-attachments/assets/b4ecc87a-4a85-4c93-bbc7-18bc694a0676">

- Співробитник обирає, що саме він хоче обрати та натискає відповідну кнопку

2) Список проектів над якими він працює:

<img width="1112" alt="Proj List" src="https://github.com/user-attachments/assets/2bf41b24-dd3d-4f53-bf47-eb52b365033b">

- Співробитник може переглянути усі записи або переглянути детальну інформацію про обраний

<img width="1012" alt="Detail Proj" src="https://github.com/user-attachments/assets/3f885391-a162-44d8-96ea-286e7a7009b2">

3) Список leave requests:

<img width="1112" alt="List leav r" src="https://github.com/user-attachments/assets/3ce75001-9e17-4c48-88fe-9613b5c98ea9">

- Співробитник може переглянути усі записи, переглянути детальну інформацію про обраний, оновити інформацію або ж створити новий

<img width="1012" alt="Detail leave r" src="https://github.com/user-attachments/assets/2a094579-834b-4550-ad6a-0aaa795f596d">

<img width="1012" alt="Update Leav r" src="https://github.com/user-attachments/assets/bb06a9b2-a1a0-42e6-8ed6-2177f3cdc742">

<img width="1012" alt="Add leave r" src="https://github.com/user-attachments/assets/5198dc3e-dde5-4d9d-abb0-d6a4910af547">

4) Список approval requests:

<img width="1112" alt="List apr r" src="https://github.com/user-attachments/assets/766e8888-dd07-44bc-b783-1918e5d0b0b4">

- Співробитник може переглянути усі записи, переглянути детальну інформацію про обраний

<img width="1012" alt="Detail app r" src="https://github.com/user-attachments/assets/f4469699-10d8-4d76-8f4e-a95644057546">


