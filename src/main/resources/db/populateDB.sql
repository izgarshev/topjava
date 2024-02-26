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

insert into meals(user_id, date_time, description, calories)
VALUES (100000, '2024-02-17 08:41:28', 'юзер завтрак', 700),
       (100000, '2024-02-17 13:41:28', 'юзер обед', 800),
       (100000, '2024-02-17 19:41:28', 'юзер ужин', 900),
       (100001, '2024-02-17 07:41:28', 'админ завтрак', 600),
       (100001, '2024-02-17 13:41:28', 'админ обед', 1000)
