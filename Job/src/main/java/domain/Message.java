package domain;

public class Message {
    private Integer id;
    private String user;
    private String msg;
    private String inputdate;

    public String getInputdate() {
        return inputdate;
    }

    public void setInputdate(String inputdate) {
        this.inputdate = inputdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", msg='" + msg + '\'' +
                ", data='" + inputdate + '\'' +
                '}';
    }
}
