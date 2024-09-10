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
('2024-09-09 20:12:51', 'юзер еда 1', 999, 100000),
('2024-09-09 20:13:06', 'юзер еда 2', 1000, 100000),
('2024-09-09 20:13:08', 'админ еда 1', 1001, 100001);