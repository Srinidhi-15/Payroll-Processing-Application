An Employee Payroll Processing System using Spring Boot 3+, Spring Batch 5+, and MySQL, designed to efficiently handle 1M+ employee salary records.

**💻 Tech Stack**
Java 17+
Spring Boot 3+
Spring Batch 5+
MySQL Database
Postman (API testing)
Lombok (boilerplate code reduction)

**⚡ Key Features**
**1. Automated Payroll Processing :**
Reads employee data (CSV → MySQL) with Spring Batch.
Calculates Gross Salary, Net Salary, HRA, Allowances, Deductions.
Stores results in MySQL for future reporting.

**2️. Scalable Batch Processing**
Efficiently handles 1M+ records using chunk-based processing.
Prevents memory issues by processing in small chunks (e.g., 100 at a time).

**3️. Scheduled Job Execution**
Spring Scheduler (CRON jobs) automatically generates payroll at month-end.
No manual intervention required.

**4️. Flexible Triggers**
Trigger manually via REST API.
Trigger automatically via CRON jobs.

**5️. Clean & Maintainable Code**
Lombok reduces boilerplate code (Getters, Setters, Constructors).
Follows layered architecture: Reader → Processor → Writer.

**🔄 Workflow**
📥 Read → Employee data (CSV/DB)
⚙️ Process → Apply payroll logic (Gross, Net, Deductions)
💾 Write → Save results into MySQL
⏰ Schedule → Runs automatically every month

**🎯 Outcome**
End-to-end automated payroll system.
Eliminates manual effort in salary processing.
Ensures accuracy, scalability & efficiency for large organizations.
