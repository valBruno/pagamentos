INSERT INTO USERS(ID, NAME, EMAIL, PASSWORD, CPF)
VALUES
('1', 'José da Silva', 'jose.silva@email.com', 'hash_qualquer', '12345678910')
ON CONFLICT DO NOTHING;