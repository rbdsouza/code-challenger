# Code Challenge

This project is a **Java 21** implementation of a simple in-memory payment processor designed for a code challenge. It supports payment registration, filtering, statistics calculation, sorting, parallel processing simulation, and unit testing with **JUnit 5**.

## Overview

The application allows you to:

- Add a new payment
- Retrieve all payments
- Retrieve payments filtered by status
- Calculate statistics:
  - Total number of payments
  - Total amount of successful payments
  - Average amount of successful payments
- Sort payments by amount
- Simulate concurrent payment processing using `parallelStream()`
- Run automated unit tests with **JUnit 5**

## Technologies

- **Java 21**
- **Maven**
- **JUnit 5**
- **Java Collections**
- **parallelStream()** for concurrency simulation

## Project Structure

```text
src
├── main
│   └── java
│       └── com
│           └── rbs
│               └── code_challenger
│                   ├── CodeChallengerApplication.java
│                   ├── dto
│                   │   ├── Payment.java
│                   │   └── PaymentStatus.java
│                   └── processor
│                       ├── Processor.java
│                       └── PaymentProcessor.java
└── test
    └── java
        └── com
            └── rbs
                └── code_challenger
                    ├── dto
                    │   └── PaymentTest.java
                    └── processor
                        └── PaymentProcessorTest.java
````

## Requirements

Before running the project, make sure you have installed:

* **Java 21**
* **Maven 3.9+**

## Check Java Version

```bash
java -version
```

Expected output should include something similar to:

```bash
openjdk version "21"
```

## Check Maven Version

```bash
mvn -version
```

## Maven Configuration

This project uses the following `pom.xml` to enable Java 21 compilation and unit testing with JUnit 5:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.rbs</groupId>
    <artifactId>code-challenger</artifactId>
    <version>1.0.0</version>
    <name>code-challenger</name>
    <description>Payment processor challenge with unit tests</description>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <junit.jupiter.version>5.10.2</junit.jupiter.version>
        <maven.surefire.plugin.version>3.2.5</maven.surefire.plugin.version>
        <maven.compiler.plugin.version>3.11.0</maven.compiler.plugin.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>${junit.jupiter.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>${maven.compiler.plugin.version}</version>
                <configuration>
                    <source>${maven.compiler.source}</source>
                    <target>${maven.compiler.target}</target>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${maven.surefire.plugin.version}</version>
            </plugin>
        </plugins>
    </build>

</project>
```

## How to Compile

From the project root, run:

```bash
mvn clean compile
```

This command compiles the source code using Java 21.

## How to Run Tests

To execute all unit tests, run:

```bash
mvn test
```

This will execute the JUnit 5 test suite located under:

```text
src/test/java
```

## How to Package

To build the project and generate compiled artifacts, run:

```bash
mvn clean package
```

## How to Run the Application

Because the current `pom.xml` is focused on compilation and unit tests only, the simplest way to run the application is:

### Option 1: Compile with Maven and run with Java

```bash
mvn clean compile
java -cp target/classes com.rbs.code_challenger.CodeChallengerApplication
```

### Option 2: Compile manually with `javac`

```bash
javac -d out src/main/java/com/rbs/code_challenger/dto/*.java src/main/java/com/rbs/code_challenger/processor/*.java src/main/java/com/rbs/code_challenger/CodeChallengerApplication.java
java -cp out com.rbs.code_challenger.CodeChallengerApplication
```

## Example Output

The application demonstrates:

* Listing all payments
* Printing total number of payments
* Printing total successful amount
* Printing average successful amount
* Listing successful payments
* Sorting payments by amount
* Processing additional payments in parallel

Example console output:

```text
=== LISTING ALL PAYMENTS ===
Payment[id=1, amount=10.0, currency=BRL, status=SUCCESS]
Payment[id=2, amount=5.0, currency=BRL, status=SUCCESS]

=== TOTAL NUMBER OF PAYMENTS ===
8

=== TOTAL SUCCESSFUL AMOUNT ===
123.97

=== AVERAGE SUCCESSFUL AMOUNT ===
20.661666666666665
```

## Notes

* The project uses a Java `record` for `Payment`
* The processor stores payments in memory using `CopyOnWriteArrayList`
* Duplicate payment IDs are not allowed
* Parallel payment processing is simulated with `parallelStream()`
* Unit tests are implemented using **JUnit 5**
* This project is intended for learning and code challenge purposes

## Java 21 Note

This project is built to use **Java 21**. Make sure your `JAVA_HOME` points to a Java 21 installation.

Example:

```bash
echo $JAVA_HOME
```

## Author

**Rogerson Bueno de Souza**
