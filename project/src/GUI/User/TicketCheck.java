package GUI.User;

import VO.ReservationVO;
import VO.RouteVO;
import VO.UserVO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import DAO.UserDAO;


public class TicketCheck extends JFrame {

	private JPanel contentPane;
	private JTable table;
    private JLabel jLabel;
	private JScrollPane scrolledTable;
    private DefaultTableModel model;
    private JButton btnBack;
    String header[] = {"예약번호", "출발지", "도착지", "탑승일", "버스id", "좌석번호", "요금"};
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */

	
	public TicketCheck(UserVO userVO) throws ClassNotFoundException, SQLException {

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 554, 554);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        jLabel = new JLabel("나의 예매 정보 확인");
        jLabel.setFont(new Font("굴림", Font.BOLD, 30));
        contentPane.add(jLabel, BorderLayout.NORTH);
		ArrayList<ReservationVO> arv = new UserDAO().selectAllReservation(userVO.getId());

        model=new DefaultTableModel(header, 0);
        for(ReservationVO rev : arv){
        	RouteVO rv = new UserDAO().SelectRoute(rev.getFk_routeId());
            model.addRow(new Object[]{rev.getId(), rv.getStartLocation(), rv.getArriveLocation(), rv.getBoardingDate(), rv.getFk_busId(), rev.getSeatId(), rv.getFee()});
        }

        table = new JTable(model);
        table.setBackground(Color.WHITE);
        scrolledTable = new JScrollPane(table);
        scrolledTable.getViewport().setBackground(Color.WHITE);
        contentPane.add(scrolledTable, BorderLayout.CENTER);
        JTableHeader hd = table.getTableHeader();
        hd.setBackground(new Color(30, 144, 255));
        
        // 삭제기능
        table.setDefaultEditor(Object.class, null);
        table.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {
              // 더블클릭 이벤트 처리
              if (e.getClickCount() == 2) {
                 // 선택된 행의 데이터 가져오기
                 int row = table.getSelectedRow();
                 int reservationId = (int) table.getValueAt(row, 0);
                 int fee = (int) table.getValueAt(row, 6);
                 int confirmed = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
                 if (confirmed == JOptionPane.YES_OPTION) {
                    try {
                       // 해당 예약 삭제
                       UserDAO userDao = new UserDAO();
                       userDao.deleteReservation(reservationId);
                       // 포인트 환불
                       userDao.updatePoint(userVO.getId(), fee);
                       userVO.setPoint(userVO.getPoint() + fee);
                       
                       // JTable 모델에서 행 삭제
                       model = (DefaultTableModel) table.getModel();
                       model.removeRow(row);

                    } catch (SQLException | ClassNotFoundException ex) {
                       ex.printStackTrace();
                    }
                 }
              }
           }
        });
        /*
            하위 버튼 구조
         */
        
        btnBack = new JButton("뒤로가기");
        btnBack.setForeground(new Color(255, 255, 255));
        btnBack.setFont(new Font("굴림", Font.BOLD, 15));
        btnBack.setBackground(new Color(30, 144, 255));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
				setVisible(false);
				try {
					new UserMain(userVO).setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
               
            }
        });
        
        contentPane.add(btnBack, BorderLayout.SOUTH);
        setVisible(true);
		
	}
	
}
