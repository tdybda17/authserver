-- SQL Migration 2021-02-17T14.43.58.382730 session
-- Session for user

CREATE TABLE IF NOT EXISTS SESSION(
    `KEY` VARCHAR(44) NOT NULL UNIQUE,
    `USER_ID` VARCHAR(36) NOT NULL,
    `EXPIRE_DATE` DATETIME NOT NULL,

    CONSTRAINT FOREIGN KEY (`USER_ID`) REFERENCES USERS(`ID`)
);
