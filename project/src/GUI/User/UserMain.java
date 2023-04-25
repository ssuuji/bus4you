package GUI.User;

import java.awt.Font;

import javax.swing.BorderFactory;
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
import java.awt.Color;

public class UserMain extends JFrame {

    private JPanel contentPane;
    private JComboBox<String> startCombo;
    private JComboBox<String> arriveCombo;
    private JTextField textDate;
    private JLabel jLabelRoute;
    private JLabel jLabelStart;
    private JLabel jLabelArrive;
    private JLabel jLabelDate;
    private JTextField textMyPoint;
    private JButton btnCheck;
    private JButton btnCharge;
    private JButton btnLogout;
    private JButton btnMyReservation;

    // 출발지,목적지 설정
    String[] location = { "서울","인천","대전","평양", "대구", "부산" };


	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public UserMain(UserVO userVO) throws ClassNotFoundException, SQLException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 604);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		jLabelRoute = new JLabel("노선조회");
		jLabelRoute.setFont(new Font("굴림", Font.PLAIN, 30));
		jLabelRoute.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelRoute.setBounds(221, 84, 148, 45);
		contentPane.add(jLabelRoute);

		jLabelStart = new JLabel("출발지");
		jLabelStart.setFont(new Font("굴림", Font.PLAIN, 20));
		jLabelStart.setBounds(111, 161, 69, 24);
		contentPane.add(jLabelStart);

		startCombo = new JComboBox(location);
		startCombo.setBackground(new Color(255, 255, 255));
		startCombo.setBounds(195, 148, 221, 50);
		contentPane.add(startCombo);

		jLabelArrive = new JLabel("목적지");
		jLabelArrive.setFont(new Font("굴림", Font.PLAIN, 20));
		jLabelArrive.setBounds(109, 232, 69, 24);
		contentPane.add(jLabelArrive);

		arriveCombo = new JComboBox(location);
		arriveCombo.setBackground(new Color(255, 255, 255));
		arriveCombo.setBounds(198, 219, 221, 50);
		contentPane.add(arriveCombo);

		jLabelDate = new JLabel("날짜");
		jLabelDate.setFont(new Font("굴림", Font.PLAIN, 20));
		jLabelDate.setBounds(117, 303, 51, 24);
		contentPane.add(jLabelDate);

		textDate = new JTextField();
		textDate.setBounds(198, 292, 221, 51);
		textDate.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
		contentPane.add(textDate);
		textDate.setColumns(10);

		/*
		 * 조회버튼
		 */
		btnCheck = new JButton("조회");
		btnCheck.setForeground(new Color(255, 255, 255));
		btnCheck.setFont(new Font("굴림", Font.BOLD, 18));
		btnCheck.setBackground(new Color(30, 144, 255));
		btnCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String startLocation = startCombo.getSelectedItem().toString();
				String arriveLocation = arriveCombo.getSelectedItem().toString();
				String boardingDate = textDate.getText();


				// 출발지 = 목적지
				if (startLocation.equals(arriveLocation)) {
					JOptionPane.showMessageDialog(UserMain.this, "출발지와 목적지가 같습니다!", "error", JOptionPane.ERROR_MESSAGE);
				}
                else if (boardingDate.equals("")) {// 날짜 입력 안했을때 경고창
						JOptionPane.showMessageDialog(UserMain.this, "날짜를 입력해주세요.", "error", JOptionPane.ERROR_MESSAGE);
					}
                else {
                    try {
                        UserFindRoute find = new UserFindRoute(startLocation, arriveLocation, boardingDate, userVO, textMyPoint);
                        find.setVisible(true);
                    } catch (ClassNotFoundException | SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
			}
		});
		btnCheck.setBounds(220, 370, 125, 36);
		contentPane.add(btnCheck);

		btnMyReservation = new JButton("나의 예매티켓 확인");
		btnMyReservation.setForeground(new Color(255, 255, 255));
		btnMyReservation.setFont(new Font("굴림", Font.BOLD, 18));
		btnMyReservation.setBackground(new Color(30, 144, 255));
		btnMyReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					TicketCheck ticketcheck = new TicketCheck(userVO);
					ticketcheck.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnMyReservation.setBounds(231, 16, 196, 45);
		contentPane.add(btnMyReservation);

		/*
		 * 포인트 충전하기
		 */
		JLabel lblPoint = new JLabel("내 포인트");
		lblPoint.setFont(new Font("굴림", Font.PLAIN, 17));
		lblPoint.setBounds(101, 438, 85, 24);
		contentPane.add(lblPoint);

		btnCharge = new JButton("충전하기");
		btnCharge.setForeground(new Color(255, 255, 255));
		btnCharge.setFont(new Font("굴림", Font.BOLD, 18));
		btnCharge.setBackground(new Color(30, 144, 255));
		btnCharge.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                try {
                    int point = new UserDAO().chargePoint(userVO.getId());
                    textMyPoint.setText(String.valueOf(point));
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
			}
		});
		btnCharge.setBounds(220, 479, 125, 36);
		contentPane.add(btnCharge);

		textMyPoint = new JTextField();
		textMyPoint.setBackground(new Color(255, 255, 255));
		textMyPoint.setText(String.valueOf(userVO.getPoint()));
		textMyPoint.setEditable(false); // 수정 못하게
		textMyPoint.setBounds(198, 439, 221, 25);
		contentPane.add(textMyPoint);
		textMyPoint.setColumns(10);
		/*
		 * 로그아웃
		 */
		btnLogout = new JButton("로그아웃");
		btnLogout.setForeground(new Color(255, 255, 255));
		btnLogout.setBackground(new Color(30, 144, 255));
		btnLogout.setFont(new Font("굴림", Font.BOLD, 18));
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose(); // UserMain 닫고
				Login login = new Login();
				login.setVisible(true); // Login 화면이동
			}
		});
		btnLogout.setBounds(441, 17, 125, 45);
		contentPane.add(btnLogout);
	}
}