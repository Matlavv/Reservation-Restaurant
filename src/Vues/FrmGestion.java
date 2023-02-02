package Vues;

import Controlers.CtrlReservation;
import Tools.ConnexionBDD;
import Tools.ModelJTable;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class FrmGestion extends JFrame{
    private JLabel lblTitre;
    private JLabel lblReservation;
    private JLabel lblAjout;
    private JTable tblReservation;
    private JLabel lblNom;
    private JLabel lblTable;
    private JLabel lblDate;
    private JLabel lblNombre;
    private JLabel lblHeure;
    private JTextField txtNom;
    private JTextField txtNombre;
    private JTextField txtTable;
    private JTextField txtDate;
    private JTextField txtHeure;
    private JButton btnAjouter;
    private JPanel rootPane;
    private JLabel lblDateAffichage;
    private JPanel pnlDate;
    private JDateChooser dteReservation;

    private CtrlReservation ctrlReservation;
    private ModelJTable modelJTable;
    ConnexionBDD cnx;


//Affichage de l'appli
public FrmGestion() throws SQLException, ClassNotFoundException
{
    this.setTitle("Gestion");
    this.setContentPane(rootPane);
    this.pack();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);

    //Gestion de la date
    dteReservation = new JDateChooser();
    dteReservation.setDateFormatString("yyyy-MM-dd");


    // Il faut d'abord instancier la connexion avant d'instancier le controller
    // pour les réservations
    cnx = new ConnexionBDD();

    ctrlReservation = new CtrlReservation();

    this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowOpened(WindowEvent e) {
            super.windowOpened(e);

            modelJTable = new ModelJTable();
            modelJTable.loadDatasReservation(ctrlReservation.GetAllReservation());
            tblReservation.setModel(modelJTable);
        }
    });


//Ajouter une reservation

   btnAjouter.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {

            //mettre les erreurs ici
           if(txtNom.getText().equals(""))
           {
               JOptionPane.showMessageDialog(null,"Ajoutez un nom à la réservation", "Nom manquant",JOptionPane.WARNING_MESSAGE);
           }
           else if(txtNombre.getText().equals(""))
           {
               JOptionPane.showMessageDialog(null,"Ajoutez un nombre de clients à la réservation", "Nombre de clients manquant",JOptionPane.WARNING_MESSAGE);
           }
           else if(txtTable.getText().equals(""))
           {
               JOptionPane.showMessageDialog(null,"Ajoutez un numéro de table à la réservation", "Numéro de table manquant",JOptionPane.WARNING_MESSAGE);
           }
           else if(txtDate.getText().equals(""))
           {
               JOptionPane.showMessageDialog(null,"Ajoutez une date à la réservation", "Date manquante",JOptionPane.WARNING_MESSAGE);
           }
           else if(txtHeure.getText().equals(""))
           {
               JOptionPane.showMessageDialog(null,"Ajoutez une heure à la réservation", "Heure manquante",JOptionPane.WARNING_MESSAGE);
           }
           else
           {
               Date dte = Date.valueOf(txtDate.getText());
               Time tps  = Time.valueOf(txtHeure.getText());
               ctrlReservation.AjouterNouvelleReservation(txtNom.getText(), Integer.parseInt(txtNombre.getText()), dte, Integer.parseInt(txtTable.getText()), tps);
           }




            // On rafraichit l'interface graphique
           modelJTable = new ModelJTable();
           modelJTable.loadDatasReservation(ctrlReservation.GetAllReservation());
           tblReservation.setModel(modelJTable);
       }});
   }
}