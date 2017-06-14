import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class login extends JFrame {

    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;


    public login() {

        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;

        setContentPane(new JLabel(new ImageIcon("bg/bg1.jpg")));
        JTextField txt1 = new JTextField();
        JPasswordField txt2 = new JPasswordField();
        JButton button = new JButton("Login");
        getRootPane().setDefaultButton(button);
        getContentPane().setBackground(new Color(0,255,255));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    try {
                        sql = "SELECT * FROM login WHERE username='" + txt1.getText() + "' AND password='" + txt2.getText() + "'";
                        rs = stat.executeQuery(sql);
                        if (rs.next())
                        {
                            menu a = new menu();
                            dispose();
                            //JOptionPane.showMessageDialog(null, "berhasil login");
                        }
                        else if (txt1.getText().isEmpty() && txt2.getText().isEmpty()) {

                            JOptionPane.showMessageDialog(null, "username dan password belum diisi");
                        } else {
                            JOptionPane.showMessageDialog(null, "username atau password salah");
                        }
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(null, "salah");
                    }
                }
            }
        });


        JLabel aa = new JLabel("Masukan username dan password Anda.");
        aa.setBounds(70, 20, 500, 50);

        JLabel a = new JLabel("Username  :");
        a.setSize(100, 30);
        a.setBounds(70, 50, 500, 50);

        JLabel b = new JLabel("Password  :");
        b.setSize(100, 30);
        b.setBounds(70, 100, 500, 50);

        txt1.setEnabled(true);
        txt2.setEnabled(true);
        txt1.setBounds(160, 65, 150, 25);
        txt2.setBounds(160, 115, 150, 25);
        button.setBounds(70, 180, 100, 25);


        setVisible(true);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        add(aa);
        add(a);
        add(b);
        add(txt1);
        add(txt2);
        add(button);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
