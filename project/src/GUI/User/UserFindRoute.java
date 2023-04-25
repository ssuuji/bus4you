package GUI.User;

import VO.RouteVO;
import VO.UserVO;
import DAO.UserDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class UserFindRoute extends JFrame {

    String header[] = {"id", "탑승일", "출발지", "목적지", "busId"};
	
	 
    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrolledTable;
    private DefaultTableModel model;
    private JLabel labelRoute;
    private JButton btnBack;
    /**
     * Create the frame.
     */

    public UserFindRoute(String startLocation, String arriveLocation, String boardingDate, UserVO userVO, JTextField textMyPoint) throws SQLException, ClassNotFoundException {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 554, 554);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        labelRoute = new JLabel("노선 조회");
        labelRoute.setBackground(new Color(0, 255, 255));
        labelRoute.setFont(new Font("굴림", Font.BOLD, 30));
        contentPane.add(labelRoute, BorderLayout.NORTH);

        /*
            하위 버튼 구조
         */
        ArrayList<RouteVO> findAllUsers = new UserDAO().findRoute(startLocation,arriveLocation,boardingDate);
        model=new DefaultTableModel(header, 0);
        for(RouteVO RouteVO : findAllUsers){
            model.addRow(new Object[]{RouteVO.getId(), RouteVO.getBoardingDate(), RouteVO.getStartLocation(),
            															RouteVO.getArriveLocation(),RouteVO.getFk_busId()});
        }

        table = new JTable(model);
        table.setBackground(Color.WHITE);
        scrolledTable = new JScrollPane(table);

        scrolledTable.getViewport().setBackground(Color.WHITE);
        contentPane.add(scrolledTable, BorderLayout.CENTER);

        JTableHeader hd = table.getTableHeader();
        hd.setBackground(new Color(30, 144, 255));

        table.setEnabled(true); // 테이블 셀값을 사용자가 수정못하게
        contentPane.add(scrolledTable, BorderLayout.CENTER);
        table.setDefaultEditor(Object.class, null);
        table.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		if (e.getClickCount() == 2) {
                    try {
                        JTable target = (JTable) e.getSource();
                        int row = target.getSelectedRow();
                    	RouteVO rv = new UserDAO().SelectRoute((int)target.getValueAt(row, 0));
                    	SeatSelect seatSelect = new SeatSelect(rv, userVO, textMyPoint);
	                    seatSelect.setVisible(true);
	                    dispose();
                    } catch(Exception e1) {
                    	e1.printStackTrace();
                    }                                                       	                 
					
        	}
        	}
        });
        
        
        btnBack = new JButton("뒤로가기");
        btnBack.setForeground(new Color(255, 255, 255));
        btnBack.setFont(new Font("굴림", Font.BOLD, 15));
        btnBack.setBackground(new Color(30, 144, 255));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(btnBack, BorderLayout.SOUTH);
        setVisible(true);
    }
}