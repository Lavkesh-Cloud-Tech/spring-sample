create database TestDB;

create table MOVIE(
	MOVIE_CODE varchar(50), 
    MOVIE_NAME varchar(200) not null, 
    primary key (movie_code)
);

create table MOVIE_TIME(
	MOVIE_TIME_ID int auto_increment,
	MOVIE_CODE varchar(50), 
    SHOW_DATE date,
    SHOW_TIME varchar(50),
    primary key (MOVIE_TIME_ID),
    unique key (MOVIE_CODE, SHOW_DATE, SHOW_TIME),
    foreign key (MOVIE_CODE) references MOVIE(MOVIE_CODE) ON DELETE CASCADE 
);

create table TICKET_AVAILABILITY(
	TICKET_AVAILABILITY_ID int auto_increment,
	MOVIE_TIME_ID int not null, 
    TICKET_CLASS varchar(50) not null,
    AVAILABLE_TICKETS int not null default 0,
    primary key (TICKET_AVAILABILITY_ID),
    unique key (MOVIE_TIME_ID, TICKET_CLASS),
    foreign key (MOVIE_TIME_ID) references MOVIE_TIME(MOVIE_TIME_ID) ON DELETE CASCADE 
);

create table RESERVATION_DETAILS(
	RESERVATION_ID int auto_increment,
    TICKET_AVAILABILITY_ID int not null,
    NUMBER_OF_SEATS int not null,
    primary key (RESERVATION_ID),
    foreign key (TICKET_AVAILABILITY_ID) references TICKET_AVAILABILITY(TICKET_AVAILABILITY_ID) ON DELETE CASCADE 
);


insert into MOVIE(MOVIE_CODE, MOVIE_NAME) values('KHNH_2000', 'Kal Ho Na Ho');

insert into MOVIE_TIME(MOVIE_CODE, SHOW_DATE, SHOW_TIME) values('KHNH_2000', '2000-07-13', '12:00');
insert into MOVIE_TIME(MOVIE_CODE, SHOW_DATE, SHOW_TIME) values('KHNH_2000', '2000-07-13', '15:00');
insert into MOVIE_TIME(MOVIE_CODE, SHOW_DATE, SHOW_TIME) values('KHNH_2000', '2000-07-13', '18:00');
insert into MOVIE_TIME(MOVIE_CODE, SHOW_DATE, SHOW_TIME) values('KHNH_2000', '2000-07-13', '21:00');

insert into TICKET_AVAILABILITY(MOVIE_TIME_ID, TICKET_CLASS, AVAILABLE_TICKETS) 
	SELECT mt.MOVIE_TIME_ID, 'ECONOMY', 20 FROM MOVIE_TIME mt where mt.MOVIE_CODE = 'KHNH_2000' and mt.SHOW_DATE = '2000-07-13' and mt.SHOW_TIME = '12:00';
insert into TICKET_AVAILABILITY(MOVIE_TIME_ID, TICKET_CLASS, AVAILABLE_TICKETS) 
	SELECT mt.MOVIE_TIME_ID, 'DELUXE', 20 FROM MOVIE_TIME mt where mt.MOVIE_CODE = 'KHNH_2000' and mt.SHOW_DATE = '2000-07-13' and mt.SHOW_TIME = '12:00';
insert into TICKET_AVAILABILITY(MOVIE_TIME_ID, TICKET_CLASS, AVAILABLE_TICKETS) 
	SELECT mt.MOVIE_TIME_ID, 'BALCONY', 20 FROM MOVIE_TIME mt where mt.MOVIE_CODE = 'KHNH_2000' and mt.SHOW_DATE = '2000-07-13' and mt.SHOW_TIME = '12:00';
    
insert into TICKET_AVAILABILITY(MOVIE_TIME_ID, TICKET_CLASS, AVAILABLE_TICKETS) 
	SELECT mt.MOVIE_TIME_ID, 'ECONOMY', 20 FROM MOVIE_TIME mt where mt.MOVIE_CODE = 'KHNH_2000' and mt.SHOW_DATE = '2000-07-13' and mt.SHOW_TIME = '15:00';
insert into TICKET_AVAILABILITY(MOVIE_TIME_ID, TICKET_CLASS, AVAILABLE_TICKETS) 
	SELECT mt.MOVIE_TIME_ID, 'DELUXE', 20 FROM MOVIE_TIME mt where mt.MOVIE_CODE = 'KHNH_2000' and mt.SHOW_DATE = '2000-07-13' and mt.SHOW_TIME = '15:00';
insert into TICKET_AVAILABILITY(MOVIE_TIME_ID, TICKET_CLASS, AVAILABLE_TICKETS) 
	SELECT mt.MOVIE_TIME_ID, 'BALCONY', 20 FROM MOVIE_TIME mt where mt.MOVIE_CODE = 'KHNH_2000' and mt.SHOW_DATE = '2000-07-13' and mt.SHOW_TIME = '15:00';    

insert into TICKET_AVAILABILITY(MOVIE_TIME_ID, TICKET_CLASS, AVAILABLE_TICKETS) 
	SELECT mt.MOVIE_TIME_ID, 'ECONOMY', 20 FROM MOVIE_TIME mt where mt.MOVIE_CODE = 'KHNH_2000' and mt.SHOW_DATE = '2000-07-13' and mt.SHOW_TIME = '18:00';
insert into TICKET_AVAILABILITY(MOVIE_TIME_ID, TICKET_CLASS, AVAILABLE_TICKETS) 
	SELECT mt.MOVIE_TIME_ID, 'DELUXE', 20 FROM MOVIE_TIME mt where mt.MOVIE_CODE = 'KHNH_2000' and mt.SHOW_DATE = '2000-07-13' and mt.SHOW_TIME = '18:00';
insert into TICKET_AVAILABILITY(MOVIE_TIME_ID, TICKET_CLASS, AVAILABLE_TICKETS) 
	SELECT mt.MOVIE_TIME_ID, 'BALCONY', 20 FROM MOVIE_TIME mt where mt.MOVIE_CODE = 'KHNH_2000' and mt.SHOW_DATE = '2000-07-13' and mt.SHOW_TIME = '18:00';  
    
insert into TICKET_AVAILABILITY(MOVIE_TIME_ID, TICKET_CLASS, AVAILABLE_TICKETS) 
	SELECT mt.MOVIE_TIME_ID, 'ECONOMY', 20 FROM MOVIE_TIME mt where mt.MOVIE_CODE = 'KHNH_2000' and mt.SHOW_DATE = '2000-07-13' and mt.SHOW_TIME = '21:00';
insert into TICKET_AVAILABILITY(MOVIE_TIME_ID, TICKET_CLASS, AVAILABLE_TICKETS) 
	SELECT mt.MOVIE_TIME_ID, 'DELUXE', 20 FROM MOVIE_TIME mt where mt.MOVIE_CODE = 'KHNH_2000' and mt.SHOW_DATE = '2000-07-13' and mt.SHOW_TIME = '21:00';
insert into TICKET_AVAILABILITY(MOVIE_TIME_ID, TICKET_CLASS, AVAILABLE_TICKETS) 
	SELECT mt.MOVIE_TIME_ID, 'BALCONY', 20 FROM MOVIE_TIME mt where mt.MOVIE_CODE = 'KHNH_2000' and mt.SHOW_DATE = '2000-07-13' and mt.SHOW_TIME = '21:00';   