import Conn.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstDataBaseCreate {
    /*
        table 삭제 sql developer

        drop TABLE bus4you_operation;
        drop TABLE bus4you_reservation;
        drop TABLE bus4you_route;
        drop TABLE bus4you_bus;
        drop TABLE bus4you_user;

     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        DBConn dbConn = new DBConn();
        Connection conn = dbConn.getCon();
        System.out.println("DB 접속, Table 생성");

        /*
            USER TABLE
            사용자 정보를 담고 있는 테이블, isManager 통해 관리자 구분
         */
        String userTable = "CREATE TABLE bus4you_User(" +
                "id NUMBER(10) generated always as IDENTITY PRIMARY KEY," +
                "userID VARCHAR2(20) NOT NULL ," +
                "password VARCHAR2(20)NOT NULL ," +
                "name varchar2(15)NOT NULL ," +
                "phone varchar2(20)NOT NULL ," +
                "point NUMBER(15) DEFAULT 10000 NOT NULL," +
                "isManager NUMBER(1) DEFAULT 0 NOT NULL" +
                ")";

        /*
            BUS TABLE
            가진 버스 정보를 담고 있는 테이블
         */
        String busTable = "CREATE TABLE bus4you_Bus(" +
                "id NUMBER(10) generated always as IDENTITY PRIMARY KEY ," +
                "busCode VARCHAR2(15) NOT NULL," +
                "totalSeat NUMBER(2) DEFAULT 4 NOT NULL" +
                ")";

        /*
            ROUTE TABLE
            노선 정보를 담고 있는 테이블,
            fk_busID 가지고 있음.
         */
        String routeTable = "CREATE TABLE bus4you_Route(" +
                "id NUMBER(10) generated always as IDENTITY PRIMARY KEY ," +
                "boardingDate DATE NOT NULL," +
                "startLocation VARCHAR2(20) NOT NULL," +
                "arriveLocation VARCHAR2(20) NOT NULL," +
                "fee NUMBER(10) NOT NULL," +
                "fk_busID NUMBER(10) NOT NULL," +
                "CONSTRAINT bus_routeID foreign key(fk_busID) references bus4you_Bus(id) ON DELETE CASCADE" +
                ")";

        /*
            OPERATION TABLE
            노선에 해당하는 버스의 출발, 도착, 탑승일 정보를 담고 있는 테이블
            fk_busID 가지고 있음.
         */
        String operationTable = "CREATE TABLE bus4you_Operation(" +
                "id NUMBER(10) generated always as IDENTITY PRIMARY KEY," +
                "boardingDate DATE NOT NULL," +
                "startTime VARCHAR2(10) NOT NULL," +
                "arriveTime VARCHAR2(10) NOT NULL," +
                "fk_routeID NUMBER(10) NOT NULL," +
                "fk_busID NUMBER(10) NOT NULL," +
                "CONSTRAINT bus_operation_routeID FOREIGN KEY(fk_routeID) REFERENCES bus4you_Route(id) ON DELETE CASCADE, " +
                "CONSTRAINT bus_operation_busID foreign key(fk_busID) references bus4you_Bus(id) ON DELETE CASCADE" +
                ")";


        /*
            RESERVATION TABLE
            유저가 결제를 완료한 시점에서 예약이 성사된 테이블
            fk_routeID, fk_userID 가지고 있음.
         */
        String reservationTable = "CREATE TABLE bus4you_Reservation(" +
                "id NUMBER(10) generated always as IDENTITY PRIMARY KEY," +
                "boardingDate DATE NOT NULL," +
                "seatID NUMBER NOT NULL," +
                "fk_userID NUMBER(10) NOT NULL," +
                "fk_routeId NUMBER(10) NOT NULL ," +
                "CONSTRAINT user_reservationID foreign key(fk_userID) references bus4you_User(id) ON DELETE CASCADE," +
                "CONSTRAINT route_reservationID foreign key(fk_routeID) references bus4you_Route(id) ON DELETE CASCADE" +
                ")";



        PreparedStatement psUser = conn.prepareStatement(userTable);
        PreparedStatement psBus = conn.prepareStatement(busTable);
        PreparedStatement psRoute = conn.prepareStatement(routeTable);
        PreparedStatement psOperation = conn.prepareStatement(operationTable);
        PreparedStatement psReservation = conn.prepareStatement(reservationTable);

        ResultSet reUser = psUser.executeQuery();
        ResultSet reBus = psBus.executeQuery();
        ResultSet reRoute = psRoute.executeQuery();
        ResultSet reOperation = psOperation.executeQuery();
        ResultSet reReservation = psReservation.executeQuery();


        conn.close();
        System.out.println(conn.isClosed()?"접속종료":"접속중");
    }
}
