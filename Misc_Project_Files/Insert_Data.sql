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
       
       
INSERT INTO Employee (emp_ID, emp_Fname, emp_Lname, admin)
VALUES (1, Chuck, Gustner, 1),
	   (2, Mahad, Hussein, 0);