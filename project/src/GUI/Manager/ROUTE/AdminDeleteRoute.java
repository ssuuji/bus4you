package GUI.Manager.ROUTE;

import DAO.ManagerDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminDeleteRoute {
    private JDialog dialog;
    private JPanel contentPane;
    private JLabel routeIdLabel;
    private JTextField routeIdValue;
    private JButton btnOK;


    public AdminDeleteRoute(){
        dialog = new JDialog();
        dialog.setBackground(new Color(255, 255, 255));
        dialog.setModal(true);
        dialog.setTitle("노선 삭제");
        dialog.setSize(200, 100);
        dialog.setLocationRelativeTo(null);

        // 패널을 만들고 라벨과 텍스트 필드를 추가합니다.
        contentPane = new JPanel(new GridLayout(1, 1));
        contentPane.setBackground(Color.WHITE);
        routeIdLabel = new JLabel("    routeId:");
        routeIdValue = new JTextField();

        contentPane.add(routeIdLabel);
        contentPane.add(routeIdValue);
        dialog.getContentPane().add(contentPane, BorderLayout.CENTER);

        // 확인 버튼을 추가합니다.
        btnOK = new JButton("확인");
        btnOK.setFont(new Font("굴림", Font.BOLD, 15));
        btnOK.setForeground(new Color(255, 255, 255));
        btnOK.setBackground(new Color(30, 144, 255));
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 사용자가 입력한 값을 가져옵니다.
                int routeId = Integer.parseInt(routeIdValue.getText());
                try {
                    ManagerDAO managerDAO = new ManagerDAO();
                    managerDAO.deleteByRouteId(routeId);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                // 모달 창을 닫습니다.
                dialog.dispose();
            }
        });
        dialog.getContentPane().add(btnOK, BorderLayout.SOUTH);

        // 모달 창을 보여줍니다.
        dialog.setVisible(true);

    }
}
