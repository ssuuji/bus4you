package GUI.Manager.BUS;


import DAO.ManagerDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminSellBus {
    private JDialog dialog;
    private JPanel contentPane;
    private JLabel busIdLabel;
    private JTextField busIdValue;
    private JButton btnOK;


    public AdminSellBus(){
        dialog = new JDialog();
        dialog.setModal(true);
        dialog.setTitle("버스 판매");
        dialog.setSize(200, 100);
        dialog.setLocationRelativeTo(null);

        // 패널을 만들고 라벨과 텍스트 필드를 추가합니다.
        contentPane = new JPanel(new GridLayout(1, 1));
        busIdLabel = new JLabel("    busId:");
        busIdValue = new JTextField();

        contentPane.add(busIdLabel);
        contentPane.add(busIdValue);
        dialog.add(contentPane, BorderLayout.CENTER);

        // 확인 버튼을 추가합니다.
        btnOK = new JButton("확인");
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 사용자가 입력한 값을 가져옵니다.
                int busId = Integer.parseInt(busIdValue.getText());
                try {
                    ManagerDAO managerDAO = new ManagerDAO();
                    managerDAO.deleteByBusId(busId);
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
