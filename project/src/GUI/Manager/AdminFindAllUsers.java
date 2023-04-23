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

public class AdminFindAllUsers extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrolledTable;



    /**
     * Create the frame.
     */
    public AdminFindAllUsers() throws SQLException, ClassNotFoundException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 554, 554);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        JLabel lblNewLabel = new JLabel("회원 목록");
        lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
        contentPane.add(lblNewLabel, BorderLayout.NORTH);

        String header[] = {"id", "userId", "name", "phone", "point"};
        ArrayList<UserVO> findAllUsers = new ManagerDAO().findAllUsers();
        DefaultTableModel model=new DefaultTableModel(header, 0);
        for(UserVO userVO : findAllUsers){
            model.addRow(new Object[]{userVO.getId(), userVO.getUserId(), userVO.getName(), userVO.getPhone(),userVO.getPoint()});
        }

        table = new JTable(model);
        scrolledTable = new JScrollPane(table);
        contentPane.add(scrolledTable, BorderLayout.CENTER);

        /*
            하위 버튼 구조
         */
        JButton btnNewButton = new JButton("메인으로");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminMain adminMain = new AdminMain();
                adminMain.setVisible(true);
                dispose();

            }
        });
        contentPane.add(btnNewButton, BorderLayout.SOUTH);
        setVisible(true);
    }
}