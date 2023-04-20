package VO;

public class RouteVO {
    private int id;
    private String boardingDate;
    private String startLocation;
    private String arriveLocation;
    private int fk_busId;

    public RouteVO(int id, String boardingDate, String startLocation, String arriveLocation, int fk_busId){
        this.id = id;
        this.boardingDate = boardingDate;
        this.startLocation = startLocation;
        this.arriveLocation = arriveLocation;
        this.fk_busId = fk_busId;
    }


}
