alter table rtneoimport_im_api_orders add column CREATE_TS timestamp ;
alter table rtneoimport_im_api_orders add column CREATED_BY varchar(50) ;
alter table rtneoimport_im_api_orders add column UPDATE_TS timestamp ;
alter table rtneoimport_im_api_orders add column UPDATED_BY varchar(50) ;
alter table rtneoimport_im_api_orders add column DELETE_TS timestamp ;
alter table rtneoimport_im_api_orders add column DELETED_BY varchar(50) ;
alter table rtneoimport_im_api_orders add column VERSION integer ^
update rtneoimport_im_api_orders set VERSION = 0 where VERSION is null ;
alter table rtneoimport_im_api_orders alter column VERSION set not null ;
