package VO;

public class ReservationVO {

    private int id;
    private String boardingDate;
    private int seatId;
    private int fk_userId;
    private int fk_routeId;

    public ReservationVO(int id, String boardingDate, int seatId, int fk_userid, int fk_routeId) {
        this.id = id;
        this.boardingDate = boardingDate;
        this.seatId = seatId;
        this.fk_userId = fk_userid;
        this.fk_routeId = fk_routeId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBoardingDate() {
        return boardingDate;
    }

    public void setBoardingDate(String boardingDate) {
        this.boardingDate = boardingDate;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getFk_userId() {
        return fk_userId;
    }

    public void setFk_userId(int fk_userId) {
        this.fk_userId = fk_userId;
    }

    public int getFk_routeId() {
        return fk_routeId;
    }

    public void setFk_routeId(int fk_routeId) {
        this.fk_routeId = fk_routeId;
    }

}