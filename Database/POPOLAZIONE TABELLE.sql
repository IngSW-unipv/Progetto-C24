-- Inserimento di alcuni dati nella tabella autenticazione
INSERT INTO authentications(email,password,role) VALUES
("admin1@gmail.com","@dmin1","admin"),
("admin2@gmail.com","@dmin2","admin"),
("admin3@gmail.com","@dmin3","admin"),
("cashier1@gmail.com","c@s","cashier");

-- Inserimento di alcuni dati nella tabella cinemahalls
INSERT INTO cinemahalls(idHall,capacity) VALUES
("1",150),
("2",200),
("3",250),
("4",300),
("5",350),
("6",400);

-- Inserimento di alcuni dati nella tabella food
INSERT INTO food(description,quantity,price) VALUES
("Pizza",50,5.0);
