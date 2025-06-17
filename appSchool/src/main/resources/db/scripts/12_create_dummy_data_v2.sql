-- 8 Students
INSERT INTO students (first_name, last_name, pkk_number, status, phone_number, email, password, hours_left, hours_driven, last_login, exams_failed, lessons_attended, lessons_omitted, is_final_exam_passed) VALUES
('Andrzej', 'Kowalski', '124871248907', 'ACTIVE', '+48 666 666 666', 'andkow@gmail.com', 'andrew', 30, 0, '2024-03-03 12:35', 0, 0, 0, FALSE),
('Marcin', 'Łopian', '4821941986412', 'ACTIVE', '+48 123 321 123', 'marlop@gmail.com', 'marcinek', 15, 15, '2024-03-20 20:35', 0, 7, 0, FALSE),
('Julia', 'Nowak', '329847298472', 'ACTIVE', '+48 501 202 303', 'julnow@example.com', 'julka123', 20, 10, '2024-04-01 08:20', 1, 5, 1, FALSE),
('Tomasz', 'Wiśniewski', '582301928349', 'INACTIVE', '+48 505 101 202', 'tomwis@example.com', 'tommyW', 0, 30, '2024-01-20 14:15', 3, 15, 3, TRUE),
('Karolina', 'Krawczyk', '781239812398', 'ACTIVE', '+48 511 223 344', 'karkra@example.com', 'karo1234', 10, 20, '2024-03-10 11:00', 2, 10, 0, FALSE),
('Michał', 'Lewandowski', '129387123971', 'ACTIVE', '+48 600 333 222', 'miclew@example.com', 'michalL', 5, 25, '2024-03-28 09:45', 1, 12, 2, TRUE),
('Agnieszka', 'Mazur', '9876543210123', 'ACTIVE', '+48 522 789 456', 'agnmaz@example.com', 'agna123', 25, 5, '2024-04-05 17:30', 0, 3, 0, FALSE),
('Paweł', 'Zieliński', '1029384756102', 'ACTIVE', '+48 533 998 776', 'pawziel@example.com', 'pawelZ', 40, 0, '2024-04-07 10:50', 0, 0, 0, FALSE);

-- 3 Payments for various states
INSERT INTO payments (payment_uid, student_id, amount, hours, due_date, status) VALUES
('123456789', 1, 1500, 30, '2024-03-22 15:00', 'AWAITING_PAYMENT'),
('24198657698', 2, 1500, 30, '2024-02-15 15:00', 'FINALIZED'),
('55566677788', 3, 1000, 20, '2024-04-05 10:00', 'FINALIZED'),
('999123456', 4, 500, 10, '2024-03-30 10:00', 'AWAITING_PAYMENT');

-- 3 Trainers
INSERT INTO trainers (first_name, last_name, identifier, phone_number, email, password, status, students_pass_rate) VALUES
('Bogdan', 'Boner', 'hgbrdoihgrsoihn3298sdvkj', '+48 777 777 777', 'bogbon@gmail.com', 'marcinekteraz', 'ACTIVE', 983),
('Anna', 'Szczepańska', 'asdkjasd231231sdasd', '+48 888 555 444', 'annaszcz@example.com', 'annaSzPass123', 'ACTIVE', 874),
('Łukasz', 'Kowalczyk', 'lkowalc234hjfgk345', '+48 600 444 333', 'lkowalczyk@example.com', 'lukaszK', 'INACTIVE', 920);

-- 4 Cars, assigned to trainers
INSERT INTO cars (plates, brand, model, prod_year, mileage, last_maintenance_mileage, trainer_id) VALUES
('XD12345', 'Fiat', 'Punto', '2024', 100000, 95000, 1),
('KR789XY', 'Toyota', 'Yaris', '2022', 80000, 78000, 2),
('KR123AB', 'Volkswagen', 'Golf', '2021', 120000, 115000, 3),
('KR456CD', 'Opel', 'Corsa', '2023', 50000, 45000, 2);

-- Trainer schedules
INSERT INTO schedules_trainers (trainer_id, date, starting_hour, ending_hour) VALUES
(1, '2024-03-25', '', ''),
(2, '2024-04-02', '09:00', '14:00'),
(3, '2024-04-10', '08:00', '12:00');

-- Student schedules
INSERT INTO schedules_students (student_id, date, starting_hour, ending_hour) VALUES
(1, '2024-03-25', '', ''),
(2, '2024-03-15', '12:00', '13:00'),
(3, '2024-04-02', '10:00', '11:00'),
(4, '2024-04-02', '11:15', '12:15'),
(5, '2024-04-07', '14:00', '15:00'),
(6, '2024-04-07', '15:15', '16:15'),
(7, '2024-04-08', '10:00', '11:00'),
(8, '2024-04-09', '11:00', '12:00');


INSERT INTO lessons (trainer_id, student_id, date, starting_hour, ending_hour, status) VALUES
(1, 1, '2024-03-25', '12:00', '13:00', 'SCHEDULED'),
(1, 2, '2024-03-15', '12:00', '13:00', 'COMPLETED'),
(2, 3, '2024-04-02', '10:00', '11:00', 'COMPLETED'),
(2, 4, '2024-04-02', '11:15', '12:15', 'SCHEDULED'),
(3, 5, '2024-04-07', '14:00', '15:00', 'SCHEDULED'), -- fixed from CANCELLED
(3, 6, '2024-04-07', '15:15', '16:15', 'SCHEDULED'),
(2, 7, '2024-04-08', '10:00', '11:00', 'SCHEDULED'), -- fixed from COMPLETED
(1, 8, '2024-04-09', '11:00', '12:00', 'SCHEDULED'); -- fixed from COMPLETED


-- Exams with pass/fail to test edge cases
INSERT INTO exams (exam_type, student_id, is_passed, score, description) VALUES
('THEORETICAL', 2, FALSE, 0, 'No questions answered'),
('PRACTICAL', 3, TRUE, 26, 'Good performance'),
('THEORETICAL', 5, TRUE, 28, 'Confident answers'),
('PRACTICAL', 6, FALSE, 12, 'Needs better clutch control'),
('THEORETICAL', 4, TRUE, 30, 'Excellent');

-- Trainer opinions covering varied sentiment and formats
INSERT INTO trainer_opinions (student_id, trainer_id, opinion) VALUES
(1, 1, 'I like this trainer'),
(2, 1, 'He no gut teacher. Me no likey. Me still fail. I want to go to my mommy.'),
(3, 2, 'Anna is super calm and motivating'),
(4, 2, 'Anna yells a bit, but it works – I drive better now.'),
(5, 3, 'Łukasz is strict but fair. Learned a lot.'),
(6, 3, 'Could improve communication skills.');

