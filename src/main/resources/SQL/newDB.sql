-- DROP SCHEMA IF EXISTS second_DB;
CREATE SCHEMA IF NOT EXISTS second_DB DEFAULT CHARACTER SET utf8mb4;
use second_DB;

DROP TABLE IF EXISTS developers_projects;
DROP TABLE IF EXISTS companies_projects;
DROP TABLE IF EXISTS customers_projects;
DROP TABLE IF EXISTS developers_skills;

DROP TABLE IF EXISTS developers;
DROP TABLE IF EXISTS skills;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS customers;

CREATE TABLE developers(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(45) NOT NULL,
	age INT,
	sex VARCHAR(1),
	salary INT,
	other VARCHAR(255),
	PRIMARY KEY (id));
	

CREATE TABLE skills(
	id INT NOT NULL AUTO_INCREMENT,
	skill VARCHAR(45) NOT NULL,
	grade VARCHAR(45) NOT NULL,
	PRIMARY KEY (id));
	

CREATE TABLE projects(
	id INT NOT NULL AUTO_INCREMENT,
	project_name VARCHAR(255) NOT NULL,
	cost INT,
	start_date DATE,
	PRIMARY KEY (id));	
	

CREATE TABLE companies(
	id INT NOT NULL AUTO_INCREMENT,
	company_name VARCHAR(255) NOT NULL,
	PRIMARY KEY (id));


CREATE TABLE customers(
	id INT NOT NULL AUTO_INCREMENT,
	customer_name VARCHAR(255) NOT NULL,
	PRIMARY KEY (id));

CREATE TABLE developers_projects(
	developers_id INT NOT NULL,
	projects_id INT NOT NULL,
	-- PRIMARY KEY (developers_id,projects_id),
	FOREIGN KEY (developers_id) references developers(id) ON DELETE CASCADE,
	FOREIGN KEY (projects_id) references projects(id) ON DELETE CASCADE);

CREATE TABLE companies_projects(
	companies_id INT NOT NULL,
	projects_id INT NOT NULL,
	-- PRIMARY KEY (companies_id,projects_id),
	FOREIGN KEY (companies_id) references companies(id) ON DELETE CASCADE,
	FOREIGN KEY (projects_id) references projects(id) ON DELETE CASCADE);
	

CREATE TABLE customers_projects(
	customers_id INT NOT NULL,
	projects_id INT NOT NULL,
	-- PRIMARY KEY (customers_id,projects_id),
	FOREIGN KEY (customers_id) references customers(id) ON DELETE CASCADE,
	FOREIGN KEY (projects_id) references projects(id) ON DELETE CASCADE);
	

CREATE TABLE developers_skills(
	developers_id INT NOT NULL,
	skills_id INT NOT NULL,
	-- PRIMARY KEY (developers_id,skills_id),
	FOREIGN KEY (developers_id) references developers(id) ON DELETE CASCADE,
	FOREIGN KEY (skills_id) references skills(id) ON DELETE CASCADE);