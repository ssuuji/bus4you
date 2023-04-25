package VO;

public class UserVO {
    private int id;
    private String userId;
    private String password;
    private String name;
    private String phone;
    private int point;
    private int isManager;

    public UserVO(int id, String userId, String password, String name, String phone, int point, int isManager){
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.phone = phone;
        this.point = point;
        this.isManager = isManager;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getPoint() {
        return point;
    }

    public int getIsManager() {
        return isManager;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setIsManager(int isManager) {
        this.isManager = isManager;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", point=" + point +
                '}';
    }
}
