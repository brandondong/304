create table Branch(
	Street varchar(40),
	HouseNumber varchar(20),
	PostalCode varchar(20),
	primary key (Street, HouseNumber, PostalCode)
);