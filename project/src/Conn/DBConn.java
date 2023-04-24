package Conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
    private static Connection con;
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String id = "hr";
    private String pw = "hr";
    public Connection getCon(){
        return con;
        
    }

    public DBConn() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection(url, id, pw);
    }

}
