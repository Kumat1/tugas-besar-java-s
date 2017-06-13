import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class dataobat
{
    public dataobat()
    {
        JFrame f = new JFrame();
        f.getContentPane().setBackground(new Color(0,255,255));
        f.setTitle("Data Obat Apotek Terserah");
        f.pack();
        f.setVisible(true);
        f.setSize(600, 400);
        f.setLocationRelativeTo(null);

        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        //  Connect to an MySQL Database, run query, get result set
        String url = "jdbc:mysql://localhost:3306/apotek";
        String userid = "root";
        String password = "";
        String sql = "SELECT * FROM obat";

        try (Connection connection = DriverManager.getConnection( url, userid, password );
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery( sql ))
        {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            //  Get column names
            for (int i = 1; i <= columns; i++)
            {
                columnNames.add( md.getColumnName(i) );
            }

            //  Get row data
            while (rs.next())
            {
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++)
                {
                    row.add( rs.getObject(i) );
                }

                data.add( row );
            }
        }
        catch (SQLException e)
        {
            System.out.println( e.getMessage() );
        }

        Vector columnNamesVector = new Vector();
        Vector dataVector = new Vector();

        for (int i = 0; i < data.size(); i++)
        {
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));


        //  Create table with database data
        JTable table = new JTable(dataVector, columnNamesVector)
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }

            public Class getColumnClass(int column)
            {


                for (int row = 0; row < getRowCount(); row++)
                {
                    Object o = getValueAt(row, column);

                    if (o != null)
                    {
                        return o.getClass();
                    }
                }
                return Object.class;
            }

        };


        JScrollPane scrollPane = new JScrollPane( table );
        f.getContentPane().add( scrollPane );

        JPanel buttonPanel = new JPanel();
        f.getContentPane().add( buttonPanel, BorderLayout.SOUTH );

    }
}