INSERT INTO RoomType (roomType_ID, king, queen, full, pull_out, suite, rate)
VALUES ('SK', 1, 0, 0, 0, 0, 100),
	   ('DQ', 0, 2, 0, 0, 0, 110),
       ('SKS', 1, 0, 0, 1, 1, 175),
       ('DQS', 0, 2, 0, 1, 1, 200);
       
INSERT INTO Room (room_num, roomType_ID, room_status, room_active, floor)
VALUES (100, 'SK', 1, 1, 1),
	   (101, 'DQ', 1, 1, 1),
       (200, 'DQ', 0, 1, 2),
       (220, 'SKS', 0, 0, 2),
       (310, 'DQS', 1, 1, 3);
       
       
INSERT INTO Employee (emp_Fname, emp_Lname, admin)
VALUES ("Chuck", "Gustner", 1),
	   ("Mahad", "Hussein", 0);
       
INSERT INTO Items(item_Name, item_Price)
VALUES ("Chicken Kiev", 13.99),
		("Caesar Salad", 8.99),
        ("16oz New York Strip Steak", 16.99),
        ("Pork Ribs", 17.99),
        ("Talapia", 6.99),
        ("Shrimp Scampi", 9.99),
        ("Pepperoni Pizza", 12.99),
        ("Chocolate Cake", 6.99),
        ("New York Cheese Cake", 9.99),
        ("French Silk Pie", 8.99);