create table usuarios(
    id int not null auto_increment,
    usuario varchar(100) not null,
    password varchar(100) not null,
    primary key (id)
)