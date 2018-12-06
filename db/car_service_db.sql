
CREATE TABLE client (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	first_name VARCHAR(40) DEFAULT NULL,
	last_name VARCHAR(40) DEFAULT NULL,
	patronnymic VARCHAR(40) DEFAULT NULL,
	phone VARCHAR(40) DEFAULT NULL
);  

CREATE TABLE mechanic (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	first_name VARCHAR(40) DEFAULT NULL,
	last_name VARCHAR(40) DEFAULT NULL,
	patronnymic VARCHAR(40) DEFAULT NULL,
	wages DECIMAL(12,2) DEFAULT NULL,
	phone VARCHAR(40) DEFAULT NULL
);  

CREATE TABLE orders (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	description VARCHAR(5000) DEFAULT NULL,
	client_id BIGINT DEFAULT NULL,
	mechanic_id BIGINT DEFAULT NULL,
	status VARCHAR(50) DEFAULT NULL,
	date_creat TIMESTAMP DEFAULT NULL,
	completion_date TIMESTAMP DEFAULT NULL,
	price DECIMAL(12,2) DEFAULT NULL,
	FOREIGN KEY (client_id) REFERENCES client(id),
	FOREIGN KEY (mechanic_id) REFERENCES mechanic(id)
);  

INSERT INTO client (first_name, last_name, patronnymic, phone)
VALUES ('Семенова', 'София', 'Семеновна', '+7(311)311-11-11');
INSERT INTO client (first_name, last_name, patronnymic, phone)
VALUES ('Сидорова', 'Светлана', 'Сидоровна', '+7(311)322-22-21');
INSERT INTO client (first_name, last_name, patronnymic, phone)
VALUES ('Иванов', 'Иван', 'Иванович', '+7(311)525-25-21');
INSERT INTO client (first_name, last_name, patronnymic, phone)
VALUES ('Максимов', 'Максим', 'Максимович', '+7(311)22-55-99');
INSERT INTO client (first_name, last_name, patronnymic, phone)
VALUES ('Федоров', 'Федор', 'Федорович', '+7(311)337-77-77');
INSERT INTO client (first_name, last_name, patronnymic, phone)
VALUES ('Николаев', 'Николай', 'Николаевич', '+7(522)555-79-79');


INSERT INTO mechanic (first_name, last_name, patronnymic, wages, phone)
VALUES ('Петров', 'Петр', 'Петрович', '200.50', '+7(311)311-11-11');
INSERT INTO mechanic (first_name, last_name, patronnymic, wages, phone)
VALUES ('Павлов', 'Павел', 'Павлович', '325.85', '+7(311)322-22-21');


INSERT INTO orders (description, client_id, mechanic_id, status, date_creat, completion_date, price)
VALUES (
	'Замена ремня ГРМ ВАЗ2110', (
		SELECT client.id FROM client WHERE first_name LIKE '%Иванов%'
	), (
		SELECT mechanic.id FROM mechanic WHERE first_name LIKE '%Павлов%'
	), 'PLANNED', '2018-09-01 12:00:00', '2018-09-01 14:00:00', '2500.50'
);

INSERT INTO orders (description, client_id, mechanic_id, status, date_creat, completion_date, price)
VALUES (
	'Замена масла двигателя ВАЗ2170', (
		SELECT client.id FROM client WHERE first_name LIKE '%Сидорова%'
	), (
		SELECT mechanic.id FROM mechanic WHERE first_name LIKE '%Петров%'
	), 'PLANNED', '2018-09-01 13:00:00', '2018-09-01 13:30:00', '350.80'
);

INSERT INTO orders (description, client_id, mechanic_id, status, date_creat, completion_date, price)
VALUES (
	'Замена двигателя Форд Фокус 2', (
		SELECT client.id FROM client WHERE first_name LIKE '%Семенова%'
	), (
		SELECT mechanic.id FROM mechanic WHERE first_name LIKE '%Павлов%'
	), 'FULFILLED', '2018-09-02 10:00:00', '2018-09-03 14:00:00', '125000.30'
);

INSERT INTO orders (description, client_id, mechanic_id, status, date_creat, completion_date, price)
VALUES (
	'Регулировка фар, замена тормозного цилиндра, тормозного шланга, прокачка тормозной системы Toyota Camry', (
		SELECT client.id FROM client WHERE first_name LIKE '%Федоров%'
	), (
		SELECT mechanic.id FROM mechanic WHERE first_name LIKE '%Петров%'
	), 'FULFILLED', '2018-09-05 13:20:00', '2018-09-05 16:30:00', '3350.20'
);

INSERT INTO orders (description, client_id, mechanic_id, status, date_creat, completion_date, price)
VALUES (
	'Замена прокладки между рулем и сиденьем', (
		SELECT client.id FROM client WHERE first_name LIKE '%Максимов%'
	), (
		SELECT mechanic.id FROM mechanic WHERE first_name LIKE '%Петров%'
	), 'ACCEPTED', '2018-11-01 13:00:00', '2018-11-01 14:00:00', '1000.00'
);

INSERT INTO orders (description, client_id, mechanic_id, status, date_creat, completion_date, price)
VALUES (
	'Замена прокладки между рулем и сиденьем', (
		SELECT client.id FROM client WHERE first_name LIKE '%Николаев%'
	), (
		SELECT mechanic.id FROM mechanic WHERE first_name LIKE '%Петров%'
	), 'ACCEPTED', '2018-10-10 10:00:00', '2018-10-10 11:00:00', '1000.00'
);

SHUTDOWN;