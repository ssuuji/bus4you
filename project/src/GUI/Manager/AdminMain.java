package GUI.Manager;


import GUI.Login;
import GUI.Manager.BUS.AdminFindAllBuses;
import GUI.Manager.ROUTE.AdminFindRoutes;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.ImageIcon;

public class AdminMain extends JFrame {

    private JPanel contentPane;
    private JButton btnBus;
    private JButton btnUser;
    private JButton btnRoute;
    private JButton btnLogout;
    private JLabel lblNewLabel_1;
    /**
     * Create the frame.
     */
    public AdminMain() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 605, 606);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("관리자 페이지");
        lblNewLabel.setBounds(189, 10, 238, 83);
        lblNewLabel.setFont(new Font("굴림", Font.BOLD, 35));
        contentPane.add(lblNewLabel);

        btnBus = new JButton("회사 버스 조회");
        btnBus.setForeground(new Color(255, 255, 255));
        btnBus.setBackground(new Color(30, 144, 255));
        btnBus.setFont(new Font("굴림", Font.BOLD, 18));
        btnBus.setBounds(64, 383, 193, 61);
        btnBus.addActionListener(new ActionListener() {
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
        contentPane.add(btnBus);


        btnUser = new JButton("회원 목록 조회");
        btnUser.setForeground(new Color(255, 255, 255));
        btnUser.setBackground(new Color(30, 144, 255));
        btnUser.setFont(new Font("굴림", Font.BOLD, 18));
        btnUser.setBounds(338, 383, 193, 61);
        btnUser.addActionListener(new ActionListener() {
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
        contentPane.add(btnUser);

        btnRoute = new JButton("노선 조회");
        btnRoute.setForeground(new Color(255, 255, 255));
        btnRoute.setBackground(new Color(30, 144, 255));
        btnRoute.setFont(new Font("굴림", Font.BOLD, 18));
        btnRoute.setBounds(64, 470, 193, 61);
        btnRoute.addActionListener(new ActionListener() {
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
        contentPane.add(btnRoute);

        btnLogout = new JButton("로그아웃");
        btnLogout.setForeground(new Color(255, 255, 255));
        btnLogout.setBackground(new Color(30, 144, 255));
        btnLogout.setFont(new Font("굴림", Font.BOLD, 18));
        btnLogout.setBounds(338, 470, 193, 61);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnLogout);
        
        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(AdminMain.class.getResource("/IMAGE/qjtm.png")));
        lblNewLabel_1.setBounds(88, 82, 385, 302);
        contentPane.add(lblNewLabel_1);
    }
}