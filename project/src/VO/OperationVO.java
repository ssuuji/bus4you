package VO;

public class OperationVO {
    private int id;
    private String boardingDate;
    private String startTime;
    private String arriveTime;
    private int fk_busId;
    private int fk_routeId;


    public OperationVO(int id, String boardingDate, String startTime, String arriveTime, int fk_busId, int fk_routeId){
        this.id = id;
        this.boardingDate = boardingDate;
        this.startTime = startTime;
        this.arriveTime = arriveTime;
        this.fk_busId = fk_busId;
        this.fk_routeId = fk_routeId;
    }

    public int getId() {
        return id;
    }

    public String getBoardingDate() {
        return boardingDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public int getFk_busId() {
        return fk_busId;
    }

    public int getFk_routeId() {
        return fk_routeId;
    }
}
