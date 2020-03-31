insert into clients values (1, '00000000A', 'Alex', 'A', 'aemail@email.com', NOW());
insert into clients values (2, '11111111A', 'Javier', 'J', 'jemail@email.com', NOW());
insert into sizes values (1,0.10,18,10);
insert into sizes values (2,0.14,23,13);
insert into products(ID,NAME,BRAND,MODEL,PRICE,IVA,WEIGHT,TS,SIZE_ID) values (1, 'S10', 'Samsung', 'smartphone', 700, 21, 0.18, NOW(), 1);
insert into products(ID,NAME,BRAND,MODEL,PRICE,IVA,WEIGHT,TS,SIZE_ID) values (2, 'S9', 'Samsung', 'smartphone', 550, 21, 0.20, NOW(), 2);
insert into products(ID,NAME,BRAND,MODEL,PRICE,IVA,WEIGHT,TS,SIZE_ID) values (3, 'One plus 7T', 'Oneplus', 'smartphone', 600, 21, 0.15, NOW(), 1);
insert into products(ID,NAME,BRAND,MODEL,PRICE,IVA,WEIGHT,TS,SIZE_ID) values (4, 'Iphone XI', 'Apple', 'smartphone', 900, 21, 0.17, NOW(), 1);