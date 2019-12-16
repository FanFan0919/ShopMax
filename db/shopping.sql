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
    description varchar(1000),
    price double not null,
    stock int not null,
    type int not null,
    imgUrl varchar(100) not null,
    primary key(pid),
    unique(name)
);

create table ShoppingCart(
    sid int not null auto_increment,
    uid int,
    pid int not null,
    quantity int not null,
    primary key(sid)
);

create table WishList(
     wid int not null auto_increment,
     uid int,
     pid int not null,
     quantity int not null,
     primary key(wid)
);

# User(uid, username, password, email, nickname)
insert into User values (null, 'root', '123456', 'test@123.com', 'Nick');
insert into User values (null, 'testName1', 'testPassword1', 'testEmail1', 'nickName1');
insert into User values (null, 'testName2', 'testPassword2', 'testEmail2', 'nickName2');
insert into User values (null, 'testName3', 'testPassword3', 'testEmail3', 'nickName3');
insert into User values (null, 'testName4', 'testPassword4', 'testEmail4', 'nickName4');
insert into User values (null, 'testName5', 'testPassword5', 'testEmail5', 'nickName5');

# Product(pid, name, description, price, stock, type, imgUrl)
insert into Product values (null, 'Razer Sunglass', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur, vitae, explicabo? Incidunt facere, natus soluta dolores iusto! Molestiae expedita veritatis nesciunt doloremque sint asperiores fuga voluptas, distinctio, aperiam, ratione dolore.','123', '22', '1', 'model_3.png');
insert into Product values (null, 'Smooth Cloth', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur, vitae, explicabo? Incidunt facere, natus soluta dolores iusto! Molestiae expedita veritatis nesciunt doloremque sint asperiores fuga voluptas, distinctio, aperiam, ratione dolore.','28', '120', '1', 'model_1.png');
insert into Product values (null, 'Black Suit', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur, vitae, explicabo? Incidunt facere, natus soluta dolores iusto! Molestiae expedita veritatis nesciunt doloremque sint asperiores fuga voluptas, distinctio, aperiam, ratione dolore.','76', '98', '1', 'model_2.png');
insert into Product values (null, 'Denim Jacket', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur, vitae, explicabo? Incidunt facere, natus soluta dolores iusto! Molestiae expedita veritatis nesciunt doloremque sint asperiores fuga voluptas, distinctio, aperiam, ratione dolore.','36', '56', '1', 'model_5.png');
insert into Product values (null, 'White T-shirt', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur, vitae, explicabo? Incidunt facere, natus soluta dolores iusto! Molestiae expedita veritatis nesciunt doloremque sint asperiores fuga voluptas, distinctio, aperiam, ratione dolore.','36', '33', '1', 'model_4.png');
insert into Product values (null, 'Yellow Jacket', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur, vitae, explicabo? Incidunt facere, natus soluta dolores iusto! Molestiae expedita veritatis nesciunt doloremque sint asperiores fuga voluptas, distinctio, aperiam, ratione dolore.','78', '37', '1', 'model_7.png');

# ShoppingCart(sid, uid, pid, quantity);
# Please use root-123456 to login => uid = 1
insert into ShoppingCart values (null, 1, 1, 3);
insert into ShoppingCart values (null, 1, 2, 5);
insert into ShoppingCart values (null, 1, 3, 1);
insert into ShoppingCart values (null, 1, 5, 1);

# WishList(wid, uid, pid, quantity);
# Please use root-123456 to login => uid = 1
insert into WishList values (null, 1, 1, 3);
insert into WishList values (null, 1, 3, 5);
insert into WishList values (null, 1, 4, 3);
insert into WishList values (null, 1, 6, 2);



