#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: reservation
#------------------------------------------------------------

CREATE TABLE reservation(
        idReservation    Int  Auto_increment  NOT NULL ,
        nomClient        Varchar (50) NOT NULL ,
        nombreClient     Int NOT NULL ,
        dateReservation  Date NOT NULL ,
        tableReservation Int NOT NULL ,
        heureReservation Time NOT NULL
	,CONSTRAINT reservation_PK PRIMARY KEY (idReservation)
)ENGINE=InnoDB;

