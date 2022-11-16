# Money Transfer Assessment for BSFDV  

#### Technologies used
    - Java 8
    - Spring Boot
    - Spring Data Jpa
    - H2 database
    - Junit

#### Application overview
    - Created Models for Account Holder and Accounts
    - Connect and load data at the time of startup
    - BigDecimal for amount. calculated debit and credit by locking the user account row
        @Lock(LockModeType.PESSIMISTIC_WRITE)
        @Query("select a from Account a where a.accountId = :id")
        Account updateAccountBalace(@Param("id") long id);
    - Exception handled by @ControllerAdvice

#### Rest API urls
###### Accounts
    1. GET http://localhost:8080/accounts - Get all accounts
    2. GET http://localhost:8080/accounts/{accountId} - Get account based on accountNum
    3. GET http://localhost:8080/accounts/{accountId}/balance - Get balance based on AccountNum
    4. PUT http://localhost:8080/accounts/{accountId}/deposit/{amount} - Amount deposit to AccountNum
    5. PUT http://localhost:8080/accounts/{accountId}/withdraw/{amount} - Amount deposit to AccountNum
    6. DELETE http://localhost:8080/accounts/{accountId} - Delete Account
    7. POST http://localhost:8080/accounts - Create account by passing required params
###### User
    1. GET http://localhost:8080/account-holders - Get all account holders
    2. GET http://localhost:8080/account-holders/{userName} - Get account holders based on userName
    3. POST http://localhost:8080/account-holders/create - Create User
    4. PUT http://localhost:8080/account-holders/{UserName} - Update User
    5. DELETE http://localhost:8080/account-holders/{userId} - Delete User
###### Transaction
    1. POST http://localhost:8080/transaction - Amount transfer between accounts

 