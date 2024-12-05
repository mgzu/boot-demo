create table ACCOUNT
(
	ID                 CHARACTER VARYING(255)  not null
		primary key,
	CREATED_BY         CHARACTER VARYING(255)  not null,
	CREATED_DATE       TIMESTAMP               not null,
	LAST_MODIFIED_BY   CHARACTER VARYING(255)  not null,
	LAST_MODIFIED_DATE TIMESTAMP               not null,
	REMARK             CHARACTER VARYING(255),
	TENANT_ID          CHARACTER VARYING(255)  not null,
	APP_VERSION        CHARACTER VARYING(10)   not null,
	DATA_VERSION       CHARACTER VARYING(10)   not null,
	VERSION_LOCK       INTEGER   default 0     not null,
	ENABLED            BOOLEAN   default false not null,
	ENABLED_DATE       TIMESTAMP,
	EMAIL              CHARACTER VARYING(255)  not null,
	ORDER_BY_PRIORITY  INTEGER   default 0     not null,
	PASSWORD           CHARACTER VARYING(255)  not null,
	USERNAME           CHARACTER VARYING(255)  not null,
	DELETED            TIMESTAMP default null
);

create table ACCOUNT_ROLE
(
	ID                 CHARACTER VARYING(255) not null
		primary key,
	CREATED_BY         CHARACTER VARYING(255) not null,
	CREATED_DATE       TIMESTAMP              not null,
	LAST_MODIFIED_BY   CHARACTER VARYING(255) not null,
	LAST_MODIFIED_DATE TIMESTAMP              not null,
	APP_VERSION        CHARACTER VARYING(10)  not null,
	DATA_VERSION       CHARACTER VARYING(10)  not null,
	REMARK             CHARACTER VARYING(255),
	TENANT_ID          CHARACTER VARYING(255) not null,
	VERSION_LOCK       INTEGER   default 0    not null,
	ACCOUNT_ID         CHARACTER VARYING(255) not null,
	ROLE_ID            CHARACTER VARYING(255) not null,
	DELETED            TIMESTAMP default null
);

create table DICT
(
	ID                 CHARACTER VARYING(255) not null
		primary key,
	CREATED_BY         CHARACTER VARYING(255) not null,
	CREATED_DATE       TIMESTAMP              not null,
	LAST_MODIFIED_BY   CHARACTER VARYING(255) not null,
	LAST_MODIFIED_DATE TIMESTAMP              not null,
	APP_VERSION        CHARACTER VARYING(10)  not null,
	DATA_VERSION       CHARACTER VARYING(10)  not null,
	REMARK             CHARACTER VARYING(255),
	TENANT_ID          CHARACTER VARYING(255) not null,
	VERSION_LOCK       INTEGER   default 0    not null,
	CODE               CHARACTER VARYING(255) not null
		unique,
	NAME               CHARACTER VARYING(255) not null,
	ORDER_BY_PRIORITY  INTEGER   default 0    not null,
	DELETED            TIMESTAMP default null
);

create table DICT_ITEM
(
	ID                 CHARACTER VARYING(255) not null
		primary key,
	CREATED_BY         CHARACTER VARYING(255) not null,
	CREATED_DATE       TIMESTAMP              not null,
	LAST_MODIFIED_BY   CHARACTER VARYING(255) not null,
	LAST_MODIFIED_DATE TIMESTAMP              not null,
	APP_VERSION        CHARACTER VARYING(10)  not null,
	DATA_VERSION       CHARACTER VARYING(10)  not null,
	REMARK             CHARACTER VARYING(255),
	TENANT_ID          CHARACTER VARYING(255) not null,
	VERSION_LOCK       INTEGER   default 0    not null,
	DICT_ID            CHARACTER VARYING(255) not null,
	ORDER_BY_PRIORITY  INTEGER   default 0    not null,
	TYPE               CHARACTER VARYING(255) not null,
	"value"            CHARACTER VARYING(255) not null,
	DELETED            TIMESTAMP default null
);

create table PERMISSION
(
	ID                 CHARACTER VARYING(255) not null
		primary key,
	CREATED_BY         CHARACTER VARYING(255) not null,
	CREATED_DATE       TIMESTAMP              not null,
	LAST_MODIFIED_BY   CHARACTER VARYING(255) not null,
	LAST_MODIFIED_DATE TIMESTAMP              not null,
	APP_VERSION        CHARACTER VARYING(10)  not null,
	DATA_VERSION       CHARACTER VARYING(10)  not null,
	REMARK             CHARACTER VARYING(255),
	TENANT_ID          CHARACTER VARYING(255) not null,
	VERSION_LOCK       INTEGER   default 0    not null,
	NAME               CHARACTER VARYING(255) not null,
	ORDER_BY_PRIORITY  INTEGER   default 0    not null,
	DELETED            TIMESTAMP default null
);

create table ROLE
(
	ID                 CHARACTER VARYING(255) not null
		primary key,
	CREATED_BY         CHARACTER VARYING(255) not null,
	CREATED_DATE       TIMESTAMP              not null,
	LAST_MODIFIED_BY   CHARACTER VARYING(255) not null,
	LAST_MODIFIED_DATE TIMESTAMP              not null,
	APP_VERSION        CHARACTER VARYING(10)  not null,
	DATA_VERSION       CHARACTER VARYING(10)  not null,
	REMARK             CHARACTER VARYING(255),
	TENANT_ID          CHARACTER VARYING(255) not null,
	VERSION_LOCK       INTEGER   default 0    not null,
	IS_ADMIN           BOOLEAN                not null,
	IS_SUPER_ADMIN     BOOLEAN                not null,
	NAME               CHARACTER VARYING(255) not null,
	ORDER_BY_PRIORITY  INTEGER   default 0    not null,
	DELETED            TIMESTAMP default null
);

create table ROLE_PERMISSION
(
	ID                 CHARACTER VARYING(255) not null
		primary key,
	CREATED_BY         CHARACTER VARYING(255) not null,
	CREATED_DATE       TIMESTAMP              not null,
	LAST_MODIFIED_BY   CHARACTER VARYING(255) not null,
	LAST_MODIFIED_DATE TIMESTAMP              not null,
	APP_VERSION        CHARACTER VARYING(10)  not null,
	DATA_VERSION       CHARACTER VARYING(10)  not null,
	REMARK             CHARACTER VARYING(255),
	TENANT_ID          CHARACTER VARYING(255) not null,
	VERSION_LOCK       INTEGER   default 0    not null,
	PERMISSION_ID      CHARACTER VARYING(255) not null,
	ROLE_ID            CHARACTER VARYING(255) not null,
	DELETED            TIMESTAMP default null
);

create table T_LOG_RECORD
(
	ID          CHARACTER VARYING(255) not null
		primary key,
	ACTION      CHARACTER VARYING(511),
	BIZ_NO      CHARACTER VARYING(200),
	CREATE_TIME TIMESTAMP,
	EXTRA       CHARACTER VARYING(255),
	FAIL        BOOLEAN                not null,
	OPERATOR    CHARACTER VARYING(63),
	SUB_TYPE    CHARACTER VARYING(255),
	TENANT      CHARACTER VARYING(255),
	TYPE        CHARACTER VARYING(200),
	DELETED     TIMESTAMP default null
);

create table T_ORDER
(
	ID                 CHARACTER VARYING(255) not null
		primary key,
	CREATED_BY         CHARACTER VARYING(255) not null,
	CREATED_DATE       TIMESTAMP              not null,
	LAST_MODIFIED_BY   CHARACTER VARYING(255) not null,
	LAST_MODIFIED_DATE TIMESTAMP              not null,
	APP_VERSION        CHARACTER VARYING(10)  not null,
	DATA_VERSION       CHARACTER VARYING(10)  not null,
	REMARK             CHARACTER VARYING(255),
	TENANT_ID          CHARACTER VARYING(255) not null,
	VERSION_LOCK       INTEGER   default 0    not null,
	BIZ_CODE           CHARACTER VARYING(255) not null,
	ORDER_ID           CHARACTER VARYING(255),
	ORDER_STATE        CHARACTER VARYING(255) not null,
	SCENE_ID           CHARACTER VARYING(255) not null,
	DELETED            TIMESTAMP default null
);

