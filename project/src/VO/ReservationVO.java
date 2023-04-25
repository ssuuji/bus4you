package VO;

public class ReservationVO {
	
	private int id;
	private String boardingdate;
	private int seatid;
	private int fk_userid;
	private int fk_routeid; 
	
	public ReservationVO(int id, String boardingdate,int seatid,int fk_userid, int fk_routeid) {
		this.id = id;
		this.boardingdate = boardingdate;
		this.seatid = seatid;
		this.fk_userid = fk_userid;
		this.fk_routeid = fk_routeid;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBoardingdate() {
		return boardingdate;
	}

	public void setBoardingdate(String boardingdate) {
		this.boardingdate = boardingdate;
	}

	public int getSeatid() {
		return seatid;
	}

	public void setSeatid(int seatid) {
		this.seatid = seatid;
	}

	public int getFk_userid() {
		return fk_userid;
	}

	public void setFk_userid(int fk_userid) {
		this.fk_userid = fk_userid;
	}

	public int getFk_routeid() {
		return fk_routeid;
	}

	public void setFk_routeid(int fk_routeid) {
		this.fk_routeid = fk_routeid;
	}

	
}
