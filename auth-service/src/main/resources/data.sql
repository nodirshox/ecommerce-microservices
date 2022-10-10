INSERT INTO users (id, email, firstname, lastname, password) VALUES (1001, 'admin@mail.com', 'Admin', 'Admin', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123
INSERT INTO users (id, email, firstname, lastname, password) VALUES (1002, 'user@mail.com', 'User', 'User', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123

INSERT INTO role (id, role) VALUES (1001, 'ADMIN');
INSERT INTO role (id, role) VALUES (1002, 'USER');

INSERT INTO users_roles (user_id, roles_id) VALUES (1001, 1001);
INSERT INTO users_roles (user_id, roles_id) VALUES (1002, 1002);
