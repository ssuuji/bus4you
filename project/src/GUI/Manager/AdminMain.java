package GUI.Manager;


import GUI.Login;
import GUI.Manager.BUS.AdminFindAllBuses;
import GUI.Manager.ROUTE.AdminFindRoutes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AdminMain extends JFrame {

    private JPanel contentPane;

//    /**
//     * Launch the application.
//     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    AdminMain frame = new AdminMain();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    /**
     * Create the frame.
     */
    public AdminMain() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 340);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("관리자 페이지");
        lblNewLabel.setBounds(136, 10, 197, 53);
        lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("회사 버스 조회");
        btnNewButton.setBounds(163, 73, 151, 44);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    AdminFindAllBuses adminFindAllBuses = new AdminFindAllBuses();
                    adminFindAllBuses.setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        contentPane.add(btnNewButton);


        JButton btnNewButton_1 = new JButton("회원 목록 조회");
        btnNewButton_1.setBounds(163, 127, 151, 44);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    AdminFindAllUsers adminFindAllUsers = new AdminFindAllUsers();
                    adminFindAllUsers.setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        contentPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("노선 조회");
        btnNewButton_2.setBounds(163, 184, 151, 44);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    AdminFindRoutes adminFindRoutes = new AdminFindRoutes();
                adminFindRoutes.setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        contentPane.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("로그아웃");
        btnNewButton_3.setBounds(163, 239, 151, 44);
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnNewButton_3);
    }

}