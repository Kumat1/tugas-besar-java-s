import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class home extends JFrame
{


    public home(){
        JPanel p = new JPanel();

        getContentPane().setBackground(new Color(0,255,255));


        //  Menentukan title bar di Frame
        setTitle("Tugas Besar");

        //  Menentukan ukuran Frame
        setSize(600, 500);

        //  Membuat letak Frame berada di tengat ketika program dijalankan
        setLocationRelativeTo(null);

        //  Mengaktifkan event agar ketika frame di close maka, program akan berhenti
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //  [Optional] Untuk menonaktifkan button maximize di Frame
        setResizable(false);

        //  Aktifkan Visible Frame
        setVisible(true);

        //  Membuat Objek JLabel
        JLabel label = new JLabel("WELCOME TO APOTEK TERSERAH");

        label.setFont(new Font("Century Gothic",Font.BOLD,25));

        //  Menentukan layout Frame
        setLayout(null);

        //  Memasukkan Label ke dalam Frame
        add(label);

        //  Menentukan posisi label di dalam Frame. Menentukan Width dan Height
        label.setBounds(115,50, 500, 100);   //  20 = posisi X; 20 = posisi Y; 100 = Width; 30; Height = 30

        //  Membuat Objek Button
        JButton button = new JButton("Login");
        getRootPane().setDefaultButton(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                dispose();
            }
        });

        //  Memasukkan Button ke dalam Frame
        add(button);

        //  Menentukan posisi Button di dalam Frame
        button.setBounds(255, 200, 100, 50);
    }
    public static void main(String[] args)
    {
        home h = new home();

        h.setBackground(Color.CYAN);

    }
}