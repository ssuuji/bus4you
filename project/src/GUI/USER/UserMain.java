package GUI.User;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DAO.UserDAO;
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
		lblRoute.setBounds(198, 59, 148, 45);
		contentPane.add(lblRoute);
		
	
		
		
		JLabel lblStart = new JLabel("출발지");
		lblStart.setFont(new Font("굴림", Font.PLAIN, 20));
		lblStart.setBounds(117, 128, 69, 24);
		contentPane.add(lblStart);
		
		
		startCombo = new JComboBox(location);
		startCombo.setBounds(198, 114, 221, 50);
		contentPane.add(startCombo);	
		
		JLabel lblArrive = new JLabel("목적지");
		lblArrive.setFont(new Font("굴림", Font.PLAIN, 20));
		lblArrive.setBounds(117, 187, 69, 24);
		contentPane.add(lblArrive);
		
		arriveCombo = new JComboBox(location);
		arriveCombo.setBounds(198, 173, 221, 50);
		contentPane.add(arriveCombo);
		
		
		
		
		
		JLabel lblDate = new JLabel("날짜");
		lblDate.setFont(new Font("굴림", Font.PLAIN, 20));
		lblDate.setBounds(117, 246, 51, 24);
		contentPane.add(lblDate);
		
		textDate = new JTextField();
		textDate.setBounds(198, 230, 221, 53);
		contentPane.add(textDate);
		textDate.setColumns(10);
		
		
		/*
		 	조회버튼
		*/
		JButton btnCheck = new JButton("조회");
		btnCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//출발지 != 목적지
				
				
				
				String startlocation = startCombo.getSelectedItem().toString();
				String arrivelocation = arriveCombo.getSelectedItem().toString();
				String boardingdate = textDate.getText();
				
				UserFindRoute find;
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
		});
		btnCheck.setBounds(198, 293, 125, 36);
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
		lblPoint.setFont(new Font("굴림", Font.PLAIN, 20));
		lblPoint.setBounds(89, 437, 125, 24);
		contentPane.add(lblPoint);
		
		
		
		JButton btnCharge = new JButton("충전하기");
		btnCharge.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//int point = Integer.parseInt(textMyPoint.getText());
				
				int point = user.chargePoint(userVO.getId()); //로그인 합치고나서 아이디 ,,내리기

			
				textMyPoint.setText(String.valueOf(point));
				
			}
		});
		btnCharge.setBounds(198, 479, 125, 36);
		contentPane.add(btnCharge);
		
		textMyPoint = new JTextField();
		textMyPoint.setText(String.valueOf(userVO.getPoint()));// 아이디,,
		textMyPoint.setEditable(false); // 수정 못하게
		textMyPoint.setBounds(226, 437, 193, 25);
		contentPane.add(textMyPoint);
		textMyPoint.setColumns(10);
		
	}
}
