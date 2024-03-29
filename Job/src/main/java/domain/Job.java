package domain;

import java.util.Date;

public class Job {
    private Integer id;
    private Integer bossid;
    private String department;
    private String title;
    private String content;
    private float salary;
    private Date starttime;
    private Date endtime;
    private Integer status;

    public String getDepartment() { return department; }

    public void setDepartment(String department) { this.department = department; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBossid() {
        return bossid;
    }

    public void setBossid(Integer bossid) {
        this.bossid = bossid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getSalary() { return salary; }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date getStarttime() { return starttime; }

    public void setStarttime(Date starttime) { this.starttime = starttime; }

    public Date getEndtime() { return endtime; }

    public void setEndtime(Date endtime) { this.endtime = endtime; }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", bossid=" + bossid +
                ", department='" + department + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", salary=" + salary +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", status=" + status +
                '}';
    }
}
