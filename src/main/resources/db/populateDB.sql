DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM dishes;
DELETE FROM RESTAURANTS;
DELETE FROM DAY_MENUS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Shmuser', 'shmuser@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO USER_ROLES (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001);

INSERT INTO RESTAURANTS (NAME)
VALUES ('Ракушка'),
       ('Уголек');

INSERT INTO DAY_MENUS( RESTAURANT_ID)
VALUES (100003),
       (100004);

INSERT INTO DISHES (name, price, DAY_MENU_ID)
VALUES ('Рыба', 100, 100005),
       ('Картофель', 80, 100005),
       ('Хлеб', 5, 100005),
       ('Икра', 600, 100005),
       ('Кабачек', 70, 100005),
       ('Орех', 35, 100005),
       ('Мойва', 35, 100006),
       ('Скумбрия', 45, 100006),
       ('Борщ', 55, 100006),
       ('Суп', 60, 100006),
       ('Блины', 115, 100006),
       ('Оладьи', 120, 100006);