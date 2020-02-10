-- begin RTNEOIMPORT_IM_ADDRESS
create table rtneoimport_im_address (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    LATITUDE decimal(19, 6),
    LONGITUDE decimal(19, 6),
    --
    POSTAL_CODE varchar(6),
    ADDRESS text,
    FIAS_CITY_ID varchar(36),
    FIAS_REGION_ID varchar(36),
    FIAS_ID varchar(36),
    IS_RR_INFO_EXISTS boolean,
    --
    primary key (ID)
)^
-- end RTNEOIMPORT_IM_ADDRESS
-- begin RTNEOIMPORT_IM_API_ORDERS
create table rtneoimport_im_api_orders (
    KN varchar(51),
    UUID uuid,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    VERSION integer not null,
    --
    PDF_ID uuid,
    ERROR_MESSAGE text,
    ORDERNUM varchar(20),
    DOCUMENT varchar(20),
    LAST_CHECK timestamp,
    STATUS varchar(50),
    TRANSACTION_ varchar(20),
    ENCODED_DOCUMENT text,
    PAID boolean,
    ENCODED_OBJECT text,
    FILE_ID uuid,
    ADDRESS_ID uuid,
    --
    primary key (KN)
)^
-- end RTNEOIMPORT_IM_API_ORDERS
-- begin RTNEOIMPORT_IM_CONTRAGENT
create table rtneoimport_im_contragent (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PAY_LIMIT_DAY integer,
    ORGANIZATION_TYPE integer,
    PERSONAL_ACCOUNT varchar(255),
    NAME varchar(255),
    SHORT_NAME varchar(255),
    CREATION_DATE varchar(255),
    LEGAL_ADDRESS text,
    ACTUAL_ADDRESS text,
    MAILING_ADDRESS text,
    OKPO varchar(10),
    OKOPF varchar(255),
    INN varchar(255),
    KPP varchar(9),
    OGRN varchar(15),
    BIK varchar(9),
    CORRESPONDENT_ACCOUNT varchar(20),
    BANK_NAME varchar(255),
    CHECKING_ACCOUNT varchar(20),
    HEAD_PERSON_NAME varchar(255),
    HEAD_PERSON_POST varchar(255),
    HEAD_PERSON_REASON_DOC text,
    HEAD_PERSON_PHONE varchar(255),
    PHONE varchar(255),
    HEAD_PERSON_FAX varchar(255),
    HEAD_PERSON_EMAIL varchar(255),
    HEAD_PERSON_INN varchar(12),
    CONTACT_PERSON_NAME varchar(255),
    CONTACT_PERSON_PHONE varchar(255),
    CONTACT_PERSON_PHONE_VERIFIED boolean,
    CONTACT_PERSON_FAX varchar(255),
    CONTACT_PERSON_EMAIL varchar(255),
    OKVED varchar(255),
    AVG_NUMBER integer,
    AVG_NUMBER_FROM_USER integer,
    ADD_OKVED text,
    AVG_INCOME decimal(19, 2),
    PASSPORT_SERIES varchar(10),
    PASSPORT_NUMBER varchar(10),
    PASSPORT_GIVEN_DATE date,
    REGISTRATION_ADDRESS text,
    HAS_WASTE_GENERATION_PROJECT boolean,
    NOT_USE_REDUCTION_FACTOR boolean,
    NOT_DOING_BUSINESS boolean,
    TECHNICAL_PASSPORT_ALLOWED boolean,
    ZONE_ID uuid,
    CHECK_PROCESSING boolean,
    CHECK_COMMENT varchar(255),
    TYPE_ integer not null,
    --
    primary key (ID)
)^
-- end RTNEOIMPORT_IM_CONTRAGENT
-- begin RTNEOIMPORT_IM_ZONE
create table rtneoimport_im_zone (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(20),
    KEYWORDS text,
    --
    primary key (ID)
)^
-- end RTNEOIMPORT_IM_ZONE
-- begin RTNEOIMPORT_IM_REAL_ESTATE_CATEGORY
create table rtneoimport_im_real_estate_category (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    VIEW_ORDER integer,
    CODE varchar(255),
    NAME varchar(255) not null,
    UNIT_ID uuid not null,
    IS_LIVING boolean,
    BLOCKED boolean,
    USABLE_WITHOUT_CADASTRAL_REAL_ESTATE boolean,
    USABLE_WITHOUT_CADASTRAL_LAND boolean,
    SUPPORTING_DOCUMENTS varchar(255),
    AUTO_APPLY boolean,
    APPLY_EMPTY_INDICATORS boolean,
    USABLE_FOR_EVERYONE boolean,
    APPLY_CALCULATION_AMOUNT boolean,
    PARENT_CATEGORY_ID uuid,
    RATIO decimal(19, 5),
    NORM decimal(19, 4),
    MIN_VALUE decimal(19, 2),
    CATEGORY_RATIO decimal(19, 3),
    CATEGORY_RATIO_SCRIPT text,
    CALCULATION_SCRIPT text,
    CONFIRMATION_SCREEN varchar(255),
    --
    primary key (ID)
)^
-- end RTNEOIMPORT_IM_REAL_ESTATE_CATEGORY
-- begin RTNEOIMPORT_IM_UNIT
create table rtneoimport_im_unit (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    IS_AREA boolean,
    --
    primary key (ID)
)^
-- end RTNEOIMPORT_IM_UNIT
-- begin RTNEOIMPORT_IM_CONTRAGENT_REAL_ESTATE
create table rtneoimport_im_contragent_real_estate (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CONTRAGENT_ID uuid,
    REAL_ESTATE_ID uuid,
    SHARE_ decimal(19, 2),
    COUNT_OF_APARTMENTS integer,
    COUNT_OF_RESIDENTS integer,
    TYPE_ID uuid,
    CALCULATION_AMOUNT decimal(19, 2),
    ENCUMBRANCE boolean,
    LIVING_AREA decimal(19, 2),
    CATEGORY_ID uuid,
    IN_RENT boolean,
    REVOTE boolean,
    DATE_REVOTE_ACCEPTION date,
    LANDLORD_ID uuid,
    AREA_IN_RENT decimal(19, 2),
    PARTICULAR_AREA decimal(19, 2),
    RENT_CONTRACT_ID uuid,
    CHECK_PROCESSING boolean,
    CHECK_COMMENT varchar(255),
    INTRODUCED_WITH_TECH_PASSPORT boolean,
    VALIDITY_FROM date,
    VALIDITY_TO date,
    OWN_TYPE integer,
    CONTROL_METHOD integer,
    --
    primary key (ID)
)^
-- end RTNEOIMPORT_IM_CONTRAGENT_REAL_ESTATE
-- begin RTNEOIMPORT_IM_CONTRAGENT_FILE
create table rtneoimport_im_contragent_file (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CONTRAGENT_ID uuid,
    NAME varchar(255),
    FILE_ID uuid,
    --
    primary key (ID)
)^
-- end RTNEOIMPORT_IM_CONTRAGENT_FILE
-- begin RTNEOIMPORT_IM_REAL_ESTATE
create table rtneoimport_im_real_estate (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    LATITUDE decimal(19, 6),
    LONGITUDE decimal(19, 6),
    --
    NAME varchar(255),
    IS_LIVING boolean,
    ADDRESS text not null,
    CADASTRAL_NUMBER varchar(255) not null,
    AREA decimal(19, 2),
    PARENT_ID uuid,
    ADDRESS_FROM_ROSSREESTR boolean,
    AREA_FROM_ROSSREESTR boolean,
    COUNT_OF_ORGANIZATIONS integer,
    ZONE_ID uuid,
    STANDARD_ADDRESS text,
    ADDRESS__ID uuid,
    CHECK_PROCESSING boolean,
    --
    primary key (ID)
)^
-- end RTNEOIMPORT_IM_REAL_ESTATE
-- begin RTNEOIMPORT_IM_REAL_ESTATE_TYPE
create table rtneoimport_im_real_estate_type (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    IS_APARTMENT_BUILDING boolean,
    IS_LIVING boolean,
    ASSIGNATION_BUILDING varchar(255),
    ASSIGNATION_CODE varchar(255),
    ASSIGNATION_TYPE varchar(255),
    --
    primary key (ID)
)^
-- end RTNEOIMPORT_IM_REAL_ESTATE_TYPE
-- begin RTNEOIMPORT_IM_ROSREESTR_ENTITY
create table rtneoimport_im_rosreestr_entity (
    ID uuid,
    --
    EXTERNAL_ID bigint,
    NAME varchar(255),
    --
    primary key (ID)
)^
-- end RTNEOIMPORT_IM_ROSREESTR_ENTITY
-- begin RTNEOIMPORT_IM_CONTRAGENT_API_CALL
create table rtneoimport_im_contragent_api_call (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    REG_NUMBER varchar(255) not null,
    RESPONSE_BODY text,
    --
    primary key (ID)
)^
-- end RTNEOIMPORT_IM_CONTRAGENT_API_CALL
-- begin RTNEOIMPORT_API_ROSREESTR_CADASTER_SEARCH_RESPONSE
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
    REQUEST_MODE varchar(50),
    IM_ADDRESS_ID uuid,
    ADDRESS_FROM_FIAS text,
    AREA varchar(255),
    RE_TYPE varchar(255),
    CATEGORY varchar(255),
    CADNOMER varchar(255),
    --
    primary key (ID)
)^
-- end RTNEOIMPORT_API_ROSREESTR_CADASTER_SEARCH_RESPONSE
