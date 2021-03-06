import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class transaksi extends JFrame {

    Connection con;
    Statement stat;
    ResultSet rs;


    public transaksi() {

        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;

        setContentPane(new JLabel(new ImageIcon("bg/bg1.jpg")));
        JTextField txt1 = new JTextField();
        JTextField txt2 = new JTextField();
        JTextField txt3 = new JTextField();
        JTextField txt4 = new JTextField();
        JTextField total = new JTextField();

        JTextField jumlah1 = new JTextField();

        JButton button = new JButton("Beli");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    try {
                        if (Integer.valueOf(txt1.getText()) > 5) {
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
                            int jlh = Integer.valueOf(jumlah1.getText());

                            stat = con.createStatement();
                            stat.addBatch("UPDATE obat SET jumlah = jumlah - '" + jlh + "'WHERE ID = '"
                                    + Integer.valueOf(txt1.getText()) + "'");
                            stat.addBatch("INSERT INTO transaksi(ID,nama,jumlah,harga,total)" + "VALUES('"
                                    + Integer.valueOf(txt1.getText()) + "','" + txt2.getText() + "','"
                                    + Integer.valueOf(txt4.getText())  + "','" + Integer.valueOf(txt3.getText())+ "','"
                                    + Integer.valueOf(total.getText())  +"')");

                            int[] rs = stat.executeBatch();
                            stat.close();
                            JOptionPane.showMessageDialog(null, "Berhasil");
                        }

                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                }
            }
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
//                    stat = con.createStatement();
//                    String query = "SELECT id_transaksi from obat WHERE ID = '" + Integer.valueOf(txt1.getText()) + "'";
//                    stat.executeQuery(query);
//                    ResultSet rs = stat.getResultSet();
//                    while (rs.next()) {
//                        int idtransaksi = rs.getInt("id_transaksi");
//                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" yyyy-MM-dd hh.mm.ss");
                    String ldt = LocalDateTime.now().format(formatter);
                    //DateFormat ldt = new SimpleDateFormat("yyyy-MM-dd_hh.mm");

                    String sep = System.lineSeparator();
                    String str = "				Struk Belanja" +sep+
                            sep+"====================================================================" + sep +
                            sep + "Tanggal - Jam       :  " + ldt + sep +
                            sep + "ID Obat   		    :  " + txt1.getText() +
                            sep + "Nama Obat   		:  " + txt2.getText()  +
                            sep + "Jumlah   		    :  " + jumlah1.getText() + "pc" +
                            sep + "Harga Satuan		:  " + "@" + "Rp. " + txt3.getText() +
                            sep + "Total Bayar   		:  "+"Rp. " + total.getText() + sep +sep +



                            "            Terimakasih Telah Berbelanja di Apotek Terserah";

                    File newTextFile = new File("struk/struk" + ldt + ".txt");
                    FileWriter y = new FileWriter(newTextFile);
                    y.write(str);
                    y.close();

                    JOptionPane.showMessageDialog(null, "Struk Belanja Sudah Dicetak",
                            "Terima kasih", JOptionPane.PLAIN_MESSAGE);
                    txt1.setText("");
                    txt2.setText("");
                    jumlah1.setText("");
                    txt3.setText("");
                    total.setText("");


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

        JButton button4 = new JButton("Check");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                INFO();
            }
            public void INFO() {
                try {
                    stat = con.createStatement();
                    String query = "SELECT * from obat WHERE ID = '" + Integer.valueOf(txt1.getText()) + "'";
                    stat.executeQuery(query);
                    ResultSet rs = stat.getResultSet();
                    while (rs.next()) {
                        String nama = rs.getString("nama");
                        int harga = rs.getInt("harga");
                        int jumlah = rs.getInt("jumlah");
                        txt3.setText(String.valueOf(harga));
                        txt2.setText(nama);
                        txt4.setText(String.valueOf(jumlah));
                        int jumlah2 = Integer.valueOf(jumlah1.getText());
                        int total1 = jumlah2 * harga;
                        total.setText(String.valueOf(total1));
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });



        JLabel a = new JLabel("ID Obat");
        a.setSize(100, 30);
        a.setBounds(70, 50, 500, 50);

        JLabel txt5 = new JLabel("Jumlah");
        txt5.setSize(100,30);
        txt5.setBounds(70, 100, 500, 50);

        JLabel b = new JLabel("Nama Obat");
        b.setSize(100, 30);
        b.setBounds(70, 150, 500, 50);

        JLabel c = new JLabel("Harga Satuan");
        c.setSize(100, 30);
        c.setBounds(70, 200, 500, 50);

        JLabel d = new JLabel("Jumlah Stok");
        d.setSize(100, 30);
        d.setBounds(70, 250,500,50);

        JLabel e = new JLabel("Total");
        e.setSize(100, 30);
        e.setBounds(70, 300,500,50);

        txt1.setEnabled(true);
        txt1.setBounds(160, 65, 90, 25);

        jumlah1.setEnabled(true);
        jumlah1.setBounds(160, 115, 90, 25);

        txt2.setSize(100, 30);
        txt2.setBounds(160, 162, 90, 25);
        txt2.setEditable(false);

        txt3.setSize(100, 30);
        txt3.setBounds(160, 212, 90, 25);
        txt3.setEditable(false);

        txt4.setSize(100, 30);
        txt4.setBounds(160, 262,90,25);
        txt4.setEditable(false);

        total.setSize(100,30);
        total.setBounds(160,312,90,25);
        total.setEditable(false);

        button.setBounds(70, 360, 100, 25);
        button1.setBounds(210, 360, 100, 25);
        button2.setBounds(70, 405,100,25);
        button3.setBounds(70, 20, 100, 25);
        button4.setBounds(210,405,100,25);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        add(a);
        add(b);
        add(c);
        add(d);
        add(txt4);
        add(txt1);
        add(txt2);
        add(txt3);
        add(txt5);
        add(jumlah1);
        add(button);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(e);
        add(total);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }
}