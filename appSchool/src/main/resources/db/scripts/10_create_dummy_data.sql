INSERT INTO students(first_name,
                     last_name,
                     pkk_number,
                     status,
                     phone_number,
                     email,
                     password,
                     hours_left,
                     hours_driven,
                     last_login,
                     exams_failed,
                     lessons_attended,
                     lessons_omitted,
                     is_final_exam_passed)
VALUES ('Andrzej',
        'Kowalski',
        '124871248907',
        'ACTIVE',
        '+48 666 666 666',
        'andkow@gmail.com',
        'andrew',
        30,
        0,
        '2024-03-03 12:35',
        '0',
        0,
        0,
        FALSE);

INSERT INTO students(first_name,
                     last_name,
                     pkk_number,
                     status,
                     phone_number,
                     email,
                     password,
                     hours_left,
                     hours_driven,
                     last_login,
                     exams_failed,
                     lessons_attended,
                     lessons_omitted,
                     is_final_exam_passed)
VALUES ('Marcin',
        'Łopian',
        '4821941986412',
        'ACTIVE',
        '+48 123 321 123',
        'marlop@gmail.com',
        'marcinek',
        15,
        15,
        '2024-03-20 20:35',
        '0',
        7,
        0,
        FALSE);

INSERT INTO payments(payment_uid,
                     student_id,
                     amount,
                     hours,
                     due_date,
                     status)
VALUES ('123456789',
        '1',
        1500,
        30,
        '2024-03-22 15:00',
        'AWAITING_PAYMENT');

INSERT INTO payments(payment_uid,
                     student_id,
                     amount,
                     hours,
                     due_date,
                     status)
VALUES ('24198657698',
        2,
        1500,
        30,
        '2024-02-15 15:00',
        'FINALIZED');

INSERT INTO schedules_students(student_id,
                               date,
                               starting_hour,
                               ending_hour)
VALUES (1,
        '2024-03-25',
        '',
        '');

INSERT INTO schedules_students(student_id,
                               date,
                               starting_hour,
                               ending_hour)
VALUES (2,
        '2024-03-15',
        '12:00',
        '13:00');

INSERT INTO trainers(first_name,
                     last_name,
                     identifier,
                     phone_number,
                     email,
                     password,
                     status,
                     students_pass_rate)
VALUES ('Bogdan',
        'Boner',
        'hgbrdoihgrsoihn3298sdvkj',
        '+48 777 777 777',
        'bogbon@gmail.com',
        'marcinekteraz',
        'ACTIVE',
        '983');

INSERT INTO cars(plates,
                 brand,
                 model,
                 prod_year,
                 mileage,
                 last_maintenance_mileage,
                 trainer_id)
values ('XD12345',
        'Fiat',
        'Punto',
        '2024',
        100000,
        95000,
        1);

INSERT INTO schedules_trainers(trainer_id,
                               date,
                               starting_hour,
                               ending_hour)
VALUES (1,
        '2024-03-25',
        '',
        '');

INSERT INTO lessons(trainer_id,
                    student_id,
                    date,
                    starting_hour,
                    ending_hour,
                    status)
VALUES (1,
        1,
        '2024-03-25',
        '12:00',
        '13:00',
        'SCHEDULED');

INSERT INTO lessons(trainer_id,
                    student_id,
                    date,
                    starting_hour,
                    ending_hour,
                    status)
VALUES (1,
        2,
        '2024-03-15',
        '12:00',
        '13:00',
        'ENDED');

INSERT INTO exams(exam_type,
                  student_id,
                  is_passed,
                  score,
                  description)
VALUES ('THEORETICAL',
        2,
        FALSE,
        0,
        'Ziomek jest nowy to nawet jednego punkta nie wystrzelał');

INSERT INTO trainer_opinions(student_id,
                              trainer_id,
                              opinion)
VALUES (1,
        1,
        'I like this trainer');

INSERT INTO trainer_opinions(student_id,
                              trainer_id,
                              opinion)
VALUES (2,
        1,
        'He no gut teacher. Me no likey. Me still fail. I want to go to my mommy.');