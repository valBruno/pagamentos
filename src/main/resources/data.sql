
INSERT INTO WALLETS(ID, BALANCE)
VALUES
('1', 500.00),
('2', 900.00)
ON CONFLICT DO NOTHING;


INSERT INTO USERS(ID, NAME, EMAIL, PASSWORD, CPF, WALLET_ID)
VALUES
('1', 'José da Silva', 'jose.silva@email.com', 'hash_qualquer', '12345678910', 1),
('2', 'Maria da Silva', 'maria.silva@email.com', 'hash_qualquer', '23456789101', 2)
ON CONFLICT DO NOTHING;
