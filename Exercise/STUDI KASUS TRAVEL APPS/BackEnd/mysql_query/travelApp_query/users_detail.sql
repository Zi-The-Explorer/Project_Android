USE TRAVELAPP;
CREATE TABLE USER_DETAIL(
	ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    USERS_ID INT UNIQUE NOT NULL,
    FOREIGN KEY (USERS_ID) REFERENCES USERS (ID)
) ENGINE = InnoDB;
