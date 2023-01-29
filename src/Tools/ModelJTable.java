package Tools;

import Entities.Reservation;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelJTable extends AbstractTableModel
{
    private String[] nomsColonnes;
    private Object[][] rows;

    @Override
    public String getColumnName(int column) {
        return nomsColonnes[column];
    }
    @Override
    public int getRowCount() {
        return rows.length;
    }

    @Override
    public int getColumnCount() {
        return nomsColonnes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows[rowIndex][columnIndex];
    }

    public void loadDatasReservation(ArrayList<Reservation> lesReservations) {
        nomsColonnes = new String[]{"ID","Nom","Nombre","Date","Table","Heure"};
        rows = new Object[lesReservations.size()][6];
        int i = 0;
        for (Reservation reservation : lesReservations) {
            rows[i][0] = reservation.getIdReservation();
            rows[i][1] = reservation.getNomClient();
            rows[i][2] = reservation.getNombreClient();
            rows[i][3] = reservation.getDateReservation();
            rows[i][4] = reservation.getTableReservation();
            rows[i][5] = reservation.getHeureReservation();
            i++;
        }
        fireTableChanged(null);
    }
}
