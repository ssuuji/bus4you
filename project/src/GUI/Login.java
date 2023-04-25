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
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField textField_id;
    private JPasswordField passwordField;
    static  UserVO user = null;

    /**
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
        setBounds(100, 100, 500, 340);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Bus4You");                 //이름
        lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
        lblNewLabel.setBounds(185, 10, 204, 42);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_id = new JLabel("ID");                   //ID 라벨
        lblNewLabel_id.setFont(new Font("굴림", Font.BOLD, 20));
        lblNewLabel_id.setBounds(59, 80, 33, 42);
        contentPane.add(lblNewLabel_id);

        JLabel lblNewLabel_pw = new JLabel("PW");                  //PW 라벨
        lblNewLabel_pw.setFont(new Font("굴림", Font.BOLD, 20));
        lblNewLabel_pw.setBounds(59, 156, 33, 42);
        contentPane.add(lblNewLabel_pw);

        textField_id = new JTextField();                           //ID 입력 받는 곳
        textField_id.setFont(new Font("굴림", Font.PLAIN, 20));
        textField_id.setBounds(104, 81, 313, 45);
        contentPane.add(textField_id);
        textField_id.setColumns(10);

        passwordField = new JPasswordField();                      //비밀번호 입력 받는 곳
        passwordField.setBounds(104, 159, 313, 42);
        contentPane.add(passwordField);



        JButton btnNewButton_join = new JButton("회원가입");                   //회원가입 누르면 join창 호출
        btnNewButton_join.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                Join join_1 = new Join();           //
                
                join_1.setVisible(true);
                join_1.setLocationRelativeTo(null);
                
            }
        });
        btnNewButton_join.setFont(new Font("굴림", Font.PLAIN, 18));
        btnNewButton_join.setBounds(104, 235, 117, 57);
        contentPane.add(btnNewButton_join);


        JButton btnNewButton_login = new JButton("로그인");
        btnNewButton_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userid = textField_id.getText();
                char[] passwordChars = passwordField.getPassword();
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
                            System.out.println("손님");
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
        
      


        btnNewButton_login.setFont(new Font("굴림", Font.PLAIN, 18));
        btnNewButton_login.setBounds(287, 235, 117, 57);
        contentPane.add(btnNewButton_login);

    }
}