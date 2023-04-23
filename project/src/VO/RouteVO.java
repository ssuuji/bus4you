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

	public String getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	public String getArriveLocation() {
		return arriveLocation;
	}

	public void setArriveLocation(String arriveLocation) {
		this.arriveLocation = arriveLocation;
	}

	public int getFk_busId() {
		return fk_busId;
	}

	public void setFk_busId(int fk_busId) {
		this.fk_busId = fk_busId;
	}



}
