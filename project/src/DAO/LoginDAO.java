package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conn.DBConn;
import VO.UserVO;

public class LoginDAO {
    private Connection conn;

    public LoginDAO() throws ClassNotFoundException, SQLException {
        conn= new DBConn().getCon();
    }

    public UserVO login(String userid, String password) {
        UserVO userVO = null;
        try {
            String sql = "SELECT * FROM BUS4YOU_USER WHERE userid = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // 유저가 존재한다면
                userVO = new UserVO(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7) );
            }


        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }
        return userVO;
    }

}