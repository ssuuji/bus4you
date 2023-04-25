package GUI.User;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DAO.UserDAO;
import GUI.Manager.AdminMain;
import VO.RouteVO;
import VO.UserVO;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.*;


public class SeatSelect extends JFrame {

	private JPanel contentPane;
	private JButton selectedButton; // 활성화된 버튼에 대한 참조를 저장할 변수
	private JLabel jLabelBusSelect;
	private JButton[] buttons;
	private JLabel jLabelRealSeat;
	private JButton btnCancel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_8;

	public SeatSelect(RouteVO rv, UserVO uv, JTextField textMyPoint) throws SQLException, ClassNotFoundException {
		int totalSeat = new UserDAO().checkTotalSeat(rv.getFk_busId());
		ArrayList<Integer> check = new UserDAO().checkSeat(rv.getId());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 327);
		initUI(totalSeat, check, rv, uv, textMyPoint);
	}	
	
	private void initUI(int totalSeat, ArrayList<Integer> check, RouteVO rv, UserVO uv, JTextField textMyPoint){
		Map<Integer, Boolean> map = new HashMap<>();
		for(int i : check) {
			map.put(i, true);
			totalSeat = totalSeat - 1;
		}
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		jLabelBusSelect = new JLabel("버스 좌석 선택");
		jLabelBusSelect.setFont(new Font("굴림", Font.BOLD, 18));
		jLabelBusSelect.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelBusSelect.setBounds(150, 10, 220, 62);
		contentPane.add(jLabelBusSelect);
		
		JLabel busSelectTime = new JLabel("<노선> " + rv.getBoardingDate() + " "
				+ rv.getStartLocation() + " - " + rv.getArriveLocation());
		
		busSelectTime.setHorizontalAlignment(SwingConstants.CENTER);
		busSelectTime.setBounds(120, 73, 300, 28);
		contentPane.add(busSelectTime);
		
		   	buttons = new JButton[4];
	        int startX = 50; // 첫번째 버튼의 x좌표
	        int startY = 100; // 첫번째 버튼의 y좌표
	        int buttonWidth = 90; // 버튼의 폭
	        int buttonHeight = 100; // 버튼의 높이
	        int gapX = 20; // 버튼과 버튼 사이의 x축 간격
	        int gapY = 20; // 버튼과 버튼 사이의 y축 간격
	        
	        for(int i=0; i<buttons.length; i++) {
	        	
	        	final int index = i;
	        	
	            buttons[i] = new JButton();
	            buttons[i].setForeground(new Color(255, 255, 255));
	            buttons[i].setBackground(new Color(255, 255, 255));
	            buttons[i].setFont(new Font("굴림", Font.BOLD, 15));
	            buttons[i].setIcon(new ImageIcon(AdminMain.class.getResource("/IMAGE/ssss.png")));
                buttons[i].setBorderPainted(false);
                buttons[i].setContentAreaFilled(false);
                buttons[i].setFocusPainted(false);
                buttons[i].setOpaque(false);
	            buttons[i].setBounds(startX + (buttonWidth+gapX)*i, startY, buttonWidth, buttonHeight);
	            if(map.get(i) != null){
//	            	ImageIcon imageIcon = new ImageIcon("/IMAGE/bus.png");
	                buttons[i].setEnabled(false);
//	                buttons[i].setBackground(Color.BLACK);
	                
	            }
	            buttons[i].addActionListener(new ActionListener() {
	            	
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    try {
	                    	selectedButton = buttons[index];
							new Pay(uv, rv, index, SeatSelect.this, textMyPoint).setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                }
	            });
	        }

	        for(JButton button : buttons) {
	            contentPane.add(button);
	        }

		jLabelRealSeat = new JLabel("현재 남은 좌석 : " + totalSeat);
		jLabelRealSeat.setBounds(200, 208, 104, 28);
		contentPane.add(jLabelRealSeat);
			
			
		btnCancel = new JButton("취소");
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBackground(new Color(30, 144, 255));
		btnCancel.setFont(new Font("굴림", Font.BOLD, 15));
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				setVisible(false);
				try {
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		
		btnCancel.setBounds(190, 246, 111, 34);
		contentPane.add(btnCancel);
		
		lblNewLabel = new JLabel("1");
		lblNewLabel.setBounds(88, 181, 28, 28);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_3 = new JLabel("2");
		lblNewLabel_3.setBounds(203, 181, 28, 28);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_6 = new JLabel("3");
		lblNewLabel_6.setBounds(307, 182, 28, 28);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_8 = new JLabel("4");
		lblNewLabel_8.setBounds(419, 181, 28, 28);
		contentPane.add(lblNewLabel_8);
	        
	} 
	
	public void refresh(RouteVO rv, UserVO uv, JTextField textMyPoint) {
		try {
			dispose();
			new SeatSelect(rv, uv, textMyPoint).setVisible(true);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JButton getSelectedButton() { // 저장된 버튼 참조를 반환하는 메소드
        return selectedButton;
    }
	
	
}
