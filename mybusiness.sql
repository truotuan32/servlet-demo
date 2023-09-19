use mybusiness;

create table user(
	id bigint auto_increment,
    username varchar(255) not null,
    password varchar(255) not null,
    fullname nvarchar(255),
    address nvarchar(255),
    phone nvarchar(255) not null,
    createddate timestamp,
    primary key(id)
);

create table product(
	id bigint auto_increment,
    name nvarchar(255) not null,
    thumbnail varchar(255),
    shortdescription nvarchar(255) not null,
    content text not null,
    price double not null,
    createddate timestamp,
    primary key(id)
);

create table bill(
	id bigint auto_increment,
    user_id bigint,
    deliverydate timestamp not null,
    deliveryaddress nvarchar(255) not null,
    createddate timestamp,
    foreign key (user_id) references user(id),
    primary key(id)
);

create table billdetail(
	id bigint auto_increment,
    bill_id bigint,
    product_id bigint,
    quantity bigint not null,
    createddate timestamp,
    foreign key (bill_id) references bill(id),
    foreign key (product_id) references product(id),
    primary key(id)
);

create table comment(
	id bigint auto_increment,
    content text not null,
    user_id bigint,
    product_id bigint,
    createddate timestamp,
    foreign key (user_id) references user(id),
    primary key(id)
);

drop table billdetail;
drop table bill;
drop table comment;
drop table product;
drop table user;

insert into product(name, thumbnail, shortdescription, content, price, createddate)
value("tphone", "https://", "siêu smartphone", "abcxyz", 9000000, current_date());

select * from product;