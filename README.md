TestSmartApp

Технології:

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
