package org.example.Pojo;

public class User {
    private Integer id;
    private String name;
    private String password;
    private Integer age;

    private  BuMen buMen;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBuMen(BuMen buMen) {
        this.buMen = buMen;
    }

    public BuMen getBuMen() {
        return buMen;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", buMen=" + buMen +
                '}';
    }
}
