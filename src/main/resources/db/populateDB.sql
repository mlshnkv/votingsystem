DELETE
FROM dishes;
DELETE
FROM menus;
DELETE
FROM restaurants;
DELETE
FROM user_roles;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Shmuser', 'shmuser@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('ADMIN', 100002),
       ('USER', 100002);

INSERT INTO restaurants (name)
VALUES ('Ракушка'),
       ('Уголек');

INSERT INTO menus(date, restaurant_id)
VALUES ('2020-08-20', 100003),
       ('2020-08-20', 100004),
       ('2020-08-21', 100003),
       ('2020-08-21', 100004);

INSERT INTO dishes (name, price, menu_id)
VALUES ('Рыба', 100, 100005),
       ('Картофель', 80, 100005),
       ('Хлеб', 5, 100005),
       ('Мойва', 35, 100006),
       ('Скумбрия', 45, 100006),
       ('Борщ', 55, 100006),
       ('Икра', 600, 100007),
       ('Кабачек', 70, 100007),
       ('Орех', 35, 100007),
       ('Суп', 60, 100008),
       ('Блины', 115, 100008),
       ('Оладьи', 120, 100008);

INSERT INTO votes (date, time, user_id, menu_id)
VALUES ('2020-08-20', '10:00:00', 100000, 100005),
       ('2020-08-20', '10:00:00', 100001, 100005),
       ('2020-08-20', '10:00:00', 100002, 100005),
       ('2020-08-21', '10:00:00', 100000, 100008),
       ('2020-08-21', '10:00:00', 100001, 100008),
       ('2020-08-21', '10:00:00', 100002, 100008);