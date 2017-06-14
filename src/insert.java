import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class insert extends JFrame {

    Connection con;
    Statement stm;

    public insert() {

        setTitle("Insert Data Obat");

        setContentPane(new JLabel(new ImageIcon("bg/bg1.jpg")));
        JTextField txt1 = new JTextField();
        JTextField txt2 = new JTextField();
        JTextField txt3 = new JTextField();


        JButton button = new JButton("Insert");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama= txt1.getText();
                int harga = Integer.valueOf(txt2.getText());
                int jumlah = Integer.valueOf(txt3.getText());

                try{
                    con=DriverManager.getConnection("jdbc:mysql://localhost/apotek", "root", "");
                    stm = con.createStatement();
                    stm.executeUpdate("INSERT INTO obat(nama, harga, jumlah)" + "VALUES('" + txt1.getText() + "','"
                            + Integer.valueOf(txt2.getText()) + "','" + Integer.valueOf(txt3.getText()) + "')");
                    stm.close();
                    JOptionPane.showMessageDialog(null,"Berhasil Memasukan Data");
                }
                catch(Exception err){
                    JOptionPane.showMessageDialog(null,"Gagal Memasukan data");
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



        JLabel a = new JLabel("Nama  :");
        a.setSize(100, 30);
        a.setBounds(70, 50, 500, 50);

        JLabel b = new JLabel("Harga  :");
        b.setSize(100, 30);
        b.setBounds(70, 100, 500, 50);

        JLabel c = new JLabel("Jumlah  :");
        c.setSize(100, 30);
        c.setBounds(70, 150, 500, 50);

        txt1.setEnabled(true);
        txt2.setEnabled(true);
        txt3.setEnabled(true);
        txt1.setBounds(160, 65, 150, 25);
        txt2.setBounds(160, 115, 150, 25);
        txt3.setBounds(160, 165, 150, 25);
        button.setBounds(70, 230, 100, 25);
        back.setBounds(210, 230, 100, 25);
        dt.setBounds(70, 20, 100, 25);

        setVisible(true);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        add(a);
        add(b);
        add(c);
        add(txt1);
        add(txt2);
        add(txt3);
        add(button);
        add(back);
        add(dt);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}