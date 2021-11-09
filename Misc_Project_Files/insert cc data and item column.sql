use ucheckin;

alter table booking
add cctoken int;

alter table request
add item_ID int;

alter table request
add FOREIGN KEY(item_ID) REFERENCES items(item_ID);