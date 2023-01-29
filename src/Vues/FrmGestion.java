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
    private ConnexionBDD cnx;


//Affichage de l'appli
public FrmGestion() throws SQLException, ClassNotFoundException
{
    this.setTitle("Gestion");
    this.setContentPane(rootPane);
    this.pack();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    ctrlReservation = new CtrlReservation();

    cnx = new ConnexionBDD();

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
        ctrlReservation.AjouterNouvelleReservation(txtNom.getText(), Integer.parseInt(txtNombre.getText().toString()), Date.valueOf(txtTable.getText().toString()), Integer.parseInt(txtDate.getText().toString()), Time.valueOf(txtHeure.getText()));
       }});
   }
}