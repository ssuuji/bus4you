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
		this.conn = dbconn.getCon();
	}

	/*
	 * 출발지,도착지,시간에 맞는 노선데이터 출력
	 */
	public ArrayList<RouteVO> findRoute(String startlocation, String arrivelocation, String boardingdate) {

		ArrayList<RouteVO> routeVOArrayList = new ArrayList<>();
		try {
			String sql = "select * from BUS4YOU_Route where Startlocation = (?)" + " and arrivelocation = (?) "
					+ " and boardingdate = (?) ";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, startlocation);
			preparedStatement.setString(2, arrivelocation);
			preparedStatement.setDate(3, Date.valueOf(boardingdate));

			ResultSet resultSet = preparedStatement.executeQuery();
<<<<<<< HEAD

			while (resultSet.next()) {
				RouteVO route = new RouteVO(resultSet.getInt("id"), resultSet.getString("BoardingDate").toString(),
						resultSet.getString("Startlocation"), resultSet.getString("Arrivelocation"),
						resultSet.getInt("fee"), resultSet.getInt("FK_busID"));

=======
			
			while(resultSet.next()) {
				RouteVO route = new RouteVO(resultSet.getInt("id"),resultSet.getDate("BoardingDate").toString(),
						resultSet.getString("Startlocation"),resultSet.getString("Arrivelocation"),resultSet.getInt("fee"), resultSet.getInt("FK_busID"));
				
>>>>>>> f0cd87873bab56446960759b12b82890b00b7f5f
				routeVOArrayList.add(route);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}

		return routeVOArrayList;

	}

	/*
	 * 충전하기
	 */
	public int chargePoint(int userid) {
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
			if (rs.next()) {

				return rs.getInt(1);

			}

		} catch (SQLException exception) {
			exception.printStackTrace();

		}
		return 0;
	}

	// 상위 클래스에서 VO 객체 받아오는 걸로 대체할 것

	public RouteVO SelectRoute(int routeId) {

		String sql = "select * from bus4you_Route where Id = ?";
		RouteVO rv = null;

		try {

			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, routeId);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				rv = new RouteVO(rs.getInt("id"), rs.getString("boardingDate"), rs.getString("startLocation"),
						rs.getString("arriveLocation"), rs.getInt("fee"), rs.getInt("fk_busID"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rv;
	}

	// 버스 아이디를 통해 해당 버스의 totalSeat 총 좌석개수를 알아온다.
	public int checkTotalSeat(int busId) {

		int totalSeat = 0;

		String sql = "select totalSeat from bus4you_Bus where Id = ?";

		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, busId);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				totalSeat = rs.getInt("totalSeat");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalSeat;
	}

	// fk_routeId 로 bus4you_Reservation 테이블의 좌석번호 리스트로 반환
	public ArrayList<Integer> checkSeat(int routeId) {

		ArrayList<Integer> ai = new ArrayList<Integer>();

		String sql = "select seatID from bus4you_Reservation where fk_RouteId = ?";

		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, routeId);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ai.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ai;
	}

	// 유저id로 bus4you_User 테이블 자료 VO로 반환

	// 결제정보로 bus4you_Reservation 테이블에 데이터 삽입
	public void insertReservation(RouteVO rv, int userId, int seatId) {

		String sql = "insert into bus4you_Reservation(boardingDate, seatId, fk_userId, fk_routeId) values (?, ?, ?, ?)";

		try {
			preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setString(1, rv.getBoardingDate());
			preparedStatement.setInt(2, seatId);
			preparedStatement.setInt(3, userId);
			preparedStatement.setInt(4, rv.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {

		}

		// return res;
	}

	// userId로 bus4you_Reservation 모든 데이터 가져오기
	public ArrayList<ReservationVO> selectAllReservation(int userId) {

		ArrayList<ReservationVO> arv = new ArrayList<ReservationVO>();

		String sql = "select * from bus4you_Reservation r where fk_userId = ?";

		try {
			preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setInt(1, userId);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				arv.add(new ReservationVO(rs.getInt("id"), rs.getString("boardingDate"), rs.getInt("seatId"),
						rs.getInt("fk_UserId"), rs.getInt("fk_routeId")));
			}

		} catch (SQLException e) {

		}
		return arv;
	}

	/*
	 * 포인트 차감
	 */
	public void updatePoint(int userId, int fee) {

		String sql = "update bus4you_User set point = point - ? where id = ?";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, fee);
			preparedStatement.setInt(2, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

		}

	}

}
