package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conn.DBConn;
import VO.ReservationVO;
import VO.RouteVO;
import VO.UserVO;

public class UserDAO {
	
	private Connection conn;
	private PreparedStatement preparedStatement = null;
	
	public UserDAO() throws SQLException, ClassNotFoundException {
		DBConn dbconn = new DBConn();
		this.conn = DBConn.getCon();
	}
	
	/*
	  	출발지,도착지,시간에 맞는 노선데이터 출력
	*/
	public ArrayList<RouteVO> findRoute(String startlocation, String arrivelocation, String boardingdate) {
		
		ArrayList<RouteVO> routeVOArrayList = new ArrayList<>();
		try {
			String sql = "select * from BUS4YOU_Route where Startlocation = (?)"+
						" and arrivelocation = (?) "+
						" and boardingdate = (?) ";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, startlocation);
			preparedStatement.setString(2, arrivelocation);
			preparedStatement.setDate(3, Date.valueOf(boardingdate));
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				RouteVO route = new RouteVO(resultSet.getInt("id"),resultSet.getString("BoardingDate").toString(),
						resultSet.getString("Startlocation"),resultSet.getString("Arrivelocation"), resultSet.getInt("FK_busID"));
				
				routeVOArrayList.add(route);
			}	
		}catch(SQLException exception){
			exception.printStackTrace();
		}
		
		return routeVOArrayList;
		
	}
	
	/*
	  나의 예매티켓 확인
	*/
	public ArrayList<ReservationVO> findByIdReservation(int userid){
		
		ArrayList<ReservationVO> reservationVOArraylist = new ArrayList<>();
		try {
			String sql = "select * from BUS4YOU_RESERVATION where FK_USERID = (?)";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, userid);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				ReservationVO reservation = new ReservationVO(resultSet.getInt("id"),
						resultSet.getString("boardingdate").toString(),resultSet.getInt("seatid"),
						resultSet.getInt("fk_userid"),resultSet.getInt("fk_routeid"));
				reservationVOArraylist.add(reservation);
			}
			
		}catch(SQLException exception){
			exception.printStackTrace();
		}
		
		
		return reservationVOArraylist;
	}
	
	
	/*
	 	충전하기
	*/
	public int chargePoint(int userid){
		UserVO UserVO = null;
		try {
			String sql = "update BUS4YOU_USER set POINT = POINT + 5000 where ID = (?)"; 
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, userid);
			
			int resultSet = preparedStatement.executeUpdate();
			
			
			String sql2 = "select point from BUS4YOU_USER where ID = (?) ";
			preparedStatement = conn.prepareStatement(sql2);
			preparedStatement.setInt(1, userid);
			
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
			
			return rs.getInt(1);	
			
			}
			
		}catch(SQLException exception){
			exception.printStackTrace();
			
		}
		return 0;
	}
	
	
	
	
		

}
