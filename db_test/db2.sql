/* 
-sin tabla estacionamiento
-sin constraint control_altura
-sin procedures do_somethin_4 y do_something_5
-sin trigger no_delete_666
*/


DROP SCHEMA IF EXISTS database2 CASCADE;
CREATE SCHEMA database2 AUTHORIZATION postgres;

SET search_path = database2;


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
altura integer
);



/******  TRIGGERS   ******/ 

-- TIGGER dni_positivo BEFORE INSERT ON persona
CREATE FUNCTION dni_positivo() RETURNS trigger AS $dni_positivo$
    BEGIN
        IF NEW.dni<0 THEN
            RAISE EXCEPTION 'dni cannot be negative';
        END IF;      
        RETURN NEW;
    END;
$dni_positivo$ LANGUAGE plpgsql;
CREATE TRIGGER Trig_dni_positivo BEFORE INSERT ON persona
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
CREATE TRIGGER Trigg_color_equal AFTER UPDATE ON vehiculo
    FOR EACH ROW EXECUTE PROCEDURE color_equal(); 



/******  PROCEDURES   ******/ 


CREATE OR REPLACE FUNCTION do_something_1( IN par1 varchar(20), OUT par2 float) RETURNS float AS
$$
BEGIN
  par2 := 0;  
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION do_something_2( IN par1 varchar(20), IN par2 varchar(20)) RETURNS varchar(20) AS
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
