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
import javax.swing.table.JTableHeader;

public class AdminFindAllBuses extends JFrame {

    private JPanel contentPane;
    private JLabel lblNewLabel;
    private JTable table;
    private JScrollPane scrolledTable;
    private DefaultTableModel model;
    private JPanel buttonPanel; // 버튼이 위치할 JPanel
    private JPanel twoButtonPanel;
    private JButton btnBuy; // 버스 구매 버튼
    private JButton btnSell; // 버스 판매 버튼

    String header[] = {"bid", "버스 이름", "좌석 수"};


    /**
     * Create the frame.
     */
    public AdminFindAllBuses() throws SQLException, ClassNotFoundException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 605, 606);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        lblNewLabel = new JLabel("회사 버스 목록");
        lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
        contentPane.add(lblNewLabel, BorderLayout.NORTH);

        ArrayList<BusVO> findAllBuses = new ManagerDAO().findAllBuses();
        model=new DefaultTableModel(header, 0);
        for(BusVO busVO : findAllBuses){
            model.addRow(new Object[]{busVO.getId(), busVO.getBusCode(), busVO.getTotalSeat()});
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
        buttonPanel = new JPanel(new BorderLayout());
        twoButtonPanel = new JPanel(new GridLayout(1, 2));
        btnBuy = new JButton("버스 구매");
        btnBuy.setForeground(new Color(255, 255, 255));
        btnBuy.setFont(new Font("굴림", Font.BOLD, 15));
        btnBuy.setBackground(new Color(30, 144, 255));
        btnBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new AdminBuyBus();
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
        btnSell.setForeground(new Color(255, 255, 255));
        btnSell.setFont(new Font("굴림", Font.PLAIN, 15));
        btnSell.setBackground(new Color(30, 144, 255));
        btnSell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new AdminSellBus();
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
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setFont(new Font("굴림", Font.BOLD, 15));
        btnNewButton.setBackground(new Color(30, 144, 255));
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
        model = new DefaultTableModel(header, 0);
        for (BusVO busVO : findAllBuses) {
            model.addRow(new Object[]{busVO.getId(), busVO.getBusCode(), busVO.getTotalSeat()});
        }
        table.setModel(model);
    }
}