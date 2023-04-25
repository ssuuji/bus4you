package GUI.User;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;

import DAO.UserDAO;
import VO.RouteVO;
import VO.UserVO;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

// 결제화면
public class Pay extends JFrame {

	private JPanel contentPane;
	private JLabel jLabelInfo;
	private JLabel jLabelStart;
	private JLabel jLabelArrive;
	private JLabel jLabelTime;
	private JLabel jLabelFee;
	private JButton btnPay;
	private JButton btnCancel;
	public Pay(UserVO uv, RouteVO rv, int index, SeatSelect ss, JTextField textMyPoint) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 405);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		jLabelInfo = new JLabel("결제정보");
		jLabelInfo.setBounds(109, 10, 203, 53);
		jLabelInfo.setFont(new Font("굴림", Font.BOLD, 18));
		jLabelInfo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(jLabelInfo);
		
		jLabelStart = new JLabel("출발지 : " + rv.getStartLocation());
		jLabelStart.setBounds(148, 61, 182, 34);
		jLabelStart.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(jLabelStart);
		
		jLabelArrive = new JLabel("도착지 : " + rv.getArriveLocation());
		jLabelArrive.setBounds(148, 106, 203, 34);
		jLabelArrive.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(jLabelArrive);
		
		jLabelTime = new JLabel("시간 : " + rv.getBoardingDate());
		jLabelTime.setBounds(148, 150, 221, 34);
		jLabelTime.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(jLabelTime);
		
		jLabelFee = new JLabel("금액 : " + rv.getFee());
		jLabelFee.setHorizontalAlignment(SwingConstants.LEFT);
		jLabelFee.setBounds(148, 194, 155, 27);
		contentPane.add(jLabelFee);
		
		btnPay = new JButton("결제");
		btnPay.setForeground(new Color(255, 255, 255));
		btnPay.setFont(new Font("굴림", Font.BOLD, 18));
		btnPay.setBackground(new Color(30, 144, 255));
		btnPay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(uv.getPoint() <= rv.getFee()) {
					JOptionPane.showMessageDialog(null, "포인트가 부족합니다. 충전해주세요.");
					dispose();
				} else {
					try {
						UserDAO userDao = new UserDAO();
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
						
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnPay.setBounds(106, 305, 119, 53);
		contentPane.add(btnPay);
		
		btnCancel = new JButton("취소");
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setFont(new Font("굴림", Font.BOLD, 18));
		btnCancel.setBackground(new Color(30, 144, 255));
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(237, 305, 119, 53);
		contentPane.add(btnCancel);
	}
	
}
