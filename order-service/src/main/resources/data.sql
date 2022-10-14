INSERT INTO order_service_address(id, state, street, zipcode) VALUES (1001, 'NY', 'Burlington 4th street', '52557');
INSERT INTO order_service_order(id, payment_method, user_id, address_id) VALUES (1001, 0, 1002, 1001);
INSERT INTO order_service_product(id, product_id, quantity, order_id) VALUES (1001, 1001, 2, 1001);
