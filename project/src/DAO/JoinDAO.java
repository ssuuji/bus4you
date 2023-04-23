package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Conn.DBConn;

public class JoinDAO {
    private Connection conn;

    public JoinDAO() throws ClassNotFoundException, SQLException {
        conn=new DBConn().getCon();
    }

    public boolean Check(String userid) { //중복체크기능
        boolean result = true;
        try {
            String sql = "SELECT COUNT(*) FROM BUS4YOU_USER WHERE userid = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            System.out.println(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            result = (rs.getInt(1) >= 1) ? true : false;
        }catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }
        return result;
    }



    public boolean Join_insert(String userid,String pw,String name,String phone) { //회원가입


        try {
            String sql = "INSERT INTO BUS4YOU_USER (userid, password, name, phone) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            pstmt.setString(2, pw);
            pstmt.setString(3, name);
            pstmt.setString(4, phone);
            pstmt.executeUpdate();

        }catch (SQLException ex) {
            return false;
        }
        return true;
    }

}
