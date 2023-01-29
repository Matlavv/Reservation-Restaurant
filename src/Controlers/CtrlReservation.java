package Controlers;

import Entities.Reservation;
import Tools.ConnexionBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlReservation {
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlReservation(){ cnx = ConnexionBDD.getCnx(); } //Se connecter à la BDD

    //Retourner la base de donnée sous un tableau

    public ArrayList<Reservation> GetAllReservation()
    {
        ArrayList<Reservation> lesReservations = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT * FROM `reservation`");  //requete SQL pour récup toute la table Reservation
            rs = ps.executeQuery(); //executer la requete
            while (rs.next()) {
                Reservation reservation = new Reservation(rs.getInt("idReservation"), rs.getString("nomReservation"),rs.getInt("nombreClient"),rs.getInt("tableReservation"),rs.getDate("dateReservation"), rs.getTime("heureReservation"));
                lesReservations.add(reservation);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesReservations;
    }

    public ArrayList<Reservation> GetAllReservationByDate(Date dateReservation)
    {
        ArrayList<Reservation> lesDateReservations = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT * FROM `reservation` WHERE dateReservation = ?");
            rs = ps.executeQuery();
            while (rs.next()) {
                Reservation reservation = new Reservation(rs.getInt("idReservation"), rs.getString("nomReservation"),rs.getInt("nombreClient"),rs.getInt("dateReservation"),rs.getDate("tableReservation"), rs.getTime("heureReservation"));
                lesDateReservations.add(reservation);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesDateReservations;
    }

    // Ajouter une réservation
    public void AjouterNouvelleReservation(String nomClient, int nombreClient, Date dateReservation, int tableReservation, Time heureReservation)
    {
        try
        {
            ps = cnx.prepareStatement("INSERT INTO `reservation`(`idReservation`, `nomClient`, `nombreClient`, `dateReservation`, `tableReservation`, `heureReservation`) VALUES (?,?,?,?,?,?)");
            ps.setObject(1, null); //id
            ps.setString(2, nomClient); //Nom
            ps.setInt(3, nombreClient); //nombre
            ps.setDate(4, (java.sql.Date) dateReservation); //date
            ps.setInt(5, tableReservation); //table
            ps.setTime(6, heureReservation); //heure
        } catch (SQLException ex) {
            Logger.getLogger(CtrlReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}