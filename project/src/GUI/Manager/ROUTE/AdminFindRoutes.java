package GUI.Manager.ROUTE;
import DAO.ManagerDAO;
import DAO.UserDAO;
import GUI.Manager.AdminMain;
import GUI.User.SeatSelect;
import VO.OperationVO;
import VO.RouteVO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class AdminFindRoutes extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrolledTable;
    private JPanel buttonPanel; // 버튼이 위치할 JPanel
    private JPanel twoButtonPanel;
    private JButton btnBuy; // 버스 구매 버튼
    private JButton btnSell; // 버스 판매 버튼
    private JButton btnBack;
    private DefaultTableModel model;
    String header[] = {"rid", "탑승일", "출발장소", "도착장소", "요금", "busId"};


    /**
     * Create the frame.
     */
    public AdminFindRoutes() throws SQLException, ClassNotFoundException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 605, 605);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        JLabel lblNewLabel = new JLabel("회사 노선 목록");
        lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
        contentPane.add(lblNewLabel, BorderLayout.NORTH);

        ArrayList<RouteVO> findAllRoutes= new ManagerDAO().findAllRoutes();
        model=new DefaultTableModel(header, 0);
        for(RouteVO routeVO : findAllRoutes){
            model.addRow(new Object[]{routeVO.getId(), routeVO.getBoardingDate(), routeVO.getStartLocation(),
                    routeVO.getArriveLocation(), routeVO.getFee(), routeVO.getFk_busId()});
        }

        table = new JTable(model);
        table.setBackground(Color.WHITE);
        table.setEnabled(true);
        table.setDefaultEditor(Object.class, null);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    try {
                        JTable target = (JTable) e.getSource();
                        int row = target.getSelectedRow();
                        System.out.println(target);
                        OperationVO operationVO = new ManagerDAO().findByRouteIdOperation((int)target.getValueAt(row, 0));
                        System.out.println(operationVO);
                        if(operationVO != null){
                            CheckByRouteIdOperation checkByRouteIdOperation = new CheckByRouteIdOperation(operationVO);
                            checkByRouteIdOperation.setVisible(true);
                        }
                    } catch(Exception e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });



        scrolledTable = new JScrollPane(table);
        scrolledTable.getViewport().setBackground(Color.WHITE);
        contentPane.add(scrolledTable, BorderLayout.CENTER);
        JTableHeader hd = table.getTableHeader();
        hd.setBackground(new Color(30, 144, 255));


        /*
            하위 버튼 구조
         */
        buttonPanel = new JPanel(new BorderLayout());
        twoButtonPanel = new JPanel(new GridLayout(1, 2));
        btnBuy = new JButton("노선 등록");
        btnBuy.setForeground(new Color(255, 255, 255));
        btnBuy.setFont(new Font("굴림", Font.BOLD, 15));
        btnBuy.setBackground(new Color(30, 144, 255));
        btnBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new AdminAddRoute();
                    updateTable();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });
        twoButtonPanel.add(btnBuy);

        btnSell = new JButton("노선 삭제");
        btnSell.setForeground(new Color(255, 255, 255));
        btnSell.setFont(new Font("굴림", Font.BOLD, 15));
        btnSell.setBackground(new Color(30, 144, 255));
        btnSell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new AdminDeleteRoute();
                    updateTable();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        twoButtonPanel.add(btnSell);




        btnBack = new JButton("메인으로");
        btnBack.setForeground(new Color(255, 255, 255));
        btnBack.setFont(new Font("굴림", Font.BOLD, 15));
        btnBack.setBackground(new Color(30, 144, 255));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminMain adminMain = new AdminMain();
                adminMain.setVisible(true);
                dispose();

            }
        });
        buttonPanel.add(twoButtonPanel, BorderLayout.NORTH);
        buttonPanel.add(btnBack, BorderLayout.SOUTH);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    public void updateTable() throws SQLException, ClassNotFoundException {
        ArrayList<RouteVO> findAllRoutes= new ManagerDAO().findAllRoutes();
        model=new DefaultTableModel(header, 0);
        for(RouteVO routeVO : findAllRoutes){
            model.addRow(new Object[]{routeVO.getId(), routeVO.getBoardingDate(), routeVO.getStartLocation(),
                    routeVO.getArriveLocation(), routeVO.getFee(), routeVO.getFk_busId()});
        }
        table.setModel(model);
    }
}




