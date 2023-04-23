package DAO;

import Conn.DBConn;
import VO.BusVO;
import VO.OperationVO;
import VO.UserVO;

import java.sql.*;
import java.util.ArrayList;
/*
    관리자가 노선을 등록할 때
    1. 관리자는 우선 버스와 출발 날짜를 선택한다.
    2. 해당 날짜 버스의 운행 상태를 확인한다.
    3. 운행 상태를 피해 노선을 등록한다.
        a. 운행 상태에 겹친다면 에러를 보낸다.

    관리자가 노선을 삭제할 때
    1. 운행 table 먼저 해당 routeId 기준으로 삭제를 한디.
    2. 다음 경로 table, id 기준으로 삭제를 한다.
 */
public class ManagerDAO {
    private Connection conn;
    private PreparedStatement preparedStatement = null;

    // 생성자
    public ManagerDAO() throws SQLException, ClassNotFoundException {
        DBConn dbConn = new DBConn();
        this.conn = DBConn.getCon();
    }

    /*
        버스를 추가하는 기능
     */
    public boolean createBus(String busCode, int totalSeat) {
        try{
            String sql = "insert into bus4you_bus(busCode, totalSeat) values(?,?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, busCode);
            preparedStatement.setInt(2, totalSeat);
            int resultSet = preparedStatement.executeUpdate();
        }catch (SQLException exception){
            return false;
        }
        return true;
    }

    /*
        해당 id 버스 조회 기능
     */
    public BusVO findByBusId(int id){
        BusVO busVO = null;
        try{
            String sql = "select * from bus4you_bus where id = (?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            busVO = new BusVO(resultSet.getInt("id"), resultSet.getString("busCode"),
                    resultSet.getInt("totalSeat"));

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return busVO;
    }

    /*
        전체 버스 조회 기능
     */
    public ArrayList<BusVO> findAllBuses(){
        ArrayList<BusVO> busVOArrayList = new ArrayList<>();
        try{
            String sql = "select * from bus4you_bus order by id";
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                busVOArrayList.add(new BusVO(resultSet.getInt("id"), resultSet.getString("busCode"),
                        resultSet.getInt("totalSeat")));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return busVOArrayList;
    }

    /*
        해당 id 버스 삭제 기능
        + operation table join 후 삭제하는거 추가해야됨.
     */
    public boolean deleteByBusId(int id){
        String sql = "delete bus4you_bus where id = ?";
        try{
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }


    /*
        회원들 정보를 전체 출력해주는 기능
     */
    public ArrayList<UserVO> findAllUsers(){
        ArrayList<UserVO> userVOArrayList = new ArrayList<>();
        try{
            String sql = "select * from bus4you_user";
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                // 회원 정보를 받아오던 중 관리자가 아닌 경우만 list 포함
                if(resultSet.getInt("isManager") == 0){
                    userVOArrayList.add(new UserVO(resultSet.getInt("id"), resultSet.getString("userId"), resultSet.getString("password"), resultSet.getString("name"),
                            resultSet.getString("phone"), resultSet.getInt("point"), resultSet.getInt("isManager")));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return userVOArrayList;
    }



    /*
        해당 날짜 버스의 운행 리스트 출력
     */

    public ArrayList<OperationVO> findByBusIdAllOperations(int id, String boardingDate) {
        ArrayList<OperationVO> findByBusIdOperationVOArrayList = new ArrayList<>();
        try {
            String sql = "SELECT *" +
                    "FROM bus4you_Operation " +
                    "WHERE fk_busId = (?) and boardingDate = (?)" +
                    "ORDER BY STARTTIME";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setDate(2, Date.valueOf(boardingDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                findByBusIdOperationVOArrayList.add(new OperationVO(resultSet.getInt("id"), resultSet.getDate("boardingDate").toString(),
                        resultSet.getString("startTime"), resultSet.getString("arriveTime"),resultSet.getInt("fk_busId"),resultSet.getInt("fk_routeId")));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return findByBusIdOperationVOArrayList;
    }

    /*
        노선 삭제로 인한 운행 삭제
     */
    public boolean deleteByRouteIdOnOperation(int id){
        String sql = "delete bus4you_operation where fk_routeId = ?";
        try{
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }


    /*
        노선 등록
     */
    public int createRoute(String boardingDate, String startLocation, String arriveLocation, int fk_budId, int fee) {
        try{
            String sql = "insert into bus4you_route(boardingDate, startLocation, arriveLocation, fee, fk_busId) values(?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(boardingDate));
            preparedStatement.setString(2, startLocation);
            preparedStatement.setString(3, arriveLocation);
            preparedStatement.setInt(4, fee);
            preparedStatement.setInt(5, fk_budId);
            preparedStatement.executeUpdate();

            sql = "select max(id) from bus4you_route";
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return 0;
    }

    /*
        노선 전체 출력
     */
    public ArrayList<RouteVO> findAllRoutes(){
        ArrayList<RouteVO> routeVOArrayList = new ArrayList<>();
        try{
            String sql = "select * from bus4you_route order by fk_busid, boardingDATE";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                routeVOArrayList.add(new RouteVO(resultSet.getInt("id"), resultSet.getDate("boardingDate").toString(),
                        resultSet.getString("startLocation"), resultSet.getString("arriveLocation"),
                        resultSet.getInt("fee"), resultSet.getInt("fk_busId")));
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return routeVOArrayList;
    }

    /*
        노선 삭제
     */
    public boolean deleteByRouteId(int id){
        String sql = "delete bus4you_route where id = ?";
        try{
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }



    /*
        해당 버스 운행 등록
    */
    public boolean createOperation(String boardingDate, String startTime, String arriveTime, int fk_routeId, int fk_budId) {
        try{
            String sql = "insert into bus4you_operation(boardingDate, startTime, arriveTime, fk_routeId, fk_busId) values(?,?,?,?,?)";

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(boardingDate));
            preparedStatement.setString(2, startTime);
            preparedStatement.setString(3, arriveTime);
            preparedStatement.setInt(4, fk_routeId);
            preparedStatement.setInt(5, fk_budId);
            int res = preparedStatement.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
            return false;
        }
        return true;
    }



}





























