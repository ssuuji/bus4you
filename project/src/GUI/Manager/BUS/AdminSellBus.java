package GUI.Manager.BUS;


import DAO.ManagerDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminSellBus extends JDialog{
    private JPanel contentPane;
    private JLabel busIdLabel;
    private JTextField busIdValue;
    private JButton btnOK;


    public AdminSellBus(){
        setBackground(new Color(255, 255, 255));
        setModal(true);
        setTitle("버스 판매");
        setSize(200, 100);
        setLocationRelativeTo(null);

        // 패널을 만들고 라벨과 텍스트 필드를 추가합니다.
        contentPane = new JPanel(new GridLayout(1, 1));
        contentPane.setBackground(Color.WHITE);
        busIdLabel = new JLabel("    bid:");
        busIdValue = new JTextField();

        contentPane.add(busIdLabel);
        contentPane.add(busIdValue);
        getContentPane().add(contentPane, BorderLayout.CENTER);

        // 확인 버튼을 추가합니다.
        btnOK = new JButton("확인");
        btnOK.setForeground(new Color(255, 255, 255));
        btnOK.setFont(new Font("굴림", Font.BOLD, 15));
        btnOK.setBackground(new Color(30, 144, 255));
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
                dispose();
            }
        });
        getContentPane().add(btnOK, BorderLayout.SOUTH);

        // 모달 창을 보여줍니다.
        setVisible(true);

    }
}
