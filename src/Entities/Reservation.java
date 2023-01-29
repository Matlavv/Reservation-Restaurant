package Entities;

import java.sql.Time;
import java.util.Date;

public class Reservation {
    private int idReservation;
    private String nomClient;
    private int nombreClient;
    private int tableReservation;
    private Date dateReservation;
    private Time heureReservation;

    public Reservation(int idReservation, String nomClient, int nombreClient, int tableReservation, Date dateReservation, Time heureReservation) {
        this.idReservation = idReservation;
        this.nomClient = nomClient;
        this.nombreClient = nombreClient;
        this.tableReservation = tableReservation;
        this.dateReservation = dateReservation;
        this.heureReservation = heureReservation;
    }



    public int getIdReservation() {
        return idReservation;
    }

    public String getNomClient() {
        return nomClient;
    }

    public int getNombreClient() {
        return nombreClient;
    }

    public int getTableReservation() {
        return tableReservation;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public Time getHeureReservation() {
        return heureReservation;
    }
}

