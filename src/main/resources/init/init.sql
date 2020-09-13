drop table if exists `camera`;
create table `camera` (
                          `id` bigint(20) not null auto_increment,
                          `address` varchar(255) default null,
                          `guid` varchar(36) not null,
                          primary key (`id`)
) ENGINE=InnoDB auto_increment=212406 default CHARSET=utf8;

create or replace procedure insert_cameras(value int)
begin
    declare i int default 0;
    repeat
        insert into camera (address, guid) values (concat('address#', i), uuid());
        set i = i + 1;
    until i > value
        end repeat;
end;

call insert_cameras(100);
drop procedure if exists insert_cameras; 