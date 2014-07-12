
CREATE TABLE usuario (
       login                varchar(20) NOT NULL,
       nombre               varchar(20) NULL,
       password             varchar(20) NULL,
       PRIMARY KEY (login)
);


CREATE TABLE contacto (
       codContacto          int NOT NULL,
       login                varchar(20) NOT NULL,
       nombres              varchar(25) NULL,
       apellidos            varchar(40) NULL,
       sexo                 varchar(10) NULL,
       dni                  varchar(8) NULL,
       telefono             varchar(12) NULL,
       email                varchar(40) NULL,
       direccion            varchar(40) NULL,
       fechaDeNacimiento    varchar(20) NULL,
       PRIMARY KEY (codContacto, login), 
       FOREIGN KEY (login)
                             REFERENCES usuario
);
