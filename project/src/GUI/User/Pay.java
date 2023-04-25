package GUI.User;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.UserDAO;
import VO.RouteVO;
import VO.UserVO;

import javax.accessibility.AccessibleContext;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

// 결제화면
public class Pay extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private int index;
	private SeatSelect ss;
	
	public Pay(UserVO uv, RouteVO rv, int index, SeatSelect ss, JTextField textMyPoint) throws SQLException {
		
		this.index = index; // 인덱스 값을 저장
		this.ss = ss; // SeatSelect 클래스의 인스턴스를 저장
		
		System.out.println(rv.getBoardingDate());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("결제정보");
		lblNewLabel.setBounds(109, 10, 203, 53);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("출발지 : " + rv.getStartLocation());
		lblNewLabel_2.setBounds(148, 61, 182, 34);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("도착지 : " + rv.getArriveLocation());
		lblNewLabel_3.setBounds(148, 106, 203, 34);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("시간 : " + rv.getBoardingDate());
		lblNewLabel_4.setBounds(148, 150, 221, 34);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("금액 : " + rv.getFee());
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setBounds(148, 194, 155, 27);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("결제");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(uv.getPoint() <= rv.getFee()) {
					JOptionPane.showMessageDialog(null, "포인트가 부족합니다. 충전해주세요.");
					dispose();
				} else {
					
					UserDAO userDao;
					try {
						userDao = new UserDAO();
						int res = userDao.insertReservation(rv, uv.getId(), index);
						
						if(res > 0) {
							ss.getSelectedButton().setEnabled(false);
							ss.getSelectedButton().setBackground(Color.BLACK);
							userDao.updatePoint(uv.getId(), -rv.getFee());
							uv.setPoint(uv.getPoint() - rv.getFee());
							ss.refresh(rv, uv, textMyPoint);
							textMyPoint.setText(String.valueOf(uv.getPoint()));
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "오류가 발생했습니다!");
							dispose();
						}
						
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
		btnNewButton.setBounds(106, 305, 119, 53);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(237, 305, 119, 53);
		contentPane.add(btnNewButton_1);
	}
	
}
