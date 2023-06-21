# Railway Reservation System using JDBC

This repository contains a console-based railway reservation system implemented in Java. The project utilizes JDBC (Java Database Connectivity) to establish a connection between the Java application and a database management system (DBMS) for efficient data retrieval and storage.

## Key Features

- **Menu-Driven Interface:** The project provides a user-friendly console-based menu system that allows users to navigate through various functionalities easily.
- **Admin Panel:** An admin panel is available to manage system operations, such as adding new trains, updating train details, and deleting trains from the system.
- **Train Enquiry:** Users can inquire about available trains by specifying the source and destination stations. The system displays relevant train information, including train number, departure time, arrival time, and available seats.
- **User Login:** Users can create an account and log in to the system using their credentials. This allows for personalized ticket booking and reservation management.
- **Ticket Booking:** Logged-in users can book tickets by providing details such as the train number, date of travel, and passenger information. The system validates seat availability and updates the database accordingly.
- **Reservation Management:** Logged-in users can manage their reservations by viewing booked tickets, canceling reservations, or modifying passenger details.
- **Error Handling:** Comprehensive error handling mechanisms are implemented to handle exceptions and provide meaningful error messages to users, enhancing the system's robustness and reliability.

## Prerequisites

To run this project, ensure that you have the following prerequisites installed:

- Java Development Kit (JDK)
- A compatible DBMS (e.g., MySQL, PostgreSQL)
- JDBC driver for your DBMS

## Getting Started

1. Clone this repository to your local machine using the following command:

   ```bash
   git clone https://github.com/Arpitpatel1706/Railway-Reservation-System-using-JDBC
   ```

2. Configure the JDBC driver for your DBMS by adding the appropriate JAR file to the project's classpath. Refer to the documentation of your DBMS and JDBC driver for detailed instructions.

3. Set up the database by executing the SQL script provided in the `Prerequisite.md` file. These scripts will create the necessary tables and initial data.

4. Compile the Java source files using the following command:

   ```bash
   javac *.java
   ```

5. Run the application using the following command:

   ```bash
   java Railway.java
   ```

6. Follow the on-screen instructions to navigate through the menu and perform various operations.


## Acknowledgments

Special thanks to the developers and contributors of the libraries, frameworks, and resources used in this project.

If you have any questions or suggestions, please feel free to open an issue or contact the project maintainers.
 
