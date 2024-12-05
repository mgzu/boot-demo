create table ACCOUNT
(
	ID                 varchar(255) not null
		primary key,
	CREATED_BY         varchar(255) not null,
	CREATED_DATE       TIMESTAMP    not null,
	LAST_MODIFIED_BY   varchar(255) not null,
	LAST_MODIFIED_DATE TIMESTAMP    not null,
	REMARK             varchar(255),
	TENANT_ID          varchar(255) not null,
	APP_VERSION        varchar(10)  not null,
	DATA_VERSION       varchar(10)  not null,
	VERSION_LOCK       INTEGER      not null default 0,
	ENABLED            bit(1)       not null default 0,
	ENABLED_DATE       TIMESTAMP,
	EMAIL              varchar(255) not null,
	ORDER_BY_PRIORITY  INTEGER      not null default 0,
	PASSWORD           varchar(255) not null,
	USERNAME           varchar(255) not null
);

create table ACCOUNT_ROLE
(
	ID                 varchar(255) not null
		primary key,
	CREATED_BY         varchar(255) not null,
	CREATED_DATE       TIMESTAMP    not null,
	LAST_MODIFIED_BY   varchar(255) not null,
	LAST_MODIFIED_DATE TIMESTAMP    not null,
	REMARK             varchar(255),
	TENANT_ID          varchar(255) not null,
	APP_VERSION        varchar(10)  not null,
	DATA_VERSION       varchar(10)  not null,
	VERSION_LOCK       INTEGER      not null default 0,
	ACCOUNT_ID         varchar(255) not null,
	ROLE_ID            varchar(255) not null
);

create table DICT
(
	ID                 varchar(255) not null
		primary key,
	CREATED_BY         varchar(255) not null,
	CREATED_DATE       TIMESTAMP    not null,
	LAST_MODIFIED_BY   varchar(255) not null,
	LAST_MODIFIED_DATE TIMESTAMP    not null,
	REMARK             varchar(255),
	TENANT_ID          varchar(255) not null,
	APP_VERSION        varchar(10)  not null,
	DATA_VERSION       varchar(10)  not null,
	VERSION_LOCK       INTEGER      not null default 0,
	CODE               varchar(255) not null
		unique,
	NAME               varchar(255) not null,
	ORDER_BY_PRIORITY  INTEGER               default 0 not null
);

create table DICT_ITEM
(
	ID                 varchar(255)      not null
		primary key,
	CREATED_BY         varchar(255)      not null,
	CREATED_DATE       TIMESTAMP         not null,
	LAST_MODIFIED_BY   varchar(255)      not null,
	LAST_MODIFIED_DATE TIMESTAMP         not null,
	REMARK             varchar(255),
	TENANT_ID          varchar(255)      not null,
	APP_VERSION        varchar(10)       not null,
	DATA_VERSION       varchar(10)       not null,
	VERSION_LOCK       INTEGER           not null,
	DICT_ID            varchar(255)      not null,
	ORDER_BY_PRIORITY  INTEGER default 0 not null,
	TYPE               varchar(255)      not null,
	"value"            varchar(255)      not null
);

create table PERMISSION
(
	ID                 varchar(255)      not null
		primary key,
	CREATED_BY         varchar(255)      not null,
	CREATED_DATE       TIMESTAMP         not null,
	LAST_MODIFIED_BY   varchar(255)      not null,
	LAST_MODIFIED_DATE TIMESTAMP         not null,
	REMARK             varchar(255),
	TENANT_ID          varchar(255)      not null,
	APP_VERSION        varchar(10)       not null,
	DATA_VERSION       varchar(10)       not null,
	VERSION_LOCK       INTEGER           not null,
	NAME               varchar(255)      not null,
	ORDER_BY_PRIORITY  INTEGER default 0 not null
);

create table ROLE
(
	ID                 varchar(255) not null
		primary key,
	CREATED_BY         varchar(255) not null,
	CREATED_DATE       TIMESTAMP    not null,
	LAST_MODIFIED_BY   varchar(255) not null,
	LAST_MODIFIED_DATE TIMESTAMP    not null,
	REMARK             varchar(255),
	TENANT_ID          varchar(255) not null,
	APP_VERSION        varchar(10)  not null,
	DATA_VERSION       varchar(10)  not null,
	VERSION_LOCK       INTEGER      not null,
	IS_ADMIN           bit(0)       not null default 0,
	IS_SUPER_ADMIN     bit(0)       not null default 0,
	NAME               varchar(255) not null,
	ORDER_BY_PRIORITY  INTEGER               default 0 not null
);

create table ROLE_PERMISSION
(
	ID                 varchar(255) not null
		primary key,
	CREATED_BY         varchar(255) not null,
	CREATED_DATE       TIMESTAMP    not null,
	LAST_MODIFIED_BY   varchar(255) not null,
	LAST_MODIFIED_DATE TIMESTAMP    not null,
	REMARK             varchar(255),
	TENANT_ID          varchar(255) not null,
	APP_VERSION        varchar(10)  not null,
	DATA_VERSION       varchar(10)  not null,
	VERSION_LOCK       INTEGER      not null,
	PERMISSION_ID      varchar(255) not null,
	ROLE_ID            varchar(255) not null
);

create table T_LOG_RECORD
(
	ID          varchar(255) not null
		primary key,
	ACTION      varchar(511),
	BIZ_NO      varchar(200),
	CREATE_TIME TIMESTAMP,
	EXTRA       varchar(255),
	FAIL        bit(0)       not null default 0,
	OPERATOR    varchar(63),
	SUB_TYPE    varchar(255),
	TENANT      varchar(255),
	TYPE        varchar(200)
);

create table T_ORDER
(
	ID                 varchar(255) not null
		primary key,
	CREATED_BY         varchar(255) not null,
	CREATED_DATE       TIMESTAMP    not null,
	LAST_MODIFIED_BY   varchar(255) not null,
	LAST_MODIFIED_DATE TIMESTAMP    not null,
	REMARK             varchar(255),
	TENANT_ID          varchar(255) not null,
	APP_VERSION        varchar(10)  not null,
	DATA_VERSION       varchar(10)  not null,
	VERSION_LOCK       INTEGER      not null,
	BIZ_CODE           varchar(255) not null,
	ORDER_ID           varchar(255),
	ORDER_STATE        varchar(255) not null,
	SCENE_ID           varchar(255) not null
);

