alter table doctors add status tinyint;
update doctors set status =1;

alter table patients add status tinyint;
update patients set status =1;