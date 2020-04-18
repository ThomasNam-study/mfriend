insert into person(`id`, `name`, `age`, `blood_type`, `year`, `month`, `day`) values(1, 'marthin', 10, 'A', 1991, 8, 16);
insert into person(`id`, `name`, `age`, `blood_type`, `year`, `month`, `day`) values(2, 'david', 9, 'B', 1994, 4, 1);
insert into person(`id`, `name`, `age`, `blood_type`, `year`, `month`, `day`) values(3, 'dennis', 8, 'O', 1998, 7, 21);
insert into person(`id`, `name`, `age`, `blood_type`, `year`, `month`, `day`) values(4, 'sophia', 7, 'AB', 2000, 8, 10);
insert into person(`id`, `name`, `age`, `blood_type`, `year`, `month`, `day`) values(5, 'benny', 6, 'A', 1982, 2, 16);

insert into block(`id`, `name`) values (1, 'dennis');
insert into block(`id`, `name`) values (2, 'sophia');

update person set block_id = 1 where id = 3;
update person set block_id = 2 where id = 4;
