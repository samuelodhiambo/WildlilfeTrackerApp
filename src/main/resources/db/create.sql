SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS Animal (
    id int PRIMARY KEY auto_increament,
    name VARCHAR
);

CREATE TABLE IF NOT EXISTS EndangeredAnimal (
    id int PRIMARY KEY auto_increament,
    name VARCHAR FOREIGN KEY,
    health VARCHAR
);