CREATE SEQUENCE IF NOT EXISTS seq_usuario START WITH 1 INCREMENT BY 1;
/*==============================================================*/
/*Table: Usuario                                                */
/*==============================================================*/

CREATE TABLE IF NOT EXISTS usuario(
    id BIGINT DEFAULT nextval('seq_usuario'),
    nombre VARCHAR(255) NOT NULL,
    edad INT,
    numero_credencial BIGINT,
    sueldo DECIMAL(10, 2),
    impuesto FLOAT4,
    impuesto_detalle DOUBLE,
    activo BOOLEAN,
    fecha_local_date DATE,
    fecha_zone_date_TIME TIMESTAMP DEFAULT NULL,
    imagen BLOB,
    imagen_content_type VARCHAR(255),
    imagen_any_blob BLOB,
    imagen_any_blob_content_type VARCHAR(255),
    imagen_blob BLOB,
    imagen_blob_content_type VARCHAR(255),
    jhi_desc clob,
    instante TIMESTAMP DEFAULT NULL,
    constraint pk_usuario primary key (id)
);
