package GUI.User;

import VO.RouteVO;
import VO.UserVO;
import DAO.UserDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class UserFindRoute extends JFrame {

	String header[] = {"id", "BoardingDate", "Startlocation", "Arrivelocation", "fk_busid"};
	
	 
    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrolledTable;



    /**
     * Create the frame.
     */
    public UserFindRoute(String startlocation, String arrivelocation, String boardingdate) throws SQLException, ClassNotFoundException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 554, 554);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        JLabel lblNewLabel = new JLabel("노선 조회");
        lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
        contentPane.add(lblNewLabel, BorderLayout.NORTH);

        String header[] = {"id", "boardingdate", "startlocation", "arrivelocation", "fk_busid"};


        
        System.out.println(Date.valueOf(boardingdate));


        /*
            하위 버튼 구조
         */
        
        
        
        ArrayList<RouteVO> findAllUsers = new UserDAO().findRoute(startlocation,arrivelocation,boardingdate);
        DefaultTableModel model=new DefaultTableModel(header, 0);
        for(RouteVO RouteVO : findAllUsers){
            model.addRow(new Object[]{RouteVO.getId(), RouteVO.getBoardingDate(), RouteVO.getStartLocation(),
            															RouteVO.getArriveLocation(),RouteVO.getFk_busId()});
        }

        table = new JTable(model);
        scrolledTable = new JScrollPane(table);
        contentPane.add(scrolledTable, BorderLayout.CENTER);

        
        
        JButton btnNewButton = new JButton("메인으로");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });
        contentPane.add(btnNewButton, BorderLayout.SOUTH);
        setVisible(true);
    }
}