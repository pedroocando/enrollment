# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table enrollments (
  id                            integer auto_increment not null,
  id_partner                    integer,
  id_branch                     integer,
  id_service                    integer,
  id_plan                       integer,
  token                         varchar(255),
  dni                           varchar(255),
  nombre                        varchar(255),
  apellido                      varchar(255),
  telefono                      varchar(255),
  direccion                     varchar(255),
  email                         varchar(255),
  fecha_nacimiento              datetime(6),
  fecha_admision                datetime(6),
  fecha_expiracion              datetime(6),
  numero_contrato               varchar(255),
  codigo                        varchar(255),
  numer_tarjeta                 varchar(255),
  nombre_propietario            varchar(255),
  mes_expiracion                varchar(255),
  ano_expiracion                integer,
  status                        tinyint(1) default 0 not null,
  created_at                    TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null,
  updated_at                    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP not null,
  constraint pk_enrollments primary key (id)
);


# --- !Downs

drop table if exists enrollments;

