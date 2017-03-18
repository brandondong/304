drop table Branch cascade constraints;
drop table Customer cascade constraints;
drop table RoomType cascade constraints;
drop table Room cascade constraints;

create table Branch(
	Street varchar(40),
	HouseNumber varchar(20),
	PostalCode varchar(20),
	primary key (Street, HouseNumber, PostalCode)
);

create table RoomType(
	TypeName varchar(20) primary key,
	Price integer
);

create table Room(
	RoomNumber integer,
	Street varchar(40),
	HouseNumber varchar(20),
	PostalCode varchar(20),
	TypeName varchar(20) not null,
	primary key (RoomNumber, Street, HouseNumber, PostalCode),
	foreign key (TypeName) references RoomType(TypeName),
	foreign key (Street, HouseNumber, PostalCode) references Branch(Street, HouseNumber, PostalCode)
);

create table Customer(
	CustomerID integer primary key,
	Name varchar(40),
	PaymentMethod varchar(20),
	PhoneNumber varchar(20)
);

Insert into Customer values (178, 'Bob', 'Credit', '55433');
Insert into RoomType values ('A', 10);
Insert into Branch values ('1St', '12B', 'V4');
Insert into Room values (1, '1St', '12B', 'V4', 'A');