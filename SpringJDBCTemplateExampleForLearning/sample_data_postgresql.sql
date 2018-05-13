create table people (
id serial not null primary key,
first_name varchar(20) not null,
last_name varchar(20) not null,
age integer not null
);

insert into people (id, first_name, last_name, age) values
(1, 'Vlad', 'Boyarskiy', 21),
(2,'Oksi', ' Bahatskaya', 30),
(3,'Vadim', ' Vadimich', 32);

commit;