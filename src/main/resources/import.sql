INSERT INTO transaction_type(id, name) VALUES(1, 'CONTRIBUTION');
INSERT INTO transaction_type(id, name) VALUES(2, 'INTEREST_INCOME');
INSERT INTO transaction_type(id, name) VALUES(3, 'DISTRIBUTION');
INSERT INTO transaction_type(id, name) VALUES(4, 'GENERAL_EXPENSE');
INSERT INTO transaction_type(id, name) VALUES(5, 'MANAGEMENT_FEE');

INSERT INTO client(id, name, created_date_time) VALUES(1, 'Big CLient', NOW());
INSERT INTO fund(id, name, description, ticker, balance, created_date_time, client_id) VALUES(1, 'Bitcoin Fund', 'Only Bitcoin companies please', 'BITGO', '0.00', NOW(), 1);
INSERT INTO investor(id, name, created_date_time, client_id) VALUES(1, 'Mr Whale', NOW(), 1);
