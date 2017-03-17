drop table Branch cascade constraints;
drop table Customer cascade constraints;

create table Branch(
	Street varchar(40),
	HouseNumber varchar(20),
	PostalCode varchar(20),
	primary key (Street, HouseNumber, PostalCode)
);

create table Customer(
	CustomerID integer primary key,
	Name varchar(40),
	PaymentMethod varchar(20),
	PhoneNumber varchar(20)
);

Insert into Customer values (178, 'Bob', 'Credit', '55433');