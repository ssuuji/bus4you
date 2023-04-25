package GUI.Manager.ROUTE;

import DAO.ManagerDAO;
import VO.BusVO;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;

public class AdminAddRoute extends JDialog implements ActionListener {
    private JTextField dateField;
    private JTable busTable;
    private DefaultTableModel busTableModel;
    private JButton btnRegister;
    private JButton btnCancel;
    String[] header = {"bid", "버스 이름", "좌석 수"};

    public AdminAddRoute() throws SQLException, ClassNotFoundException {

        setModal(true);
        setTitle("노선 등록");
        setSize(500, 300);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(new Color(255, 255, 255));
        topPanel.setBounds(0, 0, 488, 31);
        JLabel dateLabel = new JLabel("날짜:");
        dateField = new JTextField(10);
        topPanel.add(dateLabel);
        topPanel.add(dateField);
        add(topPanel);

        // 버스 테이블
        busTableModel = new DefaultTableModel(header, 0);
        busTable = new JTable(busTableModel);
        busTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        busTable.setBackground(Color.WHITE);
       
        JScrollPane scrollPane = new JScrollPane(busTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBounds(0, 31, 488, 211);
        add(scrollPane);
        
        JTableHeader hd = busTable.getTableHeader();
        hd.setBackground(new Color(30, 144, 255));

        ArrayList<BusVO> findAllBuses = new ManagerDAO().findAllBuses();
        for(BusVO busVO : findAllBuses){
            busTableModel.addRow(new Object[]{busVO.getId(), busVO.getBusCode(), busVO.getTotalSeat()});
        }


        // 등록 버튼
        btnRegister = new JButton("등록");
        btnRegister.setForeground(new Color(255, 255, 255));
        btnRegister.setFont(new Font("굴림", Font.BOLD, 15));
        btnRegister.setBackground(new Color(30, 144, 255));
        btnRegister.setBounds(1, 243, 243, 23);
        btnRegister.addActionListener(this);
        add(btnRegister);
        
        btnCancel = new JButton("취소");
        btnCancel.setForeground(new Color(255, 255, 255));
        btnCancel.setFont(new Font("굴림", Font.BOLD, 15));
        btnCancel.setBackground(new Color(30, 144, 255));
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnCancel.setBounds(243, 242, 245, 23);
        add(btnCancel);

        setVisible(true);


    }

    // 등록 버튼 클릭 시 호출되는 메소드
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == btnRegister) {
            String dateCheck = dateField.getText();
            int selectedRow = busTable.getSelectedRow();
            if(dateCheck.isEmpty()) {
                JOptionPane.showMessageDialog(null, "날짜를 입력해주세요.");
            }
            
            else if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "버스를 선택해주세요.");
                
            } else {
                int busId = (int) busTableModel.getValueAt(selectedRow, 0);
                // TODO: 입력된 날짜와 선택된 버스 ID를 이용하여 등록 처리
                try {
                    dispose();
                    new CheckOperation(busId, (String)busTableModel.getValueAt(selectedRow, 1), dateCheck);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}