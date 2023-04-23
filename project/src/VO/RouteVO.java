package VO;

public class RouteVO {
    private int id;
    private String boardingDate;
    private String startLocation;
    private String arriveLocation;
    private int fee;
    private int fk_busId;


    public RouteVO(int id, String boardingDate, String startLocation, String arriveLocation,int fee, int fk_busId){
        this.id = id;
        this.boardingDate = boardingDate;
        this.startLocation = startLocation;
        this.arriveLocation = arriveLocation;
        this.fee = fee;
        this.fk_busId = fk_busId;
    }

    public int getId() {
        return id;
    }

    public String getBoardingDate() {
        return boardingDate;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getArriveLocation() {
        return arriveLocation;
    }

    public int getFee() {
        return fee;
    }

    public int getFk_busId() {
        return fk_busId;
    }
}
