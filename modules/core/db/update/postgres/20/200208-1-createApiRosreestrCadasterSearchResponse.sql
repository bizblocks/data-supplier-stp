create table RTNEOIMPORT_API_ROSREESTR_CADASTER_SEARCH_RESPONSE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ADDRESS text,
    AREA varchar(255),
    RE_TYPE varchar(255),
    CATEGORY varchar(255),
    CADNOMER varchar(255),
    --
    primary key (ID)
);
