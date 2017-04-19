USE master;
IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'SegInf')
BEGIN
  print('Removing database named SegInf'); 
    ALTER DATABASE SegInf SET SINGLE_USER WITH ROLLBACK IMMEDIATE
    DROP DATABASE SegInf;
END

go
CREATE DATABASE SegInf;
print('Database named SegInf is created'); 

go
use SegInf
begin tran

if OBJECT_ID('dbo.Autorization') is not null
	drop table dbo.Autorization
if OBJECT_ID('dbo.Object') is not null
	drop table dbo.Object
if OBJECT_ID('dbo.UserRole') is not null
	drop table dbo.UserRole
if OBJECT_ID('dbo.RolePermission') is not null
	drop table dbo.RolePermission
if OBJECT_ID('dbo.RoleRole') is not null
	drop table dbo.RoleRole
if OBJECT_ID('dbo.UserName') is not null
	drop table dbo.UserName
if OBJECT_ID('dbo.Role') is not null
	drop table dbo.Role
if OBJECT_ID('dbo.Permission') is not null
	drop table dbo.Permission

go
create table dbo.Permission(
	p varchar(30) primary key
)
create table dbo.Role(
	r varchar(30) primary key
)
create table dbo.UserName(
	u varchar(30) primary key,
	pass varchar(50) not null
)
create table dbo.RoleRole( 
	rsenior  varchar(30),
	rjunior  varchar(30),
	primary key(rsenior, rjunior),
	foreign key(rsenior) references  Role(r),
	foreign key(rjunior) references Role(r)
)
create table dbo.RolePermission(
	r varchar(30),
	p varchar(30),
	primary key(r, p),
	foreign key(r) references Role(r),
	foreign key(p) references Permission(p)
)
create table dbo.UserRole( 
	u varchar(30),
	r  varchar(30),
	primary key(u, r),
	foreign key(u) references UserName(u),
	foreign key(r) references Role(r)
)
create table dbo.Object(
	o varchar(30) primary key
)
create table dbo.Autorization(
	u varchar(30),
	p  varchar(30),
	o varchar(30),
	primary key(u, p, o),
	foreign key(u) references UserName(u),
	foreign key(p) references Permission(p),
	foreign key(o) references Object(o)
)
go
commit
print('Finished creating tables on database SegInf.'); 