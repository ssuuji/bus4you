package GUI.Manager.ROUTE;


import DAO.ManagerDAO;
import VO.OperationVO;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class CheckOperation{
    private JPanel contentPane;
    private JTextField textFieldDepartureTime;
    private JTextField textFieldArrivalTime;
    private JTextField textFieldFee;
    private JTextField startFiled;
    private JTextField endFiled;
    private JTable table;
    private JDialog dialog;

    String[] header = {"탑 승 일", "출발 시간", "도착 시간"};
    public CheckOperation(int busId, String busCode, String date) throws SQLException, ClassNotFoundException {

        dialog = new JDialog();
        dialog.setModal(true);
        dialog.setTitle(busCode + " " + date + " 노선 등록");
        dialog.setSize(500, 300);
        dialog.setLocationRelativeTo(null);


//        setTitle(busCode + " " + date + " 노선 등록");
//        setSize(500, 300);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dialog.setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//        setContentPane(contentPane);



        // 출발시간 필드 추가
        JPanel panelTop = new JPanel();
//        contentPane.add(panelTop, BorderLayout.NORTH);
//        dialog.add(contentPane,  BorderLayout.CENTER);
//        dialog.pack();
//        dialog.setVisible(true);
        JLabel labelDepartureTime = new JLabel("출발시간");
        panelTop.add(labelDepartureTime);

        textFieldDepartureTime = new JTextField();
        panelTop.add(textFieldDepartureTime);
        textFieldDepartureTime.setColumns(5);

        // 도착시간 필드 추가
        JLabel labelArrivalTime = new JLabel("도착시간");
        panelTop.add(labelArrivalTime);

        textFieldArrivalTime = new JTextField();
        panelTop.add(textFieldArrivalTime);
        textFieldArrivalTime.setColumns(5);

        // 요금 필드 추가
        JLabel labelFee = new JLabel("요금");
        panelTop.add(labelFee);

        textFieldFee = new JTextField();
        panelTop.add(textFieldFee);
        textFieldFee.setColumns(5);

        // 시작 필드 추가
        JLabel labelStart = new JLabel("출발위치");
        panelTop.add(labelStart);

        startFiled = new JTextField();
        panelTop.add(startFiled);
        startFiled.setColumns(5);

        // 도착 필드 추가
        JLabel labelEnd = new JLabel("도착위치");
        panelTop.add(labelEnd);

        endFiled = new JTextField();
        panelTop.add(endFiled);
        endFiled.setColumns(5);



        // 테이블 추가
        ArrayList<OperationVO> findAllOperations = new ManagerDAO().findByBusIdAllOperations(busId, date);
        DefaultTableModel model=new DefaultTableModel(header, 0);
        for(OperationVO operationVO : findAllOperations){
            model.addRow(new Object[]{operationVO.getBoardingDate(), operationVO.getStartTime(), operationVO.getArriveTime()});
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(busCode + " " + date + " 운행  시간"));

        contentPane.add(scrollPane, BorderLayout.CENTER);
        JPanel panelBottom = new JPanel();
        contentPane.add(panelBottom, BorderLayout.SOUTH);

        JButton btnRegister = new JButton("등록");
        panelBottom.add(btnRegister);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	///check
            	///////////////////
            	for (OperationVO operationVO : findAllOperations) {
            	    String startTimeStr = operationVO.getStartTime();
            	    String arriveTimeStr = operationVO.getArriveTime();
            	    String depTimeStr = textFieldDepartureTime.getText();
            	    String arrTimeStr = textFieldArrivalTime.getText();
            	    
            	    int startTime = Integer.parseInt(startTimeStr.replace(":", ""));
            	    int arriveTime = Integer.parseInt(arriveTimeStr.replace(":", ""));
            	    int depTime = Integer.parseInt(depTimeStr.replace(":", ""));
            	    int arrTime = Integer.parseInt(arrTimeStr.replace(":", ""));   
            	 // 기존출발시간이 새출발시간보다 늦고,새도착시간보다 빠를 때, 기존도착시간이 새출발보다 늦고 새도착보다 빠를 때,  기존출발시간이 새출발시간보다 빠르고, 기존도착시간이 새도착시간보다 늦을 때  
            	    if ((depTime <= startTime && startTime <= arrTime) || (depTime <= arriveTime && arriveTime <= arrTime) 
            	            || (startTime <= depTime && arrTime <= arriveTime)) {
            	            JOptionPane.showMessageDialog(null, "이미 사용중인 노선입니다");
            	            return;
           	    }
            	 
            	  
            	}
            	
            		
            	
            	/////////////////////
            	
                try {
                    ManagerDAO managerDAO = new ManagerDAO();
                    int routeId = managerDAO.createRoute(date, startFiled.getText(), endFiled.getText(), busId, Integer.parseInt(textFieldFee.getText()));
                    managerDAO.createOperation(date, textFieldDepartureTime.getText(), textFieldArrivalTime.getText(),routeId,busId);

                    dialog.dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        JButton btnCancel = new JButton("취소");
        panelBottom.add(btnCancel);

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        contentPane.add(panelTop, BorderLayout.NORTH);
        dialog.add(contentPane,  BorderLayout.CENTER);
        dialog.pack();
        dialog.setVisible(true);
    }
}