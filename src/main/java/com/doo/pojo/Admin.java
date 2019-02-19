package com.doo.pojo;

import java.util.Date;

public class Admin {
    private Integer id;

    private String name;

    private String password;

    private Date createTime;

    public Admin(Integer id, String name, String password, Date createTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.createTime = createTime;
    }

    public Admin() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}