DELETE FROM dishes;
DELETE FROM DAY_MENUS;
DELETE FROM RESTAURANTS;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Shmuser', 'shmuser@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO USER_ROLES (role, user_id)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('ADMIN', 100002),
       ('USER', 100002);

INSERT INTO RESTAURANTS (NAME)
VALUES ('Ракушка'),
       ('Уголек');

INSERT INTO DAY_MENUS(date, restaurant_id)
VALUES ('2020-08-17',100003),
       ('2020-08-17', 100004),
       ('2020-08-18',100003),
       ('2020-08-18', 100004);

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
       ('Оладьи', 120, 100006),
       ('Рыба', 100, 100007),
       ('Картофель', 80, 100007),
       ('Хлеб', 5, 100007),
       ('Икра', 600, 100007),
       ('Кабачек', 70, 100007),
       ('Орех', 35, 100007),
       ('Мойва', 35, 100008),
       ('Скумбрия', 45, 100008),
       ('Борщ', 55, 100008),
       ('Суп', 60, 100008),
       ('Блины', 115, 100008),
       ('Оладьи', 120, 100008);

INSERT INTO VOTES  (DATE, TIME, USER_ID, DAY_MENU_ID)
VALUES ('2020-08-17',  '10:00:00', 100000, 100005),
       ('2020-08-17', '10:00:00', 100001, 100005),
       ('2020-08-17' ,'10:00:00', 100002, 100005)
--        ('2020-08-18',  '10:00:00', 100000, 100008),
--        ('2020-08-18', '10:00:00', 100001, 100008),
--        ('2020-08-18' ,'10:00:00', 100002, 100008);