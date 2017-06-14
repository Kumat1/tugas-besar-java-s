import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class update extends JFrame {

    Connection con;
    Statement stm;

    public update() {
        setTitle("Update Data obat");
        setContentPane(new JLabel(new ImageIcon("bg/bg1.jpg")));
        JTextField txt = new JTextField();
        JTextField txt1 = new JTextField();
        JTextField txt2 = new JTextField();
        JTextField txt3 = new JTextField();


        JButton button = new JButton("Update");


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ID = Integer.valueOf(txt.getText());
                String nama= txt1.getText();
                int harga = Integer.valueOf(txt2.getText());
                int jumlah = Integer.valueOf(txt3.getText());

                try{
                    con=DriverManager.getConnection("jdbc:mysql://localhost/apotek", "root", "");
                    stm = con.createStatement();
                    stm.executeUpdate("UPDATE obat SET nama = '"+txt1.getText()+"', " +
                            "harga = '"+Integer.valueOf(txt2.getText())+"', " +
                            "jumlah = '"+Integer.valueOf(txt3.getText())+"' " +
                            "WHERE ID = '"+Integer.valueOf(txt.getText())+"'");


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


        JLabel aa = new JLabel("ID  :");
        aa.setSize(100, 30);
        aa.setBounds(70, 50, 500, 50);

        JLabel a = new JLabel("Nama  :");
        a.setSize(100, 30);
        a.setBounds(70, 100, 500, 50);

        JLabel b = new JLabel("Harga  :");
        b.setSize(100, 30);
        b.setBounds(70, 150, 500, 50);

        JLabel c = new JLabel("Jumlah  :");
        c.setSize(100, 30);
        c.setBounds(70, 200, 500, 50);

        txt.setEnabled(true);
        txt1.setEnabled(true);
        txt2.setEnabled(true);
        txt3.setEnabled(true);
        txt.setBounds(160, 65, 150, 25);
        txt1.setBounds(160, 115, 150, 25);
        txt2.setBounds(160, 165, 150, 25);
        txt3.setBounds(160, 215, 150, 25);
        button.setBounds(70, 280, 100, 25);
        back.setBounds(210, 280, 100, 25);
        dt.setBounds(70, 20, 100, 25);


        setVisible(true);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        add(aa);
        add(a);
        add(b);
        add(c);
        add(txt);
        add(txt1);
        add(txt2);
        add(txt3);
        add(button);
        add(dt);
        add(back);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}