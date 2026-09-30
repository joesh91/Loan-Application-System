-- ============================================================
-- BANK LOAN MANAGEMENT SYSTEM
-- Database Schema
-- PostgreSQL
-- ============================================================

-- ============================================================
-- DATABASE
-- ============================================================

CREATE DATABASE bank_loan_db;

-- After creating the database, connect to bank_loan_db
-- in pgAdmin before running the following table scripts.


-- ============================================================
-- 1. CUSTOMER
-- ============================================================

CREATE TABLE CUSTOMER (
    CUSTOMER_ID SERIAL PRIMARY KEY,
    FIRST_NAME VARCHAR(50) NOT NULL,
    LAST_NAME VARCHAR(50) NOT NULL,
    NIC VARCHAR(20) UNIQUE NOT NULL,
    EMAIL VARCHAR(100) UNIQUE NOT NULL,
    PHONE VARCHAR(40) UNIQUE NOT NULL,
    ADDRESS TEXT NOT NULL,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);


-- ============================================================
-- 2. USERS
-- ============================================================

CREATE TABLE USERS (
    USERS_ID SERIAL PRIMARY KEY,
    USERNAME VARCHAR(100) UNIQUE NOT NULL,
    PASSWORD VARCHAR(100) NOT NULL,
    ROLE VARCHAR(20) NOT NULL,
    CUSTOMER_ID INT NOT NULL,

    CONSTRAINT FK_USERS_CUS
        FOREIGN KEY (CUSTOMER_ID)
        REFERENCES CUSTOMER(CUSTOMER_ID)
);


-- ============================================================
-- 3. CUSTOMER REGISTRATION
-- ============================================================

CREATE TABLE CUSTOMER_REGISTRATION (
    REGISTRATION_ID SERIAL PRIMARY KEY,
    FIRST_NAME VARCHAR(100) NOT NULL,
    LAST_NAME VARCHAR(100) NOT NULL,
    NIC VARCHAR(12) UNIQUE NOT NULL,
    EMAIL VARCHAR(100) UNIQUE NOT NULL,
    PHONE VARCHAR(30) UNIQUE NOT NULL,
    ADDRESS VARCHAR(300) NOT NULL,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    STATUS VARCHAR(20) NOT NULL
);


-- ============================================================
-- 4. OTP
-- ============================================================

CREATE TABLE OTP (
    OTP_ID SERIAL PRIMARY KEY,
    USERID INT NOT NULL,
    OTPCODE VARCHAR(6) NOT NULL,
    EXPIRED_AT TIMESTAMP NOT NULL,
    USED BOOLEAN NOT NULL DEFAULT FALSE,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT FK_OTP_USER
        FOREIGN KEY (USERID)
        REFERENCES USERS(USERS_ID)
);


-- ============================================================
-- 5. STAFF
-- ============================================================

CREATE TABLE STAFF (
    STAFF_ID SERIAL PRIMARY KEY,
    NAME VARCHAR(100),
    EMAIL VARCHAR(100),
    POSITION VARCHAR(50)
);


-- ============================================================
-- 6. LOAN APPLICATION
-- ============================================================

CREATE TABLE LOAN_APPLICATION (
    APPLICATION_ID SERIAL PRIMARY KEY,
    CUSTOMER_ID INT NOT NULL,
    LOAN_TYPE VARCHAR(50) NOT NULL,
    REQUESTED_AMOUNT DECIMAL(12,2) NOT NULL,
    PURPOSE TEXT NOT NULL,
    APPLICATION_DATE DATE DEFAULT CURRENT_DATE,
    STATUS VARCHAR(20) NOT NULL,

    CONSTRAINT FK_APP_CUS
        FOREIGN KEY (CUSTOMER_ID)
        REFERENCES CUSTOMER(CUSTOMER_ID)
);


-- ============================================================
-- 7. LOAN
-- ============================================================

CREATE TABLE LOAN (
    LOAN_ID SERIAL PRIMARY KEY,
    APPLICATION_ID INT UNIQUE,
    APPROVED_AMOUNT DECIMAL(12,2) NOT NULL,
    INTEREST_RATE DECIMAL(5,2) NOT NULL,
    DURATION_MONTH INT NOT NULL,
    START_DATE DATE NOT NULL,
    END_DATE DATE NOT NULL,
    STATUS VARCHAR(20) NOT NULL,

    CONSTRAINT FK_LN_APPID
        FOREIGN KEY (APPLICATION_ID)
        REFERENCES LOAN_APPLICATION(APPLICATION_ID)
);


-- ============================================================
-- 8. PAYMENT
-- ============================================================

CREATE TABLE PAYMENT (
    PAYMENT_ID SERIAL PRIMARY KEY,
    LOANID INT NOT NULL,
    PAYMENT_DATE DATE NOT NULL DEFAULT CURRENT_DATE,
    AMOUNT DECIMAL(12,2) NOT NULL,
    PAYMENT_STATUS VARCHAR(20) NOT NULL,

    CONSTRAINT FK_PY_LN
        FOREIGN KEY (LOANID)
        REFERENCES LOAN(LOAN_ID)
);


-- ============================================================
-- 9. APPLICATION REVIEW
-- ============================================================

CREATE TABLE APPLICATION_REVIEW (
    REVIEW_ID SERIAL PRIMARY KEY,
    APPLICATION_ID INT,
    STAFF_ID INT,
    DECISION VARCHAR(20),
    COMMENTS TEXT,
    REVIEW_DATE DATE DEFAULT CURRENT_DATE,

    CONSTRAINT FK_REVIEW_APPLICATION
        FOREIGN KEY (APPLICATION_ID)
        REFERENCES LOAN_APPLICATION(APPLICATION_ID),

    CONSTRAINT FK_REVIEW_STAFF
        FOREIGN KEY (STAFF_ID)
        REFERENCES STAFF(STAFF_ID)
);


-- ============================================================
-- 10. LOAN DOCUMENT
-- ============================================================

CREATE TABLE LOAN_DOCUMENT (
    DOCUMENT_ID SERIAL PRIMARY KEY,
    DOCUMENT_TYPE VARCHAR(100) NOT NULL,
    FILE_NAME VARCHAR(200) NOT NULL,
    FILE_PATH VARCHAR(200),
    UPLOADED_AT DATE DEFAULT CURRENT_DATE,
    STATUS VARCHAR(50) NOT NULL,
    LOANAPPLICATION INT,

    CONSTRAINT FK_DOCUMENT_APPLICATION
        FOREIGN KEY (LOANAPPLICATION)
        REFERENCES LOAN_APPLICATION(APPLICATION_ID)
);


-- ============================================================
-- END OF DATABASE SCHEMA
-- ============================================================