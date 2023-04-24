package GUI.User;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DAO.UserDAO;
import GUI.Login;
import VO.UserVO;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserMain extends JFrame {

	UserDAO user = new UserDAO();
	
	//출발지, 목적지 콤보박스
	private JComboBox<String> startCombo;
	private JComboBox<String> arriveCombo;
	//날짜 텍스트필드
	private JTextField textDate;
	
	//포인트
	private JTextField textMyPoint;
	
	
	//출발지,목적지 설정
	String[] location = {"서울", "대구", "부산"};
	

	
	private JPanel contentPane;
	
	/**
	 * Create the frame. 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public UserMain(UserVO userVO) throws ClassNotFoundException, SQLException{
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRoute = new JLabel("노선조회");
		lblRoute.setFont(new Font("굴림", Font.PLAIN, 30));
		lblRoute.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoute.setBounds(220, 60, 148, 45);
		contentPane.add(lblRoute);
		
	
		
		
		JLabel lblStart = new JLabel("출발지");
		lblStart.setFont(new Font("굴림", Font.PLAIN, 20));
		lblStart.setBounds(117, 128, 69, 24);
		contentPane.add(lblStart);
		
		
		startCombo = new JComboBox(location);
		startCombo.setBounds(198, 117, 221, 50);
		contentPane.add(startCombo);	
		
		JLabel lblArrive = new JLabel("목적지");
		lblArrive.setFont(new Font("굴림", Font.PLAIN, 20));
		lblArrive.setBounds(117, 187, 69, 24);
		contentPane.add(lblArrive);
		
		arriveCombo = new JComboBox(location);
		arriveCombo.setBounds(198, 176, 221, 50);
		contentPane.add(arriveCombo);
		
		
		
		
		
		JLabel lblDate = new JLabel("날짜");
		lblDate.setFont(new Font("굴림", Font.PLAIN, 20));
		lblDate.setBounds(117, 246, 51, 24);
		contentPane.add(lblDate);
		
		textDate = new JTextField();
		textDate.setBounds(198, 235, 221, 51);
		contentPane.add(textDate);
		textDate.setColumns(10);
		
		
		/*
		 	조회버튼
		*/
		JButton btnCheck = new JButton("조회");
		btnCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				String startlocation = startCombo.getSelectedItem().toString();
				String arrivelocation = arriveCombo.getSelectedItem().toString();
				String boardingdate = textDate.getText();
				
				UserFindRoute find;
				
				//출발지 = 목적지
				if(startlocation.equals(arrivelocation)) {
					JOptionPane.showMessageDialog(UserMain.this,"출발지와 목적지가 같습니다!","error",JOptionPane.ERROR_MESSAGE);
				} else {
					if(boardingdate.equals("")) {//날짜 입력 안했을때 경고창
						JOptionPane.showMessageDialog(UserMain.this, "날짜를 입력해주세요.","error",JOptionPane.ERROR_MESSAGE);
					}
					try {
						find = new UserFindRoute(startlocation,arrivelocation,boardingdate);
						find.setVisible(true);	
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}				
			}
		});
		btnCheck.setBounds(220, 293, 125, 36);
		contentPane.add(btnCheck);
		
		JButton btnMyreservation = new JButton("나의 예매티켓 확인");
		btnMyreservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


			}
		});
		btnMyreservation.setBounds(89, 354, 364, 45);
		contentPane.add(btnMyreservation);
		
		
		/*
		 	포인트 충전하기
		*/
		JLabel lblPoint = new JLabel("잔여 포인트");
		lblPoint.setFont(new Font("굴림", Font.PLAIN, 17));
		lblPoint.setBounds(101, 438, 85, 24);
		contentPane.add(lblPoint);
		
		
		
		JButton btnCharge = new JButton("충전하기");
		btnCharge.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//int point = Integer.parseInt(textMyPoint.getText());
				
				int point = user.chargePoint(userVO.getId());

			
				textMyPoint.setText(String.valueOf(point));
				
			}
		});
		btnCharge.setBounds(220, 479, 125, 36);
		contentPane.add(btnCharge);
		
		textMyPoint = new JTextField();
		textMyPoint.setText(String.valueOf(userVO.getPoint()));
		textMyPoint.setEditable(false); // 수정 못하게
		textMyPoint.setBounds(198, 439, 221, 25);
		contentPane.add(textMyPoint);
		textMyPoint.setColumns(10);
		/*
		     로그아웃
		*/
		JButton btblogout = new JButton("로그아웃");
		btblogout.setFont(new Font("굴림", Font.PLAIN, 15));
		btblogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose(); //UserMain 닫고
				
				Login login =new Login();
				login.setVisible(true); //Login 화면이동
			}
		});
		btblogout.setBounds(469, 519, 107, 36);
		contentPane.add(btblogout);
		
	}
}
