package GUI.Manager.BUS;



import DAO.ManagerDAO;
import GUI.Manager.AdminMain;
import VO.BusVO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class AdminFindAllBuses extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrolledTable;
    private JPanel buttonPanel; // 버튼이 위치할 JPanel
    private JPanel twoButtonPanel;
    private JButton btnBuy; // 버스 구매 버튼
    private JButton btnSell; // 버스 판매 버튼

    String header[] = {"id", "busCode", "totalSeat"};


    /**
     * Create the frame.
     */
    public AdminFindAllBuses() throws SQLException, ClassNotFoundException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 554, 554);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        JLabel lblNewLabel = new JLabel("회사 버스 목록");
        lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
        contentPane.add(lblNewLabel, BorderLayout.NORTH);

        ArrayList<BusVO> findAllBuses = new ManagerDAO().findAllBuses();
        DefaultTableModel model=new DefaultTableModel(header, 0);
        for(BusVO busVO : findAllBuses){
            model.addRow(new Object[]{busVO.getId(), busVO.getBusCode(), busVO.getTotalSeat()});
        }

        table = new JTable(model);
        scrolledTable = new JScrollPane(table);
        contentPane.add(scrolledTable, BorderLayout.CENTER);

        /*
            하위 버튼 구조
         */
        buttonPanel = new JPanel(new BorderLayout());
        twoButtonPanel = new JPanel(new GridLayout(1, 2));
        btnBuy = new JButton("버스 구매");
        btnBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    AdminBuyBus adminBuyBus = new AdminBuyBus();
                    updateTable();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });
        twoButtonPanel.add(btnBuy);

        btnSell = new JButton("버스 판매");
        btnSell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    AdminSellBus adminSellBus = new AdminSellBus();
                    updateTable();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        twoButtonPanel.add(btnSell);




        JButton btnNewButton = new JButton("메인으로");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminMain adminMain = new AdminMain();
                adminMain.setVisible(true);
                dispose();

            }
        });
        buttonPanel.add(twoButtonPanel, BorderLayout.NORTH);
        buttonPanel.add(btnNewButton, BorderLayout.SOUTH);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);



        setVisible(true);
    }
    public void updateTable() throws SQLException, ClassNotFoundException {
        ArrayList<BusVO> findAllBuses = new ManagerDAO().findAllBuses();
        DefaultTableModel model = new DefaultTableModel(header, 0);
        for (BusVO busVO : findAllBuses) {
            model.addRow(new Object[]{busVO.getId(), busVO.getBusCode(), busVO.getTotalSeat()});
        }
        table.setModel(model);
    }
}