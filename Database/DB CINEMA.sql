/*
DROP TABLE IF EXISTS cinemahalls;
DROP TABLE IF EXISTS movies;
DROP TABLE IF EXISTS projections;
DROP TABLE IF EXISTS authentications;
DROP TABLE IF EXISTS register;
drop table if exists hall_projection;
drop table if exists food;
*/

-- Creazione della tabella cinemahalls
CREATE TABLE cinemahalls (
  idHall INT auto_increment primary key,
  capacity INT DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Creazione della tabella Movies
CREATE TABLE movies (
  idMovie INT auto_increment primary key,
  title VARCHAR(100) default NULL,
  genre VARCHAR(50) DEFAULT NULL,
  duration time DEFAULT NULL,
  year INT DEFAULT NULL,
  rating VARCHAR(3) DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Creazione della tabella Projections
CREATE TABLE projections (
  idProjection INT auto_increment primary key,
  movieId int DEFAULT NULL,
  date date DEFAULT NULL,
  time time DEFAULT NULL,
  price DOUBLE DEFAULT NULL,
  availableSeats int default null,
  FOREIGN KEY (movieId) REFERENCES movies(idMovie) on update cascade on delete cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Creazione relazioni tra tabelle
create table hall_projection (
	idHall int,
    idProjection int,
    primary key (idHall, idProjection),
    foreign key (idHall) references cinemahalls(idHall) on update cascade on delete cascade,
    foreign key (idProjection) references projections(idProjection) on update cascade on delete cascade
);

-- Creazione della tabella Authentications
CREATE TABLE authentications (
  email VARCHAR(30) PRIMARY KEY NOT NULL,
  password VARCHAR(12) DEFAULT NULL,
  role VARCHAR(12) DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Creazione della tabella Register
CREATE TABLE register (
  purchaseDateAndTime VARCHAR(30) PRIMARY KEY NOT NULL,
  movieTitle VARCHAR(20) DEFAULT NULL,
  projectionDate VARCHAR(20) DEFAULT NULL,
  projectionTime VARCHAR(20) DEFAULT NULL,
  ticketNumber INT DEFAULT NULL,
  price DOUBLE DEFAULT NULL,
  total DOUBLE DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Creazione della tabella Register
CREATE TABLE food (
  description VARCHAR(30) DEFAULT NULL,
  quantity int DEFAULT NULL,
  price DOUBLE DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;