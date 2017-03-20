drop table Branch cascade constraints;
drop table BranchLocation cascade constraints;
drop table Customer cascade constraints;
drop table RoomType cascade constraints;
drop table Room cascade constraints;
drop table Manager cascade constraints;
drop table Rent cascade constraints;
drop table Reservation cascade constraints;

create table BranchLocation(
	City varchar(20),
	Province varchar(20),
	PostalCode varchar(20),
	primary key (PostalCode)
);

create table Branch(
	Street varchar(40),
	HouseNumber varchar(20),
	PostalCode varchar(20),
	primary key (Street, HouseNumber, PostalCode)
	foreign key (PostalCode) references BranchLocation(PostalCode)
);

create table Manager(
	Street varchar(40),
	HouseNumber varchar(20),
	PostalCode varchar(20),
	ManagerId integer,
	Name varchar(40),
	Salary integer,
	primary key (ManagerId)
	foreign key (Street, HouseNumber, PostalCode) references Branch
);

create table RoomType(
	TypeName varchar(20) primary key,
	Price float,
	InternetAccess bit,
	Kitchen bit,
	SatelliteTV bit
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

create table Reservation(
	ConfirmationID integer primary key,
	StartDate date,
	EndDate date,--or timestamp?
	RoomNumber integer not null,
	Street varchar(40) not null,
	HouseNumber varchar(20) not null,
	PostalCode varchar(20) not null,
	CustomerID integer not null,
	foreign key (RoomNumber, Street, HouseNumber, PostalCode) references Room(RoomNumber, Street, HouseNumber, PostalCode),
	foreign key (CustomerID) references Customer(CustomerID)
);


create table Rent(
	ConfirmationID integer primary key,
	TotalCost integer,
	foreign key (ConfirmationID) references Reservation(ConfirmationID)
);

Insert into Customer values (178, 'Bob', 'Credit', '55433');
Insert into RoomType values ('A', 10);
Insert into Branch values ('1St', '12B', 'V4');
Insert into Room values (1, '1St', '12B', 'V4', 'A');
Insert into Reservation values (12, 1, '1St', '12B', 'V4', 178);

--BranchLocation
Insert into BranchLocation values ('Vancouver', 'BC', 'V6N 2Z5');
Insert into BranchLocation values ('Vancouver', 'BC', 'V7R 9B8');
Insert into BranchLocation values ('Thetford Mines', 'QC', 'G6H 7B5');
Insert into BranchLocation values ('Yellowknife', 'NT', 'Y8J 0F9');
Insert into BranchLocation values ('Wallaceburg', 'ON', 'P9N 8A5');

--Branch
Insert into Branch values ('Ackman Ave', '4150A', 'V6N 2Z5');
Insert into Branch values ('Rupert St', '8777', 'V7R 9B8');
Insert into Branch values ('Tower St', '192', 'G6H 7B5');
Insert into Branch values ('Armann Dr', '222', 'Y8J 0F9');
Insert into Branch values ('Agarr St', '1231C', 'P9N 8A5');

--RoomType
Insert into RoomType values ('Luxury', 224.99,1,1,1);
Insert into RoomType values ('Business', 179.99,1,1,0);
Insert into RoomType values ('Double', 131.49,1,0,0);
Insert into RoomType values ('Single', 99.99,1,0,0);
Insert into RoomType values ('Penthouse', 329.49,1,1,1);

--room
Insert into Branch values (101,'Ackman Ave', '4150A', 'V6N 2Z5','Single');
Insert into Branch values (102,'Ackman Ave', '4150A', 'V6N 2Z5','Double');
Insert into Branch values (201,'Ackman Ave', '4150A', 'V6N 2Z5','Business');
Insert into Branch values (202,'Ackman Ave', '4150A', 'V6N 2Z5','Luxury');
Insert into Branch values (301,'Ackman Ave', '4150A', 'V6N 2Z5','Penthouse');
Insert into Branch values (111,'Rupert St', '8777', 'V7R 9B8','Single');
Insert into Branch values (222,'Rupert St', '8777', 'V7R 9B8','Double');
Insert into Branch values (333,'Rupert St', '8777', 'V7R 9B8','Luxury');
Insert into Branch values (101,'Tower St', '192', 'G6H 7B5','Single');
Insert into Branch values (201,'Tower St', '192', 'G6H 7B5','Luxury');
Insert into Branch values (301,'Tower St', '192', 'G6H 7B5','Penthouse');
Insert into Branch values (101,'Armann Dr', '222', 'Y8J 0F9','Single');
Insert into Branch values (201,'Armann Dr', '222', 'Y8J 0F9','Double');
Insert into Branch values (301,'Armann Dr', '222', 'Y8J 0F9','Single');
Insert into Branch values (101,'Agarr St', '1231C', 'P9N 8A5','Luxury');
Insert into Branch values (201,'Agarr St', '1231C', 'P9N 8A5','Single');
Insert into Branch values (202,'Agarr St', '1231C', 'P9N 8A5','Business');

--Customer












