package GUI.Manager.ROUTE;

import DAO.ManagerDAO;
import VO.BusVO;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;

public class AdminAddRoute extends JFrame implements ActionListener {
    private JDialog dialog;
    private JTextField dateField;
    private JTable busTable;
    private DefaultTableModel busTableModel;
    private JButton registerButton;

    String[] header = {"busId", "busCode", "totalSeat"};
    public AdminAddRoute() throws SQLException, ClassNotFoundException {

        dialog = new JDialog();
        dialog.setModal(true);
        dialog.setTitle("노선 등록");
        dialog.setSize(500, 300);
        dialog.setLocationRelativeTo(null);
//        setTitle("노선 등록");
//        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dialog.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel dateLabel = new JLabel("날짜:");
        dateField = new JTextField(10);
        topPanel.add(dateLabel);
        topPanel.add(dateField);
        dialog.add(topPanel, BorderLayout.NORTH);

        // 버스 테이블
        busTableModel = new DefaultTableModel(header, 0);
        busTable = new JTable(busTableModel);
        busTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dialog.add(new JScrollPane(busTable), BorderLayout.CENTER);

        ArrayList<BusVO> findAllBuses = new ManagerDAO().findAllBuses();
        for(BusVO busVO : findAllBuses){
            busTableModel.addRow(new Object[]{busVO.getId(), busVO.getBusCode(), busVO.getTotalSeat()});
        }


        // 등록 버튼
        registerButton = new JButton("등록");
        registerButton.addActionListener(this);
        dialog.add(registerButton, BorderLayout.SOUTH);

        dialog.setVisible(true);


    }

    // 등록 버튼 클릭 시 호출되는 메소드
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String date = dateField.getText();
            int selectedRow = busTable.getSelectedRow();
            System.out.println(selectedRow);
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "버스를 선택해주세요.");
            } else {
                int busId = (int) busTableModel.getValueAt(selectedRow, 0);
                // TODO: 입력된 날짜와 선택된 버스 ID를 이용하여 등록 처리
                CheckOperation checkOperation = null;
                try {
                    dialog.dispose();
                    checkOperation = new CheckOperation(busId, (String)busTableModel.getValueAt(selectedRow, 1), date);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}