-- -----------------------------------------------------
-- Data for table developers
-- -----------------------------------------------------
START TRANSACTION;
USE second_DB;
INSERT INTO developers (id, name, age, sex, other, salary) VALUES (1, 'Developer 1', 25, 'm', 'Other information about developer 1', '1000');
INSERT INTO developers (id, name, age, sex, other, salary) VALUES (2, 'Developer 2', 32, 'f', 'Other information about developer 2', '1500');
INSERT INTO developers (id, name, age, sex, other, salary) VALUES (3, 'Developer 3', 22, 'f', 'Other information about developer 3', '2000');
INSERT INTO developers (id, name, age, sex, other, salary) VALUES (4, 'Developer 4', 42, 'm', 'Other information about developer 4', '4000');

COMMIT;


-- -----------------------------------------------------
-- Data for table skills
-- -----------------------------------------------------
START TRANSACTION;
USE second_DB;
INSERT INTO skills (id, skill, grade) VALUES (1, 'Java', 'Junior');
INSERT INTO skills (id, skill, grade) VALUES (2, 'Java', 'Middle');
INSERT INTO skills (id, skill, grade) VALUES (3, 'Java', 'Senior');
INSERT INTO skills (id, skill, grade) VALUES (4, 'C++', 'Junior');
INSERT INTO skills (id, skill, grade) VALUES (5, 'C++', 'Middle');
INSERT INTO skills (id, skill, grade) VALUES (6, 'C++', 'Senior');
INSERT INTO skills (id, skill, grade) VALUES (7, 'C#', 'Junior');
INSERT INTO skills (id, skill, grade) VALUES (8, 'C#', 'Middle');
INSERT INTO skills (id, skill, grade) VALUES (9, 'C#', 'Senior');
INSERT INTO skills (id, skill, grade) VALUES (10, 'JS', 'Junior');
INSERT INTO skills (id, skill, grade) VALUES (11, 'JS', 'Middle');
INSERT INTO skills (id, skill, grade) VALUES (12, 'JS', 'Senior');

COMMIT;


-- -----------------------------------------------------
-- Data for table projects
-- -----------------------------------------------------
START TRANSACTION;
USE second_DB;
INSERT INTO projects (project_name, start_date, cost) VALUES ('Project 1', '2021-04-18', '10000');
INSERT INTO projects (project_name, start_date, cost) VALUES ('Project 2', '2021-06-10', '5000');
INSERT INTO projects (project_name, start_date, cost) VALUES ('Project 3', '2020-12-15', '2000');
INSERT INTO projects (project_name, start_date, cost) VALUES ('Project 4', '2021-7-18', '4000');
INSERT INTO projects (project_name, start_date, cost) VALUES ('Project 5', '2021-8-12', '40000');
INSERT INTO projects (project_name, start_date, cost) VALUES ('Project 6', '2021-8-15', '5000');

COMMIT;


-- -----------------------------------------------------
-- Data for table companies
-- -----------------------------------------------------
START TRANSACTION;
USE second_DB;
INSERT INTO companies (id, company_name) VALUES (1, 'Company 1');
INSERT INTO companies (id, company_name) VALUES (2, 'Company 2');
INSERT INTO companies (id, company_name) VALUES (3, 'Company 3');

COMMIT;


-- -----------------------------------------------------
-- Data for table customers
-- -----------------------------------------------------
START TRANSACTION;
USE second_DB;
INSERT INTO customers (id, customer_name) VALUES (1, 'Customer 1');
INSERT INTO customers (id, customer_name) VALUES (2, 'Customer 2');
INSERT INTO customers (id, customer_name) VALUES (3, 'Customer 3');

COMMIT;


-- -----------------------------------------------------
-- Data for table developers_projects
-- -----------------------------------------------------
START TRANSACTION;
USE second_DB;
INSERT INTO developers_projects (developers_id, projects_id) VALUES (1, 1);
INSERT INTO developers_projects (developers_id, projects_id) VALUES (1, 3);
INSERT INTO developers_projects (developers_id, projects_id) VALUES (2, 2);
INSERT INTO developers_projects (developers_id, projects_id) VALUES (3, 3);
INSERT INTO developers_projects (developers_id, projects_id) VALUES (3, 5);
INSERT INTO developers_projects (developers_id, projects_id) VALUES (4, 1);
INSERT INTO developers_projects (developers_id, projects_id) VALUES (4, 2);
INSERT INTO developers_projects (developers_id, projects_id) VALUES (4, 3);
INSERT INTO developers_projects (developers_id, projects_id) VALUES (4, 4);
INSERT INTO developers_projects (developers_id, projects_id) VALUES (4, 5);
INSERT INTO developers_projects (developers_id, projects_id) VALUES (4, 6);
INSERT INTO developers_projects (developers_id, projects_id) VALUES (2, 1);
INSERT INTO developers_projects (developers_id, projects_id) VALUES (3, 6);
INSERT INTO developers_projects (developers_id, projects_id) VALUES (3, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table companies_projects
-- -----------------------------------------------------
START TRANSACTION;
USE second_DB;
INSERT INTO companies_projects (companies_id, projects_id) VALUES (1, 6);
INSERT INTO companies_projects (companies_id, projects_id) VALUES (1, 5);
INSERT INTO companies_projects (companies_id, projects_id) VALUES (1, 2);
INSERT INTO companies_projects (companies_id, projects_id) VALUES (2, 1);
INSERT INTO companies_projects (companies_id, projects_id) VALUES (3, 3);
INSERT INTO companies_projects (companies_id, projects_id) VALUES (3, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table customers_projects
-- -----------------------------------------------------
START TRANSACTION;
USE second_DB;
INSERT INTO customers_projects (customers_id, projects_id) VALUES (1, 5);
INSERT INTO customers_projects (customers_id, projects_id) VALUES (2, 1);
INSERT INTO customers_projects (customers_id, projects_id) VALUES (2, 3);
INSERT INTO customers_projects (customers_id, projects_id) VALUES (2, 6);
INSERT INTO customers_projects (customers_id, projects_id) VALUES (3, 2);
INSERT INTO customers_projects (customers_id, projects_id) VALUES (3, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table developers_skills
-- -----------------------------------------------------
START TRANSACTION;
USE second_DB;
INSERT INTO developers_skills (developers_id, skills_id) VALUES (1, 2);
INSERT INTO developers_skills (developers_id, skills_id) VALUES (1, 4);
INSERT INTO developers_skills (developers_id, skills_id) VALUES (1, 9);
INSERT INTO developers_skills (developers_id, skills_id) VALUES (2, 2);
INSERT INTO developers_skills (developers_id, skills_id) VALUES (2, 6);
INSERT INTO developers_skills (developers_id, skills_id) VALUES (2, 8);
INSERT INTO developers_skills (developers_id, skills_id) VALUES (2, 11);
INSERT INTO developers_skills (developers_id, skills_id) VALUES (3, 1);
INSERT INTO developers_skills (developers_id, skills_id) VALUES (3, 5);
INSERT INTO developers_skills (developers_id, skills_id) VALUES (3, 10);
INSERT INTO developers_skills (developers_id, skills_id) VALUES (4, 3);
INSERT INTO developers_skills (developers_id, skills_id) VALUES (4, 6);
INSERT INTO developers_skills (developers_id, skills_id) VALUES (4, 9);
INSERT INTO developers_skills (developers_id, skills_id) VALUES (4, 12);

COMMIT;

START TRANSACTION;
USE second_DB;
ALTER TABLE companies
CHANGE COLUMN `company_name` `name` VARCHAR(255) NOT NULL ;
ADD COLUMN `city` VARCHAR(45) NULL AFTER `company_name`;

UPDATE `second_db`.`companies` SET `city` = 'City1' WHERE (`id` = '1');
UPDATE `second_db`.`companies` SET `city` = 'City2' WHERE (`id` = '2');
UPDATE `second_db`.`companies` SET `city` = 'City3' WHERE (`id` = '3');

ALTER TABLE `second_db`.`customers` 
CHANGE COLUMN `customer_name` `name` VARCHAR(255) NOT NULL ;

ALTER TABLE `second_db`.`projects` 
CHANGE COLUMN `project_name` `name` VARCHAR(255) NOT NULL ;

ALTER TABLE `second_db`.`skills` 
CHANGE COLUMN `skill` `name` VARCHAR(45) NOT NULL ;

COMMIT;

