INSERT INTO auth_service_users (id, email, firstname, lastname, password, payment_method) VALUES (1001, 'admin@mail.com', 'Admin', 'Admin', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2',1); --123
INSERT INTO auth_service_users (id, email, firstname, lastname, password, payment_method) VALUES (1002, 'user@mail.com', 'User', 'User', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2',2); --123

INSERT INTO auth_service_role (id, role) VALUES (1001, 'ADMIN');
INSERT INTO auth_service_role (id, role) VALUES (1002, 'USER');

INSERT INTO auth_service_users_roles (auth_service_users_id, roles_id) VALUES (1001, 1001);
INSERT INTO auth_service_users_roles (auth_service_users_id, roles_id) VALUES (1002, 1002);

INSERT INTO auth_service_address (id, state, street, zipcode, user_id) VALUES (1001,'CA','Los angeles 1000 Benning Drive', '32552',1001);
INSERT INTO auth_service_address (id, state, street, zipcode, user_id) VALUES (1002,'FL','Fairfield 1000 4th street', '53442',1002);
