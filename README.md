# Arbitrary Precision Arithmetic Library

## 📌 Overview
This project implements an arbitrary/infinite-precision arithmetic library in Java using OOP concepts. It supports operations on both integers and floating-point numbers with custom classes: `AInteger` and `AFloat`.

## ✨ Features
- Integer and Float arithmetic with arbitrary digit length
- Supports: Addition, Subtraction, Multiplication, Division
- CLI support using Java (`MyInfArith.java`)
- CLI support using Python wrapper (`run.py`)
- Packaged into a `.jar` for reusability
- Compatible with Maven projects

## 🚀 Usage

### 🔧 Mode 1: Java CLI
```bash
javac src/main/java/arbitraryarithmetic/AInteger.java src/main/java/arbitraryarithmetic/AFloat.java src/main/java/MyInfArith.java
java -cp src/main/java MyInfArith int add 123 456
```

### 🐍 Mode 2: Python CLI
```bash
python run.py float mul 1.5 2.5
```

### 📦 Mode 3: Using the JAR
```bash
java -cp.: target/aarithmetic.jar MyInfArith.MyInfArith int sub 1000 250
```

## 🔗 Using the Library in Another Java Project

```java
import arbitraryarithmetic.*;

public class TestMain {
    public static void main(String[] args) {
        AInteger a = new AInteger("1234");
        AInteger b = new AInteger("4321");
        AInteger result = a.add(b);
        System.out.println(result);  // Should print 5555
    }
}
```

### Compile and Run
```bash
javac -cp target/aarithmetic.jar TestMain.java
java -cp .:target/aarithmetic.jar TestMain
```

## 🛠 Build with Maven
```bash
mvn clean install
```

## 📁 Project Structure

```
SDF-Arbritary-Arthimetic-precision/
├── MyInfArith
├── initial_raw_files_for_arbritrayarithmetic
├── src/main/java/arbitraryarithmetic/
├── target/aarithmetic.jar
├── run.py
├── pom.xml
├── reports
├── README.md
```

## 👥 Author
- RISHIT MITTAL
- CS24BTECH11053
  
