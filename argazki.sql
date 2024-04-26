create database Argazki;
create table Argazkiak ( 
 idArgazki integer,
 izenburua varchar(50),
 data date,
 fitxategia varchar(59),
 bistaratzeKop int,
 idArgazkilari int
 );
 create table Argazkilari (
 idArgazkilari int,
 izena varchar(59),
 saritua boolean
 );
 insert into argazkiak values (1,"ansealdams1","2000-01-01","ansealdams1.jpg",0,1);
insert into argazkiak values (2,"ansealdams2","2000-01-02","ansealdams2.jpg",0,1);
insert into argazkiak values (3,"rothko1","2000-01-03","rothko1.jpg",0,2);
insert into argazkiak values (4,"vangogh1","2000-01-04","vangogh1.jpg",0,3);
insert into argazkiak values (5,"vangogh2","2000-01-05","vangogh2.jpg",0,3);
insert into argazkilari values (1,"ansealdams",true);
insert into argazkilari values (2,"rothko",false);
insert into argazkilari values (3,"vangogh",true);
