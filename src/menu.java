import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.CYAN;

public class menu {
    public menu()
    {
        JFrame menu = new JFrame();

        menu.setContentPane(new JLabel(new ImageIcon("bg/bg1.jpg")));
        menu.getContentPane().setBackground(new Color(0,255,255));
        menu.setTitle("Menu");
        menu.setSize(600, 500);
        menu.setLocationRelativeTo(null);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setResizable(false);
        menu.setLayout(null);
        JLabel label = new JLabel("MENU");
        label.setFont(new Font("Century Gothic",Font.BOLD,25));
        label.setBounds(270,50, 500, 100);
        menu.add(label);


        JButton data = new JButton("Data Obat");
        data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new dataobat();
            }
        });

        JButton transaksi = new JButton("Transaksi");
        transaksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new transaksi();
                menu.dispose();
            }
        });

        JButton insert = new JButton("Insert Obat");
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new insert();
                menu.dispose();
            }
        });

        JButton update = new JButton("Update Obat");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new update();
                menu.dispose();
            }
        });

        JButton delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new delete();
                menu.dispose();
            }
        });

       JButton logout = new JButton("Logout");
       logout.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               new home();
               menu.dispose();
           }
       });


        menu.add(data);
        data.setBounds(170, 200, 110, 50);
        menu.add(transaksi);
        transaksi.setBounds(340, 200, 110, 50);
        menu.add(insert);
        insert.setBounds(170, 260, 110, 50);
        menu.add(update);
        update.setBounds(340, 260, 110, 50);
        menu.add(delete);
        delete.setBounds(170, 320, 110, 50);
        menu.add(logout);
        logout.setBounds(340, 320, 110, 50);


        menu.setVisible(true);
    }


}