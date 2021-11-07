use ucheckin;

alter table booking
add ccnum varchar(16),
add ccexp varchar(5);

alter table request
add item_ID int;

alter table request
add FOREIGN KEY(item_ID) REFERENCES items(item_ID);