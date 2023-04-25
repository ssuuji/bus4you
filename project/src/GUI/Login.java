package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.LoginDAO;
import GUI.Manager.AdminMain;
import GUI.User.UserMain;
import VO.UserVO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldId;
    private JPasswordField textFieldPssword;
    static  UserVO user = null;

    /**t
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 604, 606);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Bus4You");                 //이름
        lblNewLabel.setFont(new Font("휴먼편지체", Font.BOLD, 70));
        lblNewLabel.setBounds(205, 43, 272, 82);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_id = new JLabel("ID");                   //ID 라벨
        lblNewLabel_id.setFont(new Font("굴림", Font.BOLD, 20));
        lblNewLabel_id.setBounds(69, 179, 33, 42);
        contentPane.add(lblNewLabel_id);

        JLabel lblNewLabel_pw = new JLabel("PW");                  //PW 라벨
        lblNewLabel_pw.setFont(new Font("굴림", Font.BOLD, 20));
        lblNewLabel_pw.setBounds(69, 277, 33, 42);
        contentPane.add(lblNewLabel_pw);

        textFieldId = new JTextField();                           //ID 입력 받는 곳
        textFieldId.setFont(new Font("굴림", Font.PLAIN, 20));
        textFieldId.setBounds(150, 178, 313, 45);
        textFieldId.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
        contentPane.add(textFieldId);
        textFieldId.setColumns(10);

        textFieldPssword = new JPasswordField();                      //비밀번호 입력 받는 곳
        textFieldPssword.setBounds(150, 280, 313, 42);
        textFieldPssword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
        contentPane.add(textFieldPssword);



        JButton btnNewButton_join = new JButton("회원가입");                   //회원가입 누르면 join창 호출
        btnNewButton_join.setForeground(new Color(255, 255, 255));
        btnNewButton_join.setBackground(new Color(30, 144, 255));
        btnNewButton_join.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Join join = new Join();
                join.setVisible(true);
                join.setLocationRelativeTo(null);
            }
        });
        btnNewButton_join.setFont(new Font("굴림", Font.BOLD, 18));
        btnNewButton_join.setBounds(150, 388, 117, 57);
        contentPane.add(btnNewButton_join);


        JButton btnNewButton_login = new JButton("로그인");
        btnNewButton_login.setForeground(new Color(255, 255, 255));
        btnNewButton_login.setBackground(new Color(30, 144, 255));
        btnNewButton_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userid = textFieldId.getText();
                char[] passwordChars = textFieldPssword.getPassword();
                String password = new String(passwordChars);

                try {
                    LoginDAO loginDAO = new LoginDAO();
                    user = loginDAO.login(userid, password);
                    if (user != null) { // DAO의 로그인 메서드 호출
                        if(user.getIsManager() == 1) {
                            // manager
                            AdminMain adminMain = new AdminMain();
                            adminMain.setVisible(true);
                            dispose();
                        }else {
                            // user
                            UserMain userMain = new UserMain(user);
                            userMain.setVisible(true);
                            dispose();
                        }
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인해주세요");
                    }

                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } // DAO 객체 생성

            }
        });

        btnNewButton_login.setFont(new Font("굴림", Font.BOLD, 18));
        btnNewButton_login.setBounds(348, 388, 117, 57);
        contentPane.add(btnNewButton_login);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\osj\\Desktop\\dddd\\asd.png"));
        lblNewLabel_1.setBounds(69, 32, 130, 109);
        contentPane.add(lblNewLabel_1);
    }
}