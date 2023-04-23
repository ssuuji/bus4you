package GUI;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.JoinDAO;
import VO.UserVO;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class Join extends JFrame {

    private JPanel contentPane;
    private JTextField textField_id;
    private JTextField textField_pw;
    private JTextField textField_name;
    private JTextField textField_phone;


    /**
     * Create the frame.
     */
    public Join() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 592, 433);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("회원가입");
        lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
        lblNewLabel.setBounds(198, 10, 134, 51);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_id = new JLabel("ID");
        lblNewLabel_id.setFont(new Font("굴림", Font.BOLD, 20));
        lblNewLabel_id.setBounds(62, 78, 38, 51);
        contentPane.add(lblNewLabel_id);

        JLabel lblNewLabel_pw = new JLabel("PW");
        lblNewLabel_pw.setFont(new Font("굴림", Font.BOLD, 20));
        lblNewLabel_pw.setBounds(62, 135, 38, 51);
        contentPane.add(lblNewLabel_pw);

        JLabel lblNewLabel_name = new JLabel("이름");
        lblNewLabel_name.setFont(new Font("굴림", Font.BOLD, 20));
        lblNewLabel_name.setBounds(62, 192, 55, 51);
        contentPane.add(lblNewLabel_name);

        JLabel lblNewLabel_phone = new JLabel("핸드폰 번호");
        lblNewLabel_phone.setFont(new Font("굴림", Font.BOLD, 20));
        lblNewLabel_phone.setBounds(33, 251, 123, 51);
        contentPane.add(lblNewLabel_phone);

        textField_id = new JTextField();
        textField_id.setBounds(157, 85, 271, 42);
        contentPane.add(textField_id);
        textField_id.setColumns(10);

        textField_pw = new JTextField();
        textField_pw.setColumns(10);
        textField_pw.setBounds(157, 142, 271, 42);
        contentPane.add(textField_pw);

        textField_name = new JTextField();
        textField_name.setColumns(10);
        textField_name.setBounds(157, 199, 271, 42);
        contentPane.add(textField_name);

        textField_phone = new JTextField();
        textField_phone.setColumns(10);
        textField_phone.setBounds(157, 258, 271, 42);
        contentPane.add(textField_phone);

        JButton btnNewButton = new JButton("중복확인");                                //ID를 입력받으면 DB에 ID가 중복되는게 있나 확인 후 가능 불가능 출력
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userid = textField_id.getText();
                if (userid.trim().isEmpty()) { // 빈 문자열인 경우
                    JOptionPane.showMessageDialog(null, "아이디를 입력해주세요."); // 메시지 출력
                    return; // 메소드를 종료합니다.
                }

                try {
                    JoinDAO joinDAO = new JoinDAO();
                    boolean checkResult = joinDAO.Check(userid);
                    JOptionPane.showMessageDialog(null, (checkResult) ? "사용 중인 아이디 입니다." : "사용 가능한 아이디 입니다.");
                }catch (ClassNotFoundException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        btnNewButton.setBounds(450, 84, 102, 42);
        contentPane.add(btnNewButton);

        JButton btnNewButton_go = new JButton("등록");                        //등록버튼 누르면 화면에 입력된 id pw 이름 핸드폰번호 그리고 point 10000원이 DB에 등록됨
        btnNewButton_go.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String userid = textField_id.getText(); // 아이디
                String pw = textField_pw.getText(); // 비밀번호
                String name = textField_name.getText(); // 이름
                String phone = textField_phone.getText(); // 휴대폰 번호

                if(userid.isEmpty()) {   // 전체 다 입력되었는지 확인
                    JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
                    return;
                }
                if(pw.isEmpty()) {   // 전체 다 입력되었는지 확인
                    JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
                    return;
                }
                if(name.isEmpty()) {   // 전체 다 입력되었는지 확인
                    JOptionPane.showMessageDialog(null, "이름를 입력해주세요.");
                    return;
                }
                if(phone.isEmpty()) {   // 전체 다 입력되었는지 확인
                    JOptionPane.showMessageDialog(null, "핸드폰 번호를 입력해주세요.");
                    return;
                }

                try {
                    JoinDAO joinDAO = new JoinDAO();
                    boolean b1 = joinDAO.Join_insert(userid, pw, name, phone); // 데이터 삽입 시도

                    if(b1) {
                        JOptionPane.showMessageDialog(null, "회원 등록이 완료되었습니다.");
                    } else {
                        JOptionPane.showMessageDialog(null, "회원 등록에 실패했습니다.");
                    }

                } catch (ClassNotFoundException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }


                //    Logger.getLogger(Join_1.class.getName()).log(Level.SEVERE, null, ex);

                dispose();
            }
        });
        btnNewButton_go.setBounds(157, 321, 102, 42);
        contentPane.add(btnNewButton_go);

        JButton btnNewButton_cancel = new JButton("취소");                  //취소버튼 누를시 창 닫기
        btnNewButton_cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton_cancel.setBounds(326, 321, 102, 42);
        contentPane.add(btnNewButton_cancel);
    }

}
