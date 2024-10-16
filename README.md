------ BANKING APPLICATION ------
## FEATURES
### SIGNUP OFFER : REGISTER TODAY AND GET INITIAL DEPOSIT $200!!! HURRY UP!!! GRAB IT!!!
### USER MANAGEMENT
    -> New Users can register, and existing users can login to access the application.
    -> Users can update and delete their profile.

### ACCOUNT MANAGEMENT
    -> User can add an account and get an initial deposit of $200.
    -> Users can view their Account Details.

### TRANSACTION MANAGEMENT
    -> User can deposit or withdraw from his account.
    -> Validations: 
          1. When the User enters withdrawal amount greater than the balance, he will get an error message stating
             "Withdraw amount is more than Balance".
          2. User should maintain a minimum balance of 50$. Else he will get an error message stating
             "Minumum Balance should be $50".
    -> User can view the transactions made.

## GETTING STARTED
### PREREQUISITES
Before starting, ensure that the following are installed.
  - Java 17+
  - Maven 3.6+
  - IDE
  - MySQL
  - A browser to view the front end.
    
### RUNNING THE APPLICATION
  1. Clone the Repository
           In GITBASH
             git clone https://github.com/nithyamahe23/Rajasekaran_Nithya_BankingApplication_CaseStudy.git
             cd Rajasekaran_Nithya_BankingApplication_CaseStudy
  2. Database Setup
             Create a MySQL database named "bankmanagement".
             --- Drop and Create the database ---
               DROP DATABASE IF EXISTS bankmanagement;
               CREATE DATABASE bankmanagement;
  3. Update the Database Configuration
             Modify 'src/main/resources/application.properties'
             Include the below lines
                   spring.datasource.url = jdbc:mysql://localhost:3306/bankmanagement
                   spring.datasource.username = db_username
                   spring.datasource.password = db_password
  4. Build and Run the Application
             Use Maven to build and run the application.
             In GITBASH
                 mvn clean install
                 mvn spring-boot:run
  5. Access the Application
             Open the Browser and navigate to
                   http://localhost:8080/user/showLoginForm
## TECHNOLOGIES USED

  1. BACKEND : Java, Spring Boot(Spring MVC, Spring Data JPA)
  2. DATABASE : MySQL(JPA/Hibernate for ORM)
  3. FRONTEND : Thymeleaf, HTML5, CSS3
  4. BUILD TOOL : Maven

### RUNNING THE APPLICATION LOCALLY

  1. REGISTRATION AND LOGIN: New Users and Register and Login to the Application
  2. ACCOUNT MANAGEMENT: User can add a new account and view the Account Details.
  3. TRANSACTION MANAGEMENT: User can Deposit or Withdraw from his account and view the Transaction Details.

## WORKING WITH THE APPLICATION
### USER FUNCTIONALITIES
    -> Register     : When User Clicks on Register, he will be redirected to fill his details. If the User is successfully registered,
                      he will be redirected back to "Login" screen.
    -> Login        : When the User gives valid Username and password, he will be redirected to "Welcome" page.
    -> Edit Profile : When the user clicks on "EDIT PROFILE", he can update his details. When clicks on "DELETE", his profile will be deleted.

### ACCOUNT MANAGEMENT
    -> Add Account     : When the user clicks on "ADD ACCOUNT", he can add an account to his profile. When he adds, he will get an initial deposit of $200.
    -> Account Details : When the user clicks on "ACCOUNT DETAILS", he can view the details of the account.

### TRANSACTION MANAGEMENT
    -> Deposit             : When the user clicks on "DEPOSIT" in account details, he can deposit amount to the account.
    -> Withdraw            : When the user clicks on "WITHDRAW" in account details, he can withdraw amount from the account.
    -> Transaction Details : When user click on "TRANSACTION DETAILS", he can view the details of the transactions he performed. 
