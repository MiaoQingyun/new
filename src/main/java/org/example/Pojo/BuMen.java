package org.example.Pojo;

public class BuMen {
    private Integer b_id;
    private String name;
    private Integer userid;

    public Integer getId() {
        return b_id;
    }

    public void setId(Integer id) {
        this.b_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "BuMen{" +
                "id=" + b_id +
                ", name='" + name + '\'' +
                ", userid=" + userid +
                '}';
    }
}
