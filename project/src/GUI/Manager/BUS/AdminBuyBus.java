package GUI.Manager.BUS;

import DAO.ManagerDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminBuyBus {
    private JDialog dialog;
    private JPanel contentPane;
    private JLabel busCodeLabel;
    private JTextField busCodeValue;
    private JLabel totalSeatLabel;
    private JTextField totalSeatValue;
    private JButton btnOK;


    public AdminBuyBus(){
        dialog = new JDialog();
        dialog.setModal(true);
        dialog.setTitle("버스 구입");
        dialog.setSize(200, 150);
        dialog.setLocationRelativeTo(null);

        // 패널을 만들고 라벨과 텍스트 필드를 추가합니다.
        contentPane = new JPanel(new GridLayout(2, 2));
        busCodeLabel = new JLabel("    busCode:");
        busCodeValue = new JTextField();
        totalSeatLabel = new JLabel("    totalSeat:");
        totalSeatValue = new JTextField();

        contentPane.add(busCodeLabel);
        contentPane.add(busCodeValue);
        contentPane.add(totalSeatLabel);
        contentPane.add(totalSeatValue);
        dialog.add(contentPane, BorderLayout.CENTER);

        // 확인 버튼을 추가합니다.
        btnOK = new JButton("확인");
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 사용자가 입력한 값을 가져옵니다.
                String busCode = busCodeValue.getText();
                int totalSeat = Integer.parseInt(totalSeatValue.getText());

                try {
                    ManagerDAO managerDAO = new ManagerDAO();
                    managerDAO.createBus(busCode, totalSeat);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                // 모달 창을 닫습니다.
                dialog.dispose();
            }
        });
        dialog.add(btnOK, BorderLayout.SOUTH);

        // 모달 창을 보여줍니다.
        dialog.setVisible(true);

    }
}
