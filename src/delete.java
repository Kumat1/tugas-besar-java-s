import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class delete extends JFrame {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;

    delete(){
        setTitle("Delete Data Obat");

        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        JTextField search= new JTextField();
        JButton button = new JButton("Delete");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    con= DriverManager.getConnection("jdbc:mysql://localhost/apotek", "root", "");
                    stat = con.createStatement();
                    stat.executeUpdate("DELETE FROM obat WHERE ID= '" + Integer.valueOf(search.getText()) + "'");
                    stat.close();
                    JOptionPane.showMessageDialog(null,"Berhasil Menghapus Data");

                } catch (Exception err)
                {
                    JOptionPane.showMessageDialog(null, "salah");
                }
            }

        });

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new menu();
                dispose();
            }
        });

        JButton dt = new JButton("Data Obat");
        dt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new dataobat();
            }
        });

        JLabel id = new JLabel("ID Obat");

        search.setEnabled(true);
        search.setBounds(160, 100, 150, 25);
        button.setBounds(70, 280, 100, 25);
        back.setBounds(210, 280, 100, 25);
        dt.setBounds(70, 20, 100, 25);
        id.setBounds(70, 100, 100, 25);

        setVisible(true);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        add(search);
        add(button);
        add(back);
        add(dt);
        add(id);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


}
