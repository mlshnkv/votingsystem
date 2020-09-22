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
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001);

INSERT INTO restaurants (name)
VALUES ('Ракушка'),
       ('Уголек');

INSERT INTO menus(created, restaurant_id)
VALUES (NOW() - 1 DAY, 100002),
       (NOW() - 1 DAY, 100003),
       (current_date, 100002),
       (current_date, 100003);

INSERT INTO dishes (name, price, menu_id)
VALUES ('Рыба', 100, 100004),
       ('Картофель', 80, 100004),
       ('Хлеб', 5, 100004),
       ('Мойва', 35, 100005),
       ('Скумбрия', 45, 100005),
       ('Борщ', 55, 100005),
       ('Икра', 600, 100006),
       ('Кабачек', 70, 100006),
       ('Орех', 35, 100006),
       ('Суп', 60, 100007),
       ('Блины', 115, 100007),
       ('Оладьи', 120, 100007);

INSERT INTO votes (created, time, user_id, restaurant_id)
VALUES (NOW() - 1 DAY, '10:00:00', 100000, 100002),
       (NOW() - 1 DAY, '10:00:00', 100001, 100002),
       (NOW(), '10:00:00', 100000, 100003),
       (NOW(), '10:00:00', 100001, 100003);


