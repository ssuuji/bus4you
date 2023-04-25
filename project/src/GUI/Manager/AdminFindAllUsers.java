package GUI.Manager;



import DAO.ManagerDAO;
import VO.UserVO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class AdminFindAllUsers extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrolledTable;
    private JButton btnBack;


    /**
     * Create the frame.
     */
    public AdminFindAllUsers() throws SQLException, ClassNotFoundException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 605, 605);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        JLabel lblNewLabel = new JLabel("회원 목록");
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
        contentPane.add(lblNewLabel, BorderLayout.NORTH);
        
        String header[] = {"uid", "아이디", "이름", "휴대폰", "포인트"};
        ArrayList<UserVO> findAllUsers = new ManagerDAO().findAllUsers();
        DefaultTableModel model=new DefaultTableModel(header, 0);
        for(UserVO userVO : findAllUsers){
            model.addRow(new Object[]{userVO.getId(), userVO.getUserId(), userVO.getName(), userVO.getPhone(),userVO.getPoint()});
        }

        table = new JTable(model);
        table.setBackground(Color.WHITE);
        scrolledTable = new JScrollPane(table);     
        scrolledTable.getViewport().setBackground(Color.WHITE);
        contentPane.add(scrolledTable, BorderLayout.CENTER);
        
        JTableHeader hd = table.getTableHeader();
        hd.setBackground(new Color(30, 144, 255));
        
        /*
            하위 버튼 구조
         */
        btnBack = new JButton("뒤로가기");
        btnBack.setForeground(new Color(255, 255, 255));
        btnBack.setFont(new Font("굴림", Font.BOLD, 15));
        btnBack.setBackground(new Color(30, 144, 255));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminMain adminMain = new AdminMain();
                adminMain.setVisible(true);
                dispose();

            }
        });
        contentPane.add(btnBack, BorderLayout.SOUTH);
        setVisible(true);
    }
}