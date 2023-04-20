package VO;

public class BusVO {

    private int id;
    private String busCode;
    private int totalSeat;


    public BusVO(int id, String busCode, int totalSeat) {
        this.id = id;
        this.busCode = busCode;
        this.totalSeat = totalSeat;
    }

    public int getId() {
        return id;
    }

    public String getBusCode() {
        return busCode;
    }

    public int getTotalSeat() {
        return totalSeat;
    }

    @Override
    public String toString() {
        return "BusVO{" +
                "id=" + id +
                ", busCode='" + busCode + '\'' +
                ", totalSeat=" + totalSeat +
                '}';
    }
}
