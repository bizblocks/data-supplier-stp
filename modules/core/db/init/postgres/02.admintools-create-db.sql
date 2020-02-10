-- begin CUBAAT_SSH_CREDENTIALS
alter table CUBAAT_SSH_CREDENTIALS add constraint FK_CUBAAT_SSH_CREDENTIALS_ON_PRIVATE_KEY foreign key (PRIVATE_KEY_ID) references SYS_FILE(ID)^
create index IDX_CUBAAT_SSH_CREDENTIALS_ON_PRIVATE_KEY on CUBAAT_SSH_CREDENTIALS (PRIVATE_KEY_ID)^

-- end CUBAAT_SSH_CREDENTIALS
