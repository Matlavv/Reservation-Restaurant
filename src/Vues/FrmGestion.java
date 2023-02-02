package Vues;

import Controlers.CtrlReservation;
import Tools.ConnexionBDD;
import Tools.ModelJTable;

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
    private JLabel lblChoixDate;
    private JPanel rootPane;

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
                JOptionPane.showMessageDialog(null,"Entrez un Nom pour la réservation.","Nom manquant",JOptionPane.WARNING_MESSAGE);
            }
            else if(txtNombre.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Entrez un Nombre de clients pour la réservation.","Nombre de clients manquant",JOptionPane.WARNING_MESSAGE);
            }
            else if(txtTable.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Entrez un numéro de table pour la réservation.","Numéro de table manquant",JOptionPane.WARNING_MESSAGE);
            }
            else if(txtDate.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Entrez une date.","Date non indiquée",JOptionPane.WARNING_MESSAGE);
            }
            else if(txtHeure.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Entrez une heure.","Heure non indiquée",JOptionPane.WARNING_MESSAGE);
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