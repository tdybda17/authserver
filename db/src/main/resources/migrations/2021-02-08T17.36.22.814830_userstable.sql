-- SQL Migration 2021-02-08T17.36.22.814830 userstable
-- Userstable

CREATE TABLE IF NOT EXISTS USERS(
    `ID` VARCHAR(36) PRIMARY KEY,
    `FIRST_NAME` VARCHAR(45) NULL DEFAULT NULL,
    `LAST_NAME` VARCHAR(130) NULL DEFAULT NULL,
    `EMAIL` VARCHAR(255) UNIQUE NOT NULL,
    `PASSWORD` VARCHAR(148) UNIQUE NOT NULL,
    `ADMIN` BOOL NOT NULL DEFAULT FALSE,
    `JOINED_DATE` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `LAST_MODIFIED` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
