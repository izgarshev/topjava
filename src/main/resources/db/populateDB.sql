delete from meals;
DELETE FROM user_role;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);
 insert into meals(datetime, description, calories, user_id) VALUES
('2024-09-09 20:12:51.644397', 'desc1', 999, 100000),
('2024-09-09 20:13:06.495155', 'desc2', 1000, 100000),
('2024-09-09 20:13:08.495155', 'desc3', 1001, 100001);