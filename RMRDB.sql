create database rmrdb;
use rmrdb;

create table perfil(
	id int(3) auto_increment primary key not null,
    nombre varchar(45) not null
)engine=MyISAM;

create table cliente(
	id int(3) auto_increment primary key not null,
    nombres varchar(45) not null,
    apellidos varchar(45) not null,
	telefono varchar(10) not null,
    email varchar(100) not null
)engine=MyISAM;

create table administrador(
	id int(3) auto_increment primary key not null,
    nombres varchar(45) not null,
    apellidos varchar(45) not null,
    dui varchar(10) not null,
	telefono varchar(10) not null
)engine=MyISAM;

create table acceso(
	id int(3) auto_increment primary key not null,
    usuario varchar(45) not null,
    clave varchar(45) not null,
    fecha_creacion varchar(10) not null,
	id_usuario int(3) not null,
    foreign key (id_usuario) references perfil(id) on update cascade on delete cascade
)engine=MyISAM;

create table producto(
	id int(3) auto_increment primary key not null,
    nombre varchar(45) not null,
    marca varchar(45) not null,
    precio decimal (5,2),
	descuento decimal (5,2)
)engine=MyISAM;

create table promocion(
	id int(3) auto_increment primary key not null,
    nombre varchar(45) not null,
    descripcion text not null
)engine=MyISAM;

create table promocion_producto(
	id int(3) auto_increment primary key not null,
    precio decimal (5,2),
	descuento decimal (5,2),
	id_producto int(3) not null,
	id_promocion int(3) not null,
	foreign key (id_producto) references producto(id) on update cascade on delete cascade,
	foreign key (id_promocion) references promocion(id) on update cascade on delete cascade
)engine=MyISAM;

create table categoria_servicio(
	id int(3) auto_increment primary key not null,
    nombre varchar(45) not null
)engine=MyISAM;

create table servicio(
	id int(3) auto_increment primary key not null,
    nombre varchar(45) not null,
    precio decimal (5,2),
	descuento decimal (5,2),
	descripcion text not null,
    id_categoria_servicio int(3) not null,
	foreign key (id_categoria_servicio) references categoria_servicio(id) on update cascade on delete cascade
)engine=MyISAM;

create table servicio_producto(
	id int(3) auto_increment primary key not null,
	id_servicio int(3) not null,
	id_producto int(3) not null,
	foreign key (id_servicio) references servicio(id) on update cascade on delete cascade,
	foreign key (id_producto) references producto(id) on update cascade on delete cascade
)engine=MyISAM;

create table promocion_servicio(
	id int(3) auto_increment primary key not null,
    precio decimal (5,2),
	descuento decimal (5,2),
	id_promocion int(3) not null,	
    id_servicio int(3) not null,
	foreign key (id_promocion) references promocion(id) on update cascade on delete cascade,
    foreign key (id_servicio) references servicio(id) on update cascade on delete cascade
)engine=MyISAM;

create table reservacion(
	id int(3) auto_increment primary key not null,
    horario int(5) not null,
    comentario text not null,
	id_usuario int(3) not null,
    foreign key (id_usuario) references acceso(id) on update cascade on delete cascade
)engine=MyISAM;

create table servicio_reservacion(
	id int(3) auto_increment primary key not null,
	id_servicio int(3) not null,
	id_reservacion int(3) not null,
	foreign key (id_servicio) references servicio(id) on update cascade on delete cascade,
	foreign key (id_reservacion) references reservacion(id) on update cascade on delete cascade
)engine=MyISAM;