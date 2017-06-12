import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;

import static java.lang.System.err;

/**
 * Created by RHAMA-PC on 5/25/2017.
 */

public class transaksi extends JFrame {

    Connection con;
    Statement stat;


    public transaksi() {


        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        JTextField txt1 = new JTextField();
        JTextField txt2 = new JTextField();
        JTextField txt3 = new JTextField();
        JTextField txt4 = new JTextField();


        JButton button = new JButton("Beli");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    try {
                        if (Integer.valueOf(txt1.getText()) > 3) {
                            JOptionPane.showMessageDialog(null, "ID obat salah");

                        } else if (txt1.getText().isEmpty() || txt2.getText().isEmpty()
                                || txt3.getText().isEmpty() || txt4.getText().isEmpty()){
                            JOptionPane.showMessageDialog(null, "Data Belum Lengkap");
                        }
                        else if (txt1.getText().isEmpty() && txt2.getText().isEmpty()
                                && txt3.getText().isEmpty() && txt4.getText().isEmpty()){
                            JOptionPane.showMessageDialog(null, "Anda Belum Mengisi Data");
                        }
                        else {
                           //harga();
                            int jlh = Integer.valueOf(txt4.getText());

                            stat = con.createStatement();
                            stat.addBatch("UPDATE obat SET jumlah = jumlah - '" + jlh + "'WHERE ID = '"
                                    + Integer.valueOf(txt1.getText()) + "'");
                            stat.addBatch("INSERT INTO transaksi(ID,nama,jumlah,harga)" + "VALUES('"
                                    + Integer.valueOf(txt1.getText()) + "','" + txt2.getText() + "','"
                                    + Integer.valueOf(txt4.getText())  + "','" + Integer.valueOf(txt3.getText()) + "')");

                            int[] rs = stat.executeBatch();
                            stat.close();
                            JOptionPane.showMessageDialog(null, "Berhasil");
                        }

                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                }

            }

            /*public void harga() {
                try {
                    stat = con.createStatement();
                    String query = "SELECT harga from obat WHERE ID = '" + Integer.valueOf(txt1.getText()) + "'";
                    stat.executeQuery(query);
                    ResultSet rs = stat.getResultSet();
                    while (rs.next()) {
                        int harga = rs.getInt("harga");
                        System.out.println(harga);


                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }*/
        });

        JButton button1 = new JButton("Cancel");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menu();
            }

        });

        JButton button2 = new JButton("Print");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sep = System.lineSeparator();

                    String str = "				Struk Belanja" +sep+
                            sep+"====================================================================" + sep +
                            sep + "ID Obat   		:  " + txt1.getText() +
                            sep + "Nama Obat   		:  " + txt2.getText()  +
                            sep + "Jumlah   		:  " + txt4.getText() + "pc" +
                            sep + "Harga Satuan		:  " + "@" + "Rp. " + txt3.getText() + sep +


                            "            Terimakasih Telah Berbelanja di Apotek Terserah";

                    File newTextFile = new File("struk/struk " + txt2.getText() + ".txt");
                    FileWriter y = new FileWriter(newTextFile);
                    y.write(str);
                    y.close();

                    JOptionPane.showMessageDialog(null, "Struk Belanja Sudah Dicetak",
                            "Terima kasih", JOptionPane.PLAIN_MESSAGE);
                    txt1.setText("");
                    txt2.setText("");
                    txt4.setText("");
                    txt3.setText("");


                } catch (IOException iox) {
                    iox.printStackTrace();
                }
            }


        });

        JButton button3 = new JButton("Data Obat");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new dataobat();
            }
        });


        JLabel aa = new JLabel("Transaksi");
        aa.setBounds(70, 20, 500, 50);

        JLabel a = new JLabel("ID Obat");
        a.setSize(100, 30);
        a.setBounds(70, 50, 500, 50);

        JLabel b = new JLabel("Nama Obat");
        b.setSize(100, 30);
        b.setBounds(70, 100, 500, 50);

        JLabel c = new JLabel("Harga Satuan");
        c.setSize(100, 30);
        c.setBounds(70, 150, 500, 50);

        JLabel d = new JLabel("Jumlah");
        d.setSize(100, 30);
        d.setBounds(70, 200, 500, 50);


        txt1.setEnabled(true);
        txt1.setBounds(160, 65, 150, 25);

        txt2.setEnabled(true);
        txt2.setBounds(160, 115, 150, 25);

        txt3.setEnabled(true);
        txt3.setBounds(160, 165, 150, 25);

        txt4.setEnabled(true);
        txt4.setBounds(160, 215,150,25);

        button.setBounds(70, 265, 100, 25);
        button1.setBounds(210, 265, 100, 25);
        button2.setBounds(70, 300, 100, 25);
        button3.setBounds(210, 300, 100, 25);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        add(aa);
        add(a);
        add(b);
        add(c);
        add(d);
        add(txt4);
        add(txt1);
        add(txt2);
        add(txt3);
        add(button);
        add(button1);
        add(button2);
        add(button3);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }
}




