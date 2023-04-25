package GUI.Manager.ROUTE;

import DAO.ManagerDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminDeleteRoute extends JDialog{
    private JPanel contentPane;
    private JLabel jLabel;
    private JTextField textFieldRouteId;
    private JButton btnOK;


    public AdminDeleteRoute(){
        setBackground(new Color(255, 255, 255));
        setModal(true);
        setTitle("노선 삭제");
        setSize(200, 100);
        setLocationRelativeTo(null);

        // 패널을 만들고 라벨과 텍스트 필드를 추가합니다.
        contentPane = new JPanel(new GridLayout(1, 1));
        contentPane.setBackground(Color.WHITE);
        jLabel = new JLabel("    rid:");
        textFieldRouteId = new JTextField();

        contentPane.add(jLabel);
        contentPane.add(textFieldRouteId);
        add(contentPane, BorderLayout.CENTER);

        // 확인 버튼을 추가합니다.
        btnOK = new JButton("확인");
        btnOK.setFont(new Font("굴림", Font.BOLD, 15));
        btnOK.setForeground(new Color(255, 255, 255));
        btnOK.setBackground(new Color(30, 144, 255));
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 사용자가 입력한 값을 가져옵니다.
                int routeId = Integer.parseInt(textFieldRouteId.getText());
                try {
                    ManagerDAO managerDAO = new ManagerDAO();
                    managerDAO.deleteByRouteId(routeId);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                // 모달 창을 닫습니다.
                dispose();
            }
        });
        add(btnOK, BorderLayout.SOUTH);

        // 모달 창을 보여줍니다.
        setVisible(true);

    }
}
