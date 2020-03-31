INSERT INTO accounts(account_iban, total_amount) 
   VALUES('ES982138577898300076030', 15000.00);
INSERT INTO accounts(account_iban, total_amount) 
   VALUES('ES782038577898300076022', 4000.00);
INSERT INTO transactions(reference, account, description, amount, fee, date) 
   VALUES('12345A', 'ES982138577898300076030', 'restaurant payment', 193.38, 3.18, '2020-03-23T15:55:42.000Z');
INSERT INTO transactions(reference, account, description, amount, fee, date) 
   VALUES('12345B', 'ES782038577898300076022', 'restaurant payment', 453.38, 0.20, '2020-03-24T15:55:42.000Z');
INSERT INTO transactions(reference, account, description, amount, fee, date) 
   VALUES('12345C', 'ES782038577898300076022', 'restaurant payment', 88.38, 3.18, NOW());
INSERT INTO transactions(reference, account, description, amount, fee, date) 
   VALUES('12345D', 'ES982138577898300076030', 'restaurant payment', 993.38, 1.18,  NOW());
INSERT INTO transactions(reference, account, description, amount, fee, date) 
   VALUES('12345E', 'ES782038577898300076022', 'restaurant payment', 1793.38, 6.18,  '2020-09-24T14:00:00.000Z');
INSERT INTO transactions(reference, account, description, amount, fee, date) 
   VALUES('12345F', 'ES782038577898300076022', 'restaurant payment', 193.38, 0.18,  '2021-01-01T12:00:00.000Z');
