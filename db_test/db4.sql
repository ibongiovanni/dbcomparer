/*
Triggers
dni_positivo: cambio a AFTER UPDATE
color_equal: cambio a BEFORE DELETE

Procedures
do_something_1: sin par1
do_something_2: cambio de tipos de parametros.
do_something_4: se cambio el orden de los parametros
*/


DROP SCHEMA IF EXISTS database4 CASCADE;
CREATE SCHEMA database4 AUTHORIZATION postgres;

SET search_path = database4;


create table vehiculo(
nro_patente varchar(20) not null primary key,
marca varchar(20),
modelo varchar(20),
color varchar(20),
saldoActual float
);

create table persona(
dni integer not null primary key,
nom_ape varchar(30),
direccion varchar(30)
);

create table dueño(
nro_patente varchar(20) not null,
dni integer not null,
constraint pk_duenio primary key (nro_patente, dni)
);

create table parquimetro(
nro_parq integer not null primary key,
calle varchar(20),
altura integer,
constraint control_altura check ((altura >= 0)and(altura <= 5000))
);

create table estacionamiento(
nro_est serial not null primary key ,
nro_patente varchar(20),
nro_parq integer,
fecha date,  
saldoInicio float,
saldoFinal float,
hora_in time,
hora_out time,
constraint fk_patente foreign key (nro_patente) references vehiculo on delete restrict,
constraint fk_parquimetro foreign key (nro_parq) references parquimetro
);

/******  TRIGGERS   ******/ 

-- TIGGER dni_positivo BEFORE INSERT ON persona
CREATE FUNCTION dni_positivo() RETURNS trigger AS $dni_positivo$
    BEGIN

    END;
$dni_positivo$ LANGUAGE plpgsql;
CREATE TRIGGER Trig_dni_positivo AFTER UPDATE ON persona
    FOR EACH ROW EXECUTE PROCEDURE dni_positivo();


-- TRIGGER hora_consist BEFORE INSERT ON parquimetro
CREATE FUNCTION hora_consist() RETURNS trigger AS $hora_consist$
    BEGIN
        IF NEW.hora_in > NEW.hora_out THEN
            RAISE EXCEPTION 'wrong time';
        END IF;
        RETURN NEW;
    END;
$hora_consist$ LANGUAGE plpgsql;
CREATE TRIGGER Trigg_hora_consist BEFORE INSERT ON parquimetro
    FOR EACH ROW EXECUTE PROCEDURE hora_consist();


-- TRIGGER color_equal AFTER UPDATE ON vehiculo
CREATE FUNCTION color_equal() RETURNS trigger AS $color_equal$
    BEGIN
		IF(new.color != old.color)THEN
			RAISE EXCEPTION 'YOU CANT';
		END IF;
    END;
$color_equal$ LANGUAGE plpgsql;
CREATE TRIGGER Trigg_color_equal BEFORE DELETE ON vehiculo
    FOR EACH ROW EXECUTE PROCEDURE color_equal(); 


-- TRIGGER no_delete_666 AFTER DELETE ON vehiculo
CREATE FUNCTION no_delete_666() RETURNS trigger AS $no_delete_666$
    BEGIN
		IF(new.nro_parq == '666')THEN
			RAISE EXCEPTION 'Todo mal..';
		END IF;
    END;
$no_delete_666$ LANGUAGE plpgsql;
CREATE TRIGGER Trigg_no_delete_666 AFTER DELETE ON vehiculo
    FOR EACH ROW EXECUTE PROCEDURE no_delete_666();           


/******  PROCEDURES   ******/ 


CREATE OR REPLACE FUNCTION do_something_1( OUT par2 float) RETURNS float AS
$$
BEGIN
  par2 := 0;  
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION do_something_2( IN par1 int, IN par2 date) RETURNS varchar(20) AS
$$
BEGIN
  RETURN "hola";
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION do_something_3( IN par1 int, INOUT par2 float) RETURNS float AS
$$
BEGIN
  par2:= 0;  
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION do_something_4( INOUT par3 date, IN par1 varchar(20), OUT par2 float) AS
$$
BEGIN
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION do_something_5( OUT par1 varchar(20)) RETURNS varchar(20) AS
$$
BEGIN
END;
$$ LANGUAGE plpgsql;