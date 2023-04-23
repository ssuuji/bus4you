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
	
	
	
}
