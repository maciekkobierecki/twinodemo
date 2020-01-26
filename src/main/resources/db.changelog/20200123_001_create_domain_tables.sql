--liquibase formatted sql

--changeset maciekkobierecki:20200123_001_create_domain_tables endDelimiter:/


CREATE TABLE LOAN
(
ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
START_DATE TIMESTAMP     NOT NULL,
END_DATE TIMESTAMP     NOT NULL,
AMOUNT NUMERIC(15,2)  NOT NULL,
CONSTRAINT C_LOAN_PK PRIMARY KEY (ID)
)
/

--rollback DROP TABLE LOANS

CREATE TABLE LOAN_PROLONG
(
ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
LOAN_ID INTEGER,
OLD_LOAN_END_DATE TIMESTAMP     NOT NULL,
NEW_LOAN_END_DATE TIMESTAMP     NOT NULL,
PROLONG_PERIOD INTEGER NOT NULL,
CONSTRAINT C_LOAN_PROLONG_PK PRIMARY KEY (ID)
)
/

ALTER TABLE LOAN_PROLONG ADD CONSTRAINT LOAN_ID_FK FOREIGN KEY (LOAN_ID) REFERENCES LOAN(ID)
/

--rollback DROP TABLE LOAN_PROLONGS

CREATE TABLE LOAN_PETITION
(
ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
CREATE_DATE TIMESTAMP     NOT NULL,
IP_ADDRESS VARCHAR(50)  NOT NULL,
CONSTRAINT C_LOAN_PETITION_PK PRIMARY KEY (ID)
)
/

CREATE INDEX IP_ADDRESS_IDX ON LOAN_PETITION(IP_ADDRESS)
/

--rollback DROP TABLE LOANS_PETITION

