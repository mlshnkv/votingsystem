DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM DISHES_IN_MENUS;
DELETE FROM dishes;
DELETE FROM RESTAURANTS;
DELETE FROM DAY_MENUS;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

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

INSERT INTO DISHES (name, price, restaurant_id)
VALUES ('Рыба', 100, 100003),
        ('Картофель', 80, 100003),
        ('Хлеб', 5, 100003),
        ('Икра', 600, 100003),
        ('Кабачек', 70, 100003),
        ('Орех', 35, 100003),
        ('Мойва', 35, 100004),
        ('Скумбрия', 45, 100004),
        ('Борщ', 55, 100004),
        ('Суп', 60, 100004),
        ('Блины', 115, 100004),
        ('Оладьи', 120, 100004);

INSERT INTO DAY_MENUS( RESTAURANT_ID)
VALUES (100003),
       (100004);

INSERT INTO DISHES_IN_MENUS (DISH, DAY_MENU)
VALUES (100005, 100017),
       (100006, 100017),
       (100007, 100017),
       (100008, 100017),
       (100009, 100017),
       (100011, 100018),
       (100012, 100018),
       (100013, 100018),
       (100014, 100018);