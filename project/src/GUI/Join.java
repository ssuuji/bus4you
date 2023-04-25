package GUI;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.JoinDAO;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Join extends JDialog {

    private JPanel contentPane;
    private JTextField textFieldId;
    private JTextField textFieldPassword;
    private JTextField textFieldName;
    private JTextField textFieldPhone;
    boolean overlap=false;
    
    /**
     * Create the frame.
     */
    public Join() {
        setModal(true); // 모달 대화상자로 설정
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 605, 605);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("회원가입");
        lblNewLabel.setFont(new Font("굴림", Font.BOLD, 60));
        lblNewLabel.setBounds(186, 10, 271, 86);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_id = new JLabel("ID");
        lblNewLabel_id.setFont(new Font("굴림", Font.BOLD, 20));
        lblNewLabel_id.setBounds(69, 131, 38, 51);
        contentPane.add(lblNewLabel_id);

        JLabel lblNewLabel_pw = new JLabel("PW");
        lblNewLabel_pw.setFont(new Font("굴림", Font.BOLD, 20));
        lblNewLabel_pw.setBounds(62, 214, 38, 51);
        contentPane.add(lblNewLabel_pw);

        JLabel lblNewLabel_name = new JLabel("이름");
        lblNewLabel_name.setFont(new Font("굴림", Font.BOLD, 20));
        lblNewLabel_name.setBounds(60, 296, 55, 51);
        contentPane.add(lblNewLabel_name);

        JLabel lblNewLabel_phone = new JLabel("핸드폰 번호");
        lblNewLabel_phone.setFont(new Font("굴림", Font.BOLD, 20));
        lblNewLabel_phone.setBounds(36, 368, 123, 51);
        contentPane.add(lblNewLabel_phone);

        textFieldId = new JTextField();
        textFieldId.setBounds(169, 138, 271, 42);
        textFieldId.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
        contentPane.add(textFieldId);
        textFieldId.setColumns(10);

        textFieldPassword = new JTextField();
        textFieldPassword.setColumns(10);
        textFieldPassword.setBounds(169, 221, 271, 42);
        textFieldPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
        contentPane.add(textFieldPassword);

        textFieldName = new JTextField();
        textFieldName.setColumns(10);
        textFieldName.setBounds(169, 297, 271, 42);
        textFieldName.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
        contentPane.add(textFieldName);

        textFieldPhone = new JTextField();
        textFieldPhone.setColumns(10);
        textFieldPhone.setBounds(169, 375, 271, 42);
        textFieldPhone.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
        contentPane.add(textFieldPhone);

        JButton btnNewButton = new JButton("중복확인");                                //ID를 입력받으면 DB에 ID가 중복되는게 있나 확인 후 가능 불가능 출력
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setFont(new Font("굴림", Font.BOLD, 18));
        btnNewButton.setBackground(new Color(30, 144, 255));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userid = textFieldId.getText();
                if (userid.trim().isEmpty()) { // 빈 문자열인 경우
                    JOptionPane.showMessageDialog(null, "아이디를 입력해주세요."); // 메시지 출력
                    return; // 메소드를 종료합니다.
                }

                try {
                    JoinDAO joinDAO = new JoinDAO();
                    boolean checkResult = joinDAO.Check(userid);
                    JOptionPane.showMessageDialog(null, (checkResult) ? "사용 중인 아이디 입니다." : "사용 가능한 아이디 입니다.");
                    overlap = (checkResult) ? false:true;
                }catch (ClassNotFoundException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        btnNewButton.setBounds(450, 138, 123, 42);
        contentPane.add(btnNewButton);

        JButton btnNewButton_go = new JButton("등록");                        //등록버튼 누르면 화면에 입력된 id pw 이름 핸드폰번호 그리고 point 10000원이 DB에 등록됨
        btnNewButton_go.setFont(new Font("굴림", Font.BOLD, 18));
        btnNewButton_go.setForeground(new Color(255, 255, 255));
        btnNewButton_go.setBackground(new Color(30, 144, 255));
        btnNewButton_go.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String userid = textFieldId.getText(); // 아이디
                String pw = textFieldPassword.getText(); // 비밀번호
                String name = textFieldName.getText(); // 이름
                String phone = textFieldPhone.getText(); // 휴대폰 번호

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
                
                if(overlap) {
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
                else {
                	JOptionPane.showMessageDialog(null, "중복 확인을 눌르세요");
                }
                
            }
        });
        btnNewButton_go.setBounds(169, 453, 102, 42);
        contentPane.add(btnNewButton_go);

        JButton btnNewButton_cancel = new JButton("취소");
        btnNewButton_cancel.setFont(new Font("굴림", Font.BOLD, 18));
        btnNewButton_cancel.setForeground(new Color(255, 255, 255));
        btnNewButton_cancel.setBackground(new Color(30, 144, 255));
        btnNewButton_cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
            }
        });
        btnNewButton_cancel.setBounds(340, 453, 102, 42);
        contentPane.add(btnNewButton_cancel);
    }

}
