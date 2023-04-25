package GUI.Manager.ROUTE;

import VO.OperationVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckByRouteIdOperation extends JFrame{
    private JLabel departureLabel, arrivalLabel, dateLabel;
    private JButton confirmButton;

    public CheckByRouteIdOperation(OperationVO operationVO) {
    	setBackground(Color.WHITE);

        setTitle("해당 운행 상태");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 레이아웃 설정
        getContentPane().setLayout(new GridLayout(4, 1));
        getContentPane().setBackground(Color.WHITE);
        // 탑승일
        dateLabel = new JLabel("탑승일:  " + operationVO.getBoardingDate());
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setFont(new Font("돋움", Font.BOLD, 20));
        getContentPane().add(dateLabel);

        // 출발 시간
        departureLabel = new JLabel("출발 시간:  " + operationVO.getStartTime());
        departureLabel.setHorizontalAlignment(JLabel.CENTER);
        departureLabel.setFont(new Font("돋움", Font.BOLD, 20));
        getContentPane().add(departureLabel);

        // 도착 시간
        arrivalLabel = new JLabel("도착 시간:  " + operationVO.getArriveTime());
        arrivalLabel.setHorizontalAlignment(JLabel.CENTER);
        arrivalLabel.setFont(new Font("돋움", Font.BOLD, 20));
        getContentPane().add(arrivalLabel);

        // 닫기 버튼
        confirmButton = new JButton("닫기");
        confirmButton.setForeground(new Color(255, 255, 255));
        confirmButton.setFont(new Font("굴림", Font.BOLD, 18));
        confirmButton.setBackground(new Color(30, 144, 255));
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        confirmButton.setPreferredSize(new Dimension(400, 50));
        getContentPane().add(confirmButton);

        setSize(400, 250);
        setLocationRelativeTo(null);
    }

}