create table `customer` (
	`id` bigint(20) not null auto_increment,
	`name` varchar(255) default null,
	`contact` varchar(255) default null,
	`telephone` varchar(255) default null,
	`email` varchar(255) default null,
	`remark` text,
	primary key (`id`)
) engine=innodb default charset=utf8;

insert into `customer` values (1, 'jack', 'andy', '18745698812', 'java@163.com', 'nothing');
insert into `customer` values (2, 'luck', 'angle', '12945685689', 'luck@126.com', 'never say goodbye');