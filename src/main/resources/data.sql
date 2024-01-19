delete from Hardware_Ref;
delete from Computer;
delete from Computer_Order;

delete from Hardware;
insert into Hardware (id, name, type) 
                values ('MATX', 'mATX', 'CASE');
insert into Hardware (id, name, type) 
                values ('IITX', 'ITX', 'CASE');
insert into Hardware (id, name, type) 
                values ('INTL', 'Intel', 'CPU');
insert into Hardware (id, name, type) 
                values ('CAMD', 'AMD', 'CPU');
insert into Hardware (id, name, type) 
                values ('NVDA', 'Nvidia', 'GPU');
insert into Hardware (id, name, type) 
                values ('GAMD', 'AMD', 'GPU');
insert into Hardware (id, name, type) 
                values ('NORM', '1 TB', 'STORAGE');
insert into Hardware (id, name, type) 
                values ('CRZY', '100 TB', 'STORAGE');
insert into Hardware (id, name, type) 
                values ('AAIR', 'Air', 'COOLING');
insert into Hardware (id, name, type) 
                values ('WATR', 'Water', 'COOLING');
