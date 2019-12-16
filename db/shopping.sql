create database shopping;

use shopping;

# User(uid, username, password, email, nickname)
# Product(pid, name, description, price, stock, type)
# ShoppingCart(sid, uid, pid, quantity);
# WishList(wid, uid, pid, quantity);

create table User(
    uid int not null auto_increment,
    username varchar(30) not null,
    password varchar(30) not null,
    email varchar(50) not null,
    nickname varchar(30) not null,
    primary key(uid),
    unique(username),
    unique(email)
);

create table Product(
    pid int not null auto_increment,
    name varchar(50) not null,
    description varchar(1000) not null,
    price int not null,
    stock int not null,
    type varchar(20) not null,
    primary key(pid),
    unique(name)
);

create table ShoppingCart(
    sid int not null auto_increment,
    uid int,
    pid int not null,
    quantity int not null,
    primary key(sid),
    foreign key(uid) references User(uid),
    foreign key(pid) references Product(pid)
);

create table WishList(
     wid int not null auto_increment,
     uid int,
     pid int not null,
     quantity int not null,
     primary key(wid),
     foreign key(uid) references User(uid),
     foreign key(pid) references Product(pid)
);




