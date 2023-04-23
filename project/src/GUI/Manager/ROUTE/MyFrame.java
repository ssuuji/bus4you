package GUI.Manager.ROUTE;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MyFrame extends JFrame {
    private JPanel contentPane;
    private JTextField textFieldDepartureTime;
    private JTextField textFieldArrivalTime;
    private JTable table;

    public MyFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // 출발시간 필드 추가
        JPanel panelTop = new JPanel();
        contentPane.add(panelTop, BorderLayout.NORTH);

        JLabel labelDepartureTime = new JLabel("출발 시간");
        panelTop.add(labelDepartureTime);

        textFieldDepartureTime = new JTextField();
        panelTop.add(textFieldDepartureTime);
        textFieldDepartureTime.setColumns(10);

        // 도착시간 필드 추가
        JLabel labelArrivalTime = new JLabel("도착 시간");
        panelTop.add(labelArrivalTime);

        textFieldArrivalTime = new JTextField();
        panelTop.add(textFieldArrivalTime);
        textFieldArrivalTime.setColumns(10);

        // 테이블 추가
        String[] columnNames = {"출발지", "도착지", "출발시간", "도착시간"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // 예시 데이터 추가
        model.addRow(new Object[]{"서울", "부산", "10:00", "14:00"});
        model.addRow(new Object[]{"부산", "서울", "15:00", "19:00"});

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        JPanel panelBottom = new JPanel();
        contentPane.add(panelBottom, BorderLayout.SOUTH);

        JButton btnRegister = new JButton("등록");
        panelBottom.add(btnRegister);

        JButton btnCancel = new JButton("취소");
        panelBottom.add(btnCancel);
    }
}